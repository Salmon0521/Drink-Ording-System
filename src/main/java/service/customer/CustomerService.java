package service.customer;

import bean.user.User;

public interface CustomerService {
    public User login(String account, String password);
    public Boolean register(User user);
    public String updateLevel(String account, String phone);
}
