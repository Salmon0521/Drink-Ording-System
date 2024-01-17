package dao.customer;

import bean.customer.Customer;

public interface CustomerDAO {
    public Customer getLogin(String account, String password);
    public String getAccount(Customer customer);
    public Customer getLevel(int customerID);
    public void insert(Customer customer);
    public void updateLevel(int ID, String levels);
    public Integer getCustomerID(String account);
}
