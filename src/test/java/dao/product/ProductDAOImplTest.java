package dao.product;

import bean.product.Product;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseUtil;

public class ProductDAOImplTest {
    private ProductDAO productDAO;

    @Before
    public void setUp() {
        productDAO = new ProductDAOImpl();
        DatabaseUtil.initDatabase();
    }

    @Test
    public void getID() {
        Product product = new Product();
        product.setName("耶加雪非");
        product.setIce("熱");
        product.setSize("M");
        product.setSugar("無糖");
        product.setPrice(70);
        Integer ProductID = productDAO.getProductID(product);
        System.out.println(ProductID);
    }
}