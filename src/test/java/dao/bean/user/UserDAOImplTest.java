package dao.bean.user;

import bean.user.User;
import dao.user.UserDAO;
import dao.user.UserDAOImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import util.DatabaseUtil;

import static org.junit.Assert.*;

public class UserDAOImplTest {

    private UserDAO userDAO;

    @Before
    public void setUp() {
        userDAO = new UserDAOImpl();
        DatabaseUtil.initDatabase();
    }

    @Test
    public void test_registerUser() {
        assertFalse(userDAO.checkRegistration("test", "0912345678"));
        User user = new User("test", "123456", "0912345678");
        userDAO.register(user);
        assertTrue(userDAO.checkRegistration("test", "0912345678"));
    }

    @Test
    @Ignore("there are one more same account")
    public void getID() {
        Integer id = userDAO.getCustomerID("test");
        System.out.println(id);
    }

    @Test
    public void getLevel() {
        assertEquals("普通會員", userDAO.getLevel(1));
    }

    @Test
    public void update() {
        userDAO.updateLevel(1,"VIP");
        assertEquals("VIP", userDAO.getLevel(1));
    }
}