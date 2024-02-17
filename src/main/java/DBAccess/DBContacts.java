package DBAccess;

/**@author Billy Payne
 *
 *  */

import DBUtility.DBConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Model.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;

public class DBContacts {
    /**
     *
     * @return collection of every contact data from mySql
     * @throws SQLException if exception has occurred
     */
    public static ObservableList<Contacts> selectAllContactsInfo() throws SQLException {
        ObservableList<Contacts> contactsObservableList = FXCollections.observableArrayList();
        //mySql statement to gather all contact info
        String sql = "SELECT * from contacts";
        //Database Connection Getter
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            //gather contactID
            int contactID = rs.getInt("Contact_ID");
            //gather contactName
            String contactName = rs.getString("Contact_Name");
            //gather contactEmail
            String contactEmail = rs.getString("Email");
            Contacts contact = new Contacts(contactID, contactName, contactEmail);
            contactsObservableList.add(contact);
        }
        return contactsObservableList;
    }
}