Create table Chat_bucket(
    chat_bucket_id int auto_increment primary key,
    first_created date not null,
    chat_room_id int ,
    foreign key  (chat_room_id) references chat_room(chat_room_id) on update cascade on delete cascade
);
delimiter //
Create trigger Chat_bucket_trigger_before_insert before insert
    on binder.Chat_bucket
    for each row
    if new.first_created is null then
		set new.first_created = sysdate() ;
end if;
//
delimiter ;