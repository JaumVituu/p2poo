CREATE TABLE tb_atividade(
    cod_atividade INT NOT NULL PRIMARY KEY,
    descricao VARCHAR(200),
    data_de_ocorrencia TIMESTAMP NOT NULL
);

DELETE FROM tb_atividade;

SELECT * FROM tb_atividade;