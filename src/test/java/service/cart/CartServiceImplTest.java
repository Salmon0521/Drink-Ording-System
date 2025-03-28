package service.cart;

import bean.product.Product;
import dao.product.ProductDAO;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseUtil;

import java.util.List;

import static org.junit.Assert.*;

public class CartServiceImplTest {

    private ProductDAO productDAO;
    private CartService cartService;

    @Before
    public void setUp() throws Exception {
        cartService = new CartServiceImpl();
        DatabaseUtil.initDatabase();
    }

    @Test
    public void test_showCartIsNull() {
        List<Product> userCart = cartService.showCart("test", "0123456789");
        assertTrue(userCart.isEmpty());
    }

    @Test
    public void test_addProductIntoCart() {
        Product product = new Product();
        product.setName("耶加雪非");
        product.setSize("M");
        product.setSugar("正常");
        product.setIce("熱");
        product.setQuantity(1);

        cartService.addProduct("test", "0123456789", product);
        List<Product> userCart = cartService.showCart("test", "0123456789");
        assertFalse(userCart.isEmpty());
        assertEquals("耶加雪非", userCart.get(0).getName());
        assertEquals("M", userCart.get(0).getSize());
        assertEquals("正常", userCart.get(0).getSugar());
        assertEquals("熱", userCart.get(0).getIce());
        assertEquals(Integer.valueOf(1), userCart.get(0).getQuantity());

        cartService.addProduct("test", "0123456789", product);
        userCart = cartService.showCart("test", "0123456789");
        assertEquals(Integer.valueOf(2), userCart.get(0).getQuantity());
    }
}