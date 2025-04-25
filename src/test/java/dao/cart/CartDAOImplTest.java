package dao.cart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import start.DrinkShopApplication;
import util.DatabaseUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = DrinkShopApplication.class)
class CartDAOImplTest {

    @Autowired
    private CartDAO cartDAO;

    @BeforeEach
    public void setUp() {
        DatabaseUtil.initDatabase();
    }

    @Test
    void test_insertProduct() {
        cartDAO.insert(1,1,3);
        assertEquals(3, cartDAO.productExist(1,1).intValue());
    }

    @Test
    void test_deleteProduct() {
        cartDAO.insert(1,1,3);
        assertEquals(3, cartDAO.productExist(1,1).intValue());
        cartDAO.deleteProduct(1,1);
        assertEquals(0, cartDAO.getProduct(1).size());
    }

    @Test
    void test_updateQuantity() {
        cartDAO.insert(1,1,3);
        assertEquals(3, cartDAO.productExist(1,1).intValue());
        cartDAO.updateQuantity(1,1,5);
        assertEquals(5, cartDAO.productExist(1,1).intValue());
    }
}