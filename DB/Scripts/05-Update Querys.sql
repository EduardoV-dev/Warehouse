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
	set @idEstado = (select idEstado from org.Estado where estado = @estado);
	set @idMedida = (select idMedida from org.Medida where medida = @medida);

	update org.Producto set nombre = @nombre, marca = @marca, idEstado = @idEstado, idMedida = @idMedida 
		where (idProducto = @idProducto) and (RIF = @RIF);
go;

-- Actualizar datos de un proveedor
create procedure updActualizarProveedor (
	@RIF varchar(20),
	@idProveedor varchar(10),
	@correo varchar(50),
	@telefono varchar(20)
)
as
	set nocount on;

	update org.Proveedor set correo = @correo, telefono = @telefono
		where (idProveedor = @idProveedor) and (RIF = @RIF);
go;

-- Actualizar datos de usuario
create procedure updActualizarUsuario (
	@usuario varchar(50),
	@rol char(25),
	@RIF varchar(20)
)
as
	set nocount on;

	declare @idRol tinyint;
	set @idRol = (select idRol from adm.Rol where rol = @rol);

	update adm.Usuario set idRol = @idRol where 
		(usuario = @usuario) and (RIF = @RIF);
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
	set @idDepartamento = (select idDepartamento from adm.Departamento where departamento = @departamento);

	update adm.Empresa set nombre = @nombre, correo = @correo, direccion = @direccion, telefono = @telefono,
		idDepartamento = @departamento where RIF = @RIF;
go;