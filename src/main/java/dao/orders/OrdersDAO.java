package dao.orders;

import bean.orders.Orders;
import bean.product.Product;

import java.util.List;

public interface OrdersDAO {
    public List<Orders> getOrders(int customerID);
    public List<Product> getOrdersByDate(int customerID, String date);
    public Integer initial();
    public void insert(int CustomerID, int productID, String dates, int amount, int quantity);
    public void update(int orderID, String dates, int amount, int status);
}
