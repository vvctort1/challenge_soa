


CREATE TABLE acessos (
    id_acesso bigint AUTO_INCREMENT PRIMARY KEY,
    id_usuario bigint NOT NULL,
    data_acesso TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);