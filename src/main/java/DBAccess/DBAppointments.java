package DBAccess;

/**@author Billy Payne
 *
 *  */

import DBUtility.DBConnection;
import java.sql.*;
import javafx.collections.ObservableList;
import java.time.LocalDateTime;

import Model.Appointments;
import javafx.collections.FXCollections;


public class DBAppointments {
    /**
     *
     * @return pulls list of all appointment data with timestamps converted to system time
     * @throws SQLException if exception has occurred
     */
    public static ObservableList<Appointments> collectAllAppointmentsInfo() throws SQLException {
        ObservableList<Appointments> appointmentsObservableList = FXCollections.observableArrayList();
        //mySql Statement to collect all appointments
        String sql = "SELECT * from appointments";
        //Database mySql Connection
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        //run Query
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            //gather AppointmentID
            int appointmentID = rs.getInt("Appointment_ID");
            //gather Title Header
            String appointmentTitle = rs.getString("Title");
            //gather Description
            String appointmentDescription = rs.getString("Description");
            //gather Location
            String appointmentLocation = rs.getString("Location");
            //gather Type
            String appointmentType = rs.getString("Type");

            // time is established by user
            //start DateTime
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            //end DateTime
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();

            //gather CustomerID
            int customerID = rs.getInt("Customer_ID");
            //gather UserID
            int userID = rs.getInt("User_ID");
            //gather ContactID
            int contactID = rs.getInt("Contact_ID");
            //Appointment Collection Info verification columns
            Appointments appointment = new Appointments(appointmentID, appointmentTitle, appointmentDescription,
                    appointmentLocation, appointmentType, start, end, customerID, userID, contactID);
            appointmentsObservableList.add(appointment);
        }
        return appointmentsObservableList;
    }
}
