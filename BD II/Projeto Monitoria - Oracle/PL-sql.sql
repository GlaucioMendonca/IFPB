--VIEWS-------------------------------------------------------------------------

--nome da disciplina e nome do curso
create or replace view curso_disciplina as
select c.nome_curso, d.nome_disciplina from
disciplina d join curso c
on c.cod_curso = d.cod_curso;

/*O nome e matrícula dos candidatos classificados nas respectivias disciplinas 
que foram classificados, o nome do curso, o ano,o periodo de monitoria, o numero
do edital e o codigo do curso*/

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
-- View read only
create or replace view edital_curso as 
select edital.ano, edital.numero, curso.nome_curso, edital.periodo, qtdVagas(edital.codigo_edital) as vagas
from edital join curso
on edital.cod_curso = curso.cod_curso
with read only;
--TRIGGER-----------------------------------------------------------------------

--update cascate entre a tabela curso e disciplina
create or replace trigger cascate_cus_dis 
before update of cod_curso on curso 
for each row
begin
    update disciplina set cod_curso = :new.cod_curso
    where cod_curso = :old.cod_curso;
end;
--inserção na view curso_disciplina
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
-- update cascate disciplina vinculo
create or replace trigger cascate_dis_vin 
before update of cod_disciplina on disciplina 
for each row
begin
    update vinculo set cod_disciplina = :new.cod_disciplina
    where cod_disciplina = :old.cod_disciplina;
end;

--FUNCTION----------------------------------------------------------------------
-- retorna quantidade de incritos no edital pesquisado

create or replace function inscritos (edital number)
return number is
total number;
begin
  select count(*) into total from candidato c join vinculo 
  on c.cod_edital = vinculo.codigo_edital 
  and c.cod_disciplina = vinculo.cod_disciplina
  where vinculo.codigo_edital = edital;
  
  return total;
end;

--calcular nota
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

--Quantidade de vagas por edital
 create or replace function qtdVagas (edital number)
 return number is
 vagas number;
 begin
    select sum(vaga) into vagas from vinculo where codigo_edital = edital;
    if vagas >= 1 then
        return vagas;
    else
        return 0;
    end if;
 end;

--PROCEDURE---------------------------------------------------------------------
-- Atualiza o campo classificado para 's' dos candidatos que passaram para monitoria
create or replace procedure classifica(edital number) is
vagas number; 
matri number;
flag number;
desqualificado exception;
begin
    
    for cursor_vinculo in (select codigo_edital, cod_disciplina, vaga from vinculo where codigo_edital = edital) loop        
        for vagas in 1 .. cursor_vinculo.vaga loop             
             for aprovado in (select matricula, nota, cre from candidato where (cod_edital = edital and cod_disciplina = cursor_vinculo.cod_disciplina and classificado = 'n')
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
exec classifica (1001);
select * from candidato;
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
exec insere_curso('Letras');
-- Insere registros automaticamente nas tabelas curso, disciplina e edital
create or replace procedure insere_automatico is
codigo_curso number;
codigo_disciplina number;
cod_edital number;
cont number;
ano_edital varchar2(4);
numero_edital number;
begin
    select max(cod_curso) + 1 into codigo_curso from curso;
    for cont in codigo_curso .. (codigo_curso + 2) loop
        INSERT INTO curso VALUES(cont,'Curso Automatico');
    end loop;
    select max(cod_disciplina) + 1 into codigo_disciplina from disciplina;
    
    for cont in codigo_disciplina .. (codigo_disciplina + 5) loop
        INSERT INTO disciplina VALUES(cont,codigo_curso,'Disciplina Automatica');
    end loop;
    select max(codigo_edital) + 1 into cod_edital from edital;
    select EXTRACT(YEAR FROM sysdate) into ano_edital from dual;
    for cont in cod_edital .. (cod_edital + 2) loop
        select max(numero) + 1 into numero_edital from edital where ano = ano_edital;
        if numero_edital >= 1 then
            INSERT INTO edital VALUES(cont,numero_edital,ano_edital,codigo_curso,sysdate,sysdate,sysdate,1);
        else
            INSERT INTO edital VALUES(cont,1,ano_edital,codigo_curso,sysdate,sysdate,sysdate,100);
        end if;
    end loop;
end;



