-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.9.3-beta1
-- PostgreSQL version: 13.0
-- Project Site: pgmodeler.io
-- Model Author: ---

-- Database creation must be performed outside a multi lined SQL file. 
-- These commands were put in this file only as a convenience.
-- 
-- object: new_database | type: DATABASE --
-- DROP DATABASE IF EXISTS new_database;
-- CREATE DATABASE new_database;
-- ddl-end --


-- object: public.empleado | type: TABLE --
-- DROP TABLE IF EXISTS public.empleado CASCADE;
CREATE TABLE public.empleado (
	telefono varchar(20),
	salario integer,
	nombre varchar(30),
	fecha_nacimiento date,
	dni varchar(20),
	id_empleado bigserial NOT NULL,
	"contraseña" varchar(20),
	CONSTRAINT "Empleado_pk" PRIMARY KEY (id_empleado)

);
-- ddl-end --
ALTER TABLE public.empleado OWNER TO postgres;
-- ddl-end --

-- object: public.monitor | type: TABLE --
-- DROP TABLE IF EXISTS public.monitor CASCADE;
CREATE TABLE public.monitor (
	turno varchar(20),
	id_actividad_actividad bigint NOT NULL,
	id_empleado_empleado bigint NOT NULL,
	CONSTRAINT monitor_pk PRIMARY KEY (id_empleado_empleado)

);
-- ddl-end --
COMMENT ON COLUMN public.monitor.turno IS E'mañana o tarde';
-- ddl-end --
ALTER TABLE public.monitor OWNER TO postgres;
-- ddl-end --

-- object: public.limpiador | type: TABLE --
-- DROP TABLE IF EXISTS public.limpiador CASCADE;
CREATE TABLE public.limpiador (
	turno varchar(20),
	zonas_asignadas varchar(40),
	id_empleado_empleado bigint NOT NULL,
	CONSTRAINT limpiador_pk PRIMARY KEY (id_empleado_empleado)

);
-- ddl-end --
COMMENT ON COLUMN public.limpiador.turno IS E'mañana o tarde';
-- ddl-end --
COMMENT ON COLUMN public.limpiador.zonas_asignadas IS E'zonas separadas por comas';
-- ddl-end --
ALTER TABLE public.limpiador OWNER TO postgres;
-- ddl-end --

-- object: public.recepcionista | type: TABLE --
-- DROP TABLE IF EXISTS public.recepcionista CASCADE;
CREATE TABLE public.recepcionista (
	id_empleado_empleado bigint NOT NULL,
	CONSTRAINT recepcionista_pk PRIMARY KEY (id_empleado_empleado)

);
-- ddl-end --
ALTER TABLE public.recepcionista OWNER TO postgres;
-- ddl-end --

-- object: public.entrenador | type: TABLE --
-- DROP TABLE IF EXISTS public.entrenador CASCADE;
CREATE TABLE public.entrenador (
	horas_reservadas integer,
	horas_libres integer,
	id_empleado_empleado bigint NOT NULL,
	CONSTRAINT entrenador_pk PRIMARY KEY (id_empleado_empleado)

);
-- ddl-end --
ALTER TABLE public.entrenador OWNER TO postgres;
-- ddl-end --

-- object: public.actividad | type: TABLE --
-- DROP TABLE IF EXISTS public.actividad CASCADE;
CREATE TABLE public.actividad (
	id_actividad bigserial NOT NULL,
	tipo varchar(20),
	horario time,
	aforo_max integer,
	sala integer,
	CONSTRAINT "Actividad_pk" PRIMARY KEY (id_actividad)

);
-- ddl-end --
COMMENT ON COLUMN public.actividad.tipo IS E'Puede ser: cardio, fuerza o piscina';
-- ddl-end --
ALTER TABLE public.actividad OWNER TO postgres;
-- ddl-end --

-- object: public.socio | type: TABLE --
-- DROP TABLE IF EXISTS public.socio CASCADE;
CREATE TABLE public.socio (
	numero_socio bigserial NOT NULL,
	nombre varchar(30),
	telefono varchar(20),
	email varchar(30),
	tipo_cuota varchar(20),
	numero_cuenta_bancaria varchar(20),
	id_empleado_empleado_entrenador bigint,
	"contraseña" varchar(20),
	CONSTRAINT "Socio_pk" PRIMARY KEY (numero_socio)

);
-- ddl-end --
COMMENT ON COLUMN public.socio.tipo_cuota IS E'Puede ser: estudiante, jubilado, familiar o estándar';
-- ddl-end --
ALTER TABLE public.socio OWNER TO postgres;
-- ddl-end --

-- object: public.realizan | type: TABLE --
-- DROP TABLE IF EXISTS public.realizan CASCADE;
CREATE TABLE public.realizan (
	id_actividad_actividad bigint NOT NULL,
	numero_socio_socio bigint NOT NULL
);
-- ddl-end --

-- object: socio_fk | type: CONSTRAINT --
-- ALTER TABLE public.realizan DROP CONSTRAINT IF EXISTS socio_fk CASCADE;
ALTER TABLE public.realizan ADD CONSTRAINT socio_fk FOREIGN KEY (numero_socio_socio)
REFERENCES public.socio (numero_socio) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: actividad_fk | type: CONSTRAINT --
-- ALTER TABLE public.realizan DROP CONSTRAINT IF EXISTS actividad_fk CASCADE;
ALTER TABLE public.realizan ADD CONSTRAINT actividad_fk FOREIGN KEY (id_actividad_actividad)
REFERENCES public.actividad (id_actividad) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: empleado_fk | type: CONSTRAINT --
-- ALTER TABLE public.monitor DROP CONSTRAINT IF EXISTS empleado_fk CASCADE;
ALTER TABLE public.monitor ADD CONSTRAINT empleado_fk FOREIGN KEY (id_empleado_empleado)
REFERENCES public.empleado (id_empleado) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: public.evalua | type: TABLE --
-- DROP TABLE IF EXISTS public.evalua CASCADE;
CREATE TABLE public.evalua (
	valoracion integer,
	numero_socio_socio bigint NOT NULL,
	id_empleado_empleado_monitor bigint NOT NULL,
	CONSTRAINT evalua_pk PRIMARY KEY (id_empleado_empleado_monitor,numero_socio_socio)

);
-- ddl-end --
COMMENT ON COLUMN public.evalua.valoracion IS E'Nota del 1 al 5';
-- ddl-end --

-- object: monitor_fk | type: CONSTRAINT --
-- ALTER TABLE public.evalua DROP CONSTRAINT IF EXISTS monitor_fk CASCADE;
ALTER TABLE public.evalua ADD CONSTRAINT monitor_fk FOREIGN KEY (id_empleado_empleado_monitor)
REFERENCES public.monitor (id_empleado_empleado) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: socio_fk | type: CONSTRAINT --
-- ALTER TABLE public.evalua DROP CONSTRAINT IF EXISTS socio_fk CASCADE;
ALTER TABLE public.evalua ADD CONSTRAINT socio_fk FOREIGN KEY (numero_socio_socio)
REFERENCES public.socio (numero_socio) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: public.imparte | type: TABLE --
-- DROP TABLE IF EXISTS public.imparte CASCADE;
CREATE TABLE public.imparte (
	fecha date,
	id_actividad_actividad bigint NOT NULL,
	id_empleado_empleado_monitor bigint NOT NULL
);
-- ddl-end --

-- object: actividad_fk | type: CONSTRAINT --
-- ALTER TABLE public.imparte DROP CONSTRAINT IF EXISTS actividad_fk CASCADE;
ALTER TABLE public.imparte ADD CONSTRAINT actividad_fk FOREIGN KEY (id_actividad_actividad)
REFERENCES public.actividad (id_actividad) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: monitor_fk | type: CONSTRAINT --
-- ALTER TABLE public.imparte DROP CONSTRAINT IF EXISTS monitor_fk CASCADE;
ALTER TABLE public.imparte ADD CONSTRAINT monitor_fk FOREIGN KEY (id_empleado_empleado_monitor)
REFERENCES public.monitor (id_empleado_empleado) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: actividad_fk | type: CONSTRAINT --
-- ALTER TABLE public.monitor DROP CONSTRAINT IF EXISTS actividad_fk CASCADE;
ALTER TABLE public.monitor ADD CONSTRAINT actividad_fk FOREIGN KEY (id_actividad_actividad)
REFERENCES public.actividad (id_actividad) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: empleado_fk | type: CONSTRAINT --
-- ALTER TABLE public.limpiador DROP CONSTRAINT IF EXISTS empleado_fk CASCADE;
ALTER TABLE public.limpiador ADD CONSTRAINT empleado_fk FOREIGN KEY (id_empleado_empleado)
REFERENCES public.empleado (id_empleado) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: empleado_fk | type: CONSTRAINT --
-- ALTER TABLE public.recepcionista DROP CONSTRAINT IF EXISTS empleado_fk CASCADE;
ALTER TABLE public.recepcionista ADD CONSTRAINT empleado_fk FOREIGN KEY (id_empleado_empleado)
REFERENCES public.empleado (id_empleado) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: empleado_fk | type: CONSTRAINT --
-- ALTER TABLE public.entrenador DROP CONSTRAINT IF EXISTS empleado_fk CASCADE;
ALTER TABLE public.entrenador ADD CONSTRAINT empleado_fk FOREIGN KEY (id_empleado_empleado)
REFERENCES public.empleado (id_empleado) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: entrenador_fk | type: CONSTRAINT --
-- ALTER TABLE public.socio DROP CONSTRAINT IF EXISTS entrenador_fk CASCADE;
ALTER TABLE public.socio ADD CONSTRAINT entrenador_fk FOREIGN KEY (id_empleado_empleado_entrenador)
REFERENCES public.entrenador (id_empleado_empleado) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.director | type: TABLE --
-- DROP TABLE IF EXISTS public.director CASCADE;
CREATE TABLE public.director (
	id_empleado_empleado bigint NOT NULL,
	CONSTRAINT director_pk PRIMARY KEY (id_empleado_empleado)

);
-- ddl-end --
ALTER TABLE public.director OWNER TO postgres;
-- ddl-end --

-- object: empleado_fk | type: CONSTRAINT --
-- ALTER TABLE public.director DROP CONSTRAINT IF EXISTS empleado_fk CASCADE;
ALTER TABLE public.director ADD CONSTRAINT empleado_fk FOREIGN KEY (id_empleado_empleado)
REFERENCES public.empleado (id_empleado) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --


