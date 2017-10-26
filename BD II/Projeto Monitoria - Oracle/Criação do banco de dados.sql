create table curso(cod_curso number,
nome_curso varchar2(30) not null,
constraint pk_curso primary key(cod_curso));

create table edital(codigo_edital number,
numero number,
ano char(4),
cod_curso number,
data_inscricao date not null,
data_encerra_inscricao date not null,
data_divulgacao date not null,
periodo char(1) not null,
constraint pk_edital primary key(codigo_edital),
constraint un_edital unique(numero,ano,cod_curso),
constraint fk_edital foreign key(cod_curso) references curso,
constraint ck_periodo check(periodo = '1' or periodo = '2'));

create table disciplina(cod_disciplina number,
cod_curso number,
nome_disciplina varchar2(30) not null,
constraint pk_disciplina primary key(cod_disciplina),
constraint fk_disciplina_curso foreign key(cod_curso) references curso);

create table banco(cod_banco number,
nome_banco varchar2(30) not null,
constraint pk_banco primary key(cod_banco),
constraint un_banco unique(nome_banco));

create table vinculo(codigo_edital number,
cod_disciplina number,
vaga number,
constraint pk_vinculo primary key(codigo_edital,cod_disciplina),
constraint fk_vinculo_disciplina foreign key(cod_disciplina) references disciplina,
constraint fk_vinculo_edital foreign key(codigo_edital) references edital);

create table candidato( matricula varchar2(15),
cod_edital number not null,
cod_disciplina number not null,
nota number not null,
cre number not null,
email varchar2(40),
telefone varchar2(15),
classificado char(1),
num_agencia varchar2(10),
num_conta varchar2(10),
cod_banco number,
nome varchar2(30),
bolsista char(1),
constraint pk_candidato primary key (matricula),
constraint ck_classificado check(classificado = 's' or classificado = 'n'),
constraint ck_bolsista check(classificado = 's' or classificado = 'n'),
constraint fk_candidato_vinculado foreign key(cod_edital,cod_disciplina) references vinculo,
constraint fk_candidato_banco foreign key(cod_banco) references banco);


