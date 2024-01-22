CREATE TABLE pessoa (
    id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nome VARCHAR(255) NOT NULL,
    ativo BOOLEAN NOT NULL,
    logradouro VARCHAR(255) NOT NULL,
    numero VARCHAR(255) NOT NULL,
    complemento VARCHAR(255) NOT NULL,
    bairro VARCHAR(255) NOT NULL,
    cep VARCHAR(255) NOT NULL,
    cidade VARCHAR(255) NOT NULL,
    estado VARCHAR(255) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa(nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)
VALUES ('Carlos Cordeiro dos Santos', true, 'Rua Centenaria', 140, 'Casa 140 A', 'Bela Vista', '39100-00', 'Diamantina', 'Minas Gerais');

INSERT INTO pessoa(nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)
VALUES ('Maria Helena da Rocha', true, 'Rua das FLores', 555, 'Apto 202', 'Santa Luzia', '45124-000', 'Belo Horizonte', 'Minas Gerais');

INSERT INTO pessoa(nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)
VALUES ('Geraldo Cruz Dias', true, 'Rua Oliveira Machado', 745, 'Casa B', 'Luiz Inacio Lula da Silva', '45687-000', 'Maracaju', 'Sergipe');

INSERT INTO pessoa(nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)
VALUES ('Ana Leticia Fonseca', true, 'Rua Luiz Padinga', 456, 'Apto 455', 'Flor Branca', '38998-000', 'Ipatinga', 'Minas Gerais');
