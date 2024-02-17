package Model;

/**@author Billy Payne
 *
 *  */

public class FirstLevelDivisions {

    //division ID variable
    private int divisionID;

    //divisionName variable
    private String divisionName;

    //countryID variable
    public int country_ID;

    /**
     *
     * @param divisionID database division ID to record
     * @param divisionName database division name to record
     * @param country_ID database country ID to record
     */
    public FirstLevelDivisions(int divisionID, String divisionName, int country_ID) {
        //divisionID getter
        this.divisionID = divisionID;
        //divisionName getter
        this.divisionName = divisionName;
        //countryID getter
        this.country_ID = country_ID;
    }

    /**
     *
     * @return database division ID record
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     *
     * @return database division name record
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     *
     * @return database country ID record
     */
    public int getCountry_ID() {
        return country_ID;
    }

}