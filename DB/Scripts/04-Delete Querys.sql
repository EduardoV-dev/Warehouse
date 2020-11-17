use Warehouse;

-- Elimina un producto por medio de su idProducto y elimina todos los registros relacionados con el
create procedure delProducto (
	@idProducto varchar(5)
)
as
	begin transaction;

	if exists (select idProducto from org.Producto where idProducto = @idProducto)
	begin
		delete a from dts.DatosSalida as a
		join org.Salida as b 
		on a.idSalida = b.idSalida where b.idProducto = @idProducto;

		delete from org.Salida where idProducto = @idProducto

		delete a from dts.DatosEntrada as a
		join org.Entrada as b 
		on a.idEntrada = b.idEntrada where b.idProducto = @idProducto;

		delete from org.Entrada where idProducto = @idProducto;

		delete from dts.DatosProducto where idProducto = @idProducto;

		delete from org.Producto where idProducto = @idProducto;

		print 'Producto y sus registros eliminados';
		commit transaction;
	end
	else 
	begin
		print 'El producto no existe';
		rollback transaction;
	end
go;

-- Elimina un proveedor por medio de su idProveedor y elimina todos los registros relacionados con el

create procedure delProveedor (
	@idProveedor varchar(10)
)
as
	begin transaction;

	if exists (select idProveedor from org.Proveedor where idProveedor = @idProveedor)
	begin
		delete a from dts.DatosEntrada as a
			join org.Entrada as b
			on a.idEntrada = b.idEntrada
			where b.idProveedor = @idProveedor;

		delete from org.Entrada where idProveedor = @idProveedor;

		delete from dts.DatosProveedor where idProveedor = @idProveedor;

		delete from org.Proveedor where idProveedor = @idProveedor;

		print 'Proveedor eliminado';
		commit transaction;
	end
	else 
	begin
		print 'No existe un proveedor con ese ID';
		rollback transaction;
	end
go;

-- Elimina un usuario de la empresa y asigna sus ventas y adquisiciones al que posee un rol de administrador
create procedure delUsuario (
	@usuario varchar(50)
)
as
	begin transaction;
	
	declare @admin varchar(50);
	declare @existeUsuario tinyint = dbo.existeUsuario(@usuario);
	declare @noEsAdmin varchar(50) = (select usuario from org.Usuario where usuario = @usuario);
	if (@existeUsuario = 1 and @noEsAdmin != 'admin')
	begin
		set @admin = (select usuario from org.Usuario where idRol = 1);

		update org.Entrada set usuario = @admin where usuario = @usuario;

		update org.Salida set usuario = @admin where usuario = @usuario;

		update org.Proveedor set usuario = @admin where usuario = @usuario;

		delete from org.Usuario where usuario = @usuario;

		print 'Usuario eliminado';
		commit transaction;
	end
	else 
	begin
		print 'El usuario no existe o se intentó eliminar al administrador';
		rollback transaction;
	end
go;

-- Elimina una unidad de medida y establece la unidad como medida alternativa
-- en caso de haber sido borrada una medida en uno
create procedure delMedida (
	@medida varchar(60)
)
as 
	begin transaction;

	declare @idMedida tinyint,
			@noEsUnidades varchar(60);

	set @idMedida = (select idMedida from org.Medida where medida = @medida);
	set @noEsUnidades = (select medida from org.Medida where idMedida = @idMedida);

	if (nullif(@idMedida, '') is not null and @noEsUnidades != 'Unidades')
	begin
		update org.Producto set idMedida = 1 where idMedida = @idMedida;

		delete from org.Medida where idMedida = @idMedida;

		print 'Medida eliminada';
		commit transaction;
	end
	else
	begin
		print 'La medida no existe o se trató de eliminar la medida de unidades';
		rollback transaction;
	end
go;

-- Elimina un estado y establece Nuevo como estado alternativo
-- en caso de haber sido borrada una medida en uno
create procedure delEstado (
	@estado char(20)
)
as 
	begin transaction;

	declare @idEstado tinyint,
			@noEsEstado varchar(20);

	set @idEstado = (select idEstado from org.Estado where estado = @estado);
	set @noEsEstado = (select estado from org.Estado where idEstado = @idEstado);

	if (nullif(@idEstado, '') is not null and @noEsEstado != 'Nuevo')
	begin
		update org.Producto set idEstado = 1 where idEstado = @idEstado;

		delete from org.Estado where @idEstado = @idEstado;

		print 'Estado eliminado';
		commit transaction;
	end
	else
	begin
		print 'El estado no existe o se trató de eliminar el estado "Nuevo"';
		rollback transaction;
	end
go;