---APRESENTAÇÃO 
select * from candidato;
select * from vinculo;

--MOSTRAR E EXECUTAR CONSULTAS EM TABELAS IMPORTANTES---------------------------
select curso.nome_curso, sum(vinculo.vaga)"Vagas"
from vinculo join disciplina 
on vinculo.cod_disciplina = disciplina.cod_disciplina
join curso 
on curso.cod_curso = disciplina.cod_curso
group by curso.nome_curso;

select c.nome, e.ano, e.numero, calcula_nota(c.nota,c.cre) as NOTA, d.nome_disciplina
from candidato c join vinculo v
on c.cod_edital = v.codigo_edital and c.cod_disciplina = v.cod_disciplina
join edital e 
on e.codigo_edital = v.codigo_edital 
join disciplina d
on d.cod_disciplina = v.cod_disciplina;

--VIEWS-------------------------------------------------------------------------
--ROBUSTA
create or replace view monitores as                                    
select c.matricula, c.nome, d.nome_disciplina, curso.nome_curso, e.ano, e.periodo, e.numero, e.cod_curso 
from candidato c join vinculo 
on c.cod_edital = vinculo.codigo_edital and c.cod_disciplina = vinculo.cod_disciplina
join edital e 
on e.codigo_edital = vinculo.codigo_edital 
join curso 
on curso.cod_curso = e.cod_curso
join disciplina d 
on d.cod_disciplina = vinculo.cod_disciplina
where c.classificado = 's';

select * from monitores;

--PERMITE INSERÇÃO
create or replace view curso_disciplina as
select c.nome_curso, d.nome_disciplina from
disciplina d join curso c
on c.cod_curso = d.cod_curso;

--TRIGGERS----------------------------------------------------------------------
--INSERÇÃO NA VIEW
create or replace trigger insert_curso_disciplina
instead of insert on curso_disciplina 
for each row
declare codigo number;
maximo number;
begin
    select cod_curso into codigo
    from curso where 
    curso.nome_curso = :new.nome_curso;
    select max(cod_disciplina) into maximo from disciplina;
    insert into disciplina values(maximo + 1,codigo, :new.nome_disciplina);
    EXCEPTION
    when no_data_found then
        select max(cod_curso) into codigo
        from curso;     
        select max(cod_disciplina) into maximo from disciplina;
        insert into curso values(codigo+1,:new.nome_curso);
        insert into disciplina values(maximo + 1, codigo + 1, :new.nome_disciplina);
end;
--Update cascate
create or replace trigger cascate_cus_dis 
before update of cod_curso on curso 
for each row
begin
    update disciplina set cod_curso = :new.cod_curso
    where cod_curso = :old.cod_curso;
end;
--FUNCTIONS---------------------------------------------------------------------
--INSCRITOS
create or replace function inscritos (edital number, disc DISCIPLINA.NOME_DISCIPLINA%type)
return number is
total number;
begin
  select count(matricula) into total from candidato c join vinculo 
  on c.cod_edital = vinculo.codigo_edital 
  and c.cod_disciplina = vinculo.cod_disciplina
  where vinculo.codigo_edital = edital 
  and c.COD_DISCIPLINA in (select cod_disciplina from disciplina where NOME_DISCIPLINA like disc);
  
  return total;
end;

select v.CODIGO_EDITAL, d.nome_disciplina 
from vinculo v join disciplina d
on v.COD_DISCIPLINA = d.COD_DISCIPLINA;

select inscritos (1002, 'Estatistica')"Inscritos" from dual;

--CALCULA NOTA
create or replace function calcula_nota(nota number, cre number)
return number is
media number;
invalido exception;
begin
    if nota < 0 or cre < 0 then
        raise invalido;
    end if;
    media := (((7*nota) + (3*cre))/10);
    return media;
    exception
    when invalido then
        DBMS_OUTPUT.put_line('Valores de nota ou CRE invalidos');
        return null;    
end;
--PROCEDURES--------------------------------------------------------------------
-- Atualiza o campo classificado para 's' dos candidatos que passaram para monitoria
create or replace procedure classifica(edital number) is
vagas number; 
matri number;
flag number;
desqualificado exception;
begin
    
    for cursor_vinculo in (select codigo_edital, cod_disciplina, vaga from vinculo where codigo_edital = edital) loop        
        for vagas in 1 .. cursor_vinculo.vaga loop             
             for aprovado in (select matricula, nota, cre from candidato 
                              where (cod_edital = edital and cod_disciplina = cursor_vinculo.cod_disciplina 
                              and classificado = 'n')
                              order by calcula_nota(nota,cre) desc, nota desc, cre desc)loop   
                              
                if calcula_nota(aprovado.nota,aprovado.cre) < 7 then
                    raise desqualificado;
                end if;
                update candidato set classificado = 's' where aprovado.matricula = matricula;                        
                exit;            
            end loop;
        end loop;
    end loop;   
    exception 
    when desqualificado then
        DBMS_OUTPUT.put_line('Candidato não possui nota suficiente para classificação');
end;

-- Insere na tabela curso um curso com id gerado automaticamente e nome passado por parametro
create or replace procedure insere_curso(nome varchar2) is
codigo number;
contador number;
existe exception;
begin 
    select count(cod_curso) into contador from curso where nome_curso = nome;
    if contador >= 1 then
        raise existe;
    end if;
    select max(cod_curso) + 1 into codigo from curso;
    insert into curso values(codigo, nome);
    exception
    when existe then
        DBMS_OUTPUT.put_line('Curso já existe');
end;

-- Consultas otimizadas

-- consulta correlata
select d.nome_disciplina
from DISCIPLINA d
where exists (select cod_disciplina from vinculo where COD_DISCIPLINA = d.COD_DISCIPLINA);

-- otimização
select d.nome_disciplina
from DISCIPLINA d join vinculo v
on v.COD_DISCIPLINA = d.COD_DISCIPLINA;



-- Consultas aninhadas
select codigo_edital, ano from edital
where CODIGO_EDITAL in (select CODIGO_EDITAL from VINCULO
                        where COD_DISCIPLINA in (select COD_DISCIPLINA from disciplina
                                                  where nome_DISCIPLINA like 'APE'));


-- Otimização                                                  
SELECT e.codigo_edital, e.ano FROM edital e JOIN vinculo v
ON v.CODIGO_EDITAL = e.CODIGO_EDITAL
JOIN disciplina d ON v.COD_DISCIPLINA = d.COD_DISCIPLINA
WHERE d.NOME_DISCIPLINA LIKE 'APE';


--INDICES
create index id_edit_ano on edital (ano); 

-- agiliza pesquisa do edital filtrando por disciplina

create index id_edit_curso on edital (cod_curso);

-- agiliza pesquisas de candidatos filtrando se está classificado ou não

create index id_candidato_classificado on candidato (classificado);




