package service.customerService;

import bean.customer.Customer;
import dao.build.BuildDAO;
import dao.build.BuildDAOImpl;
import dao.customer.CustomerDAO;
import dao.customer.CustomerDAOImpl;
import dao.orders.OrdersDAO;
import dao.orders.OrdersDAOImpl;

import javax.servlet.http.HttpSession;

public class CustomerServiceImpl implements CustomerService{
    private final CustomerDAO customerDAO = new CustomerDAOImpl();
    private final BuildDAO buildDAO = new BuildDAOImpl();
    private final OrdersDAO ordersDAO = new OrdersDAOImpl();

    @Override
    public Boolean customerLogin(HttpSession session, String account, String password){
        Customer customer = customerDAO.getLogin(account, password);
        if(customer != null){
            session.setAttribute("account", customer.getAccount());
            session.setAttribute("levels", customer.getLevel());
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Boolean register(Customer customer){
        String account = customerDAO.getAccount(customer);
        if(account == null){
            customerDAO.insert(customer);
            Integer orderID = ordersDAO.initial();
            Integer customerID = customerDAO.getCustomerID(customerDAO.getAccount(customer));
            buildDAO.insert(customerID, orderID);
            return true;
        }
        return false;
    }

    @Override
    public void updateLevel(HttpSession session, String account){
        Integer customerID = customerDAO.getCustomerID(account);
        customerDAO.updateLevel(customerID, "VIP");
        session.setAttribute("level", "VIP");
    }
}
