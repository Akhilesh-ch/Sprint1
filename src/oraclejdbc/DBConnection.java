package oraclejdbc;

import java.sql.*;

public class DBConnection {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521/XE";
    private static final String USER = "sprint1";
    private static final String PASSWORD = "sprint1";

    public static Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        con.setAutoCommit(true); 
        return con;
    }
}
