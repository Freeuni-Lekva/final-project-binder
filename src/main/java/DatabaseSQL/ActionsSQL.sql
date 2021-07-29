Create table Actions(
    actions_id int auto_increment primary key,
    relation int not null check(relation in (1 , -1)),  -- S , L , M , D
    last_modified date not null,
    user_profile_id_my int ,
    user_profile_id_other int ,
    foreign key  (user_profile_id_my) references user_profile(user_profile_id) on update cascade on delete cascade,
    foreign key  (user_profile_id_other) references user_profile(user_profile_id) on update cascade on delete cascade
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