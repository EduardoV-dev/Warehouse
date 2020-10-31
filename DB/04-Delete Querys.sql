use Warehouse;

-- Elimina un producto por medio de su idProducto y elimina todos los registros relacionados con el
create procedure delProducto (
	@RIF varchar(20),
	@idProducto varchar(5)
)
as
	set nocount on;
	begin transaction;

	delete from iop.Entrada where (idProducto = @idProducto) and (RIF = @RIF);

	if (@@ROWCOUNT > 0)
	begin
		delete iop.Salida where (idProducto = @idProducto) and (RIF = @RIF);

		if (@@ROWCOUNT > 0)
		begin
			delete org.Producto where (idProducto = @idProducto) and (RIF = @RIF);
			
			if (@@ROWCOUNT > 0)
			begin
				delete org.OrigenProducto where (idProducto = @idProducto) and RIF = @RIF;

				if (@@ROWCOUNT > 0)
				begin
					delete org.Producto where (idProducto = @idProducto) and (RIF = @RIF);
					commit transaction;
				end
				else
				begin
					rollback transaction;
				end
			end
			else 
			begin 
				rollback transaction;
			end
		end
		else
		begin
			rollback transaction;
		end
	end
	else 
	begin
		print 'No se encontró el producto';
		rollback transaction;
	end
go;

-- Elimina un proveedor por medio de su idProveedor y elimina todos los registros relacionados con el
create procedure delProveedor (
	@RIF varchar(20),
	@idProveedor varchar(10)
)
as
	set nocount on;
	begin transaction;

	delete from iop.Entrada where (idProveedor = @idProveedor) and (RIF = @RIF);

	if (@@ROWCOUNT > 0)
	begin
		delete org.OrigenProducto where (idProveedor = @idProveedor) and (RIF = @RIF);

		if (@@ROWCOUNT > 0)
		begin
			delete org.Proveedor where (idProveedor = @idProveedor) and (RIF = @RIF);
			commit transaction;
		end
		else
		begin
			rollback transaction;
		end
	end
	else 
	begin
		print 'No se encontró el producto';
		rollback transaction;
	end
go;

-- Elimina un usuario de la empresa junto con la persona designada a esa cuenta
create procedure delUsuario (
	@RIF varchar(20),
	@usuario varchar(50)
)
as
	set nocount on;
	begin transaction;

	declare @cedula varchar(20);
	delete from adm.Usuario where (usuario = @usuario) and (RIF = @RIF);

	if (@@ROWCOUNT > 0)
	begin
		select @cedula = (select cedula from adm.Personal where (cedula = @cedula));
		delete adm.Personal where (cedula = @cedula);
		commit transaction;
	end
	else 
	begin
		print 'No se encontró el producto';
		rollback transaction;
	end
go;

-- Elimina una unidad de medida y establece la unidad como medida alternativa
-- en caso de haber sido borrada una medida en uno
create procedure delMedida (
	@idMedida tinyint
)
as 
	set nocount on;
	begin transaction;

	update org.Producto set idMedida = 1 where idMedida = @idMedida;
	
	if (@@ROWCOUNT > 0) 
	begin
		delete from org.Medida where idMedida = @idMedida;
		commit transaction;
	end
	else
	begin
		rollback transaction;
	end
go;