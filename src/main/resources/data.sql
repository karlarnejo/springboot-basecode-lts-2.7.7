INSERT INTO userr (username, password, isenabled, isnotlocked, isaccountnotexpired, ispasswordnotexpired) VALUES ('test@gmail.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', true, true, true, true);
INSERT INTO userr (username, password, isenabled, isnotlocked, isaccountnotexpired, ispasswordnotexpired) VALUES ('test2@gmail.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', true, true, true, true);
INSERT INTO userr (username, password, isenabled, isnotlocked, isaccountnotexpired, ispasswordnotexpired) VALUES ('test3@gmail.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', true, true, true, true);

INSERT INTO role (rolename) VALUES ('USER');
INSERT INTO role (rolename) VALUES ('ADMIN');

INSERT INTO privilege (privilegename) VALUES ('canReadUser');
INSERT INTO privilege (privilegename) VALUES ('canReadAdmin');

INSERT INTO roletoprivilege (roleid, privilegeid) VALUES (1, 1);
INSERT INTO roletoprivilege (roleid, privilegeid) VALUES (2, 1);
INSERT INTO roletoprivilege (roleid, privilegeid) VALUES (2, 2);

INSERT INTO usertorole (username, roleid) VALUES ('test@gmail.com', 1);
INSERT INTO usertorole (username, roleid) VALUES ('test2@gmail.com', 1);
INSERT INTO usertorole (username, roleid) VALUES ('test3@gmail.com', 2);