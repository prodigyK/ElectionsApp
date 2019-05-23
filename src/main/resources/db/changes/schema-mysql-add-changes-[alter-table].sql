alter table bf_log_changes drop foreign key bf_log_changes_ibfk_1;
alter table bf_log_changes add foreign key (log_main_id) references bf_log_main (id) on delete cascade;