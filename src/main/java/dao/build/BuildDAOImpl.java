package dao.build;

import DBConnection.DBConnection;
import DBConnection.DBConnectionImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuildDAOImpl implements BuildDAO {
    private final DBConnection dbConnection = new DBConnectionImpl();
    private static final String GET_ORDERID = "SELECT build.OrderID FROM BUILD JOIN orders ON orders.OrderID = build.OrderID WHERE CUSTOMERID = ? and STATUS = ?";
    private static final String INSERT_BUILD = "INSERT INTO build(orderID, customerID) VALUES (?,?)";
    public List<Integer> getOrderID(int customerID, int status){
        Connection connection = dbConnection.getConnection();
        List<Integer> OrderIDList = new ArrayList<>();
        Integer orderID = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDERID)){

            preparedStatement.setInt(1, customerID);
            preparedStatement.setInt(2, status);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    orderID = resultSet.getInt("orderID");

                    OrderIDList.add(orderID);
                }
            }
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return OrderIDList;
    }

    public void insert(int customerID, int orderID){
        Connection connection = dbConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BUILD)){

            preparedStatement.setInt(1, orderID);
            preparedStatement.setInt(2, customerID);

            preparedStatement.executeUpdate();
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
