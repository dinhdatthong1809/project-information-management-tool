-- insert group leaders
insert into EMPLOYEE (VISA, FIRST_NAME, LAST_NAME, BIRTH_DATE, VERSION) values ('TDD', 'Thông', 'Đinh Đạt', TO_DATE('18-09-1997', 'dd-mm-yyyy'), 0);
insert into EMPLOYEE (VISA, FIRST_NAME, LAST_NAME, BIRTH_DATE, VERSION) values ('HND', 'Hào', 'Nguyễn Đại', TO_DATE('09-09-1999', 'dd-mm-yyyy'), 0);

-- insert employees
insert into EMPLOYEE (VISA, FIRST_NAME, LAST_NAME, BIRTH_DATE, VERSION) values ('VDD', 'Vi', 'Đinh Đạt', TO_DATE('30-09-1999', 'dd-mm-yyyy'), 0);
insert into EMPLOYEE (VISA, FIRST_NAME, LAST_NAME, BIRTH_DATE, VERSION) values ('TLL', 'Thành', 'Lê Long', TO_DATE('03-04-1994', 'dd-mm-yyyy'), 0);
insert into EMPLOYEE (VISA, FIRST_NAME, LAST_NAME, BIRTH_DATE, VERSION) values ('TDQ', 'Tiến', 'Đào Quang', TO_DATE('21-04-1996', 'dd-mm-yyyy'), 0);
insert into EMPLOYEE (VISA, FIRST_NAME, LAST_NAME, BIRTH_DATE, VERSION) values ('NNT', 'Nhân', 'Nguyễn Thiện', TO_DATE('09-09-1997', 'dd-mm-yyyy'), 0);
insert into EMPLOYEE (VISA, FIRST_NAME, LAST_NAME, BIRTH_DATE, VERSION) values ('TNP', 'Tài', 'Nguyễn Phúc', TO_DATE('25-05-1997', 'dd-mm-yyyy'), 0);
insert into EMPLOYEE (VISA, FIRST_NAME, LAST_NAME, BIRTH_DATE, VERSION) values ('TNM', 'Trang', 'Nguyễn Minh', TO_DATE('08-05-1997', 'dd-mm-yyyy'), 0);
insert into EMPLOYEE (VISA, FIRST_NAME, LAST_NAME, BIRTH_DATE, VERSION) values ('TND', 'Tâm', 'Nguyễn Đức', TO_DATE('01-06-1997', 'dd-mm-yyyy'), 0);
insert into EMPLOYEE (VISA, FIRST_NAME, LAST_NAME, BIRTH_DATE, VERSION) values ('DLC', 'Đạt', 'Lâm Cương', TO_DATE('11-07-1998', 'dd-mm-yyyy'), 0);

-- insert company_group
insert into COMPANY_GROUP (GROUP_LEADER_ID, VERSION) values (1, 0);
insert into COMPANY_GROUP (GROUP_LEADER_ID, VERSION) values (2, 0);

-- insert projects
insert into PROJECT (COMPANY_GROUP_ID, PROJECT_NUMBER, NAME, CUSTOMER, STATUS, START_DATE, END_DATE, VERSION) values (1, 123, 'Shopee website', 'Shopee', 'NEW', TO_DATE('20-05-2020', 'dd-mm-yyyy'), TO_DATE('20-05-2021', 'dd-mm-yyyy'), 0);
insert into PROJECT (COMPANY_GROUP_ID, PROJECT_NUMBER, NAME, CUSTOMER, STATUS, START_DATE, END_DATE, VERSION) values (1, 1234, 'Shopee mobile app', 'Shopee', 'NEW', TO_DATE('10-06-2020', 'dd-mm-yyyy'), TO_DATE('10-06-2021', 'dd-mm-yyyy'), 0);
insert into PROJECT (COMPANY_GROUP_ID, PROJECT_NUMBER, NAME, CUSTOMER, STATUS, START_DATE, END_DATE, VERSION) values (1, 1485, 'Tiki website', 'Tiki', 'PLA', TO_DATE('10-09-2020', 'dd-mm-yyyy'), TO_DATE('10-09-2021', 'dd-mm-yyyy'), 0);
insert into PROJECT (COMPANY_GROUP_ID, PROJECT_NUMBER, NAME, CUSTOMER, STATUS, START_DATE, END_DATE, VERSION) values (1, 1285, 'Tiki mobile app', 'Tiki', 'NEW', TO_DATE('04-06-2020', 'dd-mm-yyyy'), TO_DATE('04-06-2021', 'dd-mm-yyyy'), 0);
insert into PROJECT (COMPANY_GROUP_ID, PROJECT_NUMBER, NAME, CUSTOMER, STATUS, START_DATE, END_DATE, VERSION) values (1, 1237, 'PIM tool', 'Elca', 'INP', TO_DATE('10-06-2020', 'dd-mm-yyyy'), TO_DATE('10-06-2021', 'dd-mm-yyyy'), 0);
insert into PROJECT (COMPANY_GROUP_ID, PROJECT_NUMBER, NAME, CUSTOMER, STATUS, START_DATE, END_DATE, VERSION) values (1, 1243, 'Pokemite', 'Nintendo', 'NEW', TO_DATE('10-06-2020', 'dd-mm-yyyy'), TO_DATE('10-06-2021', 'dd-mm-yyyy'), 0);
insert into PROJECT (COMPANY_GROUP_ID, PROJECT_NUMBER, NAME, CUSTOMER, STATUS, START_DATE, END_DATE, VERSION) values (1, 12123, 'PokeDex 3D', 'Nintendo', 'PLA', TO_DATE('28-12-2020', 'dd-mm-yyyy'), TO_DATE('28-12-2022', 'dd-mm-yyyy'), 0);
insert into PROJECT (COMPANY_GROUP_ID, PROJECT_NUMBER, NAME, CUSTOMER, STATUS, START_DATE, END_DATE, VERSION) values (1, 12345, 'WebManga', 'Kim Dong', 'PLA', TO_DATE('12-12-2021', 'dd-mm-yyyy'), TO_DATE('12-12-2022', 'dd-mm-yyyy'), 0);
insert into PROJECT (COMPANY_GROUP_ID, PROJECT_NUMBER, NAME, CUSTOMER, STATUS, START_DATE, END_DATE, VERSION) values (1, 5123, 'On board exercise', 'Elca', 'INP', TO_DATE('20-04-2020', 'dd-mm-yyyy'), TO_DATE('03-06-2021', 'dd-mm-yyyy'), 0);
insert into PROJECT (COMPANY_GROUP_ID, PROJECT_NUMBER, NAME, CUSTOMER, STATUS, START_DATE, END_DATE, VERSION) values (1, 4563, 'LearnGeek', 'Dinh Dat Thong', 'FIN', TO_DATE('10-10-2019', 'dd-mm-yyyy'), TO_DATE('10-12-2019', 'dd-mm-yyyy'), 0);

