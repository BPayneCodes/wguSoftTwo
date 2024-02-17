package Model;

/**@author Billy Payne
 *
 *  */

public class Countries {
    //countryID variable
    private int countryID;
    //countryName variable
    private String countryName;

    /**
     *
     * @param countryID database country ID to record
     * @param countryName database country name to record
     */
    public Countries(int countryID, String countryName) {
        //countryID getter
        this.countryID = countryID;
        //countryName getter
        this.countryName = countryName;
    }

    /**
     *
     * @return database country ID record
     */
    public int collectCountryDBID() {
        return countryID;
    }

    /**
     *
     * @return database country name record
     */
    public String collectCountryDBName() {
        return countryName;
    }
}