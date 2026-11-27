package br.com.zenon.fraud.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private ConnectionFactory() {}

    public static Connection getConnection() {

        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3309/zenon_fraud",
                    "root",
                    "pass123");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao contectar com o BD", e);
        }
    }
}
