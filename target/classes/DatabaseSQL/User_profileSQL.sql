Create table User_profile(
    user_profile_id int auto_increment primary key,
    username varchar(30) unique,
    city varchar(30),
    dateOfBirth varchar(30),
    age varchar(30),
    phone_number varchar(30),
    hobbies varchar(300),
    sex varchar(7) check(sex in ('MALE','FEMALE') or sex is null),
    user_id int ,
    foreign key  (user_id) references binder.user(user_id) on update cascade on delete cascade ,
    foreign key (username) references binder.user(username) on update cascade on delete cascade
);
delimiter //
Create trigger user_profile_trigger_before_update before update
    on binder.user_profile
    for each row
    if not ( new.username = old.username and new.user_id = old.user_id) then
        set new.username = (select username
                             from binder.user
                             where binder.user.user_id = new.user_id);
end if ;
//
delimiter ;
delimiter //
Create trigger user_profile_trigger_before_insert before insert
    on binder.user_profile
    for each row
    if new.username is null then
        set new.username = ( select username
                            from binder.user
                            where binder.user.user_id = new.user_id);
end if;
//
delimiter ;