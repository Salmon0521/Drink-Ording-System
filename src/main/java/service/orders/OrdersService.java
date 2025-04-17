package service.orders;

import bean.order.Order;
import bean.product.Product;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface OrdersService {
    public List<Product> getOrdersByDate(String account, String phone, String date);
    public List<Order> getOrder(String account, String phone);
    public void buildOrder(String account, String phone, Integer amount);
}
