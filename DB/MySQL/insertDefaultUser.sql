INSERT INTO user(Account,Password,Phone, Level) VALUES ( 'test','$2a$10$W0BjSDWJzPkIgqMuuHvwfOCADLgDGo/fty71DabNE3vnGh398Dhui', '0123456789', '普通會員');
INSERT INTO orders(Status) VALUES (1);
INSERT INTO build(OrderId,UserID) VALUES (1,1);