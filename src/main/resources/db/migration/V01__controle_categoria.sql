CREATE TABLE categoria(
    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO categoria (nome) values ('Alimentação');
INSERT INTO categoria (nome) values ('Supermercado');
INSERT INTO categoria (nome) values ('Lazer');
INSERT INTO categoria (nome) values ('Roupas');
INSERT INTO categoria (nome) values ('Farmacia');
INSERT INTO categoria (nome) values ('Outros');

