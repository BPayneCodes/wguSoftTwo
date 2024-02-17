package Reports;

/**@author Billy Payne
 *
 *  */

public class AppointmentReportType {
    //appointmentType variable
    public String appointmentType;
    //appointmentTotal variable
    public int appointmentTotal;

    /**
     *
     * @param appointmentType database appointment type to record
     * @param appointmentTotal database appointment total to record
     */
    public AppointmentReportType(String appointmentType, int appointmentTotal) {
        //appointmentType getter
        this.appointmentType = appointmentType;
        //appointmentTotal getter
        this.appointmentTotal = appointmentTotal;
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
     * @return database appointment sum record
     */
    public int getAppointmentTotal() {
        return appointmentTotal;
    }

}