insert into authority (authority_name) values ('ROLE_USER');
insert into authority (authority_name) values ('ROLE_ADMIN');

-- 관리자 계정 생성
insert into member values (1,"Happy","$2a$10$ngmdi.FtFYeSM0bz2b1Nmu2I0IDFZrEmZdsnseI5pECY2PVqCKZSi",8);
insert into user_auth values (1, "ROLE_ADMIN");
insert into user_auth values (1, "ROLE_USER");