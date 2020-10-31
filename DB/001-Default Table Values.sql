use Warehouse;

insert into adm.Departamento values ('BOA', 'Boaco'),
									('CAR', 'Carazo'),
									('CHI', 'Chinandega'),
									('CHO', 'Chontales'),
									('CCN', 'Costa Caribe Norte'),
									('CCS', 'Costa Caribe Sur'),
									('EST', 'Estelí'),
									('GRA', 'Granada'),
									('JIN', 'Jinotega'),
									('LEO', 'León'),
									('MAD', 'Madríz'),
									('MAN', 'Managua'),
									('MAS', 'Masaya'),
									('MAT', 'Matagalpa'),
									('NUS', 'Nueva Segovia'),
									('RSJ', 'Rio San Juan'),
									('RIV', 'Rivas');

insert into adm.Rol values ('Administrador'),
						   ('Moderador'),
						   ('Empleado');

insert into org.Medida values ('Unidades');

insert into org.Estado values ('Nuevo'),
							  ('Usado'),
							  ('Restaurado');