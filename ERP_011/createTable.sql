drop database if exists erp;
create database erp;
use erp;

create table Depart(
	department_id bigint primary key auto_increment,
    department_name nvarchar(100) not null unique
);

create table DepartClass(
	departClass_id bigint primary key auto_increment,
	departClass_name nvarchar(100) not null
);

create table Depart_DepartClass(
	department_id bigint not null,
    departClass_id bigint not null,
    foreign key(department_id) references Depart(department_id) on delete cascade,
    foreign key(departClass_id) references DepartClass(departClass_id) on delete cascade
);

create table Task(
	task_id bigint primary key auto_increment,
    task_name nvarchar(100) not null,
    startTime long not null,
	endTime long not null,
    updateEndTime long not null,
    chairMan nvarchar(40) not null, -- 负责人
    type int not null, -- 0 不重要 1 重要
    place nvarchar(40),
    financing bigint not null,
    goal nvarchar(300) not null,
    report_type int not null, -- 0日报 1 周报，2半月报，3月报，4季报，5 半年报，6年报--
    department_id bigint not null,
	picture nvarchar(1000) not null,
	departClassId bigint not null,
	foreign key(department_id) references Depart(department_id) on delete cascade,
	foreign key(departClassId) references DepartClass(departClass_id) on delete cascade
);

create table Report(
	time long not null,
	reportIndex int not null, 
    comment nvarchar(1000) not null,
    picture nvarchar(1000) not null,
    task_id bigint not null,
    foreign key(task_id) references Task(task_id) on delete cascade,
    primary key(reportIndex,task_id)
);

create table Stuff_0(
	account nvarchar(100) primary key,
    pwd nvarchar(100) not null,
    name nvarchar(100),
    telNum nvarchar(20)
);

create table Stuff_1(
	account nvarchar(100) primary key,
    pwd nvarchar(100) not null,
    name nvarchar(100),
    telNum nvarchar(20)
);

create table Stuff_2(
	account nvarchar(100) primary key,
    pwd nvarchar(100) not null,
	type int not null, -- 0 普通人 1 领导
    name nvarchar(100),
    telNum nvarchar(20)
);

create table Advice(
	star int not null,
    time long not null,
    adviceIndex int not null,
    comment nvarchar(1000) not null,
    picture nvarchar(1000) not null,
    task_id bigint not null,
    foreign key(task_id) references Task(task_id) on delete cascade,
    primary key (adviceIndex,task_id)
);

create table Advice_2(
	time long not null,
    adviceIndex int not null,
    name nvarchar(100) not null,
    comment nvarchar(1000) not null,
    task_id bigint not null,
    foreign key(task_id) references Task(task_id) on delete cascade,
	primary key (adviceIndex,task_id)
);

create table Stuff_Depart(
	account nvarchar(100) not null,
    department_id bigint not null,
	foreign key(account) references Stuff_2(account) on delete cascade,
    foreign key(department_id) references Depart(department_id) on delete cascade
);

create table Stuff_1_Depart(
	account nvarchar(100) not null,
    department_id bigint not null,
	foreign key(account) references Stuff_1(account) on delete cascade,
    foreign key(department_id) references Depart(department_id) on delete cascade
);



insert into Depart values(1,"部门A");
insert into Depart values(2,"部门B");
insert into Depart values(3,"部门C");
insert into Depart values(4,"部门D");

insert into DepartClass values(1,"X类部门");
insert into DepartClass values(2,"Y类部门");
insert into DepartClass values(3,"Z类部门");

insert into Depart_DepartClass values(1,1);
insert into Depart_DepartClass values(2,2);
insert into Depart_DepartClass values(3,3);
insert into Depart_DepartClass values(4,1);




insert into Stuff_2 values("gk5","123",0,"李辉",""); -- 执行者
insert into Stuff_2 values("gk6","123",0,"",""); -- 执行者
insert into Stuff_2 values("gk7","123",0,"",""); -- 执行者
insert into Stuff_2 values("gk8","123",1,"",""); -- 执行者
insert into Stuff_2 values("gk9","123",0,"",""); -- 执行者

insert into Stuff_0 value("gk1","123","","");
insert into Stuff_1 value("gk2","123","","");



insert into Stuff_Depart values("gk7",1);
insert into Stuff_Depart values("gk8",3);
insert into Stuff_Depart values("gk9",3);