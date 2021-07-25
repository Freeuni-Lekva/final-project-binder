Create table User(
    user_id int AUTO_INCREMENT primary key,
    name varchar(30) ,
    surname varchar(30) ,
    email varchar(30) ,
    username varchar (30) unique,
    password varchar(30) ,
    has_user_profile char(1) default 'N' check(has_user_profile in ('Y','N') or has_user_profile is null)
);