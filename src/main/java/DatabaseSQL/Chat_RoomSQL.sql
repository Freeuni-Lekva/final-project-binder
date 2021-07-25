Create table Chat_Room(
    chat_room_id int auto_increment primary key,
    actions_id_1 int ,
    actions_id_2 int ,
    foreign key  (actions_id_1) references actions(actions_id) on update cascade on delete cascade,
    foreign key  (actions_id_2) references actions(actions_id) on update cascade on delete cascade
);