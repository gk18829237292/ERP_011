drop database if exists erp;
create database erp;
use erp;
create table Department(
	department_id bigint primary key auto_increment,
    department_name nvarchar(100) not null,
    department_type int not null
);
create table Stuff(
	account nvarchar(40) primary key,
    pwd nvarchar(40) not null,
    name nvarchar(100) not null,
    type int not null
);
create table Stuff_Department(
	account nvarchar(40) not null,
	department_id bigint not null,
	foreign key(account) references Stuff(account) on delete cascade,
	foreign key(department_id) references Department(department_id) on delete cascade
);
create table Assignment(
	assignment_id bigint primary key auto_increment,
	startTime long not null,
	endTime long not null,
	createAccount nvarchar(40) not null,
	place nvarchar(100) not null,
	financing bigint not null,
	goal nvarchar(100) not null,
	assignment_type int not null check(assignment_type in (0,1)),
	num int not null,
    completeNum int not null,
    assignment_name nvarchar(40),
	foreign key(createAccount) references Stuff(account)
);
create table Assignment_Department(
	assignment_id bigint not null,
	department_id bigint not null,
	foreign key(assignment_id) references Assignment(assignment_id)  ON DELETE CASCADE,
	foreign key(department_id) references Department(department_id)  ON DELETE CASCADE
);
create table Report(
	report_id bigint primary key auto_increment,
	account nvarchar(40) not null,
	assignment_id bigint not null,
	comment nvarchar(100),
	picture nvarchar(1000),
	reportTime long not null,
	reportIndex int not null,
	foreign key(account) references Stuff(account) on delete cascade,
	foreign key(assignment_id) references Assignment(assignment_id) on delete cascade
);
create table supervise(
	supervise_id bigint primary key auto_increment,
	report_id bigint not null,
	account nvarchar(40) not null,
	comment nvarchar(100),
	picture nvarchar(1000),
	superviseTime long not null,
	foreign key(report_id) references Report(report_id) on delete cascade,
	foreign key(account) references Stuff(account) on delete cascade
);

insert into Stuff values("gkTest","123456","gk",0);
insert into Stuff values("supervise","123"," xiaoming",1);
insert into Stuff values("guest","123","李辉",2);
insert into Stuff values("guest1","123","小红",2);
insert into assignment values(1,1,1,"gkTest","宿舍",10000,"完成project项目",0,3,2,"邓老板的项目");
insert into assignment values(2,1,1,"gkTest","教师",10000,"完成前端设计",1,5,2,"李老板的项目");
insert into assignment values(3,1,1,"gkTest","操场",10000,"完成前端设计",1,5,5,"王老板的项目");
insert into assignment values(4,1,1,"gkTest","操场",10000,"完成前端设计",1,5,5,"孙老板的项目");
insert into department values(1,"部门A","0");
insert into department values(2,"部门B","0");
insert into assignment_department values(1,1);
insert into assignment_department values(2,1);
insert into assignment_department values(3,2);
insert into assignment_department values(4,1);
insert into report values(1,"guest",1,"这次按时完成了服务器以及前端的设计","gk1.jpg;gk2.jpg",1,1);
insert into report values(2,"guest",1,"这次按时完成了服务器以及前端的设计","gk1.jpg;gk2.jpg",1,2);
insert into report values(3,"guest1",1,"这次按时完成了服务器以及前端的设计","gk1.jpg;gk2.jpg",1,1);
insert into supervise values(1,1,"supervise","完成的很好，我们很满意","gk2.jpg;",1);
insert into supervise values(2,1,"supervise","完成的很好，我们很满意","gk1.jpg",1);
insert into stuff_department values("guest",1);
insert into stuff_department values("guest1",2);