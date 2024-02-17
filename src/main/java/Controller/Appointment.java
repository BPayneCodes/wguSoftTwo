package Controller;

import java.time.format.DateTimeFormatter;
import java.util.Objects;
import DBUtility.DBConnection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;

/**
 * @author Billy Payne
 *
 * */
import java.sql.SQLException;
import java.sql.Timestamp;
import DBAccess.*;
import java.time.*;

import javafx.scene.control.*;
import javafx.stage.Stage;
import DBUtility.DBQuery;
import Model.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javax.swing.JOptionPane;
import java.io.IOException;
import java.sql.PreparedStatement;



public class Appointment {

    @FXML ToggleGroup formControlToggleGroup;

    // Appointment App table view
    @FXML public Tab appTableViewMonthTabButton;
    //Main table view
    @FXML public TableView<Appointments> appMonthTableView;
    //Appointment Month ID
    @FXML public TableColumn<Appointments, Integer> MonthViewAppointmentID;
    //Appointment Month Header
    @FXML public TableColumn<Appointments, String> MonthViewTitle;
    //Appointment Month Description
    @FXML public TableColumn<Appointments, String> MonthViewDescription;
    //Month Appointment Location
    @FXML public TableColumn<Appointments, String> MonthViewLocation;
    //Appointment Contacts
    @FXML public TableColumn<Appointments, Integer> MonthViewContact;
    //Appointment Type
    @FXML public TableColumn<Appointments, String> MonthViewType;
    //Appointment Month Start Date and Time
    @FXML public TableColumn<Appointments, LocalDateTime> MonthViewStartDateTime;
    //Appointment Month End Date and Time
    @FXML public TableColumn<Appointments, LocalDateTime> MonthViewEndDateTime;
    //Appointment Associated Customer ID
    @FXML public TableColumn<Appointments, Integer> MonthViewCustomerID;

    // Appointment Week View
    @FXML public Tab appTableViewWeekTabButton;
    //Main Appointment Week View
    @FXML public TableView<Appointments> appWeekTableView;
    //Appointment Week Appointment ID

    @FXML public TableColumn<Appointments, Integer> WeekViewAppointmentID;
    //Appointment Week Header
    @FXML public TableColumn<Appointments, String> WeekViewTitle;
    //Appointment Week Description
    @FXML public TableColumn<Appointments, String> WeekViewDescription;
    //Appointment Week Location
    @FXML public TableColumn<Appointments, String> WeekViewLocation;
    //Appointment Week Contact
    @FXML public TableColumn<Appointments, Integer> WeekViewContact;
    //Appointment Week Type
    @FXML public TableColumn<Appointments, String> WeekViewType;
    //Appointment Week Start Date and Time
    @FXML public TableColumn<Appointments, LocalDateTime> WeekViewStartDateTime;
    //Appointment Week End Date and Time
    @FXML public TableColumn<Appointments, LocalDateTime> WeekTableViewEndDateTime;
    //Appointment Week Link Customer ID
    @FXML public TableColumn<Appointments, Integer> WeekViewCustomerID;

    // View All Tables Together
    @FXML public Tab ViewAllTabButton;

    @FXML public TableView<Appointments> AllTableView;

    //All Appointment ID's
    @FXML public TableColumn<Appointments, Integer> AllViewAppointmentID;

    //All Appointment Headers
    @FXML public TableColumn<Appointments, String> AllViewTitle;

    //All Appointment Descriptions
    @FXML public TableColumn<Appointments, String> AllViewDescription;

    //All Appointment Locations
    @FXML public TableColumn<Appointments, String> AllViewLocation;

    //All Appointment Contacts
    @FXML public TableColumn<Appointments, Integer> AllViewContact;

    //All Appointment Type
    @FXML public TableColumn<Appointments, String> AllViewType;

    //All Starting Appointment Dates and Times
    @FXML public TableColumn<Appointments, LocalDateTime> AllViewStartDateTime;

    //All Ending Appointment Dates and Times
    @FXML public TableColumn<Appointments, LocalDateTime> AllViewEndDateTime;

    //All Appointments with Linked Customer ID
    @FXML public TableColumn<Appointments, Integer> AllViewCustomerID;

    //Add an Appointment Button
    @FXML Button addAppointmentButton;

    //Update an Appointment Button
    @FXML Button updateAppointmentButton;

    //Delete and appointment Button
    @FXML Button deleteAppointmentButton;

    //Reset Text Fields Appointment Button
    @FXML Button resetAppointmentButton;

    //Appointment Title Information
    @FXML TextField appointmentTitleTextField;

    //Appointment Description Information
    @FXML TextField appointmentDescriptionTextField;

    //Appointment Location Information
    @FXML TextField appointmentLocationTextField;

    //Appointment ID Information
    @FXML TextField appointmentIDTextField;

    //Appointment Type Information
    @FXML TextField appointmentTypeTextField;

    //Appointment Start Date and Time That User Chooses
    @FXML DatePicker appointmentStartDatePicker;

    //Appointment End Date and Time That User Chooses
    @FXML DatePicker appointmentEndDatePicker;

    //Appointment Start Time Information
    @FXML ComboBox<String> appointmentStartTimeComboBox;

    //Appointment End Time Information
    @FXML ComboBox<String> appointmentEndTimeComboBox;

    //Appointment Linking Customer ID
    @FXML TextField appointmentCustomerIDTextField;

    //appointmentUserID

    @FXML TextField appointmentUserIDTextField;

    //appointmentContact
    @FXML ComboBox<String> appointmentContactComboBox;

    /**
     *
     * starts columns and fieldBox options. System will be registered in localTime
     * show table view with appointment database
     *
     * @throws SQLException if exception has occurred
     */
    public void initialize() throws SQLException {
        //MonthID
        MonthViewAppointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        //MonthHeader
        MonthViewTitle.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        //MonthDescription
        MonthViewDescription.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        //MonthLocation
        MonthViewLocation.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        //MonthContact
        MonthViewContact.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        //MonthType
        MonthViewType.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        //MonthStartDate
        MonthViewStartDateTime.setCellValueFactory(new PropertyValueFactory<>("start"));
        //MonthEndDate
        MonthViewEndDateTime.setCellValueFactory(new PropertyValueFactory<>("end"));
        //MonthCustomerID
        MonthViewCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));

        //WeekAppointmentID
        WeekViewAppointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        //WeekHeader
        WeekViewTitle.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        //WeekDescription
        WeekViewDescription.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        //WeekLocation
        WeekViewLocation.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        //WeekContact
        WeekViewContact.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        //WeekType
        WeekViewType.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        //WeekStartDate
        WeekViewStartDateTime.setCellValueFactory(new PropertyValueFactory<>("start"));
        //WeekEndDate
        WeekTableViewEndDateTime.setCellValueFactory(new PropertyValueFactory<>("end"));
        //WeekCustomerID
        WeekViewCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));

        //AllAppointmentID
        AllViewAppointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        //AllViewHeaders
        AllViewTitle.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        //AllAppointmentDescription
        AllViewDescription.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        //AllLocation
        AllViewLocation.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        //AllContact
        AllViewContact.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        //AllType
        AllViewType.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        //AllStartDate
        AllViewStartDateTime.setCellValueFactory(new PropertyValueFactory<>("start"));
        //AllEndTime
        AllViewEndDateTime.setCellValueFactory(new PropertyValueFactory<>("end"));
        //AllCustomerID
        AllViewCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));

        //ObservableContact array Searcher
        ObservableList<Contacts> contactsObservableList = DBContacts.selectAllContactsInfo();
        //primary key - foreign key link
        ObservableList<String> allContactsNames = FXCollections.observableArrayList();
        contactsObservableList.forEach(contacts -> allContactsNames.add(contacts.collectContactDBName()));

        //allContactName combo
        appointmentContactComboBox.setItems(allContactsNames);
        //makes allContactName editable by user
        appointmentContactComboBox.setEditable(true);
        //makes appointmentContact editable by user
        appointmentContactComboBox.getEditor().setEditable(false);

        //time formatter
        DateTimeFormatter dateTimeHourMinuteFormatter = DateTimeFormatter.ofPattern("HH:mm");
        //primary key DB
        ObservableList<String> appointmentTimesDuringDay = FXCollections.observableArrayList();
        //gather the closest appointment for the tableview
        LocalTime firstAppointmentTimeLDT = LocalTime.MIN.plusHours(8);
        //gather last appointment for the tableview
        LocalTime lastAppointmentTimeLDT = LocalTime.MAX.minusHours(1).minusMinutes(59);
        //loop while--formatter
        while (firstAppointmentTimeLDT.isBefore(lastAppointmentTimeLDT)) {
            appointmentTimesDuringDay.add(dateTimeHourMinuteFormatter.format(firstAppointmentTimeLDT));
            firstAppointmentTimeLDT = firstAppointmentTimeLDT.plusMinutes(30);
        }
        //StartTimeSearchField
        appointmentStartTimeComboBox.setItems(appointmentTimesDuringDay);
        //makes appointmentStartTime editable by user
        appointmentStartTimeComboBox.setEditable(true);
        //appointmentStartTime editable by user
        appointmentStartTimeComboBox.getEditor().setEditable(false);
        //appointmentEndTime setter
        appointmentEndTimeComboBox.setItems(appointmentTimesDuringDay);
        //appointmentEndTime setter
        appointmentEndTimeComboBox.setEditable(true);
        //appointmentEndTime editable by user
        appointmentEndTimeComboBox.getEditor().setEditable(false);
        filterAppointmentDataToView();
    }

    /**
     *
     * forms the database into appointmentView
     * gathers data from appointments into mySql Database
     * moves through appointments to accurate Views.Filtering through months or weeks.
     *
     * @throws SQLException if exception has occurred
     */
    public void filterAppointmentDataToView() throws SQLException {
        //ObservableList Appointment Filtering
        ObservableList<Appointments> allAppointmentsList = DBAppointments.collectAllAppointmentsInfo();
        //filter by Month
        ObservableList<Appointments> appointmentsMonth = FXCollections.observableArrayList();
        //filter by Week
        ObservableList<Appointments> appointmentsWeek = FXCollections.observableArrayList();

        //Appointment Month start timer
        LocalDateTime currentMonthStart = LocalDateTime.now().minusSeconds(1);
        //Appointment Month End Timer
        LocalDateTime currentMonthEnd = LocalDateTime.now().plusMonths(1);

        //Appointment Week Start Timer
        LocalDateTime currentWeekStart = LocalDateTime.now().minusSeconds(1);
        //Appointment Week End Timer
        LocalDateTime currentWeekEnd = LocalDateTime.now().plusWeeks(1);

        //Parameter that stops the user from putting end date before start date
        for (Appointments appointment : allAppointmentsList) {
            //add appointment parameters
            if (appointment.getEnd().isAfter(currentMonthStart) && appointment.getEnd().isBefore(currentMonthEnd)) {
                //if previous statement is valid add new appointment profile
                appointmentsMonth.add(appointment);
            }
            //prevents user from putting appointment end before start in calendar
            if (appointment.getEnd().isAfter(currentWeekStart) && appointment.getEnd().isBefore(currentWeekEnd)) {
                //if previous statement is valid add new appointment profile
                appointmentsWeek.add(appointment);
            }
        }
        //monthTableView setter
        appMonthTableView.setItems(appointmentsMonth);
        //weekTableView setter
        appWeekTableView.setItems(appointmentsWeek);
        //allTableView setter
        AllTableView.setItems(allAppointmentsList);
    }

    /**
     * gathers appointment info and sets data to text fields
     * pairs ID with name to display in text fields
     * stops unauthorized adding will allow the feature of updating, deleting, and resetting
     *
     * @throws SQLException if exception has occurred
     */
    public void ViewAppointmentDataToBoxes() throws SQLException {
        //empty appointment profile
        Appointments selectedAppointment = null;
        //appointmentMonth selection tableView
        if (appTableViewMonthTabButton.isSelected()) {
            //gather appointment data
            selectedAppointment = appMonthTableView.getSelectionModel().getSelectedItem();
        }
        //appointmentWeek selection tableView
        if (appTableViewWeekTabButton.isSelected()) {
            //gather appointment data
            selectedAppointment = appWeekTableView.getSelectionModel().getSelectedItem();
        }
        //allTable selection tableView
        if (ViewAllTabButton.isSelected()) {
            //gather appointment data
            selectedAppointment = AllTableView.getSelectionModel().getSelectedItem();
        }
        if (selectedAppointment != null) {
            //selected ID highlight
            appointmentIDTextField.setText(String.valueOf((selectedAppointment.getAppointmentID())));
            //selected Header highlight
            appointmentTitleTextField.setText(selectedAppointment.getAppointmentTitle());
            //selected Description highlight
            appointmentDescriptionTextField.setText(selectedAppointment.getAppointmentDescription());
            //selected Location highlight
            appointmentLocationTextField.setText(selectedAppointment.getAppointmentLocation());
            int contactIDToDisplay = selectedAppointment.getContactID();
            String contactNameToDisplay = "";
            //Primary Key DB
            ObservableList<Contacts> contactsObservableList = DBContacts.selectAllContactsInfo();
            for (Contacts contact : contactsObservableList) {
                //Displays DB Contact Info
                if (contactIDToDisplay == contact.collectDBId()) {
                    //collect DB side Info
                    contactNameToDisplay = contact.collectContactDBName();
                }
            }
            //Appointment Contact
            appointmentContactComboBox.setValue(contactNameToDisplay);
            //Appointment Type Information
            appointmentTypeTextField.setText(selectedAppointment.getAppointmentType());
            //Appointment Start Date That User Chooses
            appointmentStartDatePicker.setValue(selectedAppointment.getStart().toLocalDate());
            //Appointmnet End Date That User Chooses
            appointmentEndDatePicker.setValue(selectedAppointment.getEnd().toLocalDate());
            //Appointment Starting Time That User Chooses
            appointmentStartTimeComboBox.setValue(selectedAppointment.getStart().toLocalTime().toString());
            //Appointment Ending Time That User Chooses
            appointmentEndTimeComboBox.setValue(selectedAppointment.getEnd().toLocalTime().toString());
            //Appointment Customer ID Link
            appointmentCustomerIDTextField.setText(String.valueOf(selectedAppointment.getCustomerID()));
            //Appointment UserID admin
            appointmentUserIDTextField.setText(String.valueOf(selectedAppointment.getUserID()));
            //Appointment Add Button Functionality
            addAppointmentButton.setDisable(true);
            //Appointment Update Button Functionality
            updateAppointmentButton.setDisable(false);
            //Appointment Delete Button Functionality
            deleteAppointmentButton.setDisable(false);
            //Appointment Reset Button Functionality
            resetAppointmentButton.setDisable(false);
        }
    }

    /**
     * resets any user interaction on off-click
     *
     */
    public void resetAllTextBoxes() {
        appMonthTableView.getSelectionModel().clearSelection();
        //Reset Appointment Header Field
        appointmentTitleTextField.clear();
        //Reset Appointment Description Field
        appointmentDescriptionTextField.clear();
        //Reset Appointment Location Field
        appointmentLocationTextField.clear();
        //Reset Appointment ID Field
        appointmentIDTextField.clear();
        //Reset Appointment Type Field
        appointmentTypeTextField.clear();
        //Reset Appointment Start Date Field
        appointmentStartDatePicker.setValue(null);
        //Reset Appointment End Date Field
        appointmentEndDatePicker.setValue(null);
        //Reset Appointment Start Time Field
        appointmentStartTimeComboBox.setValue("");
        //Reset Appointment End Time Field
        appointmentEndTimeComboBox.setValue("");
        //Reset Linking Appointment Customer ID Field
        appointmentCustomerIDTextField.clear();
        //Reset Appointment User ID Field
        appointmentUserIDTextField.clear();
        //Reset Appointment Contact Fields
        appointmentContactComboBox.setValue("");
        //disable addAppointment button option
        addAppointmentButton.setDisable(false);
        //disable updateAppointment button option
        updateAppointmentButton.setDisable(true);
        //disable deleteAppointment button option
        deleteAppointmentButton.setDisable(true);
        //disable resetAppointment button option
        resetAppointmentButton.setDisable(true);
    }

    // implementation for Appointment Options

    /**
     *
     * checks that every input has validity
     * auto-chooses an ID
     * pairs name with ID to database filtered based on name
     * gets system local date from a calendar box
     * gathers time from box fields
     * converts user time to local database time
     *
     * confirms if time and day are outside normal hours
     * confirms if any customer have overlapping appointments
     *
     * gathers and performs any insert statements
     * localDateTime automatically converted from local time to UTC by MySQL Workbench
     * functions to show data and resets data fields
     *
     * @throws SQLException if exception has occurred
     */
    public void addUserAppointments() throws SQLException {

        // verify that all boxes have inputs
        if (!appointmentTitleTextField.getText().equals("") && !appointmentDescriptionTextField.getText().equals("") &&
                //pair appointmentLocation and appointmentContact
                !appointmentLocationTextField.getText().equals("") && !appointmentContactComboBox.getValue().equals("") &&
                //pair appointmentType and customerID
                !appointmentTypeTextField.getText().equals("") && !appointmentCustomerIDTextField.getText().equals("") &&
                //pair appointmentUserID and appointmentStartDate
                !appointmentUserIDTextField.getText().equals("") && (appointmentStartDatePicker.getValue() != null) &&
                //pair appointmentEndDate and appointment StartTime
                (appointmentEndDatePicker.getValue() != null) && !appointmentStartTimeComboBox.getValue().equals("") &&
                //pair appointmentEndTime DB observable List
                !appointmentEndTimeComboBox.getValue().equals("")) {
            // checks for pairs between foreign key for customer ID and user ID
            ObservableList<Customers> allCustomerData = DBCustomers.selectAllCustomersDB();
            ObservableList<Integer> allCustomerIDs = FXCollections.observableArrayList();
            for (Customers customer : allCustomerData) {
                allCustomerIDs.add(customer.getCustomerID());
            }
            if (!allCustomerIDs.contains(Integer.parseInt(appointmentCustomerIDTextField.getText()))) {
                JOptionPane.showMessageDialog(null, "No Customer ID Found",
                        "Appointment Couldn't Be Added" , JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Primary Key search
            ObservableList<Users> allUserData = DBUsers.selectAllUsersInfo();
            //Foreign Key search
            ObservableList<Integer> allUserIDs = FXCollections.observableArrayList();
            //gather allUserData from DB
            for (Users user : allUserData) {
                allUserIDs.add(user.getUserID());
            }
            //locate appointmentUserID
            if (!allUserIDs.contains(Integer.parseInt(appointmentUserIDTextField.getText()))) {
                //error message
                JOptionPane.showMessageDialog(null, "No User ID Found",
                        "Appointment Couldn't Be Added" , JOptionPane.ERROR_MESSAGE);
                return;
            }

            int lastAppointmentID = 0;
            //Primary Key Search
            ObservableList<Appointments> allAppointmentsList = DBAppointments.collectAllAppointmentsInfo();
            for (Appointments appointment : allAppointmentsList) {
                lastAppointmentID = appointment.getAppointmentID();
            }
            int appointmentIDToAdd = lastAppointmentID + 1;
            //gather appointmentTitle
            String titleToAdd = appointmentTitleTextField.getText();
            //gather appointmentDescription
            String descriptionToAdd = appointmentDescriptionTextField.getText();
            //gather appointmentLocation
            String locationToAdd = appointmentLocationTextField.getText();
            //gather appointmentType
            String typeToAdd = appointmentTypeTextField.getText();
            //gather appointmentContact
            String contactNameToAdd = appointmentContactComboBox.getValue();
            int contactIDToAdd = 0;
            //Observable Contact List function call primary key DB
            ObservableList<Contacts> contactsObservableList = DBContacts.selectAllContactsInfo();
            for (Contacts contact : contactsObservableList) {
                if (contactNameToAdd.equals(contact.collectContactDBName())) {
                    contactIDToAdd = contact.collectDBId();
                }
            }
            //lets user pick a Start Date
            LocalDate startLocalDate = appointmentStartDatePicker.getValue();
            //lets user pick an End Date
            LocalDate endLocalDate = appointmentEndDatePicker.getValue();
            //Time Formatter
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            //appointmentStartTime zoneID formatter
            LocalTime startLocalTime = LocalTime.parse(appointmentStartTimeComboBox.getValue(), timeFormatter);
            //appointmentEndTime zoneID formatter
            LocalTime endLocalTime = LocalTime.parse(appointmentEndTimeComboBox.getValue(), timeFormatter);
            //DB appointment start time
            LocalDateTime startLocalDateTimeToAdd = LocalDateTime.of(startLocalDate, startLocalTime);
            //DB appointment end time
            LocalDateTime endLocalDateTimeToAdd = LocalDateTime.of(endLocalDate, endLocalTime);

            // Outside normal hour Zone Check
            // Start Zone Parameters
            ZonedDateTime startLDTToZDT = ZonedDateTime.of(startLocalDateTimeToAdd, ZoneId.systemDefault());
            //america/new york zone id
            ZonedDateTime startZDTToZDTEST = startLDTToZDT.withZoneSameInstant(ZoneId.of("America/New_York"));
            //zoneId appointment start time check
            LocalTime startAppointmentTimeToCheck = startZDTToZDTEST.toLocalTime();
            //zoneID dayOfWeek start appointmentCheck
            DayOfWeek startAppointmentDayToCheck = startZDTToZDTEST.toLocalDate().getDayOfWeek();
            //startAppointmentCheck variable array checker
            int startAppointmentDayToCheckInt = startAppointmentDayToCheck.getValue();
            // End Zone Parameters
            ZonedDateTime endLDTToZDT = ZonedDateTime.of(endLocalDateTimeToAdd, ZoneId.systemDefault());
            //America/newYork zoneID time
            ZonedDateTime endZDTToZDTEST = endLDTToZDT.withZoneSameInstant(ZoneId.of("America/New_York"));
            //zoneID local time end Appointment
            LocalTime endAppointmentTimeToCheck = endZDTToZDTEST.toLocalTime();
            //zoneID dayOfWeek endAppointmentChecker
            DayOfWeek endAppointmentDayToCheck = endZDTToZDTEST.toLocalDate().getDayOfWeek();
            int endAppointmentDayToCheckInt = endAppointmentDayToCheck.getValue();

            // program business hours for user to make appointments between
            //start
            LocalTime startOfBusinessHours = LocalTime.of(8, 0, 0);
            //end
            LocalTime endOfBusinessHours = LocalTime.of(22, 0, 0);
            //start of a business week day
            int startOfWeekInt = DayOfWeek.MONDAY.getValue();
            //last of a business week day
            int endOfWeekInt = DayOfWeek.FRIDAY.getValue();
            // time and day parameter check
            if (startAppointmentTimeToCheck.isBefore(startOfBusinessHours) ||
                    //if startAppointmentTime is after business hours
                    startAppointmentTimeToCheck.isAfter(endOfBusinessHours) ||
                    //if endAppointmentTime is before business hours
                    endAppointmentTimeToCheck.isBefore(startOfBusinessHours) ||
                    //if endAppointmentTime is after business
                    endAppointmentTimeToCheck.isAfter(endOfBusinessHours)) {
                //depends on above statement following message will pop up:
                JOptionPane.showMessageDialog(null, "Time selected is not within business hours\n" +
                                "Make an Appointment between 08:00 and 22:00 Eastern",
                        "Appointment Couldn't Be Added", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //checks appointment time parameters
            if (startAppointmentDayToCheckInt < startOfWeekInt || startAppointmentDayToCheckInt > endOfWeekInt ||
                    //stops user from adding ending appointment time before start time
                    endAppointmentDayToCheckInt < startOfWeekInt || endAppointmentDayToCheckInt > endOfWeekInt) {
                JOptionPane.showMessageDialog(null, "The Day you selected is not within business hours\n" +
                                "Try Again between normal Business Days (Monday-Friday)",
                        "Appointment Couldn't Be Added", JOptionPane.ERROR_MESSAGE);
                return;
            }

            //program timeStamp
            Timestamp createdDateToAdd = Timestamp.valueOf(LocalDateTime.now());
            String createdByToAdd = "admin";
            Timestamp lastUpdateToAdd = Timestamp.valueOf(LocalDateTime.now());
            String lastUpdatedByToAdd = "admin";
            int customerIDToAdd = Integer.parseInt(appointmentCustomerIDTextField.getText());

            // Database Appointment overlap checker
            ObservableList<Appointments> allAppointments = DBAppointments.collectAllAppointmentsInfo();
            for (Appointments appointment : allAppointments) {
                LocalDateTime startTimesToCheck = appointment.getStart();
                LocalDateTime endTimesToCheck = appointment.getEnd();
                int customerIDsToCheck = appointment.getCustomerID();
                if (customerIDToAdd == customerIDsToCheck &&
                        (startLocalDateTimeToAdd.isEqual(startTimesToCheck) ||
                                startLocalDateTimeToAdd.isAfter(startTimesToCheck)) &&
                        (startLocalDateTimeToAdd.isEqual(endTimesToCheck) ||
                                startLocalDateTimeToAdd.isBefore(endTimesToCheck))) {
                    JOptionPane.showMessageDialog(null, "Your selected Start Time overlaps with an existing " +
                                    "appointment for customer", "Appointment Couldn't Be Added",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //check for Overlap by CustomerID checker
                if (customerIDToAdd == customerIDsToCheck &&
                        (endLocalDateTimeToAdd.isEqual(startTimesToCheck) ||
                                endLocalDateTimeToAdd.isAfter(startTimesToCheck)) &&
                        (endLocalDateTimeToAdd.isEqual(endTimesToCheck) ||
                                endLocalDateTimeToAdd.isBefore(endTimesToCheck))) {
                    JOptionPane.showMessageDialog(null, "Your selected End Time overlaps with an existing " +
                                    "appointment for customer", "Appointment Couldn't Be Added",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            //parses table view
            int userIDToAdd = Integer.parseInt(appointmentUserIDTextField.getText());
            String insertStatement = "INSERT INTO appointments (Appointment_ID, Title, Description, Location, Type, " +
                    "Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            //Database Inquiry and Save
            DBQuery.selectPreparedStatementInfo(DBConnection.getConnection(), insertStatement);
            //DB prepared statement getter
            PreparedStatement ps = DBQuery.getPreparedStatement();
            //gather appointmentID from DB
            ps.setInt(1, appointmentIDToAdd);
            //gather appointmentTitle from DB
            ps.setString(2, titleToAdd);
            //gather appointmentDescription from DB
            ps.setString(3, descriptionToAdd);
            //gather appointmentLocation from DB
            ps.setString(4, locationToAdd);
            //gather appointmentType from DB
            ps.setString(5, typeToAdd);
            //gather startLocalDateTime from DB
            ps.setTimestamp(6, Timestamp.valueOf(startLocalDateTimeToAdd));
            //gather appointment endLocalDateTime from DB
            ps.setTimestamp(7, Timestamp.valueOf(endLocalDateTimeToAdd));
            //gather appointment Dates
            ps.setTimestamp(8, createdDateToAdd);
            //gather created by user from DB
            ps.setString(9, createdByToAdd);
            //gather last updated feature from DB
            ps.setTimestamp(10, lastUpdateToAdd);
            //gather user admin that updated from DB
            ps.setString(11, lastUpdatedByToAdd);
            //gather customerID
            ps.setInt(12, customerIDToAdd);
            //gather userID
            ps.setInt(13, userIDToAdd);
            //gather contactID
            ps.setInt(14, contactIDToAdd);
            //mySql execute statement
            ps.execute();
            //pushes execute to tableView
            filterAppointmentDataToView();
            System.out.println("Appointment has been added to the database");
            resetAllTextBoxes();
        }
        else JOptionPane.showMessageDialog(null, "Appointment Data was not within parameters",
                "Appointment Couldn't Be Added" , JOptionPane.ERROR_MESSAGE);
    }

    /**
     *
     * gathers data that was populated in text fields
     * pairs name with ID
     *
     * establishes a local date from data fields
     *
     * establishes a local date time
     *
     * confirms if selected time and day are within business hours
     * confirms if any appointments are overlapping. Some Appointment ID restrictions apply
     *
     * checks for any updated statements
     * localDateTime is already confirmed with mySql
     * calls certain functions to show data and reset text fields
     *
     * @throws SQLException if exception has occurred
     */
    public void autoUpdateAppointmentInfo() throws SQLException {
        // checks for matching foreign key for customer ID and user ID
        ObservableList<Customers> allCustomerData = DBCustomers.selectAllCustomersDB();
        ObservableList<Integer> allCustomerIDs = FXCollections.observableArrayList();
        for (Customers customer : allCustomerData) {
            allCustomerIDs.add(customer.getCustomerID());
        }
        if (!allCustomerIDs.contains(Integer.parseInt(appointmentCustomerIDTextField.getText()))) {
            JOptionPane.showMessageDialog(null, "No Correlating Customer ID",
                    "Appointment Couldn't Be Added" , JOptionPane.ERROR_MESSAGE);
            return;
        }
        ObservableList<Users> allUserData = DBUsers.selectAllUsersInfo();
        ObservableList<Integer> allUserIDs = FXCollections.observableArrayList();
        for (Users user : allUserData) {
            allUserIDs.add(user.getUserID());
        }
        if (!allUserIDs.contains(Integer.parseInt(appointmentUserIDTextField.getText()))) {
            JOptionPane.showMessageDialog(null, "No Correlating User ID",
                    "Appointment Couldn't Be Added" , JOptionPane.ERROR_MESSAGE);
            return;
        }

        int appointmentIDToUpdate = Integer.parseInt(appointmentIDTextField.getText());
        //appointmentTitle getter
        String titleToUpdate = appointmentTitleTextField.getText();
        //appointmentDescription getter
        String descriptionToUpdate = appointmentDescriptionTextField.getText();
        //appointmentLocation getter
        String locationToUpdate = appointmentLocationTextField.getText();
        //appointmentType getter
        String typeToUpdate = appointmentTypeTextField.getText();
        //contactName getter
        String contactNameToUpdate = appointmentContactComboBox.getValue();
        int contactIDToUpdate = 0;
        //contact DB primary key-- filter by all contactInfo
        ObservableList<Contacts> contactsObservableList = DBContacts.selectAllContactsInfo();
        for (Contacts contact : contactsObservableList) {
            if (contactNameToUpdate.equals(contact.collectContactDBName())) {
                contactIDToUpdate = contact.collectDBId();
            }
        }
        LocalDate startLocalDate = appointmentStartDatePicker.getValue();
        LocalDate endLocalDate = appointmentEndDatePicker.getValue();
        //date time formatter for user to pick
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        //start
        LocalTime startLocalTime = LocalTime.parse(appointmentStartTimeComboBox.getValue(), timeFormatter);
        //end
        LocalTime endLocalTime = LocalTime.parse(appointmentEndTimeComboBox.getValue(), timeFormatter);
        LocalDateTime startLocalDateTimeToUpdate = LocalDateTime.of(startLocalDate, startLocalTime);
        LocalDateTime endLocalDateTimeToUpdate = LocalDateTime.of(endLocalDate, endLocalTime);

        // zone business hours
        // start
        ZonedDateTime startLDTToZDT = ZonedDateTime.of(startLocalDateTimeToUpdate, ZoneId.systemDefault());
        //america/NewYork timeZoneID
        ZonedDateTime startZDTToZDTEST = startLDTToZDT.withZoneSameInstant(ZoneId.of("America/New_York"));
        //startAppointment zoneID
        LocalTime startAppointmentTimeToCheck = startZDTToZDTEST.toLocalTime();
        //startAppointment DayOfWeek zoneID
        DayOfWeek startAppointmentDayToCheck = startZDTToZDTEST.toLocalDate().getDayOfWeek();
        //variable Link
        int startAppointmentDayToCheckInt = startAppointmentDayToCheck.getValue();
        // end
        ZonedDateTime endLDTToZDT = ZonedDateTime.of(endLocalDateTimeToUpdate, ZoneId.systemDefault());
        //DateTime america/NewYork
        ZonedDateTime endZDTToZDTEST = endLDTToZDT.withZoneSameInstant(ZoneId.of("America/New_York"));
        //zoneID endAppointmentTime
        LocalTime endAppointmentTimeToCheck = endZDTToZDTEST.toLocalTime();
        //zoneID DayOfWeek endAppointmentDay
        DayOfWeek endAppointmentDayToCheck = endZDTToZDTEST.toLocalDate().getDayOfWeek();
        int endAppointmentDayToCheckInt = endAppointmentDayToCheck.getValue();
        // the hours and days of business in database
        LocalTime startOfBusinessHours = LocalTime.of(8, 0, 0);
        //end of business hours in zone
        LocalTime endOfBusinessHours = LocalTime.of(22, 0, 0);
        //monday variable
        int startOfWeekInt = DayOfWeek.MONDAY.getValue();
        //friday variable
        int endOfWeekInt = DayOfWeek.FRIDAY.getValue();
        // appointment time and day update checker
        if (startAppointmentTimeToCheck.isBefore(startOfBusinessHours) ||
                startAppointmentTimeToCheck.isAfter(endOfBusinessHours) ||
                endAppointmentTimeToCheck.isBefore(startOfBusinessHours) ||
                endAppointmentTimeToCheck.isAfter(endOfBusinessHours)) {
            JOptionPane.showMessageDialog(null, "Time is not within business hours\n" +
                            "Try Again Within 08:00 and 22:00 EST",
                    "Appointment Couldn't Be Updated", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (startAppointmentDayToCheckInt < startOfWeekInt || startAppointmentDayToCheckInt > endOfWeekInt ||
                endAppointmentDayToCheckInt < startOfWeekInt || endAppointmentDayToCheckInt > endOfWeekInt) {
            JOptionPane.showMessageDialog(null, "Day is not within business hours\n" +
                            "Try Again Within the Days (Monday-Friday)",
                    "Appointment Couldn't Be Updated", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Timestamp lastUpdateToUpdate = Timestamp.valueOf(LocalDateTime.now());
        String lastUpdatedByToUpdate = "admin";
        int customerIDToUpdate = Integer.parseInt(appointmentCustomerIDTextField.getText());

        // appointment Overlap Check
        ObservableList<Appointments> allAppointments = DBAppointments.collectAllAppointmentsInfo();
        int selectedAppointmentID = 0;
        //month check
        if (appTableViewMonthTabButton.isSelected()) {
            selectedAppointmentID = appMonthTableView.getSelectionModel().getSelectedItem().getAppointmentID();
        }
        //week check
        if (appTableViewWeekTabButton.isSelected()) {
            selectedAppointmentID = appWeekTableView.getSelectionModel().getSelectedItem().getAppointmentID();

        }
        //if user selects appointment populates all other data fields
        if (ViewAllTabButton.isSelected()) {
            selectedAppointmentID = AllTableView.getSelectionModel().getSelectedItem().getAppointmentID();
        }
        for (Appointments appointment : allAppointments) {
            LocalDateTime startTimesToCheck = appointment.getStart();
            LocalDateTime endTimesToCheck = appointment.getEnd();
            int customerIDsToCheck = appointment.getCustomerID();
            if ((appointment.getAppointmentID() != selectedAppointmentID) &&
                    (customerIDToUpdate == customerIDsToCheck) &&
                    (startLocalDateTimeToUpdate.isEqual(startTimesToCheck) ||
                            startLocalDateTimeToUpdate.isAfter(startTimesToCheck)) &&
                    (startLocalDateTimeToUpdate.isEqual(endTimesToCheck) ||
                            startLocalDateTimeToUpdate.isBefore(endTimesToCheck))) {
                JOptionPane.showMessageDialog(null, "Updated start time will overlap existing " +
                                "appointment for customer", "Appointment Couldn't Be Updated",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if ((appointment.getAppointmentID() != selectedAppointmentID) &&
                    (customerIDToUpdate == customerIDsToCheck) &&
                    (endLocalDateTimeToUpdate.isEqual(startTimesToCheck) ||
                            endLocalDateTimeToUpdate.isAfter(startTimesToCheck)) &&
                    (endLocalDateTimeToUpdate.isEqual(endTimesToCheck) ||
                            endLocalDateTimeToUpdate.isBefore(endTimesToCheck))) {
                JOptionPane.showMessageDialog(null, "Updated end time will overlap existing " +
                                "appointment for customer", "Appointment Couldn't Be Updated",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        //updater
        int userIDToUpdate = Integer.parseInt(appointmentUserIDTextField.getText());
        String updateStatement = "UPDATE appointments SET Appointment_ID = ?, Title = ?, Description = ?, " +
                "Location = ?, Type = ?, Start = ?, End = ?, Last_Update = ?, Last_Updated_By = ?, " +
                "Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";


        //Adds updated information into mySql database
        DBQuery.selectPreparedStatementInfo(DBConnection.getConnection(), updateStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        //Updated Appointment ID
        ps.setInt(1, appointmentIDToUpdate);
        //Updated Title Header
        ps.setString(2, titleToUpdate);
        //Updated Description
        ps.setString(3, descriptionToUpdate);
        //Updated Appointment Location
        ps.setString(4, locationToUpdate);
        //Updated Appointment Type
        ps.setString(5, typeToUpdate);
        //Updated Appointment Start Date and Time
        ps.setTimestamp(6, Timestamp.valueOf(startLocalDateTimeToUpdate));
        //Updated Appointment End Date and Time
        ps.setTimestamp(7, Timestamp.valueOf(endLocalDateTimeToUpdate));
        //last updated feature
        ps.setTimestamp(8, lastUpdateToUpdate);
        //user to do last update feature
        ps.setString(9, lastUpdatedByToUpdate);
        //Updated customerID
        ps.setInt(10, customerIDToUpdate);
        //Updated userID
        ps.setInt(11, userIDToUpdate);
        //Updated contactID
        ps.setInt(12, contactIDToUpdate);
        //Updated appointmentID
        ps.setInt(13, appointmentIDToUpdate);
        //mySql execute statement
        ps.execute();
        //pushes AppointmentData to tableView
        filterAppointmentDataToView();
        System.out.println("Appointment was successfully updated in database!");
        //after message will reset all tableView boxes
        resetAllTextBoxes();
    }

    /**
     *
     * gathers appointment ID's
     * expands delete statements
     * shows changes while notifying user of changes will reset data fields
     *
     * @throws SQLException if exception has occurred
     */
    public void userDeleteAppointmentInfo() throws SQLException {
        String deleteStatement = "DELETE FROM appointments WHERE Appointment_ID = ?";
        //Database Query Call
        DBQuery.selectPreparedStatementInfo(DBConnection.getConnection(), deleteStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        int appointmentIDToDelete = 0;
        String appointmentType = "";
        if (appTableViewMonthTabButton.isSelected()) {
            appointmentIDToDelete = appMonthTableView.getSelectionModel().getSelectedItem().getAppointmentID();
            appointmentType = appMonthTableView.getSelectionModel().getSelectedItem().getAppointmentType();
        }
        if (appTableViewWeekTabButton.isSelected()) {
            appointmentIDToDelete = appWeekTableView.getSelectionModel().getSelectedItem().getAppointmentID();
            appointmentType = appWeekTableView.getSelectionModel().getSelectedItem().getAppointmentType();
        }
        if (ViewAllTabButton.isSelected()) {
            appointmentIDToDelete = AllTableView.getSelectionModel().getSelectedItem().getAppointmentID();
            appointmentType = AllTableView.getSelectionModel().getSelectedItem().getAppointmentType();
        }
        ps.setInt(1, appointmentIDToDelete);
        ps.execute();
        filterAppointmentDataToView();
        System.out.println("Appointment was removed from database!");
        JOptionPane.showMessageDialog(null,
                "\nAppointment_ID: " + appointmentIDToDelete +
                        "\nType of Appointment: " + appointmentType,
                "Appointment removed from Database", JOptionPane.INFORMATION_MESSAGE);
        resetAllTextBoxes();
    }

    // new view changes
    /**
     *
     * @param event on mouse click will move from AppointmentForm.fxml to CustomerForm.fxml
     * @throws IOException if exception has occurred
     */
    public void buttonToCustomerSceneChange(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Main/CustomerForm.fxml")));
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        //scene window size
        primaryStage.setScene(new Scene(root, 900, 600));
    }

    /**
     *
     * @param event on mouse click will move from AppointmentForm to ReportForm
     * @throws IOException if exception has occurred
     */
    public void buttonToReportSceneChange(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Main/ReportForm.fxml")));
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(new Scene(root, 900, 600));
    }


    public class AppointmentTimeChanger {
        //reiterates main
        public static void main(String[] args) {
            // If customer appointment time is in UTC
            LocalDateTime appointmentTime = LocalDateTime.parse("2023-03-01T14:30:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);

            // Gathers the host's time zone
            ZoneId hostTimeZone = ZoneId.systemDefault();

            // This will convert all appointment times to the host's time zone
            ZonedDateTime appointmentTimeInHostTimeZone = ZonedDateTime.of(appointmentTime, ZoneId.of("UTC")).withZoneSameInstant(hostTimeZone);

            // Outputs the new appointment times
            System.out.println("Appointment time in host time zone: " + appointmentTimeInHostTimeZone.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

            // Change appointment time
            appointmentTimeInHostTimeZone = appointmentTimeInHostTimeZone.withHour(16).withMinute(0);

            // Convert back to UTC
            ZonedDateTime appointmentTimeInUTC = appointmentTimeInHostTimeZone.withZoneSameInstant(ZoneId.of("UTC"));

            // Print updated appointment time in UTC
            System.out.println("Updated appointment time in UTC: " + appointmentTimeInUTC.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }
    }
}