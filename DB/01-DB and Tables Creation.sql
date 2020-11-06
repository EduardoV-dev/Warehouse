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

create schema adm;
create schema org;
create schema iop;

-- /****** Creando tablas de Warehouse *******/

create table adm.Departamento (
	idDepartamento char(3) constraint pk_Departamento_idDepartamento primary key,
	departamento char(30) not null
);

create table adm.Empresa (
	RIF varchar(20) constraint pk_Empresa_RIF primary key,
	nombre varchar(50) not null, 
	correo varchar(50) not null,
	direccion varchar(100) not null,
	telefono varchar(20) not null,
	idDepartamento char(3) constraint fk_Empresa_idDepartamento foreign key 
	references adm.Departamento (idDepartamento)
);

create table adm.Rol (
	idRol tinyint constraint pk_Rol_idRol primary key identity(1,1),
	rol char(25) not null
);

create table adm.Usuario (
	usuario varchar(50) constraint pk_Usuario_usuario primary key,
	contrasena varchar(50) not null,
	idRol tinyint constraint fk_Usuario_idRol foreign key 
	references adm.Rol (idRol),
	RIF varchar(20) constraint fk_Usuario_RIF foreign key 
	references adm.Empresa (RIF)
);

create table org.Proveedor (
	idProveedor varchar(10) constraint pk_Proveedor_idProveedor primary key,
	nombres char(35) not null,
	apellidos char(35) not null,
	correo varchar(50) not null,
	telefono varchar(20),
	RIF varchar(20) constraint fk_Proveedor_RIF foreign key 
	references adm.Empresa (RIF)
);

create table org.Medida (
	idMedida tinyint constraint pk_Medida_idMedida primary key identity(1,1),
	medida varchar(60) not null
);

create table org.Estado (
	idEstado tinyint constraint pk_Estado_idEstado primary key identity(1,1),
	estado char(20) not null
);

create table org.Producto (
	idProducto varchar(5) constraint pk_Producto_idProducto primary key,
	nombre varchar(50) not null,
	marca varchar(40) not null,
	cantidad smallint not null constraint chk_Producto_cantidad check (cantidad >= 0),
	idMedida tinyint constraint fk_Producto_idMedida foreign key 
	references org.Medida (idMedida),
	idEstado tinyint constraint fk_Producto_idEstado foreign key 
	references org.Estado (idEstado),
	RIF varchar(20) constraint fk_Producto_RIF foreign key 
	references adm.Empresa (RIF)
);

create table org.OrigenProducto (
	idProducto varchar(5) constraint fk_OrigenProducto_idProducto foreign key 
	references org.Producto (idProducto),
	idProveedor varchar(10) constraint fk_OrigenProducto_idProveedor foreign key 
	references org.Proveedor (idProveedor)
);

create table iop.Venta (
	idVenta smallint constraint pk_Venta_idVenta primary key identity(1,1),
	cantidad smallint not null constraint chk_Venta_cantidad check (cantidad >= 0),
	fechaVenta smalldatetime not null,
	observaciones varchar(200) default 'Sin observaciones',
	RIF varchar(20) constraint fk_Venta_RIF foreign key 
	references adm.Empresa (RIF)
);

create table iop.Adquisicion (
	idAdquisicion smallint constraint pk_Adquisicion_idAdquisicion primary key identity(1,1),
	cantidad smallint not null constraint chk_Adquisicion_cantidad check (cantidad >= 0),
	fechaEntrega smalldatetime not null,
	observaciones varchar(200) default 'Sin observaciones',
	RIF varchar(20) constraint fk_Adquisicion_RIF foreign key 
	references adm.Empresa (RIF)
);

create table iop.Salida (
	idVenta smallint constraint fk_Salida_idVenta foreign key 
	references iop.Venta (idVenta),
	idProducto varchar(5) constraint fk_Salida_idProducto foreign key 
	references org.Producto (idProducto),
);

create table iop.Entrada (
	idAdquisicion smallint constraint fk_Entrada_idAquisicion foreign key 
	references iop.Adquisicion (idAdquisicion),
	idProducto varchar(5) constraint fk_Entrada_idProducto foreign key 
	references org.Producto (idProducto)
);