Create table Actions(
                                                  id int auto_increment primary key,
                                                  username_actor  varchar(30),
                                                  username_subject varchar(30),
                                                  relation int DEFAULT 0
);