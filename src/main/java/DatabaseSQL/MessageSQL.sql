Create table Message(
                        id int auto_increment primary key,
                        user_id varchar(30),
                        message tinytext,
                        sent_date date
);