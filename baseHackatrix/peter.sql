-- En DB: parainfo de SQLServer, ejecutar este script:
IF EXISTS (SELECT name FROM sysobjects WHERE type='U' AND name='usuarios')
    DROP TABLE usuarios;

-- crea tabla usuarios
CREATE TABLE usuarios(
    idusuario int IDENTITY(1, 1) NOT NULL
                CONSTRAINT PKusuarios Primary Key,
    apellidos varchar(50) NOT NULL,
    nombres varchar(50) NOT NULL,
    usuario varchar(50) NOT NULL,
    password varchar(200) NOT NULL,
    perfil varchar(10) NOT NULL, -- 'ADMIN | CLIENT'

    CONSTRAINT UQusuarios01 UNIQUE(apellidos, nombres),
    CONSTRAINT UQusuarios02 UNIQUE(usuario, password)
);


-- inserts (los password estan encriptados. Los verdaderos son: 12345)
INSERT INTO usuarios(apellidos, nombres, usuario, password, perfil) 
    VALUES('Balta Alva', 'Victor Manuel', 'vbalta',
    'aaKNVphTSnKv1sVKEEPGdQ==', 'ADMIN');
INSERT INTO usuarios(apellidos, nombres, usuario, password, perfil) 
    VALUES('Vasquez Paragulla', 'Juan Julio', 'jvasquez', 
    'aaKNVphTSnKv1sVKEEPGdQ==', 'CLIENT');


SELECT idusuario FROM usuarios

