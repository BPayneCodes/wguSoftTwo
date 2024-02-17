package DBAccess;


import javafx.collections.FXCollections;
import java.sql.PreparedStatement;
import javafx.collections.ObservableList;


/**@author Billy Payne
 *
 *  */

import java.sql.ResultSet;
import java.sql.SQLException;
import DBUtility.DBConnection;
import Model.Users;

public class DBUsers{
    /**
     *
     * @return collection of all user data from mySql
     * @throws SQLException if exception has occurred
     */
    public static ObservableList<Users> selectAllUsersInfo() throws SQLException {
        ObservableList<Users> usersObservableList = FXCollections.observableArrayList();
        //mySql statement to collect all user information from database
        String sql = "SELECT * from users";
        //database connection
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        //execute mySql query statements
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            //get userID
            int userID = rs.getInt("User_ID");
            //get userName
            String userName = rs.getString("User_Name");
            //get passWord
            String userPassword = rs.getString("Password");
            //new user profile
            Users user = new Users(userID, userName, userPassword);
            //adds new user to database
            usersObservableList.add(user);
        }
        return usersObservableList;
    }
}