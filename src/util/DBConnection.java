package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static Connection connection = null;

    public static Connection getConnection() {
//        if (connection == null) {
//            try {
//                Class.forName("");
//                connection = DriverManager.getConnection("", "", "");
//            } catch (ClassNotFoundException | SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return connection;

        if (connection == null) {
            try {
                Properties properties = DBPropertyUtil.loadProperties();
                String url = properties.getProperty("db.url");
                String username = properties.getProperty("db.username");
                String password = properties.getProperty("db.password");
                String driver = properties.getProperty("db.driver");

                Class.forName(driver);

                connection = DriverManager.getConnection(url, username, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;

    }
}
