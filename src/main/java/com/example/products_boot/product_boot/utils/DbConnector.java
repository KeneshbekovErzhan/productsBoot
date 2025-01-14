package com.example.products_boot.product_boot.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    private Connection connection = null;
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public DbConnector(){
        if(connection == null){
            try {
                connection = DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/postgres?user=postgres&password=Admin");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public Connection getConnection(){
        return connection;
    }
}
