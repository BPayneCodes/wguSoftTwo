package DBAccess;


import Model.FirstLevelDivisions;
import javafx.collections.FXCollections;


/**@author Billy Payne
 *
 *  */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import DBUtility.DBConnection;


public class DBFirstLevelDivisions {
    /**
     *
     * @return collection of all first-level division data from mySql
     * @throws SQLException if exception has occurred
     */
    public static ObservableList<FirstLevelDivisions> selectAllFirstLevelDivisionsInfo() throws SQLException {
        ObservableList<FirstLevelDivisions> firstLevelDivisionsObservableList = FXCollections.observableArrayList();
        //mySql statement that collects all first level divisions from database
        String sql = "SELECT * from first_level_divisions";
        //database connection
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        //execute query statements
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            //get division ID
            int divisionID = rs.getInt("Division_ID");
            //get divisionName
            String divisionName = rs.getString("Division");
            //get Country ID
            int country_ID = rs.getInt("COUNTRY_ID");
            //new first level division profile
            FirstLevelDivisions firstLevelDivision = new FirstLevelDivisions(divisionID, divisionName, country_ID);
            //adds new profile to database
            firstLevelDivisionsObservableList.add(firstLevelDivision);
        }
        return firstLevelDivisionsObservableList;
    }
}