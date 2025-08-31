-- Apaga as tabelas antigas na ordem correta para evitar erros de dependência
DROP TABLE IF EXISTS itens_devolucao CASCADE;
DROP TABLE IF EXISTS devolucoes CASCADE;
DROP TABLE IF EXISTS item_venda CASCADE;
DROP TABLE IF EXISTS logistica CASCADE;
DROP TABLE IF EXISTS pagamentos CASCADE;
DROP TABLE IF EXISTS vendas CASCADE;
DROP TABLE IF EXISTS estoque CASCADE;
DROP TABLE IF EXISTS produtos CASCADE;
DROP TABLE IF EXISTS usuarios CASCADE;

-- Cria a tabela 'usuarios' com todas as colunas de Usuario, Cliente e Fornecedor
CREATE TABLE usuarios (
    id BIGSERIAL PRIMARY KEY,
    user_type VARCHAR(31) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    cpf VARCHAR(255),
    endereco VARCHAR(255),
    telefone VARCHAR(255),
    cnpj VARCHAR(255) UNIQUE,
    nome_da_empresa VARCHAR(255)
);

-- Cria a tabela 'produtos'
CREATE TABLE produtos (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    preco DOUBLE PRECISION NOT NULL,
    descricao VARCHAR(255),
    fornecedor_id BIGINT,
    FOREIGN KEY (fornecedor_id) REFERENCES usuarios(id)
);

-- Cria a tabela 'estoque'
CREATE TABLE estoque (
    produto_id BIGINT PRIMARY KEY,
    quantidade INTEGER,
    FOREIGN KEY (produto_id) REFERENCES produtos(id) ON DELETE CASCADE
);

-- Cria a tabela 'vendas'
CREATE TABLE vendas (
    id BIGSERIAL PRIMARY KEY,
    data DATE,
    valor_total DOUBLE PRECISION,
    cliente_id BIGINT NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES usuarios(id)
);

-- Cria a tabela 'item_venda'
CREATE TABLE item_venda (
    id BIGSERIAL PRIMARY KEY,
    venda_id BIGINT,
    produto_id BIGINT,
    quantidade INTEGER,
    preco_unitario DOUBLE PRECISION,
    FOREIGN KEY (venda_id) REFERENCES vendas(id),
    FOREIGN KEY (produto_id) REFERENCES produtos(id)
);

-- Cria a tabela 'pagamentos'
CREATE TABLE pagamentos (
    venda_id BIGINT PRIMARY KEY,
    metodo_pagamento VARCHAR(255),
    status VARCHAR(255),
    FOREIGN KEY (venda_id) REFERENCES vendas(id) ON DELETE CASCADE
);

-- Cria a tabela 'logistica'
CREATE TABLE logistica (
    venda_id BIGINT PRIMARY KEY,
    codigo_de_rastreio VARCHAR(255),
    status VARCHAR(255),
    endereco_de_entrega VARCHAR(255),
    FOREIGN KEY (venda_id) REFERENCES vendas(id) ON DELETE CASCADE
);

-- Cria a tabela 'devolucoes'
CREATE TABLE devolucoes (
    id BIGSERIAL PRIMARY KEY,
    data DATE,
    motivo_devolucao VARCHAR(255),
    venda_id BIGINT,
    FOREIGN KEY (venda_id) REFERENCES vendas(id)
);

-- Cria a tabela 'itens_devolucao'
CREATE TABLE itens_devolucao (
    id BIGSERIAL PRIMARY KEY,
    devolucao_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    quantidade INTEGER NOT NULL,
    FOREIGN KEY (devolucao_id) REFERENCES devolucoes(id),
    FOREIGN KEY (produto_id) REFERENCES produtos(id)
);