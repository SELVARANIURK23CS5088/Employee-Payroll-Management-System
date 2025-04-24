package com.yourcompany.db;

import java.sql.*;

public class DBConnection {
    // TODO: change URL, USER, PASS to match your environment
    private static final String URL  = "jdbc:mysql://localhost:3306/payrolldb?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "password";

    static {
        try {
            // MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load JDBC driver", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}

