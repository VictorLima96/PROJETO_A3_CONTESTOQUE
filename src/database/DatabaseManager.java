package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gerenciador de conexão com banco de dados SQLite
 * Implementa padrão Singleton thread-safe
 */
public class DatabaseManager {
    
    private static DatabaseManager instance;
    private Connection connection;
    private static final String DB_URL = "jdbc:sqlite:estoque.db";
    
    // Construtor privado para Singleton
    private DatabaseManager() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection(DB_URL);
            System.out.println("✓ Conexão com banco de dados estabelecida!");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver SQLite não encontrado!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados!");
            e.printStackTrace();
        }
    }
    
    /**
     * Retorna a instância única do DatabaseManager (thread-safe)
     */
    public static synchronized DatabaseManager getInstance() {
        if (instance == null || !isConnectionValid()) {
            instance = new DatabaseManager();
        }
        return instance;
    }
    
    /**
     * Retorna a conexão ativa com o banco
     */
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter conexão!");
            e.printStackTrace();
        }
        return connection;
    }
    
    /**
     * Verifica se a conexão está válida
     */
    private static boolean isConnectionValid() {
        try {
            return instance != null && 
                   instance.connection != null && 
                   !instance.connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
    
    /**
     * Fecha a conexão com o banco de dados
     */
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("✓ Conexão fechada com sucesso!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão!");
            e.printStackTrace();
        }
    }
    
    /**
     * Testa se o banco está acessível
     */
    public boolean testConnection() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}