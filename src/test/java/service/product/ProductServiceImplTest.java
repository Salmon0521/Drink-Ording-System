package service.product;

import bean.product.Product;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseUtil;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductServiceImplTest {
    private ProductService productService;

    @Before
    public void setUp(){
        productService = new ProductServiceImpl();
        DatabaseUtil.initDatabase();
    }

    @Test
    public void test_showProducts() {
        List<Product> productList = productService.showProducts("Boutique hand coffee");
        assertEquals(7, productList.size());
        productList = productService.showProducts("Chocolate");
        assertEquals(5, productList.size());
        productList = productService.showProducts("Tea");
        assertEquals(5, productList.size());
        productList = productService.showProducts("Milk");
        assertEquals(5, productList.size());
        productList = productService.showProducts("Coffee");
        assertEquals(7, productList.size());
        productList = productService.showProducts("Latte");
        assertEquals(7, productList.size());
    }
}