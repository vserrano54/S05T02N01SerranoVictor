-- Eliminar base de datos juegodb si existe

DROP DATABASE IF EXISTS juegodb;

-- crear Base de datos juegodb
CREATE DATABASE juegodb;

-- Establer base de datos juegodb

USE juegodb;


-- Crear tabla usuario y roles intermedia
DROP TABLE IF EXISTS TBL_USER_ROL;
CREATE TABLE TBL_USER_ROL (
    ID_USER INT,
    ID_ROL INT,
    PRIMARY KEY (ID_USER, ID_ROL)
);

-- Crear tabla usuario
DROP TABLE IF EXISTS TBL_USER;
CREATE TABLE TBL_USER (

    ID_USER INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(45) NOT NULL UNIQUE,
    nombre VARCHAR(80) NOT NULL,
    PASSWORD VARCHAR(128) NOT NULL,
    ENABLED TINYINT(1) NOT NULL DEFAULT 1,
    FECHA TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Crear tabla rol
DROP TABLE IF EXISTS TBL_ROL;
CREATE TABLE TBL_ROL (
    ID_ROL INT PRIMARY KEY AUTO_INCREMENT,
    DESCRIPCION VARCHAR(45) NOT NULL
);

-- Crear clave externa en TBL_USER_ROL que hace referencia a TBL_USER
ALTER TABLE TBL_USER_ROL
ADD CONSTRAINT FK_USER_ROL_USER
FOREIGN KEY (ID_USER)
REFERENCES TBL_USER(ID_USER)
ON UPDATE CASCADE
ON DELETE CASCADE;

-- Crear clave externa en TBL_USER_ROL que hace referencia a TBL_ROL
ALTER TABLE TBL_USER_ROL
ADD CONSTRAINT FK_USER_ROL_ROL
FOREIGN KEY (ID_ROL)
REFERENCES TBL_ROL(ID_ROL)
ON UPDATE CASCADE
ON DELETE CASCADE;


-- crear tabla partida
DROP TABLE IF EXISTS TBL_PARTIDA;
CREATE TABLE TBL_PARTIDA
(
	ID_PARTIDA INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    FECHA DATE NOT NULL
);


-- Crea table Tirada
DROP TABLE IF EXISTS TBL_TIRADA;
CREATE TABLE TBL_TIRADA
(
	ID_TIRADA INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    DADO1 INT NOT NULL,
    DADO2 INT NOT NULL
);


-- Crear tabla jugada
DROP TABLE IF EXISTS TBL_JUGADA;
CREATE TABLE TBL_JUGADA
(
	ID_JUGADA INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    ID_PARTIDA INT NOT NULL,
    ID_USER INT NOT NULL,
    ID_TIRADA INT NOT NULL,
    CONSTRAINT JUGADA_PARTIDA FOREIGN KEY (ID_PARTIDA)
    REFERENCES TBL_PARTIDA(ID_PARTIDA),
	CONSTRAINT JUGADA_USER FOREIGN KEY (ID_USER)
    REFERENCES TBL_USER(ID_USER),
    CONSTRAINT JUGADA_TIRADA FOREIGN KEY (ID_TIRADA)
    REFERENCES TBL_TIRADA(ID_TIRADA)
);

-- Insertar Usuarios

INSERT INTO TBL_USER(username, nombre, PASSWORD) VALUES ('admin','Administrador','$2a$10$92xaOlTdsCVTnqPZc6kaOu23OCD8cccqoFRAI2wmaOAB7/s2NFCzK');
-- INSERT INTO TBL_USER(username, nombre, PASSWORD) VALUES ('user', 'USUARIO','$2a$10$qsYg85VmnEHqV97lpFIx3.B/02diqdLfehCsDK/u5IqY3ACE5Mijy');

-- Insertar Roles
INSERT INTO TBL_ROL(DESCRIPCION) VALUES ('ROLE_ADMIN');
INSERT INTO TBL_ROL(DESCRIPCION) VALUES ('ROLE_JUGADOR');

INSERT INTO TBL_USER_ROL(ID_USER,ID_ROL) VALUES(1,1);
-- INSERT INTO TBL_USER_ROL(ID_USER,ID_ROL) VALUES(2,2);



DROP PROCEDURE IF EXISTS getListarUsuarios;
delimiter //
CREATE PROCEDURE getListarUsuarios(IN idrol INT)
BEGIN
	SELECT u.id_user as id, u.username, u.nombre, u.fecha
    FROM tbl_user_rol ur
    INNER JOIN tbl_user u ON u.id_user = ur.id_user
    INNER JOIN tbl_rol r ON r.id_rol = ur.id_rol
	WHERE u.enabled=true and ur.id_rol = idrol
    order by u.username;
END //
delimiter ;


DROP PROCEDURE IF EXISTS lista_porcentaje_logrado;
delimiter //
CREATE PROCEDURE lista_porcentaje_logrado()
BEGIN
	SELECT u.nombre as JUGADOR,
       COUNT(PA.ID_PARTIDA) AS PARTIDAS_TOTALES,
       SUM(CASE WHEN TA.DADO1 + TA.DADO2 = 7 THEN 1 ELSE 0 END) AS PARTIDAS_GANADAS,
       SUM(CASE WHEN TA.DADO1 + TA.DADO2 = 7 THEN 1 ELSE 0 END) / COUNT(PA.ID_PARTIDA) * 100  AS PORCENTAJE_ACIERTOS
    FROM TBL_USER_ROL ur
    INNER JOIN TBL_USER u ON u.id_user = ur.id_user
    INNER JOIN TBL_ROL r ON r.id_rol = ur.id_rol
    INNER JOIN TBL_JUGADA ja on ja.id_user=u.id_user
    INNER JOIN TBL_TIRADA ta on ta.id_tirada = ja.id_tirada
    INNER JOIN TBL_PARTIDA pa on pa.id_partida = ja.id_partida
    WHERE ur.id_rol = 2 and u.enabled = true
    GROUP BY u.NOMBRE
	ORDER BY PORCENTAJE_ACIERTOS;
END //
delimiter ;

DROP PROCEDURE IF EXISTS lista_jugadas_por_jugador;
delimiter //
CREATE PROCEDURE lista_jugadas_por_jugador(IN idrol INT, IN IDUSER INT)
BEGIN
	SELECT ta.id_tirada as id,
			u.NOMBRE AS nombre, 
			PA.FECHA AS FECHA,
            TA.DADO1 AS DADO1,
            TA.DADO2 AS DADO2,
            CASE WHEN TA.DADO1 + TA.DADO2 = 7 THEN 'JUGADA GANADA' ELSE 'JUGADA PERDIDA' END AS RESULTADO
    FROM TBL_USER_ROL ur
    INNER JOIN TBL_USER u ON u.id_user = ur.id_user
    INNER JOIN TBL_ROL r ON r.id_rol = ur.id_rol
    INNER JOIN TBL_JUGADA ja on ja.id_user=u.id_user
    INNER JOIN TBL_TIRADA ta on ta.id_tirada = ja.id_tirada
    INNER JOIN TBL_PARTIDA pa on pa.id_partida = ja.id_partida
    WHERE ur.id_rol = idrol
    AND u.id_user = IDUSER
    ORDER BY PA.FECHA;
END //
delimiter ;


	DROP PROCEDURE IF EXISTS lista_rankin_por_jugador;
	delimiter //
	CREATE PROCEDURE lista_rankin_por_jugador(IN idrol INT)
	BEGIN
	SELECT u.NOMBRE AS JUGADOR, 
		   CONVERT( RANK() OVER (ORDER BY SUM(CASE WHEN ta.dado1 + ta.dado2 = 7 THEN 1 ELSE 0 END) / COUNT(pa.id_partida) DESC) , DOUBLE) AS RANKIN
	 FROM TBL_USER_ROL ur
		INNER JOIN TBL_USER u ON u.id_user = ur.id_user
		INNER JOIN TBL_ROL r ON r.id_rol = ur.id_rol
		INNER JOIN TBL_JUGADA ja on ja.id_user=u.id_user
		INNER JOIN TBL_TIRADA ta on ta.id_tirada = ja.id_tirada
		INNER JOIN TBL_PARTIDA pa on pa.id_partida = ja.id_partida
		WHERE ur.id_rol = idrol
	GROUP BY u.NOMBRE
	ORDER BY RANKIN;
	END //
	delimiter ;



DROP PROCEDURE IF EXISTS peor_porcentaje_logrado;
delimiter //
CREATE PROCEDURE peor_porcentaje_logrado()
BEGIN
SELECT u.nombre AS JUGADOR,
       COUNT(PA.ID_PARTIDA) AS PARTIDAS_TOTALES,
       SUM(CASE WHEN TA.DADO1 + TA.DADO2 = 7 THEN 1 ELSE 0 END) AS PARTIDAS_GANADAS,
       SUM(CASE WHEN TA.DADO1 + TA.DADO2 = 7 THEN 1 ELSE 0 END) / COUNT(PA.ID_PARTIDA) * 100 AS PORCENTAJE_ACIERTOS
FROM TBL_USER_ROL ur
INNER JOIN TBL_USER u ON u.id_user = ur.id_user
INNER JOIN TBL_ROL r ON r.id_rol = ur.id_rol
INNER JOIN TBL_JUGADA ja ON ja.id_user = u.id_user
INNER JOIN TBL_TIRADA ta ON ta.id_tirada = ja.id_tirada
INNER JOIN TBL_PARTIDA pa ON pa.id_partida = ja.id_partida
WHERE  u.enabled = true                 -- and ur.id_rol = 2 
GROUP BY u.NOMBRE
ORDER BY PORCENTAJE_ACIERTOS ASC  
LIMIT 1;  
END //
delimiter ;


DROP PROCEDURE IF EXISTS peor_rankin_logrado;
DELIMITER //
CREATE PROCEDURE peor_rankin_logrado()
BEGIN
    SELECT u.NOMBRE, 
           RANK() OVER (ORDER BY SUM(CASE WHEN ta.dado1 + ta.dado2 = 7 THEN 1 ELSE 0 END) / COUNT(pa.id_partida) DESC) AS RANKIN
     FROM TBL_USER_ROL ur
        INNER JOIN TBL_USER u ON u.id_user = ur.id_user
        INNER JOIN TBL_ROL r ON r.id_rol = ur.id_rol
        INNER JOIN TBL_JUGADA ja ON ja.id_user = u.id_user
        INNER JOIN TBL_TIRADA ta ON ta.id_tirada = ja.id_tirada
        INNER JOIN TBL_PARTIDA pa ON pa.id_partida = ja.id_partida
      --  WHERE ur.id_rol = 2
    GROUP BY u.NOMBRE
    ORDER BY RANKIN desc
    LIMIT 1;
END //
DELIMITER ;