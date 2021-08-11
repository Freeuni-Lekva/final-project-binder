Create table Actions(
                        actions_id int auto_increment primary key,
                        relation int not null check(relation in (1 , -1)),
                        last_modified date not null,
                        actor_id int ,
                        subject_id int ,
                        foreign key  (actor_id) references user_profile (user_profile_id) on update cascade on delete cascade,
                        foreign key  (subject_id) references user_profile (user_profile_id) on update cascade on delete cascade
);
delimiter //
Create trigger Actions_trigger_before_insert_date before insert
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



Create trigger Actions_trigger_after_insert_match after insert
    on binder.Actions
    for each row
    if new.relation = 1 and exists (select 'X' from actions
			   where actions.subject_id = new.actor_id
               and actions.actor_id = new.subject_id
               and relation = 1)
    then
    insert into chat_room (user_profile_id_1 , user_profile_id_2)
    values (new.actor_id , new.subject_id );
end if;
//