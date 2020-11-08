use Warehouse;

-- Almacena los productos eliminados en una tabla historial
create trigger tdProductosEliminados on org.Producto
after delete 
as
	if not exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA = 'org' and TABLE_NAME = 'ProductoEliminado')
	begin
		exec crearTablas 0;
		insert into org.ProductoEliminado (idProducto, nombre, marca, cantidad, idMedida, idEstado, RIF)
			(select idProducto, nombre, marca, cantidad, idMedida, idEstado, RIF from deleted);
	end
	else 
	begin
		insert into org.ProductoEliminado (idProducto, nombre, marca, cantidad, idMedida, idEstado, RIF)
			(select idProducto, nombre, marca, cantidad, idMedida, idEstado, RIF from deleted);
	end
go;

-- Almacena los proveedores eliminados en una tabla historial
create trigger tdProveedoresEliminados on org.Proveedor
after delete 
as
	if not exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA = 'org' and TABLE_NAME = 'ProveedorEliminado')
	begin
		exec crearTablas 1;
		insert into org.ProveedorEliminado (idProveedor, nombres, apellidos, correo, telefono, RIF)
			(select idProveedor, nombres, apellidos, correo, telefono, RIF from deleted);
	end
	else 
	begin
		insert into org.ProveedorEliminado (idProveedor, nombres, apellidos, correo, telefono, RIF)
			(select idProveedor, nombres, apellidos, correo, telefono, RIF from deleted);
	end
go;

-- Almacena los productos eliminados en una tabla historial
create trigger tdPersonalEliminado on adm.Personal
after delete 
as
	if not exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA = 'adm' and TABLE_NAME = 'PersonalEliminado')
	begin
		exec crearTablas 2;
		insert into adm.PersonalEliminado (cedula, nombres, apellidos, direccion, telefono, idDepartamento)
			(select cedula, nombres, apellidos, direccion, telefono, idDepartamento from deleted);
	end
	else 
	begin
		insert into adm.PersonalEliminado (cedula, nombres, apellidos, direccion, telefono, idDepartamento)
			(select cedula, nombres, apellidos, direccion, telefono, idDepartamento from deleted);
	end
go;