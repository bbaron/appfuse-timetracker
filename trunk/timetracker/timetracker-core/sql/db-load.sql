insert into timetracker.tt_user (id, first_name, last_name, username) values (2000, 'system', 'user', 'user'); 
insert into timetracker.tt_user (id, first_name, last_name, username) values (2001, 'system', 'admin', 'admin'); 
insert into timetracker.tt_user (id, first_name, last_name, username) values (2002, 'Naresh', 'Bhatia', 'nbhatia'); 
insert into timetracker.tt_user (id, first_name, last_name, username) values (2003, 'Louis', 'Coude', 'lcoude'); 
insert into timetracker.tt_user (id, first_name, last_name, username) values (2004, 'Eric', 'Crutchfield', 'ecrutchfield'); 
insert into timetracker.tt_user (id, first_name, last_name, username) values (2005, 'Chris', 'Micali', 'cmicali'); 
insert into tt_timecard (id, comments, start_date, status, submitter_id)
values (2000, 'test timecard', '2008/10/15', 'Draft', 2002);
insert into tt_timecard_alloc (timecard_id, task_date, hours, minutes, task, position)
values (2000, '2008/10/15', 4, 20, 'Admin', 0);
insert into tt_timecard_alloc (timecard_id, task_date, hours, minutes, task, position)
values (2000, '2008/10/16', 1, 0, 'Meeting', 1);