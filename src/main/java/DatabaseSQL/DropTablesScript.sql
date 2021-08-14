set foreign_key_checks = 0;
drop table if exists user;
drop table if exists user_profile;
drop table if exists image;
drop table if exists Actions;
drop table if exists chat_room;
drop table if exists Message;
drop table if exists Sessions;
drop trigger if exists user_profile_trigger_after_delete;
drop trigger if exists user_profie_trigger_before_insert;
drop trigger if exists Actions_trigger_before_insert_date;
drop trigger if exists Actions_trigger_before_update;
drop trigger if exists Chat_room_trigger_before_insert;
drop trigger if exists Message_trigger_before_insert;
set foreign_key_checks = 1;