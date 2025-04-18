package service.orders;

import bean.order.Order;
import bean.product.Product;
import org.junit.Before;
import org.junit.Test;
import service.cart.CartService;
import service.cart.CartServiceImpl;
import util.DatabaseUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class OrdersServiceImplTest {
    private OrdersService ordersService;
    private CartService cartService;

    @Before
    public void setUp() {
        ordersService = new OrdersServiceImpl();
        cartService = new CartServiceImpl();
        DatabaseUtil.initDatabase();
    }

    @Test
    public void test_getOrdersByDate() {
        Product product = new Product();
        product.setName("耶加雪非");
        product.setSize("M");
        product.setSugar("正常");
        product.setIce("熱");
        product.setQuantity(2);
        cartService.addProduct("test", "0123456789", product);

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = dateFormat.format(now);
        ordersService.buildOrder("test", "0123456789", 140);

        List<Order> orderList = ordersService.getOrder("test", "0123456789");
        assertEquals(1, orderList.size());
        assertEquals(Integer.valueOf(140), orderList.get(0).getAmount());
        assertEquals(date.substring(0, 10), orderList.get(0).getDates().substring(0, 10));

        List<Product> productInOrderList = ordersService.getOrdersByDate("test", "0123456789", date);
        assertEquals(1, productInOrderList.size());
        assertEquals("耶加雪非", productInOrderList.get(0).getName());
        assertEquals("M", productInOrderList.get(0).getSize());
        assertEquals("正常", productInOrderList.get(0).getSugar());
        assertEquals("熱", productInOrderList.get(0).getIce());
        assertEquals(Integer.valueOf(2), productInOrderList.get(0).getQuantity());
    }
}