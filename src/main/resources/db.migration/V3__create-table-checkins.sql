CREATE TABLE checkins (
    id_checkin INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    humor VARCHAR(50) NOT NULL,
    impulsividade_nivel VARCHAR(20) NOT NULL,
    data_checkin TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    UNIQUE (id_usuario, DATE(data_checkin)) -- garante apenas 1 check-in por dia por usuário
);
