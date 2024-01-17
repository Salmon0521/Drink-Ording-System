package dao.build;

import java.util.List;

public interface BuildDAO {
    public List<Integer> getOrderID(int customerID, int status);
    public void insert(int customerID, int orderID);
}
