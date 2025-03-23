package dao.bean.orders;

import bean.order.Order;
import dao.orders.OrdersDAO;
import dao.orders.OrdersDAOImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderDAOImplTest {

    private OrdersDAO ordersDAO;
    @Before
    public void setUp() throws Exception {
        ordersDAO = new OrdersDAOImpl();
    }

    @Test
    @Ignore("cart not to be order")
    public void get() {
        List<Order> orders = ordersDAO.getOrders(1);
        System.out.println(orders);
    }

    @Test
    public void insert() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ordersDAO.insert(1,2, dateFormat.format(date),1,2);
    }
}