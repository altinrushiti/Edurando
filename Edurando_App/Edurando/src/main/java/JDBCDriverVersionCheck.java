import org.postgresql.Driver;

public class JDBCDriverVersionCheck {
    public static void main(String[] args) {
        Driver driver = new Driver();
        System.out.println("PostgreSQL JDBC driver version: " + driver.getMajorVersion() + "." + driver.getMinorVersion());
    }
}
