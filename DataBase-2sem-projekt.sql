-- Den første der fejler skal findes(nede i konsollen), hvorfor fejler den? 
-- Alle dem der pejes på, skal flyttes op. 
DROP TABLE order_line
DROP TABLE sale_order
DROP TABLE dog_cut
DROP TABLE booking
DROP TABLE order_t
DROP TABLE dog
DROP TABLE customer
DROP TABLE booking_price
DROP TABLE booking_type
DROP TABLE employee
DROP TABLE stock
DROP TABLE sales_price
DROP TABLE product
DROP TABLE supplier
DROP TABLE address
DROP TABLE zip_city



CREATE table zip_city (
    zipcode int not null PRIMARY KEY,
    city varchar(40) not NULL
)

CREATE TABLE address (
    address_id int IDENTITY(1,1) PRIMARY KEY,
    house_no INT not NULL,
    road_name varchar(50) not NULL,
    zip int FOREIGN KEY REFERENCES zip_city(zipcode)
)

CREATE TABLE customer (
    customer_id int IDENTITY(1,1) PRIMARY KEY,
    name varchar(50) not NULL,
    email varchar(50) UNIQUE not NULL,
    a_id int FOREIGN key REFERENCES address(address_id),
    phone_no varchar(40) UNIQUE not NULL
)

CREATE table dog(
    dog_id int IDENTITY(1,1) PRIMARY KEY,
    name varchar(40) not null,
    dog_description varchar(100),
    c_id int FOREIGN KEY REFERENCES customer(customer_id)
)



CREATE TABLE employee (
    employee_id int IDENTITY(1,1) PRIMARY key,
    name varchar(40) not NULL,
    company_position varchar(50) not NULL,
    salary int not NULL,
    barber_type varchar(20) not NULL
);


create table order_t(
    order_id int IDENTITY(1,1) PRIMARY KEY,
    date DATE not NULL,
    total DECIMAL(8,2) not NULL,
    c_id int FOREIGN KEY REFERENCES customer(customer_id),
)

CREATE TABLE booking_type (
    booking_type_id int IDENTITY(1,1) PRIMARY KEY,
    customer_type VARCHAR(50) not NULL,
    name varchar(20) not NULL,
    booking_type_description varchar(100) not null,
    duration int not NULL --duration is the time in min. 
)

create table booking (
    booking_id int IDENTITY(1,1) PRIMARY KEY,
    start_time TIME not NULL,
    emp_id int FOREIGN KEY REFERENCES employee(employee_id),
    o_id int FOREIGN KEY REFERENCES order_t(order_id),
    bt_id int FOREIGN KEY REFERENCES booking_type(booking_type_id),
    customer_type varchar(20) not NULL 
)

create table sale_order (
    sale_order_id int IDENTITY(1,1) PRIMARY KEY,
    sale_time TIME not NULL,
    o_id int FOREIGN KEY REFERENCES order_t(order_id)
)

CREATE TABLE dog_cut (
    dog_cut_id int IDENTITY(1,1) PRIMARY KEY,
    comment varchar(100) not NULL,
    b_id int FOREIGN KEY REFERENCES booking(booking_id),
    d_id int FOREIGN KEY REFERENCES dog(dog_id)
)

create TABLE booking_price (
    booking_price_id int IDENTITY(1,1) PRIMARY KEY,
    start_date DATE not NULL,
    booking_price_value int not NULL,
    bt_id INT FOREIGN KEY REFERENCES booking_type(booking_type_id)
)

create TABLE order_line( --CASADE????
    order_line_id int IDENTITY(1,1) PRIMARY KEY,
    quantity int not null,
    sub_total int not NULL,
    so_id int FOREIGN KEY REFERENCES sale_order(sale_order_id)
)

CREATE TABLE supplier (
    supplier_id int IDENTITY(1,1) PRIMARY KEY,
    name varchar(50) not null,
    a_id int FOREIGN KEY REFERENCES address(address_id),
    country VARCHAR(20) not null,
    phone_no varchar(40) UNIQUE not NULL,
    email varchar(50) UNIQUE not NULL,
    cvr int UNIQUE not NULL
)

CREATE TABLE product (
    product_id int IDENTITY(1,1) PRIMARY KEY,
    name varchar(50) not NULL,
    product_description varchar(100) not null,
    purchase_price int not null,
    s_id INT FOREIGN KEY REFERENCES supplier(supplier_id)
)

CREATE TABLE sales_price (
    sales_price_id int IDENTITY(1,1) PRIMARY KEY,
    start_date DATE not NULL,
    value int not NULL,
    p_id INT FOREIGN KEY REFERENCES product(product_id) on delete CASCADE
)

create TABLE stock (
    stock_id int IDENTITY(1,1) PRIMARY KEY,
    min_stock int not null,
    max_stock int not NULL,
    current_stock int not NULL,
    location varchar(40) not NULL,
    p_id int FOREIGN key REFERENCES product(product_id)
)




insert into product (name, product_description, purchase_price, s_id) VALUEs 
('Shampo', 'XXXX', 125, null), ('Trøje', 'Lille', 78, null); -- NULL værdier er supplier


insert into sales_price (start_date, value, p_id) VALUEs
('2021-12-22', 69, 1), ('2021-04-06', 87, 2);


INSERT into employee VALUES
('Preben Kim', 'Manager', 40000, 'Person'), 
('Hans Peter', 'Fisør', 20000, 'Dog'),
('Richard', 'Frisør', 21000, 'Dog'),
('Kamilla', 'Frisør', 22000, 'Person')

insert into zip_city (zipcode, city) VALUEs 
(9000, 'Aalborg'), 
(8000, 'Aarhus C'),
(9400, 'Nørresundby'),
(9430, 'Vadum')

insert into address (house_no, road_name, zip) VALUES 
(1, 'Klostermarken', 9000),
(22, 'Klokkestøbergade', 8000),
(23, 'Østerbrogade', 9400),
(76, 'Vesterbrogade', 9400),
(3, 'Brorholtvej', 9430)

insert into customer (name, email, a_id, phone_no) VALUES 
('August Nielsen', 'augustnielsen@mail.com', 1, '51938113'),
('Kim Preben', 'kp@hotmail.com', 2, '11111111'),
('Lisa Thomsen', 'lt@mail.dk', 3, '22222222'),
('Elsebeth Jørgensen', 'elsejoer@mail.dk', 4, '33333333'),
('John iler', 'iler@hotmail.com', 5, '44444444')

insert into dog (name, dog_description, c_id) VALUES 
('Fido', 'Sort labrador', 1),
('Karen', 'Rottweiler', 1),
('Rollo', 'Puddel', 3)


insert into booking_type (customer_type, name, booking_type_description, duration) VALUES 
('Person', 'Herre klip', 'En frisk fade?', 30),
('Dog', 'Trimning', '???', 20)

insert into booking_price (start_date, booking_price_value, bt_id) VALUES 
('2024-05-14', 500, 1), ('2024-05-15', 600, 1)

insert into order_t (date, total, c_id) values 
('2024-07-01', 500, 1)

insert into booking (start_time, emp_id, o_id, bt_id, customer_type) VALUES 
('15:30', 1, 1, 1, 'Person')


--SELECT * from product, sales_price where product.product_id = sales_price.p_id

--delete from product where product_id  = 1
--SELECT * from product, sales_price where product.product_id = sales_price.p_id

--SELECT * from sales_price


--SELECT * from address

--select max(booking_price_value) from booking_price

--SELECT TOP 1 * FROM booking_price ORDER BY start_date DESC

SELECT * from employee

select * from customer

SELECT * from booking

SELECT * from order_t

SELECT * from dog_cut










