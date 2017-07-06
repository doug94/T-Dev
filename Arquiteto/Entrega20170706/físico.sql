-- Geração de Modelo físico
-- Sql ANSI 2003 - brModelo.



CREATE TABLE Universidade (
id_universidade INTEGER PRIMARY KEY,
nome VARCHAR(25)
)

CREATE TABLE Faculdade (
id_faculdade VARCHAR(10),
id_universidade INTEGER,
nome VARCHAR(25),
-- Erro: nome do campo duplicado nesta tabela!
id_universidade INTEGER,
PRIMARY KEY(id_faculdade,id_universidade),
FOREIGN KEY(id_universidade) REFERENCES Universidade (id_universidade)
)

CREATE TABLE Administrador (
código INTEGER PRIMARY KEY,
setor VARCHAR(10),
id_usuario INTEGER
)

CREATE TABLE Curso (
codigo INTEGER,
id_curso INTEGER,
nome VARCHAR(25),
id_faculdade INTEGER,
Roteiro VARCHAR(250),
-- Erro: nome do campo duplicado nesta tabela!
id_faculdade VARCHAR(10),
id_universidade INTEGER,
PRIMARY KEY(id_curso,nome,id_faculdade),
FOREIGN KEY(id_universidade,,) REFERENCES Faculdade (id_faculdade,id_universidade)
)

CREATE TABLE Usuario (
username VARCHAR(25),
DataNascimento DATETIME,
id_usuario INTEGER PRIMARY KEY,
senha VARCHAR(10)
)

CREATE TABLE FuturoAluno (
CPF INTEGER PRIMARY KEY,
email VARCHAR(25),
id_usuario INTEGER,
FOREIGN KEY(id_usuario) REFERENCES Usuario (id_usuario)
)

CREATE TABLE Veterano (
matricula INTEGER PRIMARY KEY,
CPF INTEGER,
email VARCHAR(50),
id_usuario INTEGER,
FOREIGN KEY(id_usuario) REFERENCES Usuario (id_usuario)
)

CREATE TABLE Aluno_curso (
id_curso INTEGER,
nome VARCHAR(25),
id_faculdade INTEGER,
matricula INTEGER,
CPF INTEGER,
id_aluno_curso INTEGER,
PRIMARY KEY(id_curso,nome,id_faculdade,matricula,CPF,id_aluno_curso)
)

CREATE TABLE Compromisso (
id_agenda INTEGER,
id_futuroAluno INTEGER,
id_Aluno VARCHAR(10),
id_aprovador INTEGER,
hora VARCHAR(10),
Relatorio_Aluno VARCHAR(1024),
Relatorio_FuturoAluno VARCHAR(1024),
data DATETIME,
curso VARCHAR(25),
Avaliado CHAR(1),
matricula INTEGER,
CPF INTEGER,
PRIMARY KEY(id_agenda,id_futuroAluno,id_Aluno,id_aprovador),
FOREIGN KEY(matricula) REFERENCES Veterano (matricula),
FOREIGN KEY(CPF) REFERENCES FuturoAluno (CPF)
)

ALTER TABLE Administrador ADD FOREIGN KEY(id_usuario) REFERENCES Usuario (id_usuario)
