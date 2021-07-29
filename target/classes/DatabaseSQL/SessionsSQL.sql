CREATE TABLE Sessions(
                        id int AUTO_INCREMENT primary key,
                        sessionID varchar(32),
                        username varchar(30),
                        foreign key (username) references user(username) on update cascade on delete cascade
);
