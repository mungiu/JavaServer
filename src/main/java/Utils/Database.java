package Utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private static Connection connection;

    private static int dbPort = 5432;
    public static final String DB_NAME = "WME";
    private static String dbAddress = "localhost";
    private static String dbUsername = "postgres";
    private static String dbPassword = "Jwan0090j";
    private static String postgresSQLUrl = "jdbc:postgresql://" + dbAddress + ":"+dbPort+"/" + DB_NAME;

    /**
     * Lazy instantiation of the database connection
     *
     * @return connection
     */
    public static Connection getConnection() {
        if (connection != null)
            return connection;
        return getNewConnection();
    }

    /**
     * Creates a new database connection through JDBC driver
     *
     * @return connection
     */
    private static Connection getNewConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(postgresSQLUrl, dbUsername, dbPassword);
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver not found");
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}
