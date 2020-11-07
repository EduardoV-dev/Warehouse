use Warehouse;

-- Procedimiento para crear tablas para items eliminados
-- opc = 0, crea tabla para productos eliminados
-- opc = 1, crea tabla para proveedores elimaados
-- opc = 2, crea tabla para personal eliminado
create procedure crearTablas (
	@opc tinyint
)
as
	if (@opc = 0)
	begin
		create table org.ProductoEliminado (
			idProducto varchar(5),
			nombre varchar(50),
			marca varchar(40),
			cantidad smallint,
			idMedida tinyint,
			idEstado tinyint,
			RIF varchar(20)
		);
	end
	if (@opc = 1) 
	begin
		create table org.ProveedorEliminado (
			idProveedor varchar(10),
			nombres char(35),
			apellidos char(35),
			correo varchar(50),
			telefono varchar(20),
			RIF varchar(20)
		);
	end
	if (@opc = 2)
	begin
		create table adm.PersonalEliminado (
			cedula varchar(20),
			nombres char(35),
			apellidos char(35),
			correo varchar(50),
			direccion varchar(100),
			telefono varchar(20),
			idDepartamento char(3)
		);
	end
go;