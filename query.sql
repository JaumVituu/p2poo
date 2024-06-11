CREATE TABLE tb_atividade(
    cod_atividade INT NOT NULL PRIMARY KEY,
    descricao VARCHAR(200),
    data_de_ocorrencia TIMESTAMP NOT NULL
);
DELETE FROM tb_atividade;

SELECT * FROM tb_atividade;
CREATE TABLE tb_usuario(
    cod_usuario SERIAL PRIMARY KEY,
    login VARCHAR(30),
    senha VARCHAR(20)
);

INSERT INTO tb_usuario(login, senha) VALUES('teste', 'teste');

SELECT * FROM tb_usuario;

DELETE FROM tb_usuario WHERE cod_usuario = 1;

ALTER TABLE tb_atividade
ADD fk_usuario INT,
ADD FOREIGN KEY(fk_usuario) REFERENCES tb_usuario(cod_usuario);

ALTER TABLE tb_atividade
DROP fk_usuario;

CREATE TABLE tb_resultado(
    cod_resultado SERIAL PRIMARY KEY,
    fk_usuario  INT,
    pontuacao   INT,
    data_de_ocorrencia TIMESTAMP,
    FOREIGN KEY (fk_usuario) REFERENCES tb_usuario(cod_usuario)
);

SELECT * FROM tb_resultado;
