package service.customerService;

import bean.customer.Customer;
import org.junit.Before;
import org.junit.Test;

public class CustomerServiceImplTest {
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
        Customer customer = new Customer();
        customer.setAccount("123");
        customer.setPassword("1212");
        customer.setPhone("0912345678");
        Boolean registertmp = customerService.register(customer);
        System.out.print(registertmp);
    }
}