package dao.user;

import bean.user.User;

public interface UserDAO {
    public User getLogin(String account, String password);
    public String getAccount(User user);
    public String getLevel(int userID);
    public void register(User user);
    public void updateLevel(int id, String levels);
    public Integer getCustomerID(String account);
}
