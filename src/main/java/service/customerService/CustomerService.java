package service.customerService;

import bean.customer.Customer;

import javax.servlet.http.HttpSession;

public interface CustomerService {
    public Boolean customerLogin(HttpSession session, String account, String password);
    public Boolean register(Customer customer);
    public void updateLevel(HttpSession session, String account);
}
