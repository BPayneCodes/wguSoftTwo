package Model;

/**@author Billy Payne
 *
 *  */

public class Contacts {

    //contactID variable
    public int contactID;

    //contactName variable

    public String contactName;

    //contactEmail variable
    public String contactEmail;

    /**
     *
     * @param contactID database contact ID to record
     * @param contactName database contact name to record
     * @param contactEmail database contact email to record
     */
    public Contacts(int contactID, String contactName, String contactEmail) {
        //contactID getter
        this.contactID = contactID;
        //contactName getter
        this.contactName = contactName;
        //contactEmail getter
        this.contactEmail = contactEmail;
    }

    /**
     *
     * @return database contact ID
     */
    public int collectDBId() {
        return contactID;
    }

    /**
     *
     * @return database contact name
     */
    public String collectContactDBName() {
        return contactName;
    }

    /**
     *
     * @return database contact email
     */
    public String collectContactDBEmail() {
        return contactEmail;
    }
}