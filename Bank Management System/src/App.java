import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new MainFrame("Bank Management System");
        new DB();
    }
}
