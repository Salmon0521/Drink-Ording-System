package dao.user;

import bean.user.User;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;
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
        User user = new User("test", BCrypt.hashpw("123456", BCrypt.gensalt()), "0912345678");
        userDAO.register(user);
        assertTrue(userDAO.checkRegistration("test", "0912345678"));
    }

    @Test
    public void test_login() {
        String hash = userDAO.login("test", "test");
        assertEquals("$2a$10$W0BjSDWJzPkIgqMuuHvwfOCADLgDGo/fty71DabNE3vnGh398Dhui", hash);
    }

    @Test
    public void test_getUserInfo() {
        User user = userDAO.getUserInfo("test", "$2a$10$W0BjSDWJzPkIgqMuuHvwfOCADLgDGo/fty71DabNE3vnGh398Dhui");
        assertEquals("普通會員", user.getLevel());
        assertEquals("0123456789", user.getPhone());
    }

    @Test
    public void test_getLevel() {
        Integer id = userDAO.getUserID("test", "0123456789");
        String level = userDAO.getLevel(id);
        assertEquals("普通會員", level);
    }
}