package service.ordersService;

import javax.servlet.http.HttpSession;

public interface OrdersService {
    public void getOrdersByDate(HttpSession session, String date);
    public void getOrder(HttpSession session, String account);
    public void buildOrder(String account, Integer amount);
}
