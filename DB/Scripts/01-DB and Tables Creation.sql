use master;

-- /*******	CREATING WAREHOUSE DATABASE	*******/
create database Warehouse on primary 
(
	name = 'Warehouse_dat',
	filename = 'D:\Programacion\SQL\SQL Server\Warehouse\Warehouse.mdf',
	size = 15mb, 
	maxsize = 30mb,
	filegrowth = 10mb
) log on 
(
	name = 'Warehouse_log',
	filename = 'D:\Programacion\SQL\SQL Server\Warehouse\Warehouse.ldf',
	size = 10mb, 
	maxsize = 20mb,
	filegrowth = 5mb
);

use Warehouse;
-- /****** Creando schemas *****/

create schema org;
create schema dts;

-- /****** Creando tablas de Warehouse *******/

create table org.Rol (
	idRol tinyint constraint pk_Rol_idRol primary key identity(1,1),
	rol char(25) not null
);

create table org.Usuario (
	usuario varchar(50) constraint pk_Usuario_usuario primary key,
	contrasena varchar(50) not null,
	idRol tinyint constraint fk_Usuario_idRol foreign key 
	references org.Rol (idRol)
);

create table org.Proveedor (
	idProveedor varchar(10) constraint pk_Proveedor_idProveedor primary key,
	usuario varchar(50) constraint fk_Proveedor_usuario foreign key 
	references org.Usuario (usuario)
);

create table dts.DatosProveedor (
	idProveedor varchar(10) constraint fk_DatosProveedor_idProveedor foreign key
	references org.Proveedor (idProveedor),
	nombres char(35) not null,
	apellidos char(35) not null,
	correo varchar(50) not null,
	telefono varchar(20) default 'No especificado'
);

create table org.Medida (
	idMedida tinyint constraint pk_Medida_idMedida primary key identity(1,1),
	medida varchar(60) not null
);

create table org.Estado (
	idEstado tinyint constraint pk_Estado_idEstado primary key identity(1,1),
	estado char(20) not null,
);

create table org.Producto (
	idProducto varchar(5) constraint pk_Producto_idProducto primary key,
	idMedida tinyint constraint fk_Producto_idMedida foreign key 
	references org.Medida (idMedida),
	idEstado tinyint constraint fk_Producto_idEstado foreign key 
	references org.Estado (idEstado)
);

create table dts.DatosProducto (
	idProducto varchar(5) constraint fk_DatosProducto_idProducto foreign key 
	references org.Producto (idProducto),
	nombre varchar(50) not null,
	marca varchar(40) not null,
	cantidad smallint not null constraint chk_DatosProducto_cantidad check (cantidad > 0)
);

create table org.Salida (
	idSalida smallint constraint pk_Salida_idSalida primary key identity(1,1),
	idProducto varchar(5) constraint fk_Salida_idProducto foreign key
	references org.Producto (idProducto),
	usuario varchar(50) constraint fk_Salida_usuario foreign key
	references org.Usuario (usuario)
);

create table dts.DatosSalida (
	idSalida smallint constraint fk_DatosSalida_idSalida foreign key
	references org.Salida (idSalida),
	cantidad smallint not null constraint chk_DatosSalida_cantidad check (cantidad > 0),
	fechaSalida char(10) not null,
	observaciones varchar(100) default 'Sin observaciones',
);

create table org.Entrada (
	idEntrada smallint constraint pk_Entrada_idEntrada primary key identity(1,1),
	idProducto varchar(5) constraint fk_Entrada_idProducto foreign key
	references org.Producto (idProducto),
	idProveedor varchar(10) constraint fk_Entrada_idProveedor foreign key
	references org.Proveedor (idProveedor),
	usuario varchar(50) constraint fk_Entrada_usuario foreign key
	references org.Usuario (usuario)
);

create table dts.DatosEntrada (
	idEntrada smallint constraint fk_DatosEntrada_idEntrada foreign key 
	references org.Entrada (idEntrada),
	cantidad smallint not null constraint chk_Adquisicion_cantidad check (cantidad > 0),
	fechaEntrada char(10) not null,
	observacion varchar(100) default 'Sin observaciones'
);