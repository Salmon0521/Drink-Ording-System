package dao.user;

import bean.user.User;

public interface UserDAO {
    public User getUserInfo(String account, String password);
    public String login(String account, String password);
    public Integer getUserID(String account, String password);
    public Boolean checkRegistration(String account, String phone);
    public String getLevel(int userID);
    public void register(User user);
    public void updateLevel(int id, String levels);
    public Integer getCustomerID(String account);
}
