package dao.user;


import db_driver.DBConnection;
import db_driver.DBConnectionImpl;
import bean.user.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    private final DBConnection dbConnection = new DBConnectionImpl();
    private static final String GET_USERINFO = "SELECT phone, level FROM user WHERE account = ? AND password = ?";
    private static final String GET_PASSWORD = "SELECT password FROM user WHERE account = ?";
    private static final String GET_USERID = "SELECT userID FROM user WHERE account = ? AND phone = ?";
    private static final String GET_LEVEL = "SELECT * FROM user WHERE userid = ?";
    private static final String INSERT_USER = "INSERT INTO user(account, password, phone) VALUES (?,?,?)";
    private static final String UPDATE_LEVEL = "UPDATE user SET level = ? WHERE userID = ?";
    private static final String GET_ID = "SELECT user FROM user WHERE account = ?";

    @Override
    public String login(String account, String password){
        String hashPassword = "";
        Connection connection = dbConnection.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_PASSWORD))
        {
            preparedStatement.setString(1, account);

            String hash = "";
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    hash = resultSet.getString("password");
                    if(BCrypt.checkpw(password, hash)){
                        hashPassword = hash;
                        break;
                    }
                }
            }
            connection.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return hashPassword;
    }

    @Override
    public User getUserInfo(String account, String password){
        User user = null;
        Connection connection = dbConnection.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_USERINFO))
        {
            preparedStatement.setString(1, account);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String phone = resultSet.getString("phone");
                    String level = resultSet.getString("level");
                    user = new User(level, phone);
                }
            }
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Boolean checkRegistration(String account, String phone) {
        Integer userID = null;
        Connection connection = dbConnection.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_USERID))
        {
            preparedStatement.setString(1, account);
            preparedStatement.setString(2, phone);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    userID = resultSet.getInt("userID");
                }
            }

            connection.close();
            if (userID == null) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public Integer getUserID(String account, String phone) {
        Integer userID = null;
        Connection connection = dbConnection.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_USERID))
        {
            preparedStatement.setString(1, account);
            preparedStatement.setString(2, phone);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    userID = resultSet.getInt("userID");
                }
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userID;
    }

    @Override
    public String getLevel(int userID){

        String level = null;
        Connection connection = dbConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_LEVEL))
        {
            preparedStatement.setInt(1, userID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()){
                    level = resultSet.getString("level");

                }
                else{
                    return level;
                }
            }
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return level;
    }

    @Override
    public void register(User user){
        Connection connection = dbConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER))
        {
            preparedStatement.setString(1, user.getAccount());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getPhone());

            preparedStatement.executeUpdate();
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public  void updateLevel(int id, String levels){
        Connection connection = dbConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LEVEL))
        {
            preparedStatement.setString(1,levels);
            preparedStatement.setInt(2, id);

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
