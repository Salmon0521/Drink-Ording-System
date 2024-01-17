package dao.user.customer;

import bean.customer.Customer;
import dao.customer.CustomerDAO;
import dao.customer.CustomerDAOImpl;
import org.junit.Before;
import org.junit.Test;

public class CustomerDAOImplTest {

    private CustomerDAO customerDAO;
    @Before
    public void setUp() throws Exception {
        customerDAO = new CustomerDAOImpl();
    }

    @Test
    public void insert() {
        Customer customer = new Customer();
        customer.setAccount("333");
        customer.setPassword("123456");
        customer.setPhone("0912345678");
        customerDAO.insert(customer);
    }

    @Test
    public void get() {
        Customer customer = customerDAO.getLogin("test","100");
        System.out.println(customer.getLevel());
    }

    @Test
    public void update() {
        customerDAO.updateLevel(1,"普通會員");
    }

    @Test
    public void getLevel() {
        Customer customer = customerDAO.getLevel(11);
        System.out.println(customer.getLevel());
    }

    @Test
    public void getID() {
        Integer id = customerDAO.getCustomerID("100");
        System.out.println(id);
    }
}