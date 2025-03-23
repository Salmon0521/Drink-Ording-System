package dao.orders;

import db_driver.DBConnection;
import db_driver.DBConnectionImpl;
import bean.orders.Orders;
import bean.product.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAOImpl implements OrdersDAO {

    private DBConnection dbConnection = new DBConnectionImpl();
    private static final String GET_ORDER = "SELECT * FROM orders JOIN build ON build.orderID = orders.orderID WHERE CustomerID = ? AND Status = 0 ORDER BY dates ASC";
    private static final String INITIAL_ORDER = "INSERT INTO orders(status) VALUES (?)";
    private static final String GET_PRODUCT = "SELECT addCart.Quantity, product.*\n" +
                                              "FROM addCart\n" +
                                              "JOIN product ON addCart.productID = product.ProductID\n" +
                                              "JOIN orders ON addCart.orderID = orders.orderID\n" +
                                              "JOIN build ON build.orderID = orders.OrderID\n" +
                                              "JOIN customer ON build.CustomerID = customer.CustomerID\n" +
                                              "WHERE build.CustomerID = ? AND dates = ?;\n";
    private static final String GET_MAX_ORDERID = "SELECT MAX(orderID) FROM orders";
    private static final String INSERT_ORDER = "INSERT INTO orders(customerID, productID, dates, amount, quantity) VALUES (?,?,?,?,?)";
    private static final String UPDATE_ORDER = "UPDATE orders SET dates = ?, amount = ?, status = ? WHERE orderID = ?";

    @Override
    public List<Orders> getOrders(int customerID){
        Connection connection = dbConnection.getConnection();
        List<Orders> ordersList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER)){

            preparedStatement.setInt(1, customerID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                Orders orders = new Orders();
                String dates = resultSet.getString("dates");
                orders.setDates(dates);
                Integer price = resultSet.getInt("amount");
                while (resultSet.next()){
                    dates = resultSet.getString("dates");
                    if (!orders.getDates().equals(dates)){
                        orders.setAmount(price);
                        ordersList.add(orders);
                        price = 0;
                        orders = new Orders();
                        orders.setDates(dates);
                        price += resultSet.getInt("amount");
                    }
                    else{
                        price += resultSet.getInt("amount");
                    }
                }
                connection.close();
                orders.setAmount(price);
                ordersList.add(orders);
                return ordersList;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersList;
    }

    @Override
    public List<Product> getOrdersByDate(int customerID, String date){
        Connection connection = dbConnection.getConnection();
        List<Product> productList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT)){
            preparedStatement.setInt(1, customerID);
            preparedStatement.setString(2, date);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()){
                    Product product = new Product();
                    product.setName(resultSet.getString("ProductName"));
                    product.setIce(resultSet.getString("ice"));
                    product.setSize(resultSet.getString("size"));
                    product.setSugar(resultSet.getString("sugar"));
                    product.setQuantity(resultSet.getInt("quantity"));
                    product.setPrice(resultSet.getInt("price"));
                    productList.add(product);
                }
                connection.close();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public Integer initial(){
        Connection connection = dbConnection.getConnection();
        Integer orderID = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INITIAL_ORDER);
            preparedStatement.setInt(1, 1);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(GET_MAX_ORDERID);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            orderID = resultSet.getInt(1);

            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return orderID;
    }

    public void insert(int customerID, int productID, String dates, int amount, int quantity){
        Connection connection = dbConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER)){
            preparedStatement.setInt(1, customerID);
            preparedStatement.setInt(2,productID);
            preparedStatement.setString(3,dates);
            preparedStatement.setInt(4,amount);
            preparedStatement.setInt(5,quantity);

            preparedStatement.executeUpdate();
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int orderID, String dates, int amount, int status){
        Connection connection = dbConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER)){
            preparedStatement.setString(1,dates);
            preparedStatement.setInt(2,amount);
            preparedStatement.setInt(3,status);
            preparedStatement.setInt(4,orderID);

            preparedStatement.executeUpdate();
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
