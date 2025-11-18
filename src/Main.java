import database.DatabaseManager;
import ui.TelaLogin;

public class Main {
    public static void main(String[] args) {
        DatabaseManager.initDatabase();
        new TelaLogin().setVisible(true);
    }
}