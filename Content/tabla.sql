CREATE DATABASE LIBRERIA;
USE LIBRERIA;
CREATE TABLE TEMAS(
	ID_TEMA INTEGER PRIMARY KEY auto_increment, 
	DESC_TEMA VARCHAR(50) NOT NULL,
	ABREVIATURA VARCHAR(6) );
 

CREATE TABLE LIBROS 
(ISBN dec(15) PRIMARY KEY, 
	TITULO VARCHAR(200) NOT NULL,
	AUTOR VARCHAR(200) NOT NULL, 
	PRECIO_UNITARIO  DECIMAL(9,2),
	STOCK INTEGER,
	ID_TEMA INTEGER NOT NULL,
	foreign key(id_tema) REFERENCES TEMAS(ID_TEMA));
 

CREATE TABLE USUARIOS (
	ID_USUARIO VARCHAR(50) PRIMARY KEY, 
	PASSWORD VARCHAR(20) NOT NULL, 
	NOMBRE VARCHAR(50), 
	APELLIDO VARCHAR(50), 
	DIRECCION VARCHAR(100), 
	FECHA_ALTA DATE,
	TIPO_USUARIO VARCHAR(10) NOT NULL
);

CREATE TABLE PEDIDOS(
	ID_PEDIDO INTEGER PRIMARY KEY auto_increment, 
	ID_USUARIO VARCHAR(50) NOT NULL, 
	DIRECCION_ENTREGA VARCHAR(50),
	estado varchar(15),
	FECHA_ALTA DATE,
	foreign key(id_usuario) REFERENCES USUARIOS(ID_USUARIO));

CREATE TABLE LINEA_PEDIDOS (
	ID_PEDIDO INTEGER NOT NULL,
	ISBN dec(15) NOT NULL, 
	FECHA_ALTA DATE, 
	CANTIDAD INTEGER,
	precio_venta DECIMAL(9,2),
	PRIMARY KEY(ID_PEDIDO,ISBN),
	foreign key(id_pedido)  REFERENCES PEDIDOS(ID_PEDIDO),
	foreign key(isbn)  REFERENCES LIBROS(ISBN));
## drop user 'librero'@'localhost';
insert into temas values(1,'Biologia','BLG');
insert into temas values(2,'Terror','TRRR');
insert into temas values(3,'Magia','MG');
insert into temas values(4,'Deportes','DPRT');
commit;
insert into usuarios values('tomas','tomas','tomasin','escudero','madrid', current_date(),'normal');
insert into usuarios values('eva','eva','evita','perez','cordoba', current_date(),'normal');
insert into usuarios values('ramon','ramon','ramoncin','ortega','sevilla', current_date(),'admon');
commit;
insert into libros values(10001,'Bioquímica','Leninger',120,10,1);
insert into libros values(10002,'Zoología','Jose Luis Sanchez',50,5,1);
insert into libros values(10003,'Fisiología Animal','Romero',75,10,1);
insert into libros values(20001,'Vracula','Condemor de la Pradera',25,50,2);
insert into libros values(20002,'Fronkestoin','Condemor de la Pradera',25,50,2);
insert into libros values(20003,'El exorcista','Uno por ahi',30,10,2);
insert into libros values(30001,'harry Potter y la Piedra filosofal','J. Rooling',50,50,3);
insert into libros values(30002,'harry Potter y el principe mestizo','J. Rooling',50,50,3);
insert into libros values(30003,'harry Potter y las reliquias de la muerte','J. Rooling',50,50,3);
insert into libros values(40001,'Mecanica del futbol','J. Rubiales',35,50,4);
insert into libros values(40002,'Balonmano Europeo','Campeones de europa',45,50,4);
commit;


create user 'librero'@'localhost' identified by 'librero';
grant all privileges on libreria.* to 'librero'@'localhost';