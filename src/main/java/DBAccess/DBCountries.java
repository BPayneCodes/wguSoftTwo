package DBAccess;

/**@author Billy Payne
 *
 *  */

import Model.Countries;
import javafx.collections.ObservableList;
import java.sql.*;
import DBUtility.DBConnection;
import javafx.collections.FXCollections;

public class DBCountries {
    /**
     *
     * @return collection of all country information from mySql
     * @throws SQLException if exception has occurred
     */
    public static ObservableList<Countries> selectAllCountriesInfo() throws SQLException {
        ObservableList<Countries> countriesObservableList = FXCollections.observableArrayList();
        //mySql statement to collect all countries
        String sql = "SELECT * from countries";
        //Database Connection Getter
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        //Query Execute
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            //Country ID Getter
            int countryID = rs.getInt("Country_ID");
            //Country Name Getter
            String countryName = rs.getString("Country");
            //new country profile
            Countries country = new Countries(countryID, countryName);
            //adds new country to database
            countriesObservableList.add(country);
        }
        return countriesObservableList;
    }
}