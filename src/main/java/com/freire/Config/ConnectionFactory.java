package com.freire.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:postgresql://localhost:5432/aluno";
    private static final String USER = "postgres";
    private static final String PASSWORD = System.getenv("DB_PASSWORD");
    private static final ConnectionFactory INSTANCE = new ConnectionFactory();

    private ConnectionFactory() {}

    public static ConnectionFactory getInstance(){
        return INSTANCE;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD);
    }
}

