drop database if exists erp;
create database erp;
use erp;

create table Depart(
	department_id bigint primary key auto_increment,
    department_name nvarchar(100) not null
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
    chairMan nvarchar(40) not null, -- 负责人
    type int not null, -- 0 不重要 1 重要
    place nvarchar(40),
    financing bigint not null,
    goal nvarchar(300) not null,
    report_type int not null, -- 0日报 1 周报，2半月报，3月报，4季报，5 半年报，6年报--
    department_id bigint not null,
	foreign key(department_id) references Depart(department_id) on delete cascade
);

create table Report(
	report_id bigint primary key auto_increment,
	time long not null,
	reportIndex int not null, 
    comment nvarchar(1000) not null,
    picture nvarchar(1000) not null,
    task_id bigint not null,
    foreign key(task_id) references Task(task_id) on delete cascade
);

create table Stuff(
	account nvarchar(100) primary key,
    pwd nvarchar(100) not null,
	type int not null, -- 0 监督者 1 管理者 2 执行者
    name nvarchar(100),
    telNum nvarchar(20)
);

create table Advice(
	addvise_id bigint primary key auto_increment,
    time long not null,
    adviseIndex int not null,
    comment nvarchar(1000) not null,
    picture nvarchar(1000) not null,
    task_id bigint not null,
    type int not null,
    foreign key(task_id) references Task(task_id) on delete cascade
);

create table Stuff_Depart(
	account nvarchar(100) not null,
    department_id bigint not null,
	foreign key(account) references Stuff(account) on delete cascade,
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

insert into Task values(1,"项目A",1,1,"小赵",0,"地点A",1000,"完成项目A",0,3);
insert into Task values(2,"项目B",1,1,"小钱",1,"地点B",1000,"完成项目B",1,3);
insert into Task values(3,"项目C",1,1,"小孙",0,"地点C",1000,"完成项目C",2,4);
insert into Task values(4,"项目D",1,1,"小李",0,"地点D",1000,"完成项目D",3,4);

insert into Report values(1,1,1,"评论A","gk1.jpg;gk2.jpg",1);
insert into Report values(2,1,2,"评论B","gk1.jpg;gk2.jpg",1);
insert into Report values(3,1,3,"评论C","gk1.jpg;gk2.jpg",1);
insert into Report values(4,1,1,"评论D","gk1.jpg;gk2.jpg",2);
insert into Report values(5,1,2,"评论E","gk1.jpg;gk2.jpg",2);
insert into Report values(6,1,1,"评论F","gk1.jpg;gk2.jpg",3);

insert into Stuff values("gk5","123",0,"",""); -- 监督者
insert into Stuff values("gk6","123",1,"",""); -- 管理者
insert into Stuff values("gk7","123",2,"",""); -- 执行者
insert into Stuff values("gk8","123",3,"",""); -- 执行者
insert into Stuff values("gk9","123",2,"",""); -- 执行者
insert into Stuff values("gk1","123",2,"",""); -- 执行者

insert into Advice values(1,1,1,"评论A_1","gk1,jpg;gk2.jpg",1,0);
insert into Advice values(2,1,2,"评论A_2","gk1.jpg;gk2.jpg",1,0);
insert into Advice values(3,1,1,"评论B_1","gk1,jpg;gk2.jpg",1,1);

insert into Stuff_Depart values("gk7",1);
insert into Stuff_Depart values("gk8",1);
insert into Stuff_Depart values("gk9",2);
insert into Stuff_Depart values("gk1",3);