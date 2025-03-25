package service.customer;

import bean.user.User;
import dao.build.BuildDAO;
import dao.build.BuildDAOImpl;
import dao.user.UserDAO;
import dao.user.UserDAOImpl;
import dao.orders.OrdersDAO;
import dao.orders.OrdersDAOImpl;

import javax.servlet.http.HttpSession;

public class CustomerServiceImpl implements CustomerService{
    private final UserDAO userDAO = new UserDAOImpl();
    private final BuildDAO buildDAO = new BuildDAOImpl();
    private final OrdersDAO ordersDAO = new OrdersDAOImpl();

    @Override
    public Boolean customerLogin(HttpSession session, String account, String password){
        User user = userDAO.getLogin(account, password);
        if(user != null){
            session.setAttribute("account", user.getAccount());
            session.setAttribute("levels", user.getLevel());
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Boolean register(User user){
        if(!userDAO.checkRegistration(user.getAccount(), user.getPhone())){
            userDAO.register(user);
            Integer orderID = ordersDAO.initial();
            Integer userID = userDAO.getUserID(user.getAccount(), user.getPhone());
            buildDAO.insert(userID, orderID);
            return true;
        }
        return false;
    }

    @Override
    public void updateLevel(HttpSession session, String account){
        Integer customerID = userDAO.getCustomerID(account);
        userDAO.updateLevel(customerID, "VIP");
        session.setAttribute("level", "VIP");
    }
}
