package Model;

/**@author Billy Payne
 *
 *  */

public class Customers{
    //customerID variable
    private int customerID;

    //customerName variable
    private String customerName;

    //customerAddress variable
    private String customerAddress;

    //customerZip code variable
    private String customerPostalCode;

    //customerMobileNumber variable
    private String customerPhoneNumber;

    public int divisionID;

    /**
     *
     * @param customerID database customer ID to record
     * @param customerName database customer name to record
     * @param customerAddress database customer address to record
     * @param customerPostalCode database customer postal code to record
     * @param customerPhoneNumber database customer phone number to record
     * @param divisionID database division ID to record
     */
    //gather customer table view
    public Customers(int customerID, String customerName, String customerAddress, String customerPostalCode,
                     String customerPhoneNumber, int divisionID) {
        //customer ID
        this.customerID = customerID;
        //customer Name
        this.customerName = customerName;
        //customer Address
        this.customerAddress = customerAddress;
        //customer ZipCode
        this.customerPostalCode = customerPostalCode;
        //customer Mobile Number
        this.customerPhoneNumber = customerPhoneNumber;
        //first level customer division ID
        this.divisionID = divisionID;
    }

    /**
     *
     * @return database customer ID record
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     *
     * @return database customer name record
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     *
     * @return database customer address record
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     *
     * @return database customer ZipCode record
     */
    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    /**
     *
     * @return database customer phone number record
     */
    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    /**
     *
     * @return database division ID record
     */
    public int getDivisionID () {
        return divisionID;
    }

}
