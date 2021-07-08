Create table User(
                     id int AUTO_INCREMENT PRIMARY KEY,
                     name varchar(30) ,
                     surname varchar(30) ,
                     email varchar(30) ,
                     username varchar (30),
                     password varchar(30) ,
                     gender varchar(30)  ,
                     user_profile_id int default 0
);