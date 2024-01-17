package dao.customer;


import DBConnection.DBConnection;
import DBConnection.DBConnectionImpl;
import bean.customer.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAOImpl implements CustomerDAO{

    private DBConnection dbConnection = new DBConnectionImpl();
    private  static  final String GET_LOGIN = "SELECT * FROM customer WHERE account = ? AND password = ?";
    private  static  final String GET_ACCOUNT = "SELECT account FROM customer WHERE account = ?";
    private static final String GET_LEVEL = "SELECT * FROM customer WHERE customerid = ?";
    private static final String INSERT_CUSTOMER = "INSERT INTO customer(account, password, phone) VALUES (?,?,?)";
    private static final String UPDATE_LEVEL = "UPDATE customer SET level = ? WHERE customerid = ?";
    private static final String GET_ID = "SELECT customerID FROM customer WHERE account = ?";
    Customer customer = null;

    @Override
    public Customer getLogin(String account, String password){

        Connection connection = dbConnection.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_LOGIN))
        {
            preparedStatement.setString(1, account);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    customer = new Customer();
                    customer.setAccount(resultSet.getString("account"));
                    customer.setPassword(resultSet.getString("password"));
                    customer.setLevel(resultSet.getString("level"));
                }
                else{
                    return customer;
                }
            }
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public String getAccount(Customer customer){

        Connection connection = dbConnection.getConnection();
        String account = null;

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT))
        {
            preparedStatement.setString(1, customer.getAccount());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    account = resultSet.getString("account");
                }
            }
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return account;
    }

    @Override
    public Customer getLevel(int customerID){

        Connection connection = dbConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_LEVEL))
        {
            preparedStatement.setInt(1,customerID);
            customer = null;

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()){
                    customer.setLevel(resultSet.getString("level"));
                }
                else{
                    return customer;
                }
            }
            connection.close();
        }catch (SQLException e) {


            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public void insert(Customer customer){
        Connection connection = dbConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER))
        {
            preparedStatement.setString(1,customer.getAccount());
            preparedStatement.setString(2,customer.getPassword());
            preparedStatement.setString(3,customer.getPhone());

            preparedStatement.executeUpdate();
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public  void updateLevel(int ID, String levels){
        Connection connection = dbConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LEVEL))
        {
            preparedStatement.setString(1,levels);
            preparedStatement.setInt(2,ID);

            preparedStatement.executeUpdate();
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer getCustomerID(String account){
        Connection connection = dbConnection.getConnection();
        int customerID = 0;

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_ID))
        {
            preparedStatement.setString(1, account);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    customerID = resultSet.getInt("customerID");
                }
                else{
                    return customerID;
                }
            }
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return customerID;
    }
}
