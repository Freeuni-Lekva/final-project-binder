Create table Image(
    image_id int auto_increment primary key,
    image_path varchar(200) unique,
    user_profile_id int ,
    foreign key  (user_profile_id) references binder.user_profile(user_profile_id) on update cascade on delete cascade
);


