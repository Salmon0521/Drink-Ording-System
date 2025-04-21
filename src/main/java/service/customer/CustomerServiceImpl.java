package service.customer;

import bean.user.User;
import dao.build.BuildDAO;
import dao.user.UserDAO;
import dao.orders.OrdersDAO;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{
    private final UserDAO userDAO;
    private final BuildDAO buildDAO;
    private final OrdersDAO ordersDAO;

    public CustomerServiceImpl(UserDAO userDAO, BuildDAO buildDAO, OrdersDAO ordersDAO) {
        this.userDAO = userDAO;
        this.buildDAO = buildDAO;
        this.ordersDAO = ordersDAO;
    }

    @Override
    public User login(String account, String password){
        String hashPassword = userDAO.login(account, password);

        User user = null;
        if(hashPassword != null){
            user = userDAO.getUserInfo(account, hashPassword);
        }

        return user;
    }

    @Override
    public Boolean register(User user){
        if(Boolean.FALSE.equals(userDAO.checkRegistration(user.getAccount(), user.getPhone()))){
            userDAO.register(user);
            Integer orderID = ordersDAO.initial();
            Integer userID = userDAO.getUserID(user.getAccount(), user.getPhone());
            buildDAO.insert(userID, orderID);
            return true;
        }
        return false;
    }

    @Override
    public String updateLevel(String account, String phone){
        Integer customerID = userDAO.getUserID(account, phone);
        userDAO.updateLevel(customerID, "VIP");
        return userDAO.getLevel(customerID);
    }
}
