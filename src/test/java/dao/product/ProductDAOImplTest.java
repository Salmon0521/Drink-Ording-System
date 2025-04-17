package dao.product;

import bean.product.Product;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseUtil;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductDAOImplTest {
    private ProductDAO productDAO;

    @Before
    public void setUp() {
        productDAO = new ProductDAOImpl();
        DatabaseUtil.initDatabase();
    }

    @Test
    public void test_getProductsByType() {
        List<Product> productList = productDAO.getProductsByType("Boutique hand coffee");
        assertEquals(7, productList.size());
        productList = productDAO.getProductsByType("Chocolate");
        assertEquals(5, productList.size());
        productList = productDAO.getProductsByType("Tea");
        assertEquals(5, productList.size());
        productList = productDAO.getProductsByType("Milk");
        assertEquals(5, productList.size());
        productList = productDAO.getProductsByType("Coffee");
        assertEquals(7, productList.size());
        productList = productDAO.getProductsByType("Latte");
        assertEquals(7, productList.size());
    }

    @Test
    public void test_getProductID() {
        Product product = new Product();
        product.setName("耶加雪非");
        product.setIce("熱");
        product.setSize("M");
        product.setSugar("無糖");
        product.setPrice(70);

        Integer ProductID = productDAO.getProductID(product);
        assertEquals(2, ProductID.intValue());
    }
}