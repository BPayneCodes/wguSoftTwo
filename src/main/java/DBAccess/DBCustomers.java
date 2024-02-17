package DBAccess;

/**@author Billy Payne
 *
 *  */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Model.Customers;
import java.sql.SQLException;
import DBUtility.DBConnection;


public class DBCustomers {
    /**
     *
     * @return collection of all customer information from mySql
     * @throws SQLException if exception has occurred
     */
    public static ObservableList<Customers> selectAllCustomersDB() throws SQLException {
        ObservableList<Customers> customersObservableList = FXCollections.observableArrayList();
        //mySql statement to collect all customer info
        String sql = "SELECT * from customers";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            //get customerID
            int customerID = rs.getInt("Customer_ID");
            //get customerName
            String customerName = rs.getString("Customer_Name");
            //get customerAddress
            String customerAddress = rs.getString("Address");
            //get customerZipCode
            String customerPostalCode = rs.getString("Postal_Code");
            //get customerMobilePhone
            String customerPhone = rs.getString("Phone");
            //get first level division ID
            int divisionID = rs.getInt("Division_ID");
            //new customer Profile
            Customers customer = new Customers(customerID, customerName, customerAddress, customerPostalCode, customerPhone, divisionID);
            //add customer to database
            customersObservableList.add(customer);
        }
        return customersObservableList;
    }
}