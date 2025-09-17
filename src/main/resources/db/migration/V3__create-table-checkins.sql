CREATE TABLE checkins (
    id_checkin BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_usuario BIGINT NOT NULL,
    humor VARCHAR(255) NOT NULL,
    impulsividade_nivel VARCHAR(50),
    data DATETIME NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);