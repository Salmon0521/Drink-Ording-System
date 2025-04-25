package service.cart;

import bean.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import start.DrinkShopApplication;
import util.DatabaseUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = DrinkShopApplication.class)
class CartServiceImplTest {

    @Autowired
    private CartService cartService;

    @BeforeEach
    public void setUp() throws Exception {
        DatabaseUtil.initDatabase();
    }

    @Test
    void test_showCartIsNull() {
        List<Product> userCart = cartService.showCart("test", "0123456789");
        assertTrue(userCart.isEmpty());
    }

    @Test
    void test_addProductIntoCart() {
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

    @Test
    void test_deleteProductInCart() {
        Product product = new Product();
        product.setName("耶加雪非");
        product.setSize("M");
        product.setSugar("正常");
        product.setIce("熱");
        product.setQuantity(1);

        cartService.addProduct("test", "0123456789", product);
        List<Product> userCart = cartService.showCart("test", "0123456789");
        assertEquals(1, userCart.size());
        assertEquals(Integer.valueOf(1), userCart.get(0).getId());

        cartService.deleteProduct("test", "0123456789", 1);

        userCart = cartService.showCart("test", "0123456789");
        assertTrue(userCart.isEmpty());
    }
}