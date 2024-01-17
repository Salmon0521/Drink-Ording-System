package service.productService;

import bean.product.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ProductServiceImplTest {
    private ProductService productService;

    @Before
    public void setUp() throws Exception {
        productService = new ProductServiceImpl();
    }

    @Test
    public void get() {
        List<Product> productList = productService.showProductByType("Boutique hand coffee");
        for (Product product : productList) {
            System.out.println(product);
        }
    }

    @Test
    public void getType() {
        List<Product> productList = productService.showType();
        for (Product product : productList) {
            System.out.println(product.getType());
        }
    }
}