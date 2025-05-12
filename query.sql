### 스프링 부트 ###
create database if not exists springboot_db;
use springboot_db;

CREATE TABLE IF NOT EXISTS test (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

SELECT * FROM test;

-- student 테이블
create table if not exists student (
	id bigint auto_increment primary key,
    name varchar(255) not null,
    email varchar(255) not null unique
);

select * from student;

-- book 테이블

create table if not exists book(
	id bigint auto_increment primary key,
    writer varchar(50) not null,
    title varchar(100) not null,
    content varchar(500) not null,
    category varchar(255) not null,
    constraint chk_category CHECK (category in ('NOVEL', 'ESSAY', 'POEM', 'MAGAZINE'))
);

select * from book;

use springboot_db;

-- post(게시물) 테이블 --
CREATE TABLE IF NOT EXISTS post (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL
);

-- comment(댓글) 테이블 --
CREATE TABLE IF NOT EXISTS comment (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_id BIGINT,
    content VARCHAR(255) NOT NULL,
    commenter VARCHAR(255) NOT NULL,
    FOREIGN KEY (post_id) REFERENCES post(id) ON DELETE CASCADE
);
USE springboot_db;
SELECT * FROM post;
SELECT * FROM comment;


-- users(사용자) 테이블 --
CREATE TABLE IF NOT EXISTS users (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME
);







