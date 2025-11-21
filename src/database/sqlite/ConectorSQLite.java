package database.sqlite;

import java.sql.*;

public class ConectorSQLite {
    static String URL = "jdbc:sqlite:sistema.db";
    static String username = "teste";
    static String password = "teste";

    public static Connection obterConexao() {
        try {
            return DriverManager.getConnection(URL, username, password);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getLocalizedMessage());
            return null;
        }
    } 
}