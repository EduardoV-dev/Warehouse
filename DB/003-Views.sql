use Warehouse;

-- Vista para obtener el top 5 de los últimos productos vendidos y productos más vendidos
create view viewTopProductos 
as
	select E.RIF, P.nombre, V.cantidad from adm.Empresa as E
		inner join iop.Venta as V
		on E.RIF = V.RIF
		inner join org.Producto P
		on P.RIF = E.RIF
go;

-- Vista para obtener el historial completo de ventas de una empresa
create view viewHistorialVentas
as
	select E.RIF, P.idProducto, P.nombre, V.cantidad, V.fechaVenta from adm.Empresa as E
		inner join iop.Venta as V
		on E.RIF = V.RIF
		inner join org.Producto P
		on P.RIF = E.RIF;
go;

-- Vista para datos sobre un producto
create view viewDatosProducto
as
	select Pd.idProducto, Em.RIF, Pd.nombre, Pd.marca, Pd.cantidad, E.estado, M.medida, (Pr.nombres + ' ' + Pr.apellidos) as proveedor from org.Producto as Pd
		inner join adm.Empresa as Em
		on Pd.RIF = Em.RIF
		inner join org.Estado as E
		on Pd.idEstado = E.idEstado
		inner join org.Medida as M
		on Pd.idMedida = M.idMedida
		inner join org.OrigenProducto as Op
		on Pd.idProducto = OP.idProducto
		inner join org.Proveedor as Pr
		on Op.idProveedor = Pr.idProveedor;
go;

-- Vista para datos sobre un usuario
create view viewDatosUsuario 
as
	select E.RIF, U.usuario, R.rol from adm.Usuario as U
		inner join adm.Empresa as E
		on U.RIF = E.RIF
		inner join adm.Rol as R
		on U.idRol = R.idRol;
go;

-- Vista para datos del login del usuario
create view viewLoginUsuario
as
	select E.nombre, U.usuario, U.contrasena, E.RIF from adm.Usuario as U
		inner join adm.Empresa as E
		on U.RIF = E.RIF
go;

-- Vista creada para eliminar los registros de entrada y adquisicion de un producto en una empresa
create view viewEntrada
as
	select AD.idAdquisicion, EM.RIF, PR.idProducto from iop.Entrada as EN
		inner join iop.Adquisicion as AD
		on EN.idAdquisicion = AD.idAdquisicion
		inner join adm.Empresa as EM
		on EM.RIF = AD.RIF
		inner join org.Producto as PR
		on PR.RIF = EM.RIF;
go;

-- Vista creada para eliminar los registros de origenproducto de una empresa
create view viewOrigenProducto
as
	select EM.RIF, PR.idProducto from org.Producto as PR
		inner join adm.Empresa as EM
		on PR.RIF = EM.RIF
		inner join org.OrigenProducto as OP
		on OP.idProducto = PR.idProducto;
go;

-- vista para eliminar los registros de salida y venta de un producto en una empresa
create view viewSalida
as
	select SA.idVenta, EM.RIF, PR.idProducto from iop.Salida as SA
		inner join iop.Venta as VE
		on SA.idVenta = VE.idVenta
		inner join adm.Empresa as EM
		on EM.RIF = VE.RIF
		inner join org.Producto as PR
		on PR.RIF = EM.RIF;
go;