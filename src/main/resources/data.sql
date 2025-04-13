CREATE TABLE veiculos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(255),
    modelo VARCHAR(255),
    placa VARCHAR(255),
    ano INT,
    cor VARCHAR(255),
    valor_diaria DECIMAL(10, 2)
);

INSERT INTO veiculos (marca, modelo, placa, ano, cor, valor_diaria) VALUES
    ('Chevrolet', 'Celta', 'ABC-1234', 2016, 'Preto', 150.00);

CREATE TABLE pessoas(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    cpf VARCHAR(255),
    telefone VARCHAR(255),
    email VARCHAR(255)
);

INSERT INTO pessoas(nome, cpf, telefone, email)
VALUES ( 'Daniel', '11111111111', '11970707070', 'daniel@email.com' );


CREATE TABLE alugueis (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pessoa_id BIGINT NOT NULL,
    veiculo_id BIGINT NOT NULL,
    data_inicio DATE,
    data_fim DATE,
    valor_total DECIMAL(10, 2),
    FOREIGN KEY (pessoa_id) REFERENCES pessoas(id),
    FOREIGN KEY (veiculo_id) REFERENCES veiculos(id)
);

INSERT INTO veiculos (marca, modelo, placa, ano, cor, valor_diaria) VALUES
    ('Jeep', 'Renegade', 'EFG-1234', 2016, 'Preto', 150.00),
    ('Honda', 'Civic', 'EFG-5678', 2020, 'Preto', 550.00);



INSERT INTO alugueis (pessoa_id, veiculo_id, data_inicio, data_fim, valor_total)
VALUES ( 1, 2, '2025-04-01', '2025-06-01', 1550.00 );