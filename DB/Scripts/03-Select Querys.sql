use Warehouse;

-- Obtener el número de ventas que ha realizado una empresa
-- METODO FUNCIONAL
create procedure selTotalVentas
as
	select count(idVenta) as [Ventas totales] from org.Salida;
go;

-- Obtener el número de productos que vende una empresa
-- METODO FUNCIONAL
create procedure selTotalProductos
as
	select count(idProducto) as [Productos registrados] from org.Producto;
go;

-- Obtener el número de usuarios registrados en una empresa
-- METODO FUNCIONAL
create procedure selTotalUsuarios 
as
	select count(usuario) as [Usuarios registrados] from org.Usuario;
go;

-- Si se le pasa el parametro 0, Obtiene el top 5 de los últimos productos vendidos
-- Si se le pasa el parametro 1, Obtiene el top 5 de los productos más vendidos
-- METODO FUNCIONAL
create procedure selTopProductos (
	@opc tinyint
)
as
	if (@opc = 0)
	begin
		select top 5 nombre as Producto from viewTopProductos order by idSalida desc;
	end
	if (@opc = 1)
	begin
		select top 5 nombre as Producto, sum(cantidad) as Ventas from viewTopProductos group by nombre order by Ventas desc;
	end;
go;

-- Opc = 0, Obtener el historial completo de ventas de una empresa
-- Opc = 1, Obtener el historial de ventas con respecto a una fecha
-- El orden va desde la venta mas reciente hasta la menos reciente
-- Si las ventas tienen la misma fecha, se ordena por el nombre
-- METODO FUNCIONAL
create procedure selHistorialVentas (
	@fecha char(10) = '01/01/2000',
	@opc tinyint = 0
)
as
	if (@opc = 0)
	begin	
		select idProducto as IDProducto, nombre as Producto,
			cantidad as Cantidad, fechaSalida as Fecha, usuario as Gestor
			from viewHistorialVentas order by convert(smalldatetime, fechaSalida, 103) desc, nombre;
	end
	if (@opc = 1)
	begin
		select idProducto as IDProducto, nombre as Producto,
			cantidad as Cantidad, fechaSalida as Fecha, usuario as Gestor
			from viewHistorialVentas where fechaSalida = @fecha
			order by convert(smalldatetime, fechaSalida, 103) desc, nombre;
	end
go;

-- opcion 0 para buscar productos por su nombre
-- opcion 1 para obtener todos los productos de una empresa
-- METODO FUNCIONAL
create procedure selInfoProducto (
	@nombre varchar(50) = 'Ninguno',
	@opc tinyint = 1
)
as
	if (@opc = 0)
	begin
		select idProducto as IDProducto, nombre as Producto, marca as Marca, cantidad as Cantidad,
			estado as Estado, medida as Medida from viewDatosProducto
			where nombre like '%'+@nombre+'%';
	end
	if (@opc = 1)
	begin 
		select idProducto as IDProducto, nombre as Producto, marca as Marca, cantidad as Cantidad,
			estado as Estado, medida as Medida from viewDatosProducto;
	end
go;

-- opcion 0 para buscar proveedores por su nombre
-- opcion 1 para mostrar todos los proveedores de una empresa
-- opcion 2 para mostrar el nombre completo de los proveedores
-- METODO FUNCIONAL
create procedure selInfoProveedor (
	@nombre varchar(70) = 'Ninguno',
	@opc tinyint = 1
)
as
	if (@opc = 0)
	begin
		select idProveedor as IDProveedor, nombres as Nombres, apellidos as Apellidos,
			correo as Correo, telefono as Telefono, usuario as Gestor from viewDatosProveedor 
			where CONCAT(RTRIM(nombres), ' ', apellidos) like '%'+@nombre+'%'
			order by nombres;
	end
	if (@opc = 1)
	begin
		select idProveedor as IDProveedor, nombres as Nombres, apellidos as Apellidos,
			correo as Correo, telefono as Telefono, usuario as Gestor from viewDatosProveedor
			order by nombres;
	end
go;

-- Devuelve el nombre completo de los proveedores de una empresa
-- METODO FUNCIONAL
create procedure selProveedores 
as
	select CONCAT(RTRIM(nombres), ' ', apellidos) as Nombre from dts.DatosProveedor order by nombre;
go;

-- opcion 0 para buscar usuarios por nombre de usuario
-- opcion 1 para mostrar todos los usuarios de una empresa
-- METODO FUNCIONAL
create procedure selInfoUsuario (
	@usuario varchar(50) = 'Ninguno',
	@opc tinyint = 1
)
as
	if (@opc = 0)
	begin
		select usuario as Usuario, rol as Rol from viewDatosUsuario 
			where usuario = @usuario;
	end
	if (@opc = 1)
	begin
		select usuario as Usuario, rol as Rol from viewDatosUsuario;
	end
go;

-- Obtiene la información necesaria para login
-- METODO FUNCIONAL
create procedure selInfoLogin (
	@usuario varchar(50),
	@contrasena varchar(50)
)
as
	select usuario, contrasena from org.Usuario 
		where (usuario = @usuario) and (contrasena = @contrasena);
go;

-- Obtiene las ventas realizadas en los ultimos 7 dias
-- METODO FUNCIONAL
create procedure selVentasSiete 
as
	select top 7 fechaSalida as Fecha, SUM(cantidad) as Ventas from dts.DatosSalida group by fechaSalida order by fechaSalida desc;
go;

-- Muestra las unidades de medidas registradas en una empresa
-- METODO FUNCIONAL
create procedure selMedidaProducto 
as
	select medida from org.Medida order by medida;
go;

-- Muestra los estados de un producto registradas en una empresa
-- METODO FUNCIONAL
create procedure selEstadoProducto 
as
	select estado from org.Estado order by estado;
go;

-- Opc = 0, verifica si ya existe un idProducto para Producto
-- Opc = 1, verifica si ya existe un idProveedor para Proveedor
-- METODO FUNCIONAL
create procedure selVerificarCodigo (
	@ID varchar(10),
	@opc tinyint
)
as
	if (@opc = 0)
	begin
		select idProducto from org.Producto where (idProducto = @ID);
	end
	if (@opc = 1)
	begin
		select idProveedor from org.Proveedor where (idProveedor = @ID);
	end
go;
