package Model;
import java.time.LocalDateTime;

/**@author Billy Payne
 *
 *  */

public class Appointments {

    //id
    private int appointmentID;

    //header
    private String appointmentTitle;

    //description
    private String appointmentDescription;

    //location
    private String appointmentLocation;

    //type
    private String appointmentType;

    //start time
    private LocalDateTime start;

    //end time
    private LocalDateTime end;

    //end customer ID
    public int customerID;

    //end user ID
    public int userID;
    public int contactID;

    /**
     *
     * @param appointmentID database appointment ID to record
     * @param appointmentTitle database appointment title to record
     * @param appointmentDescription database appointment description to record
     * @param appointmentLocation database appointment location to record
     * @param appointmentType database appointment type to record
     * @param start database appointment start LocalDateTime to record
     * @param end database appointment end LocalDateTime to record
     * @param customerID database customer ID to record
     * @param userID database user ID to record
     * @param contactID database contact ID to record
     */
    public Appointments(int appointmentID, String appointmentTitle, String appointmentDescription,
                        String appointmentLocation, String appointmentType, LocalDateTime start, LocalDateTime end, int customerID,
                        int userID, int contactID) {
        //AppointmentID getter
        this.appointmentID = appointmentID;
        //Appointment header getter
        this.appointmentTitle = appointmentTitle;
        //Appointment description getter
        this.appointmentDescription = appointmentDescription;
        //Appointment Location getter
        this.appointmentLocation = appointmentLocation;
        //Appointment Type getter
        this.appointmentType = appointmentType;
        //Appointment Start getter
        this.start = start;
        //Appointment End getter
        this.end = end;
        //Appointment CustomerID getter
        this.customerID = customerID;
        //Appointment UserID getter
        this.userID = userID;
        //Appointment ContactID getter
        this.contactID = contactID;
    }

    /**
     *
     * @return database appointmentID record
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     *
     * @return database appointment title record
     */
    public String getAppointmentTitle() {
        return appointmentTitle;
    }

    /**
     *
     * @return database appointment description record
     */
    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    /**
     *
     * @return database appointment location record
     */
    public String getAppointmentLocation() {
        return appointmentLocation;
    }

    /**
     *
     * @return database appointment type record
     */
    public String getAppointmentType() {
        return appointmentType;
    }

    /**
     *
     * @return database start LocalDateTime record
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     *
     * @return database end LocalDateTime record
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     *
     * @return database customer ID record
     */
    public int getCustomerID () {
        return customerID;
    }

    /**
     *
     * @return database user ID record
     */
    public int getUserID() {
        return userID;
    }

    /**
     *
     * @return database contact ID record
     */
    public int getContactID() {
        return contactID;
    }

}