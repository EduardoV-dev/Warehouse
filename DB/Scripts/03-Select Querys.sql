use Warehouse;

create procedure selInfoEmpresa	(
	@RIF varchar(20)
)
as
	select * from viewInfoEmpresa where RIF = @RIF;
go;

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
	select count(idProducto) as TotalProductos from org.Producto where RIF = @RIF;
go;

-- Obtener el número de usuarios registrados en una empresa
create procedure selTotalUsuarios (
	@RIF varchar(20)
)
as
	select count(usuario) as TotalUsuarios from adm.Usuario where RIF = @RIF;
go;

-- Si se le pasa el parametro 0, Obtiene el top 5 de los últimos productos vendidos
-- Si se le pasa el parametro 1, Obtiene el top 5 de los productos más vendidos
create procedure selTopProductos (
	@RIF varchar(20),
	@opc tinyint
)
as
	set nocount on;

	declare @top5 table (Nombre varchar(50), Ventas smallint default 0);
	if (@opc = 0)
	begin
		insert into @top5 (Nombre)
			(select nombre from viewTopProductos where RIF = @RIF group by nombre);

		select top 5 * from @top5 order by nombre desc;
	end
	if (@opc = 1)
	begin
		insert into @top5 (Nombre, Ventas) 
			(select nombre, sum(cantidad) from viewTopProductos where RIF = @RIF group by nombre);

		select top 5 * from @top5 order by Ventas desc;
	end;
go;

-- Opc = 0, Obtener el historial completo de ventas de una empresa
-- Opc = 1, Obtener el historial de ventas con respecto a una fecha
-- El orden va desde la venta mas reciente hasta la menos reciente
alter procedure selHistorialVentas (
	@RIF varchar(20),
	@fecha char(10) = '01/01/2000',
	@opc tinyint = 0
)
as
	if (@opc = 0)
	begin	
		select RIF, idProducto as IDProducto, nombre as Nombre,
			cantidad as Cantidad, fechaVenta as Fecha
			from viewHistorialVentas where RIF = @RIF order by convert(smalldatetime, fechaVenta, 103) desc;
		return;
	end
	if (@opc = 1)
	begin
		select RIF, idProducto as IDProducto, nombre as Nombre,
			cantidad as Cantidad, fechaVenta as Fecha
			from viewHistorialVentas where (RIF = @RIF) and (fechaVenta = @fecha) 
			order by convert(smalldatetime, fechaVenta, 103) desc;
		return;
	end
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
		select idProducto as IDProducto, nombre as Producto, marca as Marca, cantidad as Cantidad,
			estado as Estado, medida as Medida, proveedor as Proveedor from viewDatosProducto
			where (nombre like '%'+@nombre+'%') and (RIF = @RIF);
		return;
	end
	if (@opc = 1)
	begin 
		select idProducto as IDProducto, nombre as Producto, marca as Marca, cantidad as Cantidad,
			estado as Estado, medida as Medida, proveedor as Proveedor from viewDatosProducto
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
		select idProveedor as IDProveedor, nombres as Nombres, apellidos as Apellidos,
			correo as Correo, telefono as Telefono from org.Proveedor 
			where ((nombres + ' ' + apellidos) like '%'+@nombre+'%') and (RIF = @RIF);
	end
	if (@opc = 1)
	begin
		select idProveedor as IDProveedor, nombres as Nombres, apellidos as Apellidos,
			correo as Correo, telefono as Telefono from org.Proveedor 
			where RIF = @RIF;
	end
	if (@opc = 2)
	begin
		select (nombres + ' ' + apellidos) as nombre from org.Proveedor
			where RIF = @RIF;
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
		select usuario as Usuario, rol as Rol from viewDatosUsuario 
			where (usuario = @usuario) and (RIF = @RIF);
	end
	if (@opc = 1)
	begin
		select usuario as Usuario, rol as Rol from viewDatosUsuario 
			where RIF = @RIF;
	end
go;

-- Devuelve todos los departamentos existentes
create procedure selDepartamentos 
as
	select departamento from adm.Departamento;
go;

-- Obtiene la información necesaria para login
create procedure selInfoLogin (
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

-- Obtiene las ventas realizadas en los ultimos 7 dias
create procedure selVentasSiete (
	@RIF varchar(20)
)
as
	select top 7 fechaVenta, SUM(cantidad) as Ventas from iop.Venta where RIF = @RIF group by fechaVenta order by fechaVenta desc;
go;

-- Muestra las unidades de medidas registradas
create procedure selMedidaProducto
as
	select medida from org.Medida;
go;

create procedure selEstadoProducto 
as
	select estado from org.Estado;
go;