import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDriverLoadingCheck {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("PostgreSQL JDBC driver loaded successfully.");
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load the PostgreSQL JDBC driver.");
            e.printStackTrace();
        }
    }
}
