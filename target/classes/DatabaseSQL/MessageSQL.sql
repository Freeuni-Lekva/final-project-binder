Create table Message(
    message_id int auto_increment primary key,
    message tinytext,
    sent_date date not null,
    chat_bucket_id int,
    user_profile_id int,
    foreign key  (chat_bucket_id) references chat_bucket(chat_bucket_id) on update cascade on delete cascade,
    foreign key  (user_profile_id) references user_profile(user_profile_id) on update cascade on delete cascade
);
delimiter //
Create trigger Message_trigger_before_insert before insert
    on binder.Message
    for each row
    if new.sent_date is null then
		set new.sent_date = sysdate() ;
end if;
//
delimiter ;