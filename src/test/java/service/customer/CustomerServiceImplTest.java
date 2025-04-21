package service.customer;

import bean.user.User;
import dao.build.BuildDAOImpl;
import dao.orders.OrdersDAOImpl;
import dao.user.UserDAOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import start.DrinkShopApplication;
import util.DatabaseUtil;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = DrinkShopApplication.class)
public class CustomerServiceImplTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserDAOImpl userDAO;

    @BeforeEach
    public void setUp() {
        DatabaseUtil.initDatabase();
    }

    @Test
    public void test_registerUserSuccessful() {
        assertFalse(userDAO.checkRegistration("test", "0912345678"));
        User user = new User("test", BCrypt.hashpw("123456", BCrypt.gensalt()), "0912345678");
        assertTrue(customerService.register(user));
    }

    @Test
    public void test_registerUserFailure() {
        assertTrue(userDAO.checkRegistration("test", "0123456789"));
        User user = new User("test", BCrypt.hashpw("123456", BCrypt.gensalt()), "0123456789");
        assertFalse(customerService.register(user));
    }

    @Test
    public void test_loginSuccessful() {
        User user = customerService.login("test", "test");
        assertEquals("0123456789", user.getPhone());
        assertEquals("普通會員", user.getLevel());
    }

    @Test
    public void test_loginFailure() {
        User user = customerService.login("test", "123");
        assertNull(user);
    }

    @Test
    public void test_updateLevel() {
        User user = customerService.login("test", "test");
        assertEquals("0123456789", user.getPhone());
        assertEquals("普通會員", user.getLevel());

        String level = customerService.updateLevel("test", "0123456789");
        assertEquals("VIP", level);
    }
}