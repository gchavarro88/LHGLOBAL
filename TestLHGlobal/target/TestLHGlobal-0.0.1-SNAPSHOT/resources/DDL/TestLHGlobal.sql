CREATE TABLE categorias
(
  categoriaId NUMBER(18) PRIMARY KEY,
  nombre VARCHAR2(100),
  estado NUMBER(1)
);


CREATE TABLE eventos
(
  eventoId NUMBER(18) PRIMARY KEY,
  nombre VARCHAR2(100),
  fecha DATE,
  lugar VARCHAR(100),
  categoriaId NUMBER(18)
);


ALTER TABLE eventos ADD CONSTRAINT FK_EVENTO_X_CATEGORIA FOREIGN KEY (categoriaId) REFERENCES categorias(categoriaId);

CREATE SEQUENCE sqcategorias
MINVALUE 1
MAXVALUE 111111111111111111
START WITH 1
INCREMENT BY 1
CACHE 10;


CREATE SEQUENCE sqeventos
MINVALUE 1
MAXVALUE 111111111111111111
START WITH 1
INCREMENT BY 1
CACHE 10;


SELECT * FROM categorias;
SELECT * FROM eventos;


INSERT INTO categorias (categoriaId, nombre, estado) VALUES(SQCATEGORIAS.NEXTVAL, 'CategoriaPrueba', 1);
INSERT INTO categorias (categoriaId, nombre, estado) VALUES(SQCATEGORIAS.NEXTVAL, 'Otra Categoria', 1);
INSERT INTO categorias (categoriaId, nombre, estado) VALUES(SQCATEGORIAS.NEXTVAL, 'Compras', 1);
INSERT INTO categorias (categoriaId, nombre, estado) VALUES(SQCATEGORIAS.NEXTVAL, 'Ventas', 1);

INSERT INTO eventos (eventoId, categoriaId, nombre, fecha, lugar) VALUES(SQEVENTOS.NEXTVAL,3, 'Evento Prueba', (date '2017-12-23'), 'El estadio');
INSERT INTO eventos (eventoId, categoriaId, nombre, fecha, lugar) VALUES(SQEVENTOS.NEXTVAL,3, 'Evento Prueba', (date '2017-12-23'), 'El Parque del Gato');
INSERT INTO eventos (eventoId, categoriaId, nombre, fecha, lugar) VALUES(SQEVENTOS.NEXTVAL,3, 'Evento Prueba', (date '2017-05-23'), 'Canchas Panamericanas');
INSERT INTO eventos (eventoId, categoriaId, nombre, fecha, lugar) VALUES(SQEVENTOS.NEXTVAL,3, 'Evento Prueba', (date '2017-05-12'), 'Edificio de Colores');




