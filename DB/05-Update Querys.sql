use Warehouse;

-- Actualizar datos de un producto
create procedure updActualizarProducto (
	@RIF varchar(20),
	@idProducto varchar(5),
	@nombre varchar(50),
	@marca varchar(40),
	@estado char(20),
	@medida varchar(60)
)
as
	set nocount on;

	declare @idEstado tinyint, @idMedida tinyint;
	select @idEstado = (select idEstado from org.Estado where estado = @estado);
	select @idMedida = (select idMedida from org.Medida where medida = @medida);

	update org.Producto set nombre = @nombre, marca = @marca, idEstado = @idEstado, idMedida = @idMedida 
		where (idProducto = @idProducto) and (RIF = @RIF);
go;

-- Actualizar datos de un proveedor
create procedure updActualizarProveedor (
	@RIF varchar(20),
	@idProveedor varchar(10),
	@nombres char(35),
	@apellidos char(35),
	@correo varchar(50),
	@telefono varchar(20)
)
as
	set nocount on;

	update org.Proveedor set nombres = @nombres, apellidos = @apellidos, correo = @correo, telefono = @telefono
		where (idProveedor = @idProveedor) and (RIF = @RIF);
go;

-- Actualizar datos de usuario
create procedure updActualizarUsuario (
	@cedula varchar(20),
	@nombres char(35),
	@apellidos char(35),
	@direccion varchar(100),
	@telefono varchar(20),
	@departamento char(30)
)
as
	set nocount on;

	declare @idDepartamento char(3);
	select @idDepartamento = (select idDepartamento from adm.Departamento where departamento = @departamento);

	update adm.Personal set nombres = @nombres, apellidos = @apellidos, direccion = @direccion, 
		   telefono = @telefono, idDepartamento = @idDepartamento where (cedula = @cedula);
go;

-- Actualizar datos de la empresa
create procedure updActualizarEmpresa (
	@RIF varchar(20),
	@nombre varchar(50),
	@correo varchar(50),
	@direccion varchar(100),
	@telefono varchar(20),
	@departamento char(30)
)
as
	set nocount on;

	declare @idDepartamento char(3);
	select @idDepartamento = (select idDepartamento from adm.Departamento where departamento = @departamento);

	update adm.Empresa set nombre = @nombre, correo = @correo, direccion = @direccion, telefono = @telefono,
		idDepartamento = @departamento where RIF = @RIF;
go;