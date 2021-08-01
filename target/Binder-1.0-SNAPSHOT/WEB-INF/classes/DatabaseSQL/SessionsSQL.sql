CREATE TABLE Sessions(
    id int AUTO_INCREMENT primary key,
    sessionID varchar(32),
    user_id int,
    foreign key (user_id) references user(user_id) on update cascade on delete cascade
);