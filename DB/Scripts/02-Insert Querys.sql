use Warehouse;

-- Crea usuarios de la empresa
-- METODO FUNCIONAL
create procedure insUsuario (
	@usuario varchar(50),
	@contrasena varchar(50),
	@rol char(25)
)
as
	begin transaction;

	declare @idRol tinyint;
	set @idRol = (select idRol from org.Rol where rol = @rol);
	if not exists (select usuario from org.Usuario where usuario = @usuario)
	begin
		insert into org.Usuario (usuario, contrasena, idRol)
			values (@usuario, @contrasena, @idRol);

		print 'Usuario agregado';
		commit transaction;
	end
	else 
	begin
		print 'El usuario no pudo ser agregado';
		rollback transaction;
	end
go;

-- Inserta nuevos proveedores
-- METODO FUNCIONAL
create procedure insProveedor (
	@idProveedor varchar(10),
	@nombres char(35),
	@apellidos char(35),
	@correo varchar(50),
	@telefono varchar(20) = 'No especificado',
	@usuario varchar(50)
)
as 
	begin transaction;

	declare @existeProveedor tinyint,
			@existeUsuario tinyint;
	set @existeProveedor = dbo.existeProveedor(@idProveedor);
	set @existeUsuario = dbo.existeUsuario(@usuario);
	if (@existeProveedor = 0 and @existeUsuario = 1)
	begin
		insert into org.Proveedor (idProveedor, usuario)
			values (@idProveedor, @usuario);
		
		insert into dts.DatosProveedor (idProveedor, nombres, apellidos, correo, telefono)
			values (@idProveedor, @nombres, @apellidos, @correo, @telefono);

		print 'Proveedor insertado';
		commit transaction;
	end
	else 
	begin
		print 'IDProveedor en uso';
		rollback transaction;
	end
go;

-- En caso de necesitar una nueva medida
-- El usuario será capaz de crearla
-- METODO FUNCIONAL
create procedure insMedida (
	@medida varchar(60)
)
as
	begin transaction;

	if not exists (select medida from org.Medida where medida = @medida)
	begin
		insert into org.Medida (medida)
			values (@medida);

		print 'Medida insertada';
		commit transaction;
	end
	else
	begin
		print 'La medida ya existe';
		rollback transaction;
	end
go;

-- En caso de necesitar un nuevo estado
-- El usuario será capaz de crearlo
-- METODO FUNCIONAL
create procedure insEstado (
	@estado char(20)
)
as
	begin transaction;
	
	if not exists (select estado from org.Estado where estado = @estado)
	begin
		insert into org.Estado (estado)
			values (@estado);

		print 'Estado creado';
		commit transaction;
	end
	else 
	begin
		print 'El estado ya existe';
		rollback transaction;
	end
go;

-- inserta un nuevo producto
-- METODO FUNCIONAL
create procedure insProducto (
	@idProducto varchar(5),
	@nombre varchar(50),
	@marca varchar(40),
	@cantidad smallint, 
	@medida varchar(60),
	@estado char(20),
	@proveedor char(70),
	@usuario varchar(50)
)
as
	begin transaction;

	declare @idMedida tinyint, 
			@idEstado tinyint, 
			@idProveedor varchar(10),
			@idEntrada smallint;

	set @idMedida = (select idMedida from org.Medida where medida = @medida);
	set @idEstado = (select idEstado from org.Estado where estado = @estado);
	set @idProveedor = (select idProveedor from dts.DatosProveedor where CONCAT(RTRIM(nombres), ' ', apellidos) = @proveedor);

	if (nullif(@idProveedor, '') is not null AND nullif(@idEstado, '') is not null AND nullif(@idMedida, '') is not null)
	begin
		if not exists (select idProducto from org.Producto where idProducto = @idProducto)
		begin
			insert into org.Producto (idProducto, idMedida, idEstado)
				values (@idProducto, @idMedida, @idEstado);

			insert into dts.DatosProducto (idProducto, nombre, marca, cantidad)
				values (@idProducto, @nombre, @marca, @cantidad);

			insert into org.Entrada (idProducto, idProveedor, usuario)
				values (@idProducto, @idProveedor, @usuario);

			set @idEntrada = @@IDENTITY;

			insert into dts.DatosEntrada (idEntrada, cantidad, fechaEntrada, observacion)
				values (@idEntrada, @cantidad, CONVERT(char(10), getDate(), 103), 'Producto inicial');

			print 'Producto creado';
			commit transaction;
		end
		else
		begin 
			print 'El idProducto está en uso';
			rollback transaction;
		end
	end
	else 
	begin
		print 'Proveedor no existe o medida o estado incorrecto';
		rollback transaction;
	end
go;

-- realiza una venta y extrae la cantidad de producto necesaria
-- METODO FUNCIONAL
create procedure insSalida (
	@idProducto varchar(5),
	@cantidad smallint,
	@observaciones varchar(100) = 'Sin observaciones',
	@usuario varchar(50)
)
as 
	begin transaction;

	declare @existenciasProducto smallint, 
			@restanteProducto smallint,
			@idSalida smallint;

	if exists (select idProducto from org.Producto where idProducto = @idProducto)
	begin
		set @existenciasProducto = (select cantidad from dts.DatosProducto where idProducto = @idProducto);
		set @restanteProducto = @existenciasProducto - @cantidad;
		if (@restanteProducto >= 0)
		begin
			insert into org.Salida(idProducto, usuario) 
				values (@idProducto, @usuario);

			set @idSalida = @@IDENTITY;

			insert into dts.DatosSalida (idSalida, cantidad, fechaSalida, observaciones)
				values (@idSalida, @cantidad, CONVERT(char(10), GETDATE(), 103), @observaciones);

			update dts.DatosProducto set cantidad = @restanteProducto where idProducto = @idProducto;

			print 'Venta completada';
			commit transaction;
		end
		else
		begin
			print 'Cantidad insuficiente';
			rollback transaction;
		end
	end
	else 
	begin
		print 'IDProducto inexistente';
		rollback transaction;
	end
go;

-- realiza entrada de inventario para un producto
-- METODO FUNCIONAL
create procedure insEntrada (
	@idProducto varchar(5),
	@proveedor char(70),
	@cantidad smallint,
	@observaciones varchar(100) = 'Sin observaciones',
	@usuario varchar(50)
)
as 
	begin transaction;

	declare @idEntrada smallint, 
			@existenciasProducto smallint, 
			@nuevaCantidad smallint,
			@idProveedor varchar(10);

	set @idProveedor = (select idProveedor from dts.DatosProveedor where CONCAT(RTRIM(nombres), ' ', apellidos) = @proveedor);
	declare @existeUsuario tinyint = dbo.existeUsuario(@usuario);
	if (nullif(@idProveedor, '') is not null and @existeUsuario = 1)
	begin
		set @existenciasProducto = (select cantidad from dts.DatosProducto where idProducto = @idProducto);
		set @nuevaCantidad = @existenciasProducto + @cantidad;

		insert into org.Entrada(idProducto, idProveedor, usuario) 
			values (@idProducto, @idProveedor, @usuario);

		set @idEntrada = @@identity;

		insert into dts.DatosEntrada (idEntrada, cantidad, fechaEntrada, observacion)
			values (@idEntrada, @cantidad, CONVERT(char(10), GETDATE(), 103), @observaciones);

		update dts.DatosProducto set cantidad = @nuevaCantidad where idProducto = @idProducto;

		print 'Producto anexado';
		commit transaction;
	end
	else 
	begin
		print 'Proveedor o usuario inexistente';
		rollback transaction;
	end
go;