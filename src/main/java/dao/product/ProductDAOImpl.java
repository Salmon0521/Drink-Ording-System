package dao.product;

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
public class ProductDAOImpl implements ProductDAO{
    private final DBConnection dbConnection = new DBConnectionImpl();
    private static final String GET_PRODUCTS_BY_TYPE = "SELECT distinct ProductName FROM product WHERE type = ?";
    private static final String GET_PRODUCTID = "SELECT ProductID FROM product WHERE ProductName = ? AND ice = ? AND size = ? AND sugar = ?";

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
}
