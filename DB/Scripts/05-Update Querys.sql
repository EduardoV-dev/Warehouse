use Warehouse;

-- Actualizar datos de un producto
create procedure updActualizarProducto (
	@idProducto varchar(5),
	@nombre varchar(50),
	@marca varchar(40),
	@estado char(20),
	@medida varchar(60)
)
as
	begin transaction;

	declare @idEstado tinyint,
			@idMedida tinyint;

	set @idEstado = (select idEstado from org.Estado where estado = @estado);
	set @idMedida = (select idMedida from org.Medida where medida = @medida);

	if exists (select idProducto from org.Producto where idProducto = @idProducto)
	begin
		update org.Producto set idEstado = @idEstado, idMedida = @idMedida 
			where (idProducto = @idProducto);

		update dts.DatosProducto set nombre = @nombre, marca = @marca where idProducto = @idProducto;

		print 'Producto actualizado';
		commit transaction;
	end
	else 
	begin
		print 'El producto no existe';
		rollback transaction;
	end
go;

-- Actualizar datos de un proveedor
create procedure updActualizarProveedor (
	@idProveedor varchar(10),
	@nombres char(35),
	@apellidos char(35),
	@correo varchar(50),
	@telefono varchar(20)
)
as
	begin transaction;

	declare @existeProveedor tinyint = dbo.existeProveedor(@idProveedor);

	if (@existeProveedor = 1)
	begin
		update dts.DatosProveedor set nombres = @nombres, apellidos = @apellidos, correo = @correo, telefono = @telefono
		where idProveedor = @idProveedor;

		print 'Proveedor actualizado';
		commit transaction;
	end
	else
	begin
		print 'El Proveedor no existe';
		rollback transaction;
	end
go;

-- Actualizar rol de usuario
create procedure updActualizarRolUsuario (
	@usuario varchar(50),
	@rol char(25)
)
as
	begin transaction;

	declare @idRol tinyint,
			@existeUsuario tinyint,
			@noEsAdmin varchar(50);
	set @idRol = (select idRol from org.Rol where rol = @rol);
	set @existeUsuario = dbo.existeUsuario(@usuario);
	set @noEsAdmin = (select usuario from org.Usuario where usuario = @usuario);

	if (@idRol != 1 and @existeUsuario = 1 and @noEsAdmin != 'admin')
	begin
		update org.Usuario set idRol = @idRol where usuario = @usuario;

		print 'Rol del usuario actualizado';
		commit transaction;
	end
	else
	begin
		print 'El rol no pudo ser actualizado';
		rollback transaction;
	end
go;

-- Actualizar contraseña de usuario
create procedure updActualizarUsuario (
	@usuario varchar(50),
	@contrasena varchar(50)
)
as
	begin transaction;

	declare @existeUsuario tinyint;
	set @existeUsuario = dbo.existeUsuario(@usuario);
	if (@existeUsuario = 1)
	begin
		update org.Usuario set contrasena = @contrasena where usuario = @usuario;

		print 'Contraseña cambiada';
		commit transaction;
	end
	else
	begin
		print 'El usuario no existe';
		rollback transaction;
	end
go;