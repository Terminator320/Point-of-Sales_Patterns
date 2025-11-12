package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1/pos";
        String username = "root";
        String password = "";

        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
}
