package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Database {
    private static Connection connection;
    private static final String DB_NAME = "WME";
    private static String dbAddress = "localhost";
    private static int dbPort = 5432;
    private static String dbUsername = "postgres";
<<<<<<< HEAD
    private static String dbPassword = "930yuqiang";
    private static String postgresSQLUrl = "jdbc:postgresql://" + dbAddress + "/" + DB_NAME;
//    private static String postgresSQLUrl = "jdbc:postgresql://" + dbAddress + ":" + dbPort + "/" + DB_NAME;
=======
    private static String dbPassword = "1111";
    private static String postgresSQLUrl = "jdbc:postgresql://" + dbAddress + ":" + dbPort + "/" + DB_NAME;
    private static Database INSTANCE;
    private static Lock lock = new ReentrantLock();

    private Database() {
    }

    public static Database getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (lock) {
                if (INSTANCE == null)
                    INSTANCE = new Database();
            }
        }
        return INSTANCE;
    }
>>>>>>> master

    /**
     * Lazy instantiation of the database connection
     *
     * @return connection
     */
    public Connection getConnection() {
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}
