package dao.orders;

import bean.order.Order;
import bean.product.Product;

import java.util.List;

public interface OrdersDAO {
    public List<Order> getOrders(int userID);
    public List<Product> getOrdersByDate(int userID, String date);
    public Integer initial();
    public void insert(int userID, int productID, String dates, int amount, int quantity);
    public void update(int orderID, String dates, int amount, int status);
}
