drop table images;
drop table guestbook;
drop table guestbook_user;

drop sequence guestbook_seq;
drop sequence images_seq;

create table guestbook (
seq number primary key,
name varchar2(20) not null,
content clob not null,
regdate date not null );

create table images (
seq number primary key,
guestbook_seq number,
file_name varchar2(255) not null,
save_file_name varchar2(255) not null,
real_path varchar2(4000) not null,
file_length number,
content_type varchar2(255),
regdate date not null,
CONSTRAINT images_guestbook_seq_fk FOREIGN KEY (guestbook_seq)
REFERENCES guestbook(seq) ON DELETE CASCADE
);

create sequence images_seq;
create sequence guestbook_seq;

create table guestbook_user(
user_id varchar2(10) primary key,
user_passwd varchar2(500) not null,
user_name varchar2(100) not null,
email varchar2(255) not null,
admin_flag number(1),
regdate date );

