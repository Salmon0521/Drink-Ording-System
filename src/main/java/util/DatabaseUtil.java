package util;

import db_driver.DBConnection;
import db_driver.DBConnectionImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {
    private static final String DROP_TABLE = "DB/MySQL/dropAllTable.sql";
    private static final String CREATE_TABLE = "DB/MySQL/createAllTable.sql";
    private static final String INSERT_DEFAULT_PRODUCT = "DB/MySQL/insertDefaultProduct.sql";
    private static final String INSERT_DEFAULT_USER = "DB/MySQL/insertDefaultUser.sql";
    private static DatabaseUtil databaseUtil = null;

    private DatabaseUtil() {}

    public static DatabaseUtil getDatabaseUtil() {
        if (databaseUtil == null) {
            databaseUtil = new DatabaseUtil();
        }

        return databaseUtil;
    }

    private static StringBuilder getSQLFromFile(String filePath) {
        StringBuilder sql = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sql.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sql;
    }

    public static void executeSQLFile(String filePath) {
        DBConnection dbConnection = new DBConnectionImpl();
        Connection connection = dbConnection.getConnection();

        try (Statement statement = connection.createStatement()) {
            StringBuilder sql = getSQLFromFile(filePath);
            for (String sqlStatement : sql.toString().split(";")) {
                if (!sqlStatement.trim().isEmpty()) {
                    statement.execute(sqlStatement);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void initDatabase(){
        executeSQLFile(DROP_TABLE);
        executeSQLFile(CREATE_TABLE);
        executeSQLFile(INSERT_DEFAULT_PRODUCT);
        executeSQLFile(INSERT_DEFAULT_USER);
    }
}
