Create table Actions(
    actions_id int auto_increment primary key,
    relation int not null check(relation in (1 , -1)),
    last_modified date not null,
    actor_id int ,
    subject_id int ,
    foreign key  (actor_id) references user(user_id) on update cascade on delete cascade,
    foreign key  (subject_id) references user(user_id) on update cascade on delete cascade
);
delimiter //
Create trigger Actions_trigger_before_insert before insert
    on binder.Actions
    for each row
    if new.last_modified is null then
	    set new.last_modified = sysdate();
end if;
//
delimiter ;

delimiter //
Create trigger Actions_trigger_before_update before update
    on binder.Actions
    for each row
    if new.last_modified is null then
	    set new.last_modified = sysdate();
end if;
//
delimiter ;