package dao.cart;

import org.junit.Before;
import org.junit.Test;
import util.DatabaseUtil;

import static org.junit.Assert.assertEquals;

public class CartDAOImplTest {

    private CartDAO cartDAO;

    @Before
    public void setUp() {
        cartDAO = new CartDAOImpl();
        DatabaseUtil.initDatabase();
    }

    @Test
    public void test_insertProduct() {
        cartDAO.insert(1,1,3);
        assertEquals(3, cartDAO.productExist(1,1).intValue());
    }

    @Test
    public void test_deleteProduct() {
        cartDAO.insert(1,1,3);
        assertEquals(3, cartDAO.productExist(1,1).intValue());
        cartDAO.deleteProduct(1,1);
        assertEquals(0, cartDAO.getProduct(1).size());
    }

    @Test
    public void test_updateQuantity() {
        cartDAO.insert(1,1,3);
        assertEquals(3, cartDAO.productExist(1,1).intValue());
        cartDAO.updateQuantity(1,1,5);
        assertEquals(5, cartDAO.productExist(1,1).intValue());
    }
}