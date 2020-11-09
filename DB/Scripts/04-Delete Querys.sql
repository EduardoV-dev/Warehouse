use Warehouse;

-- Elimina un producto por medio de su idProducto y elimina todos los registros relacionados con el
create procedure delProducto (
	@RIF varchar(20),
	@idProducto varchar(5)
)
as
	set nocount on;
	--begin transaction;

	--begin try
		delete from iop.Entrada where idAdquisicion = (select idAdquisicion from viewEntrada 
			where (idProducto = @idProducto) and (RIF = @RIF));
		delete from iop.Adquisicion where idAdquisicion = (select idAdquisicion from viewEntrada 
			where (idProducto = @idProducto) and (RIF = @RIF));
		delete from org.OrigenProducto where idProducto = (select idProducto from viewOrigenProducto 
			where (idProducto = @idProducto) and (RIF = @RIF));
		delete from iop.Salida where idVenta = (select idVenta from viewSalida 
			where (idProducto = @idProducto) and (RIF = @RIF));
		delete from iop.Venta where idVenta = (select idVenta from viewSalida 
			where (idProducto = @idProducto) and (RIF = @RIF));
		delete from org.Producto where (idProducto = @idProducto) and (RIF = @RIF);

		--commit transaction;
	--end try
	--begin catch
		--rollback transaction;
	--end catch
go;

-- Elimina un proveedor por medio de su idProveedor y elimina todos los registros relacionados con el
create procedure delProveedor (
	@RIF varchar(20),
	@idProveedor varchar(10)
)
as
	set nocount on;
	--begin transaction;

	--begin try
		delete from org.OrigenProducto where idProveedor = (select idProveedor from viewOrigenProducto 
			where (idProveedor = @idProveedor) and (RIF = @RIF));
		delete from org.Proveedor where (idProveedor = @idProveedor) and (RIF = @RIF);

		--commit transaction;
	--end try
	--begin catch
		--rollback transaction;
	--end catch
go;

-- Elimina un usuario de la empresa junto con la persona designada a esa cuenta
create procedure delUsuario (
	@RIF varchar(20),
	@usuario varchar(50)
)
as
	set nocount on;
	--begin transaction;

	--begin try
		delete from adm.Usuario where (usuario = @usuario) and (RIF = @RIF);
		--commit transaction;
	--end try
	--begin catch
		--rollback transaction;
	--end catch
go;

-- Elimina una unidad de medida y establece la unidad como medida alternativa
-- en caso de haber sido borrada una medida en uno
create procedure delMedida (
	@medida varchar(60)
)
as 
	set nocount on;
	begin transaction;

	declare @idMedida tinyint;
	set @idMedida = (select idMedida from org.Medida where medida = @medida);
	update org.Producto set idMedida = 1 where idMedida = @idMedida;
	
	if (@@ROWCOUNT > 0) 
	begin
		delete from org.Medida where idMedida = @idMedida;
		--commit transaction;
	end
go;

-- Elimina un estado y establece Nuevo como estado alternativo
-- en caso de haber sido borrada una medida en uno
create procedure delEstado (
	@estado char(20)
)
as 
	set nocount on;
	--begin transaction;

	declare @idEstado tinyint;
	set @idEstado = (select idEstado from org.Estado where estado = @estado);
	update org.Producto set idEstado = 1 where idEstado = @idEstado;
	
	if (@@ROWCOUNT > 0) 
	begin
		delete from org.Estado where @idEstado = @idEstado;
		--commit transaction;
	end
go;