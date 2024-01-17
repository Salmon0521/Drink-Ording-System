package dao.user.product;

import bean.product.Product;
import dao.product.ProductDAO;
import dao.product.ProductDAOImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ProductDAOImplTest {
    private ProductDAO productDAO;
    @Before
    public void setUp() throws Exception {
        productDAO = new ProductDAOImpl();
    }

    @Test
    public void get() {
        Product product = productDAO.getAddInfo(95);
        System.out.println(product.getName());
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

    @Test
    public void getType() {
        List<Product> typeList = productDAO.getType();

        for(int i = 0; i < typeList.size(); i++){
            Product product = typeList.get(i);
            System.out.println(product.getType());
        }
    }
}