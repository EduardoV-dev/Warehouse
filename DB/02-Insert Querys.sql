use Warehouse;

create procedure insEmpresa (
	@RIF varchar(20),
	@nombre varchar(50),
	@correo varchar(50), 
	@direccion varchar(100),
	@telefono varchar(20),
	@departamento char(30)
)
as
	set nocount on;
	begin transaction;
	declare @idDepartamento tinyint = 0;
	select @idDepartamento = (select idDepartamento from adm.Departamento where departamento = @departamento);
	if (@idDepartamento != 0)
	begin	
		if not exists(select RIF from adm.Empresa where RIF = @RIF)
		begin
			insert into adm.Empresa (RIF, nombre, correo, direccion, telefono, idDepartamento) 
			values (@RIF, @nombre, @correo, @direccion, @telefono, @idDepartamento);

			print 'Empresa Agregada Exitosamente';
			commit transaction;
		end
		else 
		begin
			print 'Existe una empresa con el código ingresado';
			rollback transaction;
		end
	end
go;

create procedure insCuenta (
	@usuario varchar(50),
	@contrasena varchar(50),
	@RIF varchar(20)
)
as
	set nocount on;
	begin transaction;

	if not exists(select usuario from adm.Cuenta where usuario = @usuario)
	begin
		insert into adm.Cuenta (usuario, contrasena, RIF) 
			values (@usuario, @contrasena, @RIF);
		print 'Cuenta Creada';
		commit transaction;
	end
	else 
	begin
		print 'Ya existe una cuenta con ese usuario';
		rollback transaction;
	end
go;

create procedure insPersonal (
	@cedula varchar(20),
	@nombres varchar(35),
	@apellidos varchar(35),
	@correo varchar(50),
	@direccion varchar(100) = 'No especificado',
	@telefono varchar(20) = 'No especificado',
	@departamento char(30)
)
as
	set nocount on;
	begin transaction;

	declare @idDepartamento tinyint = 0;
	select @idDepartamento = (select idDepartamento from adm.Departamento where departamento = @departamento);
	if (@idDepartamento != 0)
	begin
		if not exists(select cedula from adm.Personal where cedula = @cedula)
		begin
			insert into adm.Personal (cedula, nombres, apellidos, correo, direccion, telefono, idDepartamento)
				values (@cedula, @nombres, @apellidos, @correo, @direccion, @telefono, @idDepartamento);

			print 'Personal agregado';
			commit transaction;
		end
		else 
		begin
			print 'Personal existente';
			rollback transaction;
		end
	end
go;

create procedure insUsuario (
	@usuario varchar(50),
	@contrasena varchar(50),
	@rol char(25),
	@cedula varchar(20),
	@RIF varchar(20)
)
as
	set nocount on;
	begin transaction;

	declare @idRol tinyint = 0;
	select @idRol = (select idRol from adm.Rol where rol = @rol);
	if (@idRol != 0)
	begin
		if not exists (select usuario from adm.Usuario where usuario = @usuario)
		begin
			if exists (select cedula from adm.Personal where cedula = @cedula)
			begin
				insert into adm.Usuario (usuario, contrasena, idRol, cedula, RIF)
					values (@usuario, @contrasena, @idRol, @cedula, @RIF);

				print 'Usuario Creado';
				commit transaction;
			end
			else 
			begin
				print 'No existe alguien dentro del personal con ese número de cedula';
				rollback transaction;
			end
		end
		else 
		begin
			print 'Usuario en uso';
			rollback transaction;
		end
	end
go;

create procedure insProveedor (
	@idProveedor varchar(10),
	@nombres char(35),
	@apellidos char(35),
	@correo varchar(50),
	@telefono varchar(20) = 'No especificado',
	@RIF varchar(20)
)
as 
	set nocount on;
	begin transaction;

	if not exists (select idProveedor from org.Proveedor where idProveedor = @idProveedor)
	begin
		insert into org.Proveedor (idProveedor, nombres, apellidos, correo, telefono, RIF)
			values (@idProveedor, @nombres, @apellidos, @correo, @telefono, @RIF);

		print 'Proveedor agregado';
		commit transaction;
	end
	else 
	begin
		print 'Proveedor existente';
		rollback transaction;
	end
go;

create procedure insMedida (
	@medida varchar(60)
)
as
	set nocount on;
	begin transaction;

	if not exists (select idMedida from org.Medida where medida = @medida)
	begin
		insert into org.Medida values (@medida);
		print 'Medida creada';
		commit transaction;
	end
	else 
	begin
		print 'Medida existente';
		rollback transaction;
	end
go;

create procedure insProducto (
	@idProducto varchar(5),
	@nombre varchar(50),
	@marca varchar(40),
	@cantidad smallint, 
	@medida varchar(60),
	@estado char(20),
	@RIF varchar(20)
)
as
	set nocount on;
	begin transaction;

	declare @idMedida tinyint = 0, @idEstado tinyint = 0;
	select @idMedida = (select idMedida from org.Medida where medida = @medida);
	select @idEstado = (select idEstado from org.Estado where estado = @estado);

	if not exists(select idProducto from org.Producto where idProducto = @idProducto)
	begin
		insert into org.Producto (idProducto, nombre, marca, cantidad, idMedida, idEstado, RIF)
			values (@idProducto, @nombre, @marca, @cantidad, @idMedida, @idEstado, @RIF);

		print 'Producto creado e ingresado';
		commit transaction;
	end
	else 
	begin
		declare @totalProducto smallint, @actualProducto smallint;
		select @actualProducto = (select cantidad from org.Producto where idProducto = @idProducto);
		set @totalProducto = @actualProducto + @cantidad;
		
		update org.Producto set cantidad = @actualProducto where idProducto = @idProducto;

		print 'Producto anexado';
		commit transaction;
	end
go;

create procedure insSalida (
	@idProducto varchar(5),
	@cantidad smallint,
	@observaciones varchar(200) = 'Sin observaciones',
	@RIF varchar(20)
)
as 
	set nocount on;
	begin transaction;

	declare @idVenta smallint = 0, @existenciasProducto smallint = 0, @restanteProducto smallint = 0;
	if exists (select idProducto from org.Producto where idProducto = @idProducto)
	begin
		select @existenciasProducto = (select cantidad from org.Producto where idProducto = @idProducto);
		set @restanteProducto = @existenciasProducto - @cantidad;
		if (@restanteProducto > 0)
		begin
			insert into iop.Venta (cantidad, fechaVenta, observaciones, RIF) 
				values (@cantidad, convert(char(10), getDate(), 103), @observaciones, @RIF);
			set @idVenta = @@identity;

			update org.Producto set cantidad = @restanteProducto where (idProducto = @idProducto and RIF = @RIF);
			insert into iop.Salida (idVenta, idProducto, RIF) 
				values(@idVenta, @idProducto, @RIF);

			print 'Venta realizada';
			commit transaction;
		end
		else 
		begin
			print 'No se puede sacar mas de lo que hay';
			rollback transaction;
		end
	end
	else
	begin
		print 'No existe un producto con ese código';
		rollback transaction;
	end
go;

create procedure insEntrada (
	@idProducto varchar(5),
	@idProveedor varchar(10),
	@cantidad smallint,
	@observaciones varchar(200) = 'Sin observaciones',
	@RIF varchar(20)
)
as 
	set nocount on;
	begin transaction;

	declare @idAdquisicion smallint = 0, @existenciasProducto smallint = 0, @nuevaCantidad smallint = 0;
	if exists (select idProducto from org.Producto where idProducto = @idProducto)
	begin
		select @existenciasProducto = (select cantidad from org.Producto where idProducto = @idProducto);
		set @nuevaCantidad = @existenciasProducto + @cantidad;

		insert into iop.Adquisicion(cantidad, fechaEntrega, observaciones, RIF) 

			values (@cantidad, convert(char(10), getDate(), 103), @observaciones, @RIF);
		set @idAdquisicion = @@identity;

		update org.Producto set cantidad = @nuevaCantidad where (idProducto = @idProducto and RIF = @RIF);
		insert into iop.Entrada(idAdquisicion, idProducto, idProveedor, RIF) 
			values(@idAdquisicion, @idProducto, @idProveedor, @RIF);

		print 'Entrada realizada';
		commit transaction;
	end
	else
	begin
		print 'No existe un producto con ese código';
		rollback transaction;
	end
go;