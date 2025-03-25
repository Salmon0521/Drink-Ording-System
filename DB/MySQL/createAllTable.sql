create table user
(
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    Account VARCHAR(20) DEFAULT NULL,
    Password CHAR(128) DEFAULT NULL,
    Level ENUM('普通會員', 'VIP') DEFAULT '普通會員',
    Phone VARCHAR(10) DEFAULT NULL
);

create table orders
(
    OrderID INT AUTO_INCREMENT PRIMARY KEY,
    Dates   VARCHAR(50) DEFAULT NULL,
    Amount  INT         DEFAULT NULL,
    Status  INT         DEFAULT 0
);

create table build
(
    OrderID INT NOT NULL,
    UserID INT NOT NULL,
    PRIMARY KEY (OrderID, UserID),
    FOREIGN KEY (OrderID) REFERENCES orders(OrderID),
    FOREIGN KEY (UserID) REFERENCES user(UserID)
);

create table product
(
    ProductID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    ProductName VARCHAR(200) DEFAULT NULL,
    Price INT DEFAULT NULL,
    Size CHAR DEFAULT NULL,
    Sugar VARCHAR(2) DEFAULT NULL,
    Ice VARCHAR(2) DEFAULT NULL,
    Type VARCHAR(30) DEFAULT NULL
);

create table addCart
(
    OrderID INT NOT NULL,
    ProductID INT NOT NULL,
    Quantity INT,
    PRIMARY KEY (OrderID, ProductID),
    FOREIGN KEY (OrderID) REFERENCES orders(OrderID),
    FOREIGN KEY (ProductID) REFERENCES product(ProductID)
);


