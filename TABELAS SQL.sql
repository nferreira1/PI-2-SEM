CREATE DATABASE padoka;
USE padoka;

CREATE TABLE clientes (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    CPF VARCHAR(11) NOT NULL UNIQUE,
    data_de_nascimento DATE NOT NULL,
	celular VARCHAR(15) NULL,
    telefone VARCHAR(15) NULL,
    email VARCHAR(100) NULL,
    sexo ENUM('MASCULINO', 'FEMININO', 'OUTRO') NOT NULL,
    estado_civil ENUM('SOLTEIRO', 'CASADO', 'SEPARADO', 'DIVORCIADO', 'VIÚVO') NOT NULL,
    observacoes VARCHAR(200) NULL,
    status_conta BOOLEAN NOT NULL
);

CREATE TABLE enderecos (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    CEP VARCHAR(8) NOT NULL,
    logradouro VARCHAR(100)  NOT NULL,
    numero VARCHAR(5)  NULL,
    bairro VARCHAR(50)  NOT NULL,
    complemento VARCHAR(50) NULL,
    UF VARCHAR(2)  NOT NULL,
    cidade VARCHAR(30)  NOT NULL,
    cliente_id INT NOT NULL,
    FOREIGN KEY(cliente_id) REFERENCES clientes(ID)
);

CREATE TABLE categorias_produtos (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(40) NOT NULL
);

INSERT INTO categorias_produtos (nome) VALUES
('BEBIDAS'),
('CONFEITARIA'),
('LATICÍNIOS'),
('SALGADOS'),
('PÃES'),
('OUTROS');

CREATE TABLE produtos (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    unidade_de_medida ENUM('KG', 'G', 'L', 'ML') NOT NULL,
    estoque INT NOT NULL,
    valor DECIMAL(5, 2),
	categoria_id INT NOT NULL,
    status_produto BOOLEAN NOT NULL,   
    FOREIGN KEY(categoria_id) REFERENCES categorias_produtos(ID)
);

CREATE TABLE vendas (
	ID INT PRIMARY KEY AUTO_INCREMENT,
    valor DECIMAL(10, 2),
    cliente_id INT NOT NULL,
	FOREIGN KEY(cliente_id) REFERENCES clientes(ID)
);

CREATE TABLE itensVendas (
	ID INT PRIMARY KEY AUTO_INCREMENT,
    vendas_id INT NOT NULL,
    produto_id INT NOT NULL,
    quantidade_produto INT NOT NULL,
	FOREIGN KEY(produto_id) REFERENCES produtos(ID),
    FOREIGN KEY(vendas_id) REFERENCES vendas(ID)
);

DELIMITER //
CREATE TRIGGER diminuirEstoqueAposVenda 
AFTER INSERT ON itensVendas 
FOR EACH ROW 
BEGIN
    UPDATE produtos
    SET estoque = estoque - NEW.quantidade_produto
    WHERE ID = NEW.produto_id;
END;
//
DELIMITER ;



SHOW TRIGGERS;
SELECT * FROM clientes;
SELECT * FROM produtos;
SELECT * FROM vendas;
SELECT * FROM itensVendas;
SELECT * FROM enderecos;