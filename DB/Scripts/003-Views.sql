use Warehouse;

-- Vista para obtener el top 5 de los últimos productos vendidos y productos más vendidos
-- VISTA FUNCIONAL
create view viewTopProductos 
as
	select S.idSalida, DP.nombre , DS.cantidad from org.Salida as S
		inner join dts.DatosSalida as DS
		on S.idSalida = DS.idSalida
		inner join dts.DatosProducto as DP
		on DP.idProducto = S.idProducto;
go;

-- Vista para obtener el historial completo de ventas de una empresa
-- VISTA FUNCIONAL
create view viewHistorialVentas
as
	select DP.idProducto, DP.nombre, DS.cantidad, DS.fechaSalida, S.usuario from org.Salida as S
		inner join dts.DatosSalida as DS
		on S.idSalida = DS.idSalida
		inner join dts.DatosProducto as DP
		on DP.idProducto = S.idProducto;
go;

-- Vista para datos sobre un producto
-- VISTA FUNCIONAL
create view viewDatosProducto
as
	select Pd.idProducto, DP.nombre, DP.marca, DP.cantidad, E.estado, M.medida from org.Producto as Pd
		inner join dts.DatosProducto as DP
		on DP.idProducto = Pd.idProducto
		inner join org.Estado as E
		on Pd.idEstado = E.idEstado
		inner join org.Medida as M
		on Pd.idMedida = M.idMedida;
go;

-- Vista para ver los datos de los proveedores
-- VISTA FUNCIONAL
create view viewDatosProveedor 
as
	select P.idProveedor, DP.nombres, DP.apellidos, DP.correo, DP.telefono, P.usuario from org.Proveedor as P
		inner join dts.DatosProveedor as DP
		on P.idProveedor = DP.idProveedor;
go;

-- Vista para datos sobre un usuario
-- VISTA FUNCIONAL
create view viewDatosUsuario 
as
	select U.usuario, R.rol from org.Usuario as U
		inner join org.Rol as R
		on U.idRol = R.idRol;
go;