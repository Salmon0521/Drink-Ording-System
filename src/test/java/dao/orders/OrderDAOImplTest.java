package dao.orders;

import bean.order.Order;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseUtil;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderDAOImplTest {

    private OrdersDAO ordersDAO;
    @Before
    public void setUp() throws Exception {
        ordersDAO = new OrdersDAOImpl();
        DatabaseUtil.initDatabase();
    }

    @Test
    public void test_getOrders() {
        List<Order> orders = ordersDAO.getOrders(1);
        assertEquals(0, orders.size());
    }
}