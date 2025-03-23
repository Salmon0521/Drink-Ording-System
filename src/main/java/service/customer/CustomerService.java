package service.customer;

import bean.user.User;

import javax.servlet.http.HttpSession;

public interface CustomerService {
    public Boolean customerLogin(HttpSession session, String account, String password);
    public Boolean register(User user);
    public void updateLevel(HttpSession session, String account);
}
