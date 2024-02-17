package DBUtility;

/**@author Billy Payne
 *
 *  */

//java sql packages
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** .java page provided by WGU */

public class DBConnection {
    // JDBC URL parts
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//127.0.0.1:3306/";
    private static final String dbName = "client_schedule";
    //JDBC URL
    private static final String jdbcURL = protocol + vendorName + ipAddress + dbName;
    //driver and connection interface
    private static final String MYSQLJDBCDriver = "com.mysql.cj.jdbc.Driver";
    private static Connection conn = null;
    private static final String username = "sqlUser";
    private static final String password = "Passw0rd!";

    /**
     *
     * @return the established connection
     */
    public static Connection startConnection() {
        System.out.println("Attempting database connection...");
        try {
            Class.forName(MYSQLJDBCDriver);
            conn = (Connection)DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connection successful");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return conn;
    }

    /**
     *
     * @return the active connection
     */
    public static Connection getConnection(){
        return conn;
    }

    /**
     * closes the connection
     */
    public static void stopConnection() {
        try {
            conn.close();
            System.out.println("Connection closed");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
