package Reports;

/**@author Billy Payne
 *
 *  */

public class AppointmentReportMonth {

    //appointmentMonth variable
    public String appointmentMonth;

    //appointmentTotal variable
    public int appointmentTotal;

    /**
     *
     * @param appointmentMonth database appointment month record
     * @param appointmentTotal database appointment sum record
     */
    public AppointmentReportMonth(String appointmentMonth, int appointmentTotal) {
        //appointmentMonth getter
        this.appointmentMonth = appointmentMonth;
        //appointmentTotal getter
        this.appointmentTotal = appointmentTotal;
    }

    /**
     *
     * @return database appointment month record
     */
    public String getAppointmentMonth() {
        return appointmentMonth;
    }

    /**
     *
     * @return database appointment sum record
     */
    public int getAppointmentTotal() {
        return appointmentTotal;
    }
}