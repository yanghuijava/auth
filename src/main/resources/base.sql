create table organization (
	id			int			not null	auto_increment,
	pid			int			null,
	name		varchar(50)	null,
	full_name		varchar(100)	null,
	address		varchar(100)	null,
	telephone	varchar(15)	null,
	remark		 varchar(1024) null,
	create_by	 varchar(128)	 null,
	create_time	datetime		 null,
	update_by 	varchar(128)	 null,
	update_time 	datetime 		 null,
	status		 int 			null,
	constraint PK_ACCOUNT primary key (id)
);
create table resource (
	id			int			not null	auto_increment,
	pid			int			null,
	type			int			null,
	name		varchar(50)	null,
	code			varchar(50)	null,
	index_		int			null,
	action		varchar(100)	null,
	url			varchar(100)	null,
	icon			varchar(50)	null,
	remark		 varchar(1024) null,
	create_by	 varchar(128)	 null,
	create_time	datetime		 null,
	update_by 	varchar(128)	 null,
	update_time 	datetime 		 null,
	status		 int 			null,
	constraint PK_ACCOUNT primary key (id)
);
create table role(
	id			int			not null	auto_increment,
	code			varchar(50)	null,
	name		varchar(50)	null,
	description	varchar(80)	null,
	remark		 varchar(1024) null,
	create_by	 varchar(128)	 null,
	create_time	datetime		 null,
	update_by 	varchar(128)	 null,
	update_time 	datetime 		 null,
	status		 int 			null,
	constraint PK_ACCOUNT primary key (id)
);
create table user(
	 id			int			not null	auto_increment,
	org_id		int			null,
	account		varchar(50)	null,
	password		varchar(64)	null,
	name		varchar(80)	null,
	telephone	varchar(15)	null,
	mobile		varchar(20)	null,
	email		varchar(50)	null,
	type		int			null,
	remark		 varchar(1024) null,
	create_by	 varchar(128)	 null,
	create_time	datetime		 null,
	update_by 	varchar(128)	 null,
	update_time 	datetime 		 null,
	status		 int 			null,
	constraint PK_ACCOUNT primary key (id)
);
create table role_resource(
	id			int			not null	auto_increment,
	role_id		int			null,
	res_id		int  			null,
	remark		 varchar(1024) null,
	create_by	 varchar(128)	 null,
	create_time	datetime		 null,
	update_by 	varchar(128)	 null,
	update_time 	datetime 		 null,
	status		 int 			null,
	constraint PK_ACCOUNT primary key (id)
);
create table user_role(
	id			int			not null	auto_increment,
	user_id		int			null,
	role_id		int  			null,
	remark		 varchar(1024) null,
	create_by	 varchar(128)	 null,
	create_time	datetime		 null,
	update_by 	varchar(128)	 null,
	update_time 	datetime 		 null,
	status		 int 			null,
	constraint PK_ACCOUNT primary key (id)
);
