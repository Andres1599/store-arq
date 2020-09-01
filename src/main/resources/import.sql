insert into rol (id, name) values (1, 'Administrador');
insert into rol (id, name) values (2, 'Ventas');

insert into users (id, first_name, last_name, username, password, rol_id) values (1, 'Javier', 'Alvarez', 'jalvarez', 'jalvarez', 1);
insert into users (id, first_name, last_name, username, password, rol_id) values (2, 'Andres', 'Higueros', 'ahigueros', 'ahigueros', 1);

insert into subscription (id, name) values (1, 'Taller de reparaciones');
insert into subscription (id, name) values (2, 'Mayorista');