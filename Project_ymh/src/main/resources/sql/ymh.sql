
create table todouser(
userid varchar2(20) primary key,
userpwd varchar2(20) not null,
username varchar2(30) not null
)


create table todo(
todoseq number primary key,
userid varchar2(20) references todouser(userid),
tododata varchar2(300) not null,
regdate date default sysdate not null,
importance varchar2(10) constraint imp_check check(importance in('good','average','poor')),
deadline date,
result varchar2(10) constraint result_check check (result in('complete','failure','pend')),
comments varchar2(2000)
)

create sequence todoseq
