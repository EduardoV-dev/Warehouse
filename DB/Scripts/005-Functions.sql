use Warehouse;

-- Si existe un usuario existente, devuelve 1,
-- si no, devuelve 0
create function existeUsuario (
	@usuario varchar(50)
)
returns tinyint
as
	begin
		if exists (select usuario from org.Usuario where usuario = @usuario)
		begin
			return 1;
		end
		return 0;
	end
go;

-- Si existe el proveedor con un idProveedor al que se provee, devuelve 1
-- si no existe, devuelve 0
create function existeProveedor (
	@idProveedor varchar(10)
)
returns tinyint
as
	begin
		if exists (select idProveedor from org.Proveedor where idProveedor = @idProveedor)
		begin
			return 1;
		end
		return 0;
	end
go;
