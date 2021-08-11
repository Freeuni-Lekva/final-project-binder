Create table Chat_Room(
                          chat_room_id int auto_increment primary key,
                          user_profile_id_1 int ,
                          user_profile_id_2 int ,
                          create_date date ,
                          last_message varchar(30) default '',
                          last_message_sent_date date ,
                          foreign key  (user_profile_id_1) references user_profile (user_profile_id) on update cascade on delete cascade,
                          foreign key  (user_profile_id_2) references user_profile (user_profile_id) on update cascade on delete cascade
);
delimiter //
Create trigger Chat_Room_trigger_before_insert before insert
    on binder.chat_room
    for each row
    if new.create_date is null then
		set new.create_date = sysdate() ,  new.last_message_sent_date = sysdate();
end if;
//
delimiter ;
