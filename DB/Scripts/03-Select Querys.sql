use Warehouse;

-- Obtener el número de ventas que ha realizado una empresa
create procedure selTotalVentas (
	@RIF varchar(20)
)
as
	select count(idVenta) as VentasTotales from iop.Venta where (RIF = @RIF);
go;

-- Obtener el número de productos que vende una empresa
create procedure selTotalProductos (
	@RIF varchar(20)
)
as
	select count(idProducto) from org.Producto where RIF = @RIF;
go;

-- Obtener el número de usuarios registrados en una empresa
create procedure selTotalUsuarios (
	@RIF varchar(20)
)
as
	select count(usuario) from adm.Usuario where RIF = @RIF;
go;

-- Si se le pasa el parametro 0, Obtiene el top 5 de los últimos productos vendidos
-- Si se le pasa el parametro 1, Obtiene el top 5 de los productos más vendidos
create procedure selTopProductos (
	@RIF varchar(20),
	@opc tinyint
)
as
	set nocount on;

	declare @top5 table (nombre varchar(50), cantidadVendida smallint default 0);
	--if (@opc = 0)
	begin
		insert into @top5 (nombre)
			(select nombre from viewTopProductos where RIF = @RIF group by nombre);

		select top 5 * from @top5 order by nombre desc;
	end
	--if (@opc = 1)
	begin
		insert into @top5 (nombre, cantidadVendida) 
			(select nombre, sum(cantidad) from viewTopProductos where RIF = @RIF group by nombre);

		select top 5 * from @top5 order by cantidadVendida desc;
	end;
go;

-- Obtener el historial completo de ventas de una empresa
create procedure selHistorialVentas (
	@RIF varchar(20)
)
as
	select * from viewHistorialVentas where RIF = @RIF;
go;

-- opcion 0 para buscar productos por su nombre
-- opcion 1 para obtener todos los productos de una empresa
create procedure selInfoProducto (
	@RIF varchar(20),
	@nombre varchar(50) = 'Ninguno',
	@opc tinyint = 1
)
as
	if (@opc = 0)
	begin
		select idProducto, nombre, marca, cantidad, estado, medida, proveedor from viewDatosProducto
			where (nombre like '%'+@nombre+'%') and (RIF = @RIF);
		return;
	end
	if (@opc = 1)
	begin 
		select idProducto, nombre, marca, cantidad, estado, medida, proveedor from viewDatosProducto
			where RIF = @RIF;
		return;
	end
go;

-- opcion 0 para buscar proveedores por su nombre
-- opcion 1 para mostrar todos los proveedores de una empresa
-- opcion 2 para mostrar el nombre completo de los proveedores
create procedure selInfoProveedor (
	@RIF varchar(20),
	@nombre char(70) = 'Ninguno',
	@opc tinyint = 1
)
as
	if (@opc = 0)
	begin
		select idProveedor, nombres, apellidos, correo, telefono from org.Proveedor 
			where ((nombres + ' ' + apellidos) like '%'+@nombre+'%') and (RIF = @RIF);
	end
	if (@opc = 1)
	begin
		select idProveedor, nombres, apellidos, correo, telefono from org.Proveedor where RIF = @RIF;
	end
	if (@opc = 2)
	begin
		select (nombres + ' ' + apellidos) as nombre from org.Proveedor where RIF = @RIF;
	end
go;

-- opcion 0 para buscar usuarios por nombre de usuario
-- opcion 1 para mostrar todos los usuarios de una empresa
create procedure selInfoUsuario (
	@RIF varchar(20),
	@usuario varchar(50) = 'Ninguno',
	@opc tinyint = 1
)
as
	if (@opc = 0)
	begin
		select usuario, rol from viewDatosUsuario where (usuario = @usuario) and (RIF = @RIF);
		return;
	end
	if (@opc = 1)
	begin
		select usuario, rol from viewDatosUsuario where RIF = @RIF;
		return;
	end
go;

-- Obtiene la información necesaria para login
alter procedure selInfoLogin (
	@empresa varchar(50),
	@usuario varchar(50),
	@contrasena varchar(50)
)
as
	declare @RIF varchar(20);
	set @RIF = (select RIF from adm.Empresa where nombre = @empresa);
	if (nullif(@RIF, '') is not null)
	begin
		select usuario, contrasena, RIF from viewLoginUsuario 
			where ((usuario = @usuario and contrasena = @contrasena) and (RIF = @RIF));
	end
go;

exec selInfoLogin 'ABC', 'usuarioGen', '1234'

-- Muestra las unidades de medidas registradas
create procedure selUnidadesMedida 
as
	select * from org.Medida;
go;

create procedure selEstadoProducto 
as
	select * from org.Estado;
go;