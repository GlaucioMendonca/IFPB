-- CURSOS

INSERT INTO curso VALUES(1,'Sistemas para Internet');
INSERT INTO curso VALUES(2,'Redes de Computadores');
INSERT INTO curso VALUES(3,'Engenharia Elétrica');
INSERT INTO curso VALUES(4,'Design de Interiores');

-- DISCIPLINAS

INSERT INTO disciplina VALUES(1,1,'APE');
INSERT INTO disciplina VALUES(2,1,'Fundamentos da computação');
INSERT INTO disciplina VALUES(3,1,'Estrutura de dados');
INSERT INTO disciplina VALUES(4,1,'Linguagem de script');
INSERT INTO disciplina VALUES(5,2,'Estatistica');
INSERT INTO disciplina VALUES(6,2,'Sistemas operacionais');
INSERT INTO disciplina VALUES(7,3,'inglês instrumental');
INSERT INTO disciplina VALUES(8,3,'Português instrumental');
INSERT INTO disciplina VALUES(9,1,'Banco de dados I');
INSERT INTO disciplina VALUES(10,1,'Programação WEB I');
INSERT INTO disciplina VALUES(11,4,'Dezenho');

-- EDITAIS

INSERT INTO edital VALUES(1001,1,'2017',1,'10/05/2017',sysdate,'01/05/2017',1);
INSERT INTO edital VALUES(1002,1,'2017',2,'10/05/2017','01/08/2017','01/05/2017',1);
INSERT INTO edital VALUES(1003,1,'2017',3,'10/05/2017','20/07/2017','01/05/2017',2);
INSERT INTO edital VALUES(1004,1,'2017',4,'10/05/2017','20/07/2017','01/05/2017',2);
-- Vinculo Edital / Disciplina

INSERT INTO vinculo VALUES(1001,1,1);
INSERT INTO vinculo VALUES(1001,2,1);
INSERT INTO vinculo VALUES(1001,3,1);
INSERT INTO vinculo VALUES(1001,4,2);
INSERT INTO vinculo VALUES(1002,5,2);
INSERT INTO vinculo VALUES(1002,6,1);
INSERT INTO vinculo VALUES(1003,7,1);
INSERT INTO vinculo VALUES(1003,8,1);
INSERT INTO vinculo VALUES(1004,11,1);

-- CANDIDATOS
-- composição da matricula: os 2 primeiros digitos referem ao ano
--                          os 2 ultimos são incrementados sequencialmente

-- Edital 1 curso SI
INSERT INTO candidato VALUES(1600,1001,1,8,8,'maria@ifpb.edu','83 0000-0000','n',null,null,null,'Maria Clara','n');
INSERT INTO candidato VALUES(1601,1001,2,8,8,'Ana@ifpb.edu','83 0000-0000','n',null,null,null,'Ana Paula','n');
INSERT INTO candidato VALUES(1602,1001,2,7,9,'Rita@ifpb.edu','83 0000-0000','n',null,null,null,'Rita lins','n');
INSERT INTO candidato VALUES(1603,1001,1,9,9,'Cristina@ifpb.edu','83 0000-0000','n',null,null,null,'Cristina Maria','n');
-- edital 2 curso Redes
INSERT INTO candidato VALUES(1604,1002,5,8,8,'suzi@ifpb.edu','83 0000-0000','n',null,null,null,'Suziane Martins','n');
INSERT INTO candidato VALUES(1605,1002,5,9,9,'aline@ifpb.edu','83 0000-0000','n',null,null,null,'Aline Silva','n');
-- edital 3 curso eletrica
INSERT INTO candidato VALUES(1606,1003,8,7,7,'Joao@ifpb.edu','83 0000-0000','n',null,null,null,'João Martins','n');
INSERT INTO candidato VALUES(1607,1003,7,9,7,'Junior@ifpb.edu','83 0000-0000','n',null,null,null,'Pedro Junior','n');
-- edital 4 curso design de interiores
INSERT INTO candidato VALUES(1609,1004,11,5,5,'PPaiva@ifpb.edu','83 0000-0000','n',null,null,null,'Pedro Paiva','n');

--- BANCOS

INSERT INTO banco VALUES(1,'Banco do Brasil');
INSERT INTO banco VALUES(2,'Banco Itaú');
INSERT INTO banco VALUES(3,'Banco Santander');
INSERT INTO banco VALUES(4,'Banco Bradesco');
INSERT INTO banco VALUES(5,'Banco HSBC');
INSERT INTO banco VALUES(6,'Banco Votorantim');
