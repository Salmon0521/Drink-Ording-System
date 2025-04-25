package service.product;

import bean.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import start.DrinkShopApplication;
import util.DatabaseUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = DrinkShopApplication.class)
class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @BeforeEach
    public void setUp(){
        DatabaseUtil.initDatabase();
    }

    @Test
    void test_showProducts() {
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