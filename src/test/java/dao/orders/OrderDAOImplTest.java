package dao.orders;

import bean.order.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import start.DrinkShopApplication;
import util.DatabaseUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = DrinkShopApplication.class)
class OrderDAOImplTest {

    @Autowired
    private OrdersDAO ordersDAO;

    @BeforeEach
    public void setUp() throws Exception {
        DatabaseUtil.initDatabase();
    }

    @Test
    void test_getOrders() {
        List<Order> orders = ordersDAO.getOrders(1);
        assertEquals(0, orders.size());
    }
}