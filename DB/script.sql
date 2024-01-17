create table customer
(
    CustomerID int identity (0, 1)
        primary key,
    Account    varchar(20) default NULL,
    Password   varchar(20) default NULL,
    Level      nvarchar(4) default '普通會員',
    Phone      varchar(10) default NULL
)
go

create table orders
(
    OrderID int identity (0, 1)
        primary key,
    Dates   varchar(50) default NULL,
    Amount  int         default NULL,
    Status  int         default 0
)
go

create table build
(
    OrderID    int not null
        references orders,
    CustomerID int not null
        references customer,
    primary key (OrderID, CustomerID)
)
go

create table product
(
    ProductID   int not null
        primary key,
    ProductName varchar(200) default NULL,
    price       int          default NULL,
    size        char         default NULL,
    sugar       nvarchar(2)  default NULL,
    ice         nvarchar(2)  default NULL,
    type        varchar(30)  default NULL
)
go

create table addCart
(
    OrderID   int not null
        references orders,
    ProductID int not null
        references product,
    Quantity  int,
    primary key (OrderID, ProductID)
)
go


