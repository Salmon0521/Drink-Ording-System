package service.customer;

import bean.user.User;
import dao.user.UserDAOImpl;
import org.junit.Before;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;
import util.DatabaseUtil;

import static org.junit.Assert.*;

public class CustomerServiceTest {
    private CustomerService customerService;
    private UserDAOImpl userDAO;

    @Before
    public void setUp() {
        customerService = new CustomerServiceImpl();
        userDAO = new UserDAOImpl();
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
}