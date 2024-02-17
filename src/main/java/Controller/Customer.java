package Controller;

import DBUtility.DBQuery;
import Model.*;
import javax.swing.JOptionPane;
import DBAccess.*;
import javafx.fxml.FXMLLoader;
/**@author Billy Payne
 *
 * */
import java.util.Objects;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.function.Function;
import java.sql.SQLException;



import DBUtility.DBConnection;
import javafx.collections.FXCollections;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;


import javafx.stage.Stage;
import java.sql.Timestamp;


import java.time.LocalDateTime;
import javafx.fxml.FXML;
import javafx.scene.Node;
import java.sql.PreparedStatement;
import javafx.scene.Parent;


public class Customer {

    @FXML public ToggleGroup formControlToggleGroup;
    //database table view
    @FXML public TableView<Customers> customerTableView;
    //customer ID
    @FXML public TableColumn<Customers, Integer> customerViewID;
    //customer Name
    @FXML public TableColumn<Customers, String> customerViewName;
    //customer address
    @FXML public TableColumn<Customers, String> customerViewAddress;
    //customer first-level-division
    @FXML public TableColumn<Customers, Integer> customerViewFirstLevelDivision;
    //customer zip code
    @FXML public TableColumn<Customers, String> customerViewPostalCode;
    //customer mobile phone
    @FXML public TableColumn<Customers, String> customerViewPhone;
    //add a customer
    @FXML public Button customerAddButton;
    //reset customer information
    @FXML public Button customerResetButton;
    //update customer changes
    @FXML public Button customerUpdateButton;
    //delete a customer
    @FXML public Button customerDeleteButton;
    //show customer ID
    @FXML public TextField customerIDTextField;
    //show customer name
    @FXML public TextField customerNameTextField;
    //show customer Address
    @FXML public TextField customerAddressTextField;
    //show customer Zip Code
    @FXML public TextField customerPostalCodeTextField;
    //show customer phone number
    @FXML public TextField customerPhoneNumberTextField;
    //show customer region location
    @FXML public ComboBox<String> customerCountryComboBox;
    //show customer first level division
    @FXML public ComboBox<String> customerFirstLevelDivisionComboBox;
    int lastClickedButton = -1;

    /**
     *
     * starts and fills table with database data
     *
     * @throws SQLException if exception has occurred
     */
    public void initialize() throws SQLException {
        customerViewID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerViewName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerViewAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        customerViewFirstLevelDivision.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
        customerViewPostalCode.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
        customerViewPhone.setCellValueFactory(new PropertyValueFactory<>("customerPhoneNumber"));

        ObservableList<Countries> allCountries = DBCountries.selectAllCountriesInfo();
        ObservableList<String> countryNames = FXCollections.observableArrayList();
        //DB countryName profile
        for (Countries country : allCountries) {
            countryNames.add(country.collectCountryDBName());
        }
        //countryNames getter
        customerCountryComboBox.setItems(countryNames);
        //allows countryName to be editable by user
        customerCountryComboBox.setEditable(true);
        //to be editable
        customerCountryComboBox.getEditor().setEditable(false);
        //matches primary with foreign keys
        ObservableList<FirstLevelDivisions> allFirstLevelDivisions = DBFirstLevelDivisions.selectAllFirstLevelDivisionsInfo();
        ObservableList<String> firstLevelDivisionAllNames = FXCollections.observableArrayList();

        allFirstLevelDivisions.forEach(firstLevelDivision -> firstLevelDivisionAllNames.add(firstLevelDivision.getDivisionName()));
        customerChooseAddUpdateButton();

        //customerFirstLevelDivision setter
        customerFirstLevelDivisionComboBox.setItems(firstLevelDivisionAllNames);
        //getter
        customerFirstLevelDivisionComboBox.setItems(firstLevelDivisionAllNames);
        //makes customerFirstLevelDivision editable by user
        customerFirstLevelDivisionComboBox.setEditable(true);
        //makes it unable to be edited by DB
        customerFirstLevelDivisionComboBox.getEditor().setEditable(false);
        //populates tableView
        fillCustomerDataToView();
    }

    /**
     *
     * on launch will inform user if they have an appointment within fifteen minutes
     * user is required to use "test" for username and password to get past /Main/LoginForm
     * database will make the first user as an admin
     *
     * @throws SQLException if exception has occurred
     */
    public void lookInUpcomingAppointmentInfo() throws SQLException {
        int currentUserID = 1;
        //looks through database on login for appointments within 15 minutes
        //Appointment current time
        LocalDateTime timeOfLogInNow = LocalDateTime.now();
        //Appointment before 15 minutes
        LocalDateTime timeOfLogInBottomWindow = LocalDateTime.now().minusMinutes(15);
        //Appointment After 15 minutes
        LocalDateTime timeOfLogInTopWindow = LocalDateTime.now().plusMinutes(15);
        //start date and time checker
        LocalDateTime startDateTimeToCheck;
        //end date and time checker
        LocalDateTime endDateTimeToCheck;
        //display appointmentID
        int appointmentIDToDisplay = 0;
        //display startDateTime
        LocalDateTime startDateTimeToDisplay = null;
        //display endDateTime
        LocalDateTime endDateTimeToDisplay = null;
        boolean hasUpcomingAppointment = false;
        boolean hasCurrentAppointment = false;
        //database appointment gatherer
        ObservableList<Appointments> appointmentsObservableList = DBAppointments.collectAllAppointmentsInfo();
        for (Appointments appointment : appointmentsObservableList) {
            //time parameters
            //Appointment Start Timer
            startDateTimeToCheck = appointment.getStart();
            //Appointment End Timer
            endDateTimeToCheck = appointment.getEnd();
            int userIDsToCheck = appointment.getUserID();
            if ((userIDsToCheck == currentUserID) &&
                    //user Id Parsing
                    (startDateTimeToCheck.isAfter(timeOfLogInBottomWindow) || startDateTimeToCheck.isEqual(timeOfLogInBottomWindow)) &&
                    (startDateTimeToCheck.isBefore(timeOfLogInTopWindow) || (startDateTimeToCheck.isEqual(timeOfLogInTopWindow)))) {
                appointmentIDToDisplay = appointment.getAppointmentID();
                //Display star timer
                startDateTimeToDisplay = startDateTimeToCheck;
                //Display end timer
                endDateTimeToDisplay = endDateTimeToCheck;
                hasUpcomingAppointment = true;
            }

            /** User ID Appointment Check */
            else if ((userIDsToCheck == currentUserID) &&
                    //startDateTime after login checker
                    (timeOfLogInNow.isAfter(startDateTimeToCheck) || timeOfLogInNow.isEqual(startDateTimeToCheck)) &&
                    //endDateTime before login checker
                    (timeOfLogInNow.isBefore(endDateTimeToCheck) || (timeOfLogInNow.isEqual(endDateTimeToCheck)))) {
                //inputs time user logs into program
                System.out.println(timeOfLogInNow);
                //outputs if appointment start time is near
                System.out.println(startDateTimeToCheck);
                //will also output ending dateTime
                System.out.println(endDateTimeToCheck);
                //appointmentID display getter
                appointmentIDToDisplay = appointment.getAppointmentID();
                //start of login appointment time
                startDateTimeToDisplay = startDateTimeToCheck;
                //ending of login appointment time
                endDateTimeToDisplay = endDateTimeToCheck;
                hasCurrentAppointment = true;
            }
        }
        if (hasUpcomingAppointment) {
            JOptionPane.showMessageDialog(null,
                    //notifies of any appointments that begins in 15 minutes
                    "You have an appointment that is within 15 minutes" +
                            //displays ID
                            "\nAppointment ID: " + appointmentIDToDisplay +
                            //displays start date
                            "\nStart Date: " + startDateTimeToDisplay.toLocalDate() +
                            //displays end date
                            "\nEnd Date: " + endDateTimeToDisplay.toLocalDate() +
                            //displays start time
                            "\nStart Time: " + startDateTimeToDisplay.toLocalTime() +
                            //displays end time
                            "\nEnd Time: " + endDateTimeToDisplay.toLocalTime(),
                    //notifies of any upcoming appointments
                    "Upcoming Appointment", JOptionPane.WARNING_MESSAGE);
        }
        else if (hasCurrentAppointment) {
            JOptionPane.showMessageDialog(null,
                    "You have an current appointment" +
                            //ID
                            "\nAppointment ID: " + appointmentIDToDisplay +
                            //Start Date
                            "\nStart Date: " + startDateTimeToDisplay.toLocalDate() +
                            //End Date
                            "\nEnd Date: " + endDateTimeToDisplay.toLocalDate() +
                            //Start Time
                            "\nStart Time: " + startDateTimeToDisplay.toLocalTime() +
                            //End Time
                            "\nEnd Time: " + endDateTimeToDisplay.toLocalTime(),
                    //Current Appointment that is within 15 minutes of login
                    "Current Appointment", JOptionPane.WARNING_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(null,
                    //else will output that no appointments are within 15 minutes
                    "No further upcoming appointments",
                    //alert
                    "Appointments", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     *
     * @throws SQLException if exception has occurred
     */
    public void fillCustomerDataToView() throws SQLException {
        ObservableList<Customers> allCustomersList = DBCustomers.selectAllCustomersDB();
        customerTableView.setItems(allCustomersList);
    }

    /**
     *
     * gathers data for selected customer and fills customer table view
     * pairs first level division ID with first level division name to set text fields
     * pairs country with division ID fields
     * stop unauthorized uses of adding/deleting/resetting
     *
     * @throws SQLException if exception has occurred
     */
    public void fillCustomerDataInfoToFields() throws SQLException {
        //get selectedCustomer tableView
        Customers selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            //primary key countries
            ObservableList<Countries> allCountries = DBCountries.selectAllCountriesInfo();
            //foreign key FirstLevelDivisions
            ObservableList<FirstLevelDivisions> allFirstLevelDivisions = DBFirstLevelDivisions.selectAllFirstLevelDivisionsInfo();
            //FXCollections
            ObservableList<String> firstLevelDivisionAllNames = FXCollections.observableArrayList();
            for (FirstLevelDivisions firstLevelDivision : allFirstLevelDivisions) {
                firstLevelDivisionAllNames.add(firstLevelDivision.getDivisionName());
            }
            //first level division
            customerFirstLevelDivisionComboBox.setItems(firstLevelDivisionAllNames);
            //customer ID
            customerIDTextField.setText(String.valueOf((selectedCustomer.getCustomerID())));
            //customer Name
            customerNameTextField.setText(selectedCustomer.getCustomerName());
            //customer Location
            customerAddressTextField.setText(selectedCustomer.getCustomerAddress());
            //customer ZipCode
            customerPostalCodeTextField.setText(selectedCustomer.getCustomerPostalCode());
            //customer Mobile Number
            customerPhoneNumberTextField.setText(selectedCustomer.getCustomerPhoneNumber());
            int divisionIDToSet = selectedCustomer.getDivisionID();
            String divisionNameToSet = "";
            int countryIDToSet;
            String countryNameToSet = "";
            //pair first level division set
            for (FirstLevelDivisions firstLevelDivision : allFirstLevelDivisions) {
                if (divisionIDToSet == firstLevelDivision.getDivisionID()) {
                    divisionNameToSet = firstLevelDivision.getDivisionName();
                    countryIDToSet = firstLevelDivision.getCountry_ID();
                    for (Countries country : allCountries) {
                        if (countryIDToSet == country.collectCountryDBID()) {
                            countryNameToSet = country.collectCountryDBName();
                        }
                    }
                }
            }
            //pairs customer to country
            customerFirstLevelDivisionComboBox.setValue(divisionNameToSet);
            customerCountryComboBox.setValue(countryNameToSet);
            //disables customerAdd option button
            customerAddButton.setDisable(true);
            //disables customerUpdate option button
            customerUpdateButton.setDisable(false);
            //disables customerDeleteButton
            customerDeleteButton.setDisable(false);
            //disables customerResetButton
            customerResetButton.setDisable(false);
        }
    }

    /**
     *
     * pulls up first level division through country text field pools
     *
     * @throws SQLException if exception has occurred
     */
    public void filterFirstLevelDivisionComboBox () throws SQLException {
        //primary key FirstLevelDivisions DB
        ObservableList<FirstLevelDivisions> allFirstLevelDivisions = DBFirstLevelDivisions.selectAllFirstLevelDivisionsInfo();
        //titleText US array choice
        ObservableList<String> firstLevelDivisionNamesUS = FXCollections.observableArrayList();
        //titleText UK array choice
        ObservableList<String> firstLevelDivisionNamesUK = FXCollections.observableArrayList();
        //titleText Canada array choice
        ObservableList<String> firstLevelDivisionNamesCanada = FXCollections.observableArrayList();
        //primary key
        for (FirstLevelDivisions firstLevelDivision : allFirstLevelDivisions) {
            if (firstLevelDivision.getCountry_ID() == 1) {
                //get divisionName
                firstLevelDivisionNamesUS.add(firstLevelDivision.getDivisionName());
            }
            //get countryID UK
            else if (firstLevelDivision.getCountry_ID() == 2) {
                firstLevelDivisionNamesUK.add(firstLevelDivision.getDivisionName());
            }//get countryID Canada
            else if (firstLevelDivision.getCountry_ID() == 3) {
                firstLevelDivisionNamesCanada.add(firstLevelDivision.getDivisionName());
            }
        }
        //if the customer resides in United States
        String selectedCountry = customerCountryComboBox.getSelectionModel().getSelectedItem();
        //set firstLevelDivisionNamesU.S
        if (selectedCountry.equals("U.S")) {
            customerFirstLevelDivisionComboBox.setItems(firstLevelDivisionNamesUS);
        }
        //if the customer resides in United Kingdom
        else if (selectedCountry.equals("UK")) {
            //set firstLevelDivisionNamesUK
            customerFirstLevelDivisionComboBox.setItems(firstLevelDivisionNamesUK);
        }
        //if the customer resides in Canada
        else if (selectedCountry.equals("Canada")) {
            //set firstLevelDivisionNamesCanada
            customerFirstLevelDivisionComboBox.setItems(firstLevelDivisionNamesCanada);
        }
    }

    /**
     * resets boxes for appropriate user interaction
     *
     */
    public void resetTextFields() {
        //customerTable
        customerTableView.getSelectionModel().clearSelection();
        customerIDTextField.clear();
        //customerID
        customerIDTextField.setDisable(true);
        //customerName
        customerNameTextField.clear();
        //customerLocation
        customerAddressTextField.clear();
        //customerZipCode
        customerPostalCodeTextField.clear();
        //customerMobile
        customerPhoneNumberTextField.clear();
        //customerRegion
        customerCountryComboBox.setValue("");
        //customerFirstLevelDivision
        customerFirstLevelDivisionComboBox.setValue("");
        //AddCustomerReset
        customerAddButton.setDisable(false);
        //UpdateCustomerReset
        customerUpdateButton.setDisable(true);
        //Delete customer Reset
        customerDeleteButton.setDisable(true);
        //Main Reset function
        customerResetButton.setDisable(true);
    }

    /**
     *
     * add/update clean up code
     *
     * @param string start of string ("Customer")
     * @param fn outPrint string based on user click like "added/updated" into database
     * @return notify user of updates or deleting of customers
     */
    public String tellUserAlertMessage(String string, Function<String, String> fn) {
        return fn.apply(string);
    }

    /**
     *  reply output if user has completed a successful add or update action
     */
    public void userAllNotification() {
        //start null
        Function<String, String> fn = null;
        //output if successful param
        if (lastClickedButton == 1) { fn = parameter -> parameter + " added to database"; }
        //output if successful update param
        if (lastClickedButton == 2) { fn = parameter -> parameter + " updated in database"; }
        //customer alert
        String userNotification = tellUserAlertMessage("Customer", fn);
        //notification alert
        System.out.println(userNotification);
        lastClickedButton = -1;
    }

    public void customerChooseAddUpdateButton() {
        customerAddButton.setOnAction(e -> { lastClickedButton = 1;try { userAddCustomerButton();
        } catch (SQLException throwables) { throwables.printStackTrace();}});
        customerUpdateButton.setOnAction(e -> { lastClickedButton = 2;try { userUpdateCustomerInfo();
        } catch (SQLException throwables) { throwables.printStackTrace();}});
    }

    // gives option button functionality
    /**
     *
     * check that text fields have values
     * auto-chooses customer ID
     * matches division name string with division ID
     * shows prepared insert statement
     * display proper data and resets boxes
     *
     * @throws SQLException if exception has occurred
     */
    public void userAddCustomerButton() throws SQLException {
        //pairs customerName with customerAddress
        if (!customerNameTextField.getText().equals("") && !customerAddressTextField.getText().equals("") &&
                //pairs customerCountry with customerFirstLevelDivision
                !customerCountryComboBox.getValue().equals("") && !customerFirstLevelDivisionComboBox.getValue().equals("") &&
                //pairs customerPostalCode with customerPhoneNumber
                !customerPostalCodeTextField.getText().equals("") && !customerPhoneNumberTextField.getText().equals("")) {
            int lastID = 0;
            //database customer pull
            ObservableList<Customers> allCustomersList = DBCustomers.selectAllCustomersDB();
            //DB customerList
            for (Customers customer : allCustomersList) {
                lastID = customer.getCustomerID();
            }
            int idToAdd = lastID + 1;
            //add customerName
            String nameToAdd = customerNameTextField.getText();
            //add customerAddress
            String addressToAdd = customerAddressTextField.getText();
            //add customerPostalCode
            String postalCodeToAdd = customerPostalCodeTextField.getText();
            //add customerPhoneNumber
            String phoneNumberToAdd = customerPhoneNumberTextField.getText();
            //Primary key DB
            int firstLevelDivisionIDToAdd = 0;
            //gets customerFirstLevelDivision
            String selectedFirstLevelDivision = customerFirstLevelDivisionComboBox.getSelectionModel().getSelectedItem();
            //primary key firstLevelDivision
            ObservableList<FirstLevelDivisions> allFirstLevelDivisions = DBFirstLevelDivisions.selectAllFirstLevelDivisionsInfo();
            for (FirstLevelDivisions firstLevelDivision : allFirstLevelDivisions) {
                //foreign key
                if (selectedFirstLevelDivision.equals(firstLevelDivision.getDivisionName())) {
                    firstLevelDivisionIDToAdd = firstLevelDivision.getDivisionID();
                }
            }
            //1st user is admin
            LocalDateTime createdDateToAdd = LocalDateTime.now();
            //admin privilege to alter
            String createdByToAdd = "admin";
            //timestamp of updated change
            Timestamp lastUpdateToAdd = Timestamp.valueOf(LocalDateTime.now());
            String lastUpdatedByToAdd = "admin";
            String insertStatement = "INSERT INTO customers (Customer_ID, Customer_Name, Address, Postal_Code, Phone, " +
                    "Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?)";
            //Database Connection insert Statements
            DBQuery.selectPreparedStatementInfo(DBConnection.getConnection(), insertStatement);
            //mySql query statement
            PreparedStatement ps = DBQuery.getPreparedStatement();
            //gets ID
            ps.setInt(1, idToAdd);
            //gets customerName
            ps.setString(2, nameToAdd);
            //gets customerAddress
            ps.setString(3, addressToAdd);
            //gets customerPostalCode
            ps.setString(4, postalCodeToAdd);
            //gets customerPhoneNumber
            ps.setString(5, phoneNumberToAdd);
            //gets createdDate
            ps.setTimestamp(6, Timestamp.valueOf(createdDateToAdd));
            //gets createdUser
            ps.setString(7, createdByToAdd);
            //gets lastUpdate
            ps.setTimestamp(8, lastUpdateToAdd);
            //gets user that updated
            ps.setString(9, lastUpdatedByToAdd);
            ps.setInt(10, firstLevelDivisionIDToAdd);
            //execute query
            ps.execute();
            //populates table View
            fillCustomerDataToView();
            //notifies of changes and resets previous fields
            userAllNotification();
            resetTextFields();
        }
        else JOptionPane.showMessageDialog(null, "Entered Customer Inputted was Incomplete",
                "Customer Couldn't Be Added" , JOptionPane.ERROR_MESSAGE);
    }

    /**
     *
     * gathers the data that will be put into text fields
     * pairs  ID with first level division names
     * display data and reset data fields
     * loads update statements
     *
     * @throws SQLException if exception has occurred
     */
    public void userUpdateCustomerInfo() throws SQLException {
        int idToUpdate = Integer.parseInt(customerIDTextField.getText());
        //gets updated customerName
        String nameToUpdate = customerNameTextField.getText();
        //gets updated customerAddress
        String addressToUpdate = customerAddressTextField.getText();
        //gets updated customerPostalCode
        String postalCodeToUpdate = customerPostalCodeTextField.getText();
        //gets updated customerPhoneNumber
        String phoneNumberToUpdate = customerPhoneNumberTextField.getText();
        //gets updated firstLevelDivision
        String firstLevelDivisionStringToUpdate = customerFirstLevelDivisionComboBox.getValue();
        int firstLevelDivisionIntToUpdate = 0;
        //primary key DB
        ObservableList<FirstLevelDivisions> allFirstLevelDivisions = DBFirstLevelDivisions.selectAllFirstLevelDivisionsInfo();
        for (FirstLevelDivisions firstLevelDivision : allFirstLevelDivisions) {
            if (firstLevelDivisionStringToUpdate.equals(firstLevelDivision.getDivisionName())) {
                //foreign key
                firstLevelDivisionIntToUpdate = firstLevelDivision.getDivisionID();
            }
        }
        Timestamp lastUpdateToUpdate = Timestamp.valueOf(LocalDateTime.now());
        String lastUpdatedByToUpdate = "admin";
        //gather DB customerInfo list
        String updateStatement = "UPDATE customers SET Customer_ID = ?, Customer_Name = ?, Address = ?, " +
                "Postal_Code = ?, Phone = ?, Last_Update = ?, Last_Updated_By = ?, " +
                "Division_ID = ? WHERE Customer_ID = ?";
        //database parameter connection
        DBQuery.selectPreparedStatementInfo(DBConnection.getConnection(), updateStatement);
        //DB query statement
        PreparedStatement ps = DBQuery.getPreparedStatement();
        //gets updated ID
        ps.setInt(1, idToUpdate);
        //gets updatedName
        ps.setString(2, nameToUpdate);
        //gets updatedAddress
        ps.setString(3, addressToUpdate);
        //gets updated postalCode
        ps.setString(4, postalCodeToUpdate);
        //gets updated phoneNumber
        ps.setString(5, phoneNumberToUpdate);
        //gets updated update
        ps.setTimestamp(6, lastUpdateToUpdate);
        //get last user that updated
        ps.setString(7, lastUpdatedByToUpdate);
        //gets last updated firstLevelDivision
        ps.setInt(8, firstLevelDivisionIntToUpdate);
        //gets last updated ID
        ps.setInt(9, idToUpdate);
        //execute statement query
        ps.execute();
        //populate updated tableView
        fillCustomerDataToView();
        //notifies of changes
        userAllNotification();
        //resets previous field
        resetTextFields();
    }

    /**
     *
     * pulls user selected ID's
     * pulls and activates delete statements
     * notifies the user of  change,reset,and notification alert process
     *
     * @throws SQLException if exception has occurred
     */
    public void userDeleteCustomerInfo() throws SQLException {

        String deleteStatement = "DELETE FROM customers WHERE Customer_ID = ?";
        //DBQuery connection
        DBQuery.selectPreparedStatementInfo(DBConnection.getConnection(), deleteStatement);
        //preparedStatement query
        PreparedStatement ps = DBQuery.getPreparedStatement();
        //customerTableView
        int customerIDToDelete = customerTableView.getSelectionModel().getSelectedItem().getCustomerID();
        //primary key
        ObservableList<Appointments> appointmentsObservableList = DBAppointments.collectAllAppointmentsInfo();
        //foreign key
        for (Appointments appointment : appointmentsObservableList) {
            //gathers customerID and related appointment
            int customerIDToCheck = appointment.getCustomerID();
            //if user deletes selects all appointment info
            int appointmentIDToDelete = appointment.getAppointmentID();
            String appointmentTypeToDisplay = appointment.getAppointmentType();
            //if user deletes a customer gathers all customer info
            if (customerIDToDelete == customerIDToCheck) {
                //mySql statement
                String deleteStatementAppointments = "DELETE FROM appointments WHERE Appointment_ID = ?";
                //DB connection query
                DBQuery.selectPreparedStatementInfo(DBConnection.getConnection(), deleteStatementAppointments);
                //Prepared statement database query
                PreparedStatement psAppointments = DBQuery.getPreparedStatement();
                psAppointments.setInt(1, appointmentIDToDelete);
                //mySql execute statement
                psAppointments.execute();
                //notify
                JOptionPane.showMessageDialog(null,
                        //AppointmentID to delete
                        "\nAppointment_ID: " + appointmentIDToDelete +
                                //the type of appointment to be displayed
                                "\nType of Appointment: " + appointmentTypeToDisplay,
                        //notifies user that appointment has been deleted
                        "Appointment Has Been Deleted From Database", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        ps.setInt(1, customerIDToDelete);
        //query execute
        ps.execute();
        //populate tableView
        fillCustomerDataToView();
        //resets fields
        resetTextFields();
        //notifies user of customer being deleted
        JOptionPane.showMessageDialog(null,
                //Id to be deleted
                "Customer ID: " + customerIDToDelete,
                //notifies user of deleted customer
                "Customer deleted from database", JOptionPane.INFORMATION_MESSAGE);

    }

    // fxml changes
    /**
     *
     * @param event with button press from user the program will switch from CustomerForm to AppointmentForm
     * @throws IOException if exception has occurred
     */
    public void ButtonAppointmentSceneChange(ActionEvent event) throws IOException {
        //loads fxml file
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Main/AppointmentForm.fxml")));
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        //sets window size
        primaryStage.setScene(new Scene(root, 900, 600));
    }

    /**
     *
     * @param event on user interaction the program will change from CustomerForm to ReportForm
     * @throws IOException if exception has occurred
     */
    public void ButtonReportSceneChange(ActionEvent event) throws IOException {
        //fxml /Main/ReportForm change
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Main/ReportForm.fxml")));
        //gathers scene
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        //screen window size
        primaryStage.setScene(new Scene(root, 900, 600));
    }
}