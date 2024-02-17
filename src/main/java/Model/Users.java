package Model;

/**@author Billy Payne
 *
 *  */

public class Users {

    //userID variable
    public int userID;

    //userName variable
    public String userName;

    //userPassword variable
    public String userPassword;

    /**
     *
     * @param userID database user ID to record
     * @param userName database username to record
     * @param userPassword database userpassword to record
     */
    public Users(int userID, String userName, String userPassword) {
        //userId getter
        this.userID = userID;
        //userName getter
        this.userName = userName;
        //userPassword getter
        this.userPassword = userPassword;
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
     * @return database user name record
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @return database user password record
     */
    public String getUserPassword() {
        return userPassword;
    }
}
