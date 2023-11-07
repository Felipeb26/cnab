package com.batsworks.cnab.config.database;

import java.sql.*;

public class DataBaseDriver {

    private static final String DB_URL = "jdbc:sqlite:db/cnab.db";
    private static Connection conn;
    public static Connection connection() {
        try {
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("DATABASE CONNECTED");
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
