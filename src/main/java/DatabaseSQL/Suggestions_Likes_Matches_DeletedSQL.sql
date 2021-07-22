Create table Suggestions_Likes_Matches_Deleted(
                                                  id int auto_increment primary key,
                                                  username_my  varchar(30),
                                                  username_other varchar(30),
                                                  relation char(1),
                                                  last_modified date,
                                                  chat_id int default 0
);