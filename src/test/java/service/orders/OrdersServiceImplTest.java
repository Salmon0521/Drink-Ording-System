package service.orders;

import bean.order.Order;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseUtil;

import java.util.List;

import static org.junit.Assert.*;

public class OrdersServiceImplTest {
    private OrdersService ordersService;

    @Before
    public void setUp() {
        ordersService = new OrdersServiceImpl();
        DatabaseUtil.initDatabase();
    }

    @Test
    public void test_getOrder() {
        List<Order> orders = ordersService.getOrder("test", "0123456789");
        
    }
}