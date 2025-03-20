create table customer
(
    CustomerID INT AUTO_INCREMENT PRIMARY KEY,
    Account VARCHAR(20) DEFAULT NULL,
    Password VARCHAR(20) DEFAULT NULL,
    Level ENUM('普通會員', '高級會員') DEFAULT '普通會員',
    Phone VARCHAR(10) DEFAULT NULL
);

create table orders
(
    OrderID INT AUTO_INCREMENT PRIMARY KEY,
    Dates   varchar(50) default NULL,
    Amount  int         default NULL,
    Status  int         default 0
);

create table build
(
    OrderID    int not null
        references orders,
    CustomerID int not null
        references customer,
    primary key (OrderID, CustomerID)
);

create table product
(
    ProductID   INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ProductName VARCHAR(30) default NULL,
    Price       INT          default NULL,
    Size        CHAR(1)      default NULL,
    Sugar       VARCHAR(10)  default NULL,
    Ice         VARCHAR(10)  default NULL,
    Type        VARCHAR(30)  default NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table addCart
(
    OrderID   int not null
        references orders,
    ProductID int not null
        references product,
    Quantity  int,
    primary key (OrderID, ProductID)
);


