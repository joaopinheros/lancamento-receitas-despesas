CREATE TABLE lancamento (
   id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
   descricao VARCHAR(50) NOT NULL,
   data_vencimento DATE NOT NULL,
   data_lancamento DATE,
   observacao VARCHAR(100),
   tipo VARCHAR(20) NOT NULL,
   id_categoria BIGINT(20) NOT NULL,
   id_pessoa BIGINT(20) NOT NULL,
   FOREIGN KEY (id_categoria) REFERENCES categoria(id),
   FOREIGN KEY (id_pessoa) REFERENCES pessoa(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO lancamento (descricao, data_vencimento, data_lancamento, observacao, tipo, id_categoria, id_pessoa)
VALUES
    ('Compra Supermercado', '2024-01-20', '2024-01-15', 'Compras mensais', 'DESPESA', 1, 1),
    ('Pagamento de Conta de Luz', '2024-01-25', '2024-01-20', 'Fatura referente ao mês de janeiro', 'DESPESA', 2, 2),
    ('Salário', '2024-01-31', '2024-01-31', 'Recebimento do salário', 'RECEITA', 3, 3),
    ('Aluguel', '2024-02-05', '2024-02-01', 'Pagamento do aluguel', 'DESPESA', 4, 4),
    ('Presente de Aniversário', '2024-02-10', '2024-02-10', 'Presente para amigo', 'DESPESA', 5, 1),
    ('Freelance', '2024-02-15', '2024-02-15', 'Trabalho freelance realizado', 'RECEITA', 6, 2),
    ('Jantar Fora', '2024-02-20', '2024-02-20', 'Jantar em restaurante', 'DESPESA', 1, 3),
    ('Compra Roupas', '2024-02-25', '2024-02-25', 'Roupas para a temporada', 'DESPESA', 2, 4);
