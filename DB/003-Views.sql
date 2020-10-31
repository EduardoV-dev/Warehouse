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
	select U.usuario, P.cedula, P.nombres, P.apellidos, P.correo, P.direccion, D.departamento, E.RIF from adm.Empresa as E
		inner join adm.Usuario as U
		on E.RIF = U.RIF
		inner join adm.Personal as P
		on U.cedula = P.cedula
		inner join adm.Departamento as D
		on P.idDepartamento = D.idDepartamento;
go;