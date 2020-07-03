/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/7/3 星期五 21:40:30                        */
/*==============================================================*/


drop table if exists Course;

drop table if exists Dictionary;

drop table if exists RoleInfo;

drop table if exists StudentCourse;

drop table if exists UserInfo;

drop table if exists UserMenu;

drop table if exists UserRole;

drop table if exists loginInfo;

drop table if exists menu;

drop table if exists "数据字典-内容";

drop table if exists 角色权限信息;

/*==============================================================*/
/* Table: Course                                                */
/*==============================================================*/
create table Course
(
   CourseId             int not null,
   CourseName           nvarchar(256),
   CreateDate           datetime,
   CreateBy             nvarchar(64),
   ModifyDate           datetime,
   ModifyBy             nvarchar(64),
   courseDesc           text,
   semester             nvarchar(16),
   classroom            nvarchar(256),
   startSection         int,
   endSection           int,
   startWeek            int,
   endWeek              int,
   status               boolean,
   lng                  double,
   lat                  double,
   primary key (CourseId)
);

alter table Course comment '课程基本信息';

/*==============================================================*/
/* Table: Dictionary                                            */
/*==============================================================*/
create table Dictionary
(
   DectionaryId         int not null,
   DataType             nvarchar(128),
   DataValue            nvarchar(128),
   DataDesc             text,
   CreateDate           datetime,
   CreateBy             nvarchar(64),
   ModifyDate           datetime,
   ModifyBy             nvarchar(64),
   primary key (DectionaryId)
);

/*==============================================================*/
/* Table: RoleInfo                                              */
/*==============================================================*/
create table RoleInfo
(
   RoleId               int not null,
   RoleName             nvarchar(64),
   CreateDate           datetime,
   CreateBy             nvarchar(64),
   ModifyDate           datetime,
   ModifyBy             nvarchar(64),
   primary key (RoleId)
);

alter table RoleInfo comment '角色基本信息';

/*==============================================================*/
/* Table: StudentCourse                                         */
/*==============================================================*/
create table StudentCourse
(
   UserCourseID         int not null,
   UserId               int,
   CourseId             int,
   SignInStatus         int,
   CreateDate           datetime,
   CreateBy             nvarchar(64),
   ModifyDate           datetime,
   ModifyBy             nvarchar(64),
   Score                int,
   is_charge            bool,
   primary key (UserCourseID)
);

/*==============================================================*/
/* Table: UserInfo                                              */
/*==============================================================*/
create table UserInfo
(
   UserId               int not null,
   UserName             nvarchar(128),
   UserSex              nvarchar(8),
   UserAccount          nvarchar(64),
   UserEmail            nvarchar(128),
   UserTelephone        nvarchar(16),
   CreateDate           datetime,
   CreateBy             nvarchar(64),
   ModifyDate           datetime,
   ModifyBy             varbinary(64),
   primary key (UserId)
);

alter table UserInfo comment '用户基本信息';

/*==============================================================*/
/* Table: UserMenu                                              */
/*==============================================================*/
create table UserMenu
(
   RoleId               int,
   MenuId               int,
   UserMenuID           int,
   status               nvarchar(0)
);

/*==============================================================*/
/* Table: UserRole                                              */
/*==============================================================*/
create table UserRole
(
   UserRoleId           int not null,
   RoleId               int,
   UserId               int,
   CreateDate           datetime,
   CreateBy             nvarchar(64),
   ModifyDate           datetime,
   ModifyBy             nvarchar(64),
   primary key (UserRoleId)
);

alter table UserRole comment '用户角色';

/*==============================================================*/
/* Table: loginInfo                                             */
/*==============================================================*/
create table loginInfo
(
   UserId               int,
   Id                   int,
   Account              nvarchar(0),
   LoginDate            datetime,
   LoginWay             nvarchar(0),
   Password             nvarchar(0),
   primary key ()
);

/*==============================================================*/
/* Table: menu                                                  */
/*==============================================================*/
create table menu
(
   MenuId               int not null,
   men_MenuIdmen_MenuId int,
   MenuName             nvarchar(256),
   MenuURL              nvarchar(256),
   MenuStatus           nvarchar(16),
   CreateDate           datetime,
   CreateBy             nvarchar(64),
   ModifyDate           datetime,
   ModifyBy             nvarchar(64),
   Code                 char(10),
   Type                 nvarchar(128),
   sequence             int,
   icon                 nvarchar(0),
   primary key (MenuId)
);

alter table menu comment '系统菜单权限控制';

/*==============================================================*/
/* Table: "数据字典-内容"                                             */
/*==============================================================*/
create table "数据字典-内容"
(
   DictionaryContentId  int not null,
   DectionaryId         int,
   DictionaryContentValue nvarchar(128),
   DictionaryContentText nvarchar(256),
   DictionaryContentStatus int,
   DictionaryContentDesc text,
   CreateDate           datetime,
   CreateBy             nvarchar(64),
   ModifyDate           datetime,
   ModifyBy             nvarchar(64),
   primary key (DictionaryContentId)
);

/*==============================================================*/
/* Table: 角色权限信息                                                */
/*==============================================================*/
create table 角色权限信息
(
   id                   int not null,
   UserRoleId           int,
   S                    smallint,
   CreateDate           datetime,
   CreateBy             nvarchar(64),
   ModifyDate           datetime,
   ModifyBy             nvarchar(64)
);

alter table StudentCourse add constraint FK_Reference_6 foreign key (UserId)
      references UserInfo (UserId) on delete restrict on update restrict;

alter table StudentCourse add constraint FK_Reference_7 foreign key (CourseId)
      references Course (CourseId) on delete restrict on update restrict;

alter table UserMenu add constraint FK_Reference_4 foreign key (RoleId)
      references RoleInfo (RoleId) on delete restrict on update restrict;

alter table UserMenu add constraint FK_Reference_5 foreign key (MenuId)
      references menu (MenuId) on delete restrict on update restrict;

alter table UserRole add constraint FK_Reference_2 foreign key (RoleId)
      references RoleInfo (RoleId) on delete restrict on update restrict;

alter table UserRole add constraint FK_Reference_3 foreign key (UserId)
      references UserInfo (UserId) on delete restrict on update restrict;

alter table UserRole add constraint FK_Reference_9 foreign key ()
      references 角色权限信息 on delete restrict on update restrict;

alter table loginInfo add constraint FK_Reference_1 foreign key (UserId)
      references UserInfo (UserId) on delete restrict on update restrict;

alter table "数据字典-内容" add constraint FK_Reference_8 foreign key (DectionaryId)
      references Dictionary (DectionaryId) on delete restrict on update restrict;

