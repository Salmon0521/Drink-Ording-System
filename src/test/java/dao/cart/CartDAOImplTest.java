package dao.cart;

import bean.product.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CartDAOImplTest {

    private CartDAO cartDAO;

    @Before
    public void setUp() throws Exception {
        cartDAO = new CartDAOImpl();
    }

    @Test
    public void insert() {
        cartDAO.insert(5,1,3);
    }

    @Test
    public void delete() {
        cartDAO.deleteProduct(1,3);
    }


    @Test
    public void get() {
        List<Product> productList = cartDAO.getProduct(20);

        for(int i = 0; i < 3; i++){
            Product product = productList.get(i);
            System.out.println(product.getQuantity());
            System.out.println(product.getName());
            System.out.println(product.getIce());
            System.out.println(product.getSize());
            System.out.println(product.getSugar());
            System.out.println(product.getPrice());
        }

    }

    @Test
    public void updateQuantity() {
        cartDAO.updateQuantity(1,327,3);
    }
}