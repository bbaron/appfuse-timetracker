insert into users (first_name, last_name, username, password, enabled) values ('system', 'user', 'user', 'user', 1); 
insert into users (first_name, last_name, username, password, enabled) values ('system', 'admin', 'admin', 'changeme', 1); 
insert into users (first_name, last_name, username, password, enabled) values ('Naresh', 'Bhatia', 'nbhatia', 'changeme', 1); 
insert into users (first_name, last_name, username, password, enabled) values ('Louis', 'Coude', 'lcoude', 'changeme', 1); 
insert into users (first_name, last_name, username, password, enabled) values ('Eric', 'Crutchfield', 'ecrutchfield', 'changeme', 1); 
insert into users (first_name, last_name, username, password, enabled) values ('Chris', 'Micali', 'cmicali', 'changeme', 1);
insert into authorities(id, username, authority) values (1, 'user', 'ROLE_USER'); 
insert into authorities(id, username, authority) values (2, 'admin', 'ROLE_USER'); 
insert into authorities(id, username, authority) values (3, 'admin', 'ROLE_ADMIN'); 
insert into authorities(id, username, authority) values (4, 'nbhatia', 'ROLE_USER'); 
insert into authorities(id, username, authority) values (5, 'lcoude', 'ROLE_USER'); 
insert into authorities(id, username, authority) values (6, 'ecrutchfield', 'ROLE_USER'); 
insert into authorities(id, username, authority) values (7, 'cmicali', 'ROLE_USER'); 
insert into tt_timecard (id, comments, start_date, status, submitter_id)
values (2000, 'test timecard', '2008/10/15', 'Draft', 'nbhatia');
insert into tt_timecard_alloc (timecard_id, task_date, hours, minutes, task, position)
values (2000, '2008/10/15', 4, 20, 'Admin', 0);
insert into tt_timecard_alloc (timecard_id, task_date, hours, minutes, task, position)
values (2000, '2008/10/16', 1, 0, 'Meeting', 1);