package service.customer;

import bean.user.User;
import org.junit.Before;
import org.junit.Test;

public class UserServiceImplTest {
    private CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        customerService = new CustomerServiceImpl();
    }

    @Test
    public void login() {
        //Boolean logintmp = customerService.customerLogin("123","1234");
        //System.out.print(logintmp);
    }

    @Test
    public void register() {
        User user = new User("test", "123456", "0912345678");
        Boolean registertmp = customerService.register(user);
        System.out.print(registertmp);
    }
}