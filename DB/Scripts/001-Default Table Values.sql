use Warehouse;

insert into org.Rol values ('Administrador'),
						   ('Moderador'),
						   ('Empleado');

insert into org.Usuario (usuario, contrasena, idRol)
	values ('admin', '12345678', 1);

insert into org.Medida values ('Unidades');

insert into org.Estado values ('Nuevo'),
							  ('Usado'),
							  ('Restaurado');