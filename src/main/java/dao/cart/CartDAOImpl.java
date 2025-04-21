package dao.cart;

import db_driver.DBConnection;
import db_driver.DBConnectionImpl;
import bean.product.Product;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CartDAOImpl implements CartDAO{

    private final DBConnection dbConnection = new DBConnectionImpl();
    private static final String INSERT_CART = "INSERT INTO addCart(orderID, productID, quantity) VALUES (?,?,?)";
    private static final String DELETE_PRODUCT = "DELETE FROM addCart WHERE orderID = ? AND productID = ?";
    private static final String GET_PRODUCT = "SELECT product.*, addCart.Quantity FROM addCart JOIN product ON addCart.productID = product.productID WHERE orderID = ?";
    private static final String PRODUCT_EXIST = "SELECT quantity FROM addCart WHERE orderID = ? AND productID = ?";
    private static final String UPDATE_QUANTITY = "UPDATE addCart SET quantity = ? WHERE orderID = ? AND productID = ?";

    @Override
    public void insert(int orderID, int productID, int quantity){
        Connection connection = dbConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CART)){
            preparedStatement.setInt(1,orderID);
            preparedStatement.setInt(2,productID);
            preparedStatement.setInt(3,quantity);

            preparedStatement.executeUpdate();
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(int orderID, int productID){
        Connection connection = dbConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT)){
            preparedStatement.setInt(1,orderID);
            preparedStatement.setInt(2,productID);

            preparedStatement.executeUpdate();
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getProduct(int orderID){
        Connection connection = dbConnection.getConnection();
        List<Product> productList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT)){

            preparedStatement.setInt(1, orderID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Integer productID = resultSet.getInt("ProductID");
                    String productName = resultSet.getString("ProductName");
                    String ice = resultSet.getString("ice");
                    String size = resultSet.getString("size");
                    String sugar = resultSet.getString("sugar");
                    Integer quantity = resultSet.getInt("quantity");
                    Integer price = resultSet.getInt("price") * quantity;

                    Product product = new Product(productID, productName, size, price, ice, sugar, quantity);
                    productList.add(product);
                }
            }
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public Integer productExist(int orderID, int productID){
        Connection connection = dbConnection.getConnection();
        Integer quantity = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(PRODUCT_EXIST)){

            preparedStatement.setInt(1, orderID);
            preparedStatement.setInt(2, productID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    quantity = resultSet.getInt("quantity");
                }
            }
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return quantity;
    }

    @Override
    public void updateQuantity(int orderID, int productID, int quantity){
        Connection connection = dbConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUANTITY)){

            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, orderID);
            preparedStatement.setInt(3, productID);

            preparedStatement.executeUpdate();
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
