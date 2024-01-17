package dao.product;

import DBConnection.DBConnection;
import DBConnection.DBConnectionImpl;
import bean.product.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO{
    private DBConnection dbConnection = new DBConnectionImpl();
    private static final String GET_PRODUCTS_BY_TYPE = "SELECT distinct ProductName FROM product WHERE type = ?";
    private static final String GET_ADD_INFO = "SELECT * FROM product WHERE ProductID = ?";
    private static final String GET_PRODUCTID = "SELECT ProductID FROM product WHERE ProductName = ? AND ice = ? AND size = ? AND sugar = ?";
    private static final String GET_TYPE = "SELECT DISTINCT type FROM product";

    Product product = null;
    @Override
    public List<Product> getProductsByType(String productType) {
        List<Product> productList = new ArrayList<>();

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCTS_BY_TYPE)) {

            preparedStatement.setString(1, productType);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setName(resultSet.getString("ProductName"));
                    productList.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public List<Product> getMilk() {
        return getProductsByType("Milk");
    }

    @Override
    public List<Product> getBoutique() {
        return getProductsByType("Boutique hand coffee");
    }
    @Override
    public List<Product> getLatte() {
        return getProductsByType("Latte");
    }
    @Override
    public List<Product> getTea() {
        return getProductsByType("Tea");
    }
    @Override
    public List<Product> getChocolate() {
        return getProductsByType("Chocolate");
    }
    @Override
    public List<Product> getCoffee() {
        return getProductsByType("Coffee");
    }
    @Override
    public Product getAddInfo(int ProductID) {
        Connection connection = dbConnection.getConnection();
        List<Product> MilkList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ADD_INFO))
        {
            preparedStatement.setInt(1, ProductID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if(resultSet.next()){
                    product = new Product();
                    product.setSize(resultSet.getString("size"));
                    product.setPrice(resultSet.getInt("price"));
                    product.setName(resultSet.getString("ProductName"));
                    product.setIce(resultSet.getString("ice"));
                    product.setSugar(resultSet.getString("sugar"));
                }
                else{
                    return product;
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public Integer getProductID(Product product){
        Connection connection = dbConnection.getConnection();
        Integer productID = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCTID))
        {
            preparedStatement.setString(1,product.getName());
            preparedStatement.setString(2,product.getIce());
            preparedStatement.setString(3,product.getSize());
            preparedStatement.setString(4,product.getSugar());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    productID = resultSet.getInt("ProductID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return productID;
    }

    @Override
    public List<Product> getType(){
        List<Product> typeList = new ArrayList<>();
        Connection connection = dbConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_TYPE))
        {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setType(resultSet.getString("type"));

                    typeList.add(product);
                }
            }
            connection.close();
        } catch (SQLException e) {


            e.printStackTrace();
        }
        return typeList;
    }

}
