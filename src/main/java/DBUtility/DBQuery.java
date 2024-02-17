package DBUtility;


import java.sql.SQLException;

/**@author Billy Payne
 *
 *  */

import java.sql.Connection;

import java.sql.PreparedStatement;


public class DBQuery {

    //prepared sql Statement to open connection to database query
    private static PreparedStatement preparedStatement;

    //javadoc

    /**
     *
     * @param conn bridge to mySQL database
     * @param sqlStatement statements from sql to insert,remove,and update
     * @throws SQLException if exception has occurred
     */

    //database connection
    public static void selectPreparedStatementInfo(Connection conn, String sqlStatement) throws SQLException {
        preparedStatement = conn.prepareStatement(sqlStatement);
    }

    // .getprepared statement function

    /**
     *
     * @return statement created
     */
    public static PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

}