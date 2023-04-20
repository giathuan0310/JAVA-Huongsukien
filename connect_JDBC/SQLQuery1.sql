drop database qlsv
create database qlsv
go
use qlsv
go
create table  LopHoc(
		Malop varchar(30) primary key,
		Tenlop nvarchar(100) unique not null,
		giaovienCN nvarchar(100)


)

go
create table  Sinhvien(
		Masv varchar(30) primary key,
		hoTen varchar(100) unique not null,
		diachi nvarchar(100),
		email nvarchar(100),
		Malop varchar(30) foreign key references  LopHoc(Malop)


)
select * from LopHoc
go
insert into LopHoc 
values ('DHTH17','CNTT','ThayPhuoc');

insert into Sinhvien
values ('21078061','GiaThuan','Baclieu','luonggiathuan2019.gr@gmail.com','DHTH17')
select * from Sinhvien

update LopHoc set Tenlop='Ke Toan',giaovienCN='ly' where Malop='DHTH17'
select * from LopHoc
delete from LopHoc where Malop='?e'
delete from LopHoc where Malop='DHTH18'
delete from LopHoc where Malop='DHTH19'
delete from LopHoc where Malop=''
delete from LopHoc where Malop='gghggrj'

