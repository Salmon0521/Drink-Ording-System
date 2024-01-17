package dao.user.orders;

import bean.orders.Orders;
import dao.orders.OrdersDAO;
import dao.orders.OrdersDAOImpl;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrdersDAOImplTest {

    private OrdersDAO ordersDAO;
    @Before
    public void setUp() throws Exception {
        ordersDAO = new OrdersDAOImpl();
    }

    @Test
    public void get() {
        List<Orders> orders = ordersDAO.getOrders(2);
        System.out.println(orders);
    }

    @Test
    public void insert() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ordersDAO.insert(1,2, dateFormat.format(date),1,2);
    }
}