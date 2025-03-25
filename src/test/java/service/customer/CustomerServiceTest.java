package service.customer;

import bean.user.User;
import dao.user.UserDAOImpl;
import org.junit.Before;
import org.junit.Test;
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
        User user = new User("test", "123456", "0912345678");
        assertTrue(customerService.register(user));
    }

    @Test
    public void test_registerUserFailure() {
        assertTrue(userDAO.checkRegistration("test", "0123456789"));
        User user = new User("test", "123456", "0123456789");
        assertFalse(customerService.register(user));
    }
}