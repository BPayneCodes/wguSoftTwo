package Reports;

import java.util.ArrayList;

/**@author Billy Payne
 *
 *  */

public class memberFirstLevelDivisionReport {
    public String firstLevelDivision;
    public ArrayList<String> customerList;

    /**
     *
     * @param firstLevelDivision database first-level division records
     * @param customerList database customer list records
     */
    public memberFirstLevelDivisionReport(String firstLevelDivision, ArrayList<String> customerList) {
        this.firstLevelDivision = firstLevelDivision;
        this.customerList = customerList;
    }

    /**
     *
     * @return database first-level division records
     */
    public String getFirstLevelDivision() {
        return firstLevelDivision;
    }

    /**
     *
     * @return database customer list records
     */
    //gathers customer database list
    public ArrayList<String> getCustomerList() {
        return customerList;
    }
}