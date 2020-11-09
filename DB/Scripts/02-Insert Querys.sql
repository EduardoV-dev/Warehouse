use Warehouse;

-- Crea las empresas y su primer usuario
create procedure insEmpresaYAdmin (
	@RIF varchar(20),
	@nombre varchar(50),
	@correo varchar(50), 
	@direccion varchar(100),
	@telefono varchar(20),
	@departamento char(30),
	@usuario varchar(50),
	@contrasena varchar(50)
)
as
	set nocount on;
	--begin transaction;
	declare @idDepartamento char(3);
	set @idDepartamento = (select idDepartamento from adm.Departamento where departamento = @departamento);

	--if not exists(select RIF from adm.Empresa where RIF = @RIF)
	begin
		insert into adm.Empresa (RIF, nombre, correo, direccion, telefono, idDepartamento) 
			values (@RIF, @nombre, @correo, @direccion, @telefono, @idDepartamento);

		insert into adm.Usuario (usuario, contrasena, idRol, RIF) 
			values (@usuario, @contrasena, 1, @RIF);

		--commit transaction;
	end
	--else 
		--rollback transaction;
go;

-- Crea usuarios de la empresa
create procedure insUsuario (
	@usuario varchar(50),
	@contrasena varchar(50),
	@rol char(25),
	@RIF varchar(20)
)
as
	set nocount on;
	--begin transaction;

	declare @idRol tinyint = 0;
	select @idRol = (select idRol from adm.Rol where rol = @rol);
	--if not exists (select usuario from adm.Usuario where (usuario = @usuario) and (RIF = @RIF))
	begin
		insert into adm.Usuario (usuario, contrasena, idRol, RIF)
			values (@usuario, @contrasena, @idRol, @RIF);

		--commit transaction;
	end
	--else 
		--rollback transaction;
go;

-- Inserta nuevos proveedores
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
	--begin transaction;

	--if not exists (select idProveedor from org.Proveedor where (idProveedor = @idProveedor) and (RIF = @RIF))
	begin
		insert into org.Proveedor (idProveedor, nombres, apellidos, correo, telefono, RIF)
			values (@idProveedor, @nombres, @apellidos, @correo, @telefono, @RIF);

		--commit transaction;
	end
	--else 
		--rollback transaction;
go;

-- En caso de necesitar una nueva medida
-- El usuario será capaz de crearla
create procedure insMedida (
	@medida varchar(60)
)
as
	set nocount on;
	--begin transaction;

	--if not exists (select idMedida from org.Medida where medida = @medida)
	begin
		insert into org.Medida values (@medida);

		--commit transaction;
	end
	--else 
		--rollback transaction;
go;

-- En caso de necesitar un nuevo estado
-- El usuario será capaz de crearlo
create procedure insEstado (
	@estado char(20)
)
as
	set nocount on;
	--if not exists (select idEstado from org.Estado where estado = @estado)
	begin
		insert into org.Estado (estado)
			values (@estado);

		--commit transaction;
	end
	--else 
		--rollback transaction;
go;

-- inserta un nuevo producto
create procedure insProducto (
	@idProducto varchar(5),
	@nombre varchar(50),
	@marca varchar(40),
	@cantidad smallint, 
	@medida varchar(60),
	@estado char(20),
	@proveedor char(70),
	@RIF varchar(20)
)
as
	set nocount on;
	--begin transaction;

	declare @idMedida tinyint, @idEstado tinyint, @idProveedor varchar(10), @idAdquisicion smallint;
	set @idMedida = (select idMedida from org.Medida where medida = @medida);
	set @idEstado = (select idEstado from org.Estado where estado = @estado);
	set @idProveedor = (select idProveedor from org.Proveedor where (((nombres+' '+apellidos) = @proveedor) and (RIF = @RIF)));

	--if not exists(select idProducto from org.Producto where ((idProducto = @idProducto) and (RIF = @RIF)))
	begin
		insert into org.Producto (idProducto, nombre, marca, cantidad, idMedida, idEstado, RIF)
			values (@idProducto, @nombre, @marca, @cantidad, @idMedida, @idEstado, @RIF);
		
		insert into org.OrigenProducto (idProducto, idProveedor) 
			values (@idProducto, @idProveedor);

		insert into iop.Adquisicion (cantidad, fechaEntrega, observaciones, RIF)
			values (@cantidad, convert(char(10), getDate(), 103), 'Producto inicial', @RIF);

		set @idAdquisicion = @@identity;

		insert into iop.Entrada (idAdquisicion, idProducto)
			values (@idAdquisicion, @idProducto);

		--commit transaction;
	end
	--else 
		--rollback transaction;
go;

-- realiza una venta y extrae la cantidad de producto necesaria
create procedure insSalida (
	@producto varchar(50),
	@cantidad smallint,
	@observaciones varchar(200) = 'Sin observaciones',
	@RIF varchar(20)
)
as 
	set nocount on;
	--begin transaction;

	declare @idVenta smallint, @existenciasProducto smallint, @restanteProducto smallint;
	declare @idProducto varchar(5);

	set @idProducto = (select idProducto from org.Producto where ((nombre = @producto) and (RIF = @RIF)));

	if (nullif(@idProducto, '') is not null)
	begin
		set @existenciasProducto = (select cantidad from org.Producto where ((idProducto = @idProducto) and (RIF = @RIF)));
		set @restanteProducto = @existenciasProducto - @cantidad;
		if (@restanteProducto > 0)
		begin
			insert into iop.Venta (cantidad, fechaVenta, observaciones, RIF) 
				values (@cantidad, convert(char(10), getDate(), 103), @observaciones, @RIF);

			set @idVenta = @@identity;

			update org.Producto set cantidad = @restanteProducto where ((idProducto = @idProducto) and (RIF = @RIF));

			insert into iop.Salida (idVenta, idProducto) 
				values(@idVenta, @idProducto);

			--commit transaction;
		end
	end
	--else
		--rollback transaction;
go;

-- realiza entrada de inventario para un producto
create procedure insEntrada (
	@producto varchar(50),
	@proveedor char(70),
	@cantidad smallint,
	@observaciones varchar(200) = 'Sin observaciones',
	@RIF varchar(20)
)
as 
	set nocount on;
	--begin transaction;

	declare @idAdquisicion smallint, @existenciasProducto smallint, @nuevaCantidad smallint;
	declare @idProducto varchar(5), @idProveedor varchar(10);

	set @idProveedor = (select idProveedor from org.Proveedor where (((nombres+' '+apellidos) = @proveedor) and (RIF = @RIF)));
	set @idProducto = (select idProducto from org.Producto where ((nombre = @producto) and (RIF = @RIF)));

	if (nullif(@idProducto, '') is not null)
	begin
		select @existenciasProducto = (select cantidad from org.Producto where ((idProducto = @idProducto) and (RIF = @RIF)));
		set @nuevaCantidad = @existenciasProducto + @cantidad;

		insert into iop.Adquisicion(cantidad, fechaEntrega, observaciones, RIF) 
			values (@cantidad, convert(char(10), getDate(), 103), @observaciones, @RIF);

		set @idAdquisicion = @@identity;

		update org.Producto set cantidad = @nuevaCantidad where ((idProducto = @idProducto) and (RIF = @RIF));

		insert into iop.Entrada(idAdquisicion, idProducto) 
			values(@idAdquisicion, @idProducto);

		--commit transaction;
	end
	--else
		--rollback transaction;
go;