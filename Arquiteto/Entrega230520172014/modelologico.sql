-- Geração de Modelo físico
-- Sql ANSI 2003 - brModelo.



CREATE TABLE Admin (
setor VARCHAR(10),
código INTEGER PRIMARY KEY,
id_usuario INTEGER
)

CREATE TABLE Usuario (
senha VARCHAR(10),
id_usuario INTEGER PRIMARY KEY,
username VARCHAR(10)
)

CREATE TABLE Universidade (
id_universidade INTEGER PRIMARY KEY,
nome VARCHAR(10)
)

CREATE TABLE Veterano (
CPF VARCHAR(10),
matricula VARCHAR(10) PRIMARY KEY,
email VARCHAR(10),
id_usuario INTEGER,
FOREIGN KEY(id_usuario) REFERENCES Usuario (id_usuario)
)

CREATE TABLE FuturoAluno (
email VARCHAR(10),
CPF VARCHAR(11) PRIMARY KEY,
id_usuario INTEGER,
FOREIGN KEY(id_usuario) REFERENCES Usuario (id_usuario)
)

CREATE TABLE Curso (
id_curso INTEGER,
codigo VARCHAR(10),
nome VARCHAR(10),
id_faculdade INTEGER,
-- Erro: nome do campo duplicado nesta tabela!
id_faculdade INTEGER,
id_universidade INTEGER,
Roteiro VARCHAR(250),
PRIMARY KEY(id_curso,nome,id_faculdade)
)

CREATE TABLE Faculdade (
id_faculdade INTEGER,
id_universidade INTEGER,
nome VARCHAR(10),
-- Erro: nome do campo duplicado nesta tabela!
id_universidade INTEGER,
PRIMARY KEY(id_faculdade,id_universidade),
FOREIGN KEY(id_universidade) REFERENCES Universidade (id_universidade)
)

CREATE TABLE FuturoAluno_Curso_Veterano (
id_curso VARCHAR(10),
nome VARCHAR(10),
id_faculdade INTEGER,
matricula VARCHAR(10),
CPF VARCHAR(10),
id_aluno_curso INTEGER,
PRIMARY KEY(id_curso,nome,id_faculdade,matricula,CPF,id_aluno_curso)
)

CREATE TABLE Compromisso (
id_agenda VARCHAR(10) PRIMARY KEY,
data SMALLDATETIME,
hora VARCHAR(10),
curso VARCHAR(10),
matricula VARCHAR(10),
CPF VARCHAR(10),
FOREIGN KEY(matricula) REFERENCES Veterano (matricula),
FOREIGN KEY(CPF) REFERENCES FuturoAluno (CPF)
)

ALTER TABLE Admin ADD FOREIGN KEY(id_usuario) REFERENCES Usuario (id_usuario)
ALTER TABLE Curso ADD FOREIGN KEY(id_universidade,,) REFERENCES Faculdade (id_faculdade,id_universidade)
