use plan;

CREATE TABLE users (
                       id	bigint AUTO_INCREMENT PRIMARY KEY COMMENT '유저 식별자',
                       name	varchar(20)	comment '이름',
                       email	varchar(50)	comment '이메일',
                       password	varchar(20)	comment '비밀번호',
                       createAt	timestamp comment '생성일',
                       updateAt	timestamp comment '수정일'
);

CREATE TABLE plan (
                      id	bigint AUTO_INCREMENT PRIMARY KEY COMMENT '일정 식별자',
                      userId	bigint	comment '작성자 식별자',
                      title	varchar(20)	comment '제목',
                      content	varchar(200) comment '할일',
                      createAt	timestamp comment '생성일',
                      updateAt	timestamp comment '수정일',
                      foreign key (userId) references users(id)
);