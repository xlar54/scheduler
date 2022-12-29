create database scheduler;
use scheduler;

CREATE USER sqluser IDENTIFIED BY 'sqluserpw';

grant usage on *.* to sqluser@localhost identified by 'sqluserpw';
grant all privileges on scheduler.* to sqluser@localhost;

SET FOREIGN_KEY_CHECKS = 0;
drop table apppintments;
drop table contacts;
drop table users;
drop table customers;
drop table firstlevel_divisions;
drop table countries;
SET FOREIGN_KEY_CHECKS = 1;


create table countries (
	Country_ID int(10) NOT NULL,
    Country varchar(50),
    Create_Date datetime,
    Created_By varchar(50),
    Last_Update timestamp,
    Last_Updated_By varchar(50),
    PRIMARY KEY (Country_ID)
);

create table firstlevel_divisions (
	Division_ID int(10),
    Division varchar(50),
    Create_Date datetime,
    Created_By varchar(50),
    Last_Update timestamp,
    Last_Updated_By varchar(50),
    Country_ID int(10),
    PRIMARY KEY (Division_ID),
    FOREIGN KEY (Country_ID)
        REFERENCES countries(Country_ID)
        ON DELETE CASCADE
);

create table customers (
	Customer_ID int(10),
    Customer_Name varchar(50),
    Address varchar(100),
    Postal_Code varchar(50),
    Phone varchar(50),
	Create_Date datetime,
    Created_By varchar(50),
    Last_Update timestamp,
    Last_Updated_By varchar(50),
    Division_ID int(10),
    PRIMARY KEY (Customer_ID),
    FOREIGN KEY (Division_ID)
        REFERENCES firstlevel_divisions(Division_ID)
        ON DELETE CASCADE
);

create table users (
	User_ID int(10),
    User_Name varchar(50) UNIQUE,
    password text,
	Create_Date datetime,
    Created_By varchar(50),
    Last_Update timestamp,
    Last_Updated_By varchar(50),
    PRIMARY KEY (User_ID)
);

create table contacts (
	Contact_ID int(10),
    Contact_Name varchar(50),
    Email varchar(50),
    PRIMARY KEY (Contact_ID)
);

create table appointments (
	Appointment_ID int(10),
    Title varchar(50),
    Description varchar(50),
    Location varchar(50),
    Type varchar(50),
    Start datetime,
    End datetime,
	Create_Date datetime,
    Created_By varchar(50),
    Last_Update timestamp,
    Last_Updated_By varchar(50),
    Customer_ID int(10),
    User_ID int(10),
    Contact_ID int(10),
    PRIMARY KEY (Appointment_ID),
    FOREIGN KEY (Customer_ID)
        REFERENCES customers(Customer_ID)
        ON DELETE CASCADE,
	FOREIGN KEY (User_ID)
        REFERENCES users(User_ID)
        ON DELETE CASCADE,
	FOREIGN KEY (Contact_ID)
        REFERENCES contacts(Contact_ID)
        ON DELETE CASCADE
);


## insert data

insert into countries (Country_ID, Country, Create_Date, Created_By, Last_Update, Last_Updated_By)
values (1,'USA', curdate(), 'script', current_timestamp(), 'script');

insert into countries (Country_ID, Country, Create_Date, Created_By, Last_Update, Last_Updated_By)
values (2,'France', curdate(), 'script', current_timestamp(), 'script');

insert into firstlevel_divisions (Division_ID, Division, Create_Date, Created_By, Last_Update, Last_Updated_By, Country_ID)
values (1,'North', curdate(), 'script', current_timestamp(), 'script', 1);

insert into firstlevel_divisions (Division_ID, Division, Create_Date, Created_By, Last_Update, Last_Updated_By, Country_ID)
values (2,'South', curdate(), 'script', current_timestamp(), 'script', 1);

insert into firstlevel_divisions (Division_ID, Division, Create_Date, Created_By, Last_Update, Last_Updated_By, Country_ID)
values (3,'North FR', curdate(), 'script', current_timestamp(), 'script', 2);

insert into firstlevel_divisions (Division_ID, Division, Create_Date, Created_By, Last_Update, Last_Updated_By, Country_ID)
values (4,'South FR', curdate(), 'script', current_timestamp(), 'script', 2);

insert into Customers (Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID)
values (1,'Nicholas Hutter', '123 Main St', '37091', '444-555-6667', curdate(), 'script', current_timestamp(), 'script', 1);

insert into Customers (Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID)
values (2,'Bob Smith', '4323 Elenear St', '47071', '444-221-6432', curdate(), 'script', current_timestamp(), 'script', 2);

insert into Users (User_ID, User_Name, password, Create_Date, Created_By, Last_Update, Last_Updated_By)
values (1, 'nicholas', 'Freddy12!@', curdate(), 'script', current_timestamp(), 'script');

insert into Users (User_ID, User_Name, password, Create_Date, Created_By, Last_Update, Last_Updated_By)
values (2, 'scott', 'Freddy12!@', curdate(), 'script', current_timestamp(), 'script');

insert into Contacts (Contact_ID, Contact_Name, Email)
values (1, 'Jim Wilson', 'jimw@gmail.com');

insert into Contacts (Contact_ID, Contact_Name, Email)
values (2, 'Michael Jackson', 'kingofpop@gmail.com');

insert into Appointments (Appointment_ID, Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By,
Customer_ID, User_ID, Contact_ID)
values (1, 'Appt 1', 'Appointment 1', 'New York', 'Type 1', '2023-01-23 12:45:56', '2023-01-24 12:45:56', curdate(), 'script', current_timestamp(), 'script', 1, 1, 1);

insert into Appointments (Appointment_ID, Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By,
Customer_ID, User_ID, Contact_ID)
values (2, 'Appt 2', 'Appointment 2', 'Los Angeles', 'Type 2', '2023-03-13 12:45:56', '2023-03-19 12:45:56', curdate(), 'script', current_timestamp(), 'script', 1, 1, 1);