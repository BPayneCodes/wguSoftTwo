package Controller;

/**@author Billy Payne
 *
 * */
import javafx.scene.Parent;
import javafx.scene.Scene;
import DBAccess.*;
import java.util.ArrayList;
import java.util.Objects;
import Model.*;
import Reports.AppointmentReportMonth;
import Reports.AppointmentReportType;
import Reports.memberFirstLevelDivisionReport;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.*;
import java.util.Collections;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.time.LocalDateTime;
import javafx.fxml.FXMLLoader;
import java.time.Month;
import java.io.IOException;
import java.sql.SQLException;


public class Report {

    @FXML ToggleGroup formControlToggleGroup;

    // customer appointment total
    @FXML Tab customerAppointmentsTab;
    //  Appointment Type Table View
    @FXML TableView<AppointmentReportType> customerAppointmentsReportTypeView;
    @FXML TableColumn<AppointmentReportType, String> customerAppointmentsReportType;
    @FXML TableColumn<AppointmentReportType, Integer> customerAppointmentsSumReportTypeCol;
    //  Appointment Month Table View
    @FXML TableView<AppointmentReportMonth> customerAppointmentsMonthView;
    @FXML TableColumn<AppointmentReportMonth, String> customerAppointmentsMonthCol;
    @FXML TableColumn<AppointmentReportMonth, Integer> customerAppointmentsTotalMonthCol;

    // contact appointments
    @FXML Tab contactAppointmentsTab;
    @FXML TableView<Appointments> contactAppointmentsTableView;
    //Contact ID
    @FXML TableColumn<Appointments, Integer> contactAppointmentsIDCol;
    //contact Title
    @FXML TableColumn<Appointments, String> contactAppointmentsTitleCol;
    //Contact Type
    @FXML TableColumn<Appointments, String> contactAppointmentTypeCol;
    //Contact Description
    @FXML TableColumn<Appointments, String> contactAppointmentsDescriptionCol;
    //Contact Start Date and Time
    @FXML TableColumn<Appointments, LocalDateTime> contactAppointmentsStartDateAndTimeCol;
    //Contact End Date and Time
    @FXML TableColumn<Appointments, LocalDateTime> contactAppointmentsEndDateAndTimeCol;
    //Contact Customer Link ID
    @FXML TableColumn<Appointments, Integer> contactAppointmentsCustomerIDCol;
    //contactHeader
    @FXML Label contactLabel;
    //contact ComboBox
    @FXML ComboBox<String> contactComboBox;

    // customers by first-level division
    @FXML Tab customerFirstLevelDivisionTab;
    @FXML TableView<memberFirstLevelDivisionReport> customerFirstLevelDivisionTableView;
    //First Level Division column
    @FXML TableColumn<memberFirstLevelDivisionReport, String> firstLevelDivisionCol;
    //Customer List column
    @FXML TableColumn<memberFirstLevelDivisionReport, ArrayList<String>> customerListCol;


    /**
     *
     * @throws SQLException if exception has occurred
     */
    public void initialize() throws SQLException {
        //Label Contact visibility
        contactLabel.setVisible(false);
        //Makes Contacts nonviable from the program
        contactComboBox.setVisible(false);
        //Makes Contacts Editable in the program
        contactComboBox.setEditable(true);
        //disables DB edit
        contactComboBox.getEditor().setEditable(false);

        // first report-AppointmentTotal
        customerAppointmentsReportType.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        customerAppointmentsSumReportTypeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTotal"));

        // second report-AppointmentMonth
        customerAppointmentsMonthCol.setCellValueFactory(new PropertyValueFactory<>("appointmentMonth"));
        //second report-AppointmentTotal
        customerAppointmentsTotalMonthCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTotal"));

        // third report-AppointmentContact
        //ID Report
        contactAppointmentsIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        //Header Report
        contactAppointmentsTitleCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        //Type Report
        contactAppointmentTypeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        //Description Report
        contactAppointmentsDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        //Appointment Start Date and Time Report
        contactAppointmentsStartDateAndTimeCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        //Appointment End Date and Time Report
        contactAppointmentsEndDateAndTimeCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        //Customer ID Report
        contactAppointmentsCustomerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));

        // third report-AppointmentFirstLevelDivision-CustomerList
        firstLevelDivisionCol.setCellValueFactory(new PropertyValueFactory<>("firstLevelDivision"));
        customerListCol.setCellValueFactory(new PropertyValueFactory<>("customerList"));
    }

    /**
     * gathers appointment types from database
     * prevents appointments with duplicates
     *
     * @throws SQLException if exception has occurred
     */
    public void fillAppointmentTypesTotals() throws SQLException {
        String typeToSet;
        int totalToSet;
        //primary key
        ObservableList<Appointments> allAppointmentData = DBAppointments.collectAllAppointmentsInfo();
        //FXCollections Array
        ObservableList<String> appointmentTypes = FXCollections.observableArrayList();
        //foreign key
        allAppointmentData.forEach(appointments -> appointmentTypes.add(appointments.getAppointmentType()));
        //DB duplicate filter
        ObservableList<String> appointmentTypesWithoutDuplicates = FXCollections.observableArrayList();
        //Looks and bypasses duplicates
        for (Appointments appointment : allAppointmentData) {
            //getter appointmentType from DB
            String type = appointment.getAppointmentType();
            if (!appointmentTypesWithoutDuplicates.contains(type)) {
                //without duplicate add profile type
                appointmentTypesWithoutDuplicates.add(type);
            }
        }
        //match primary key with foreign key
        ObservableList<AppointmentReportType> appointmentReportTypes = FXCollections.observableArrayList();
        //primary key
        for (String type : appointmentTypesWithoutDuplicates) {
            //foreign key collections
            int total = Collections.frequency(appointmentTypes, type);
            typeToSet = type;
            //total
            totalToSet = total;
            AppointmentReportType typeInstance = new AppointmentReportType(typeToSet, totalToSet);
            //add appointmentReportType
            appointmentReportTypes.add(typeInstance);
        }
        customerAppointmentsReportTypeView.setItems(appointmentReportTypes);
    }

    /**
     * pairs appointment months from all databases
     * finds list of month duplications
     *
     * @throws SQLException if exception has occurred
     */
    public void fillAppointmentMonthsSums() throws SQLException {
        String monthToSet;
        int totalToSet;
        //primary key
        ObservableList<Appointments> allAppointmentData = DBAppointments.collectAllAppointmentsInfo();
        //foreign mey
        ObservableList<Month> appointmentMonths = FXCollections.observableArrayList();
        //gather all appointment data
        //get month Data
        for (Appointments appointment : allAppointmentData) {
            //get appointment INFo
            Month month = appointment.getStart().getMonth();
            //adds appointment month profile
            appointmentMonths.add(month);
        }
        //Searches through month appointments and bypasses duplicates
        ObservableList<Month> appointmentMonthsWithoutDuplicates = FXCollections.observableArrayList();
        for (Month month : appointmentMonths) {
            //key month--
            if (!appointmentMonthsWithoutDuplicates.contains(month)) {
                appointmentMonthsWithoutDuplicates.add(month);
            }
        }
        //Appointment Report Month Observable List
        ObservableList<AppointmentReportMonth> appointmentReportMonths = FXCollections.observableArrayList();
        //No Duplicate Month Reports
        for (Month month : appointmentMonthsWithoutDuplicates) {
            int total = Collections.frequency(appointmentMonths, month);
            //Month
            monthToSet = month.name();
            //Total
            totalToSet = total;
            //appointmentMonth instance
            AppointmentReportMonth monthInstance = new AppointmentReportMonth(monthToSet, totalToSet);
            //add appointmentReportMonth profile
            appointmentReportMonths.add(monthInstance);
        }
        customerAppointmentsMonthView.setItems(appointmentReportMonths);
    }

    /**
     * to be paired with above statements
     * @throws SQLException if exception has occurred
     */
    public void fillSumCustomerAppoint() throws SQLException {
        fillAppointmentTypesTotals();
        fillAppointmentMonthsSums();
    }


    /**
     * shows text field boxes
     * pulls contact name in table views
     *
     * @throws SQLException if exception has occurred
     */
    public void fillAppointmentsByContactInfo() throws SQLException {
        contactLabel.setVisible(true);
        contactComboBox.setVisible(true);
        ObservableList<Contacts> allContacts = DBContacts.selectAllContactsInfo();
        ObservableList<String> contactNames = FXCollections.observableArrayList();
        for (Contacts contacts : allContacts) {
            String contactName = contacts.collectContactDBName();
            contactNames.add(contactName);
        }
        contactComboBox.setItems(contactNames);
    }

    /**
     * collects contact name from text field
     * collects clicked name with ID from all data
     * collects clicked appointment data
     *
     * @throws SQLException if exception has occurred
     */
    public void gatheredContactAppointmentInfo() throws SQLException {
        int selectedContactID = 0;
        Appointments selectedContactAppointmentData;
        String selectedContactName = contactComboBox.getSelectionModel().getSelectedItem();
        if (selectedContactName != null) {

            ObservableList<Contacts> allContactData = DBContacts.selectAllContactsInfo();
            for (Contacts contact : allContactData) {
                if (selectedContactName.equals(contact.collectContactDBName())) {
                    selectedContactID = contact.collectDBId();
                }
            }
            //primary key collect
            ObservableList<Appointments> allAppointmentData = DBAppointments.collectAllAppointmentsInfo();
            ObservableList<Appointments> appointmentDataForSelectedContact = FXCollections.observableArrayList();
            for (Appointments appointment : allAppointmentData) {
                if (selectedContactID == appointment.getContactID()) {
                    selectedContactAppointmentData = appointment;
                    appointmentDataForSelectedContact.add(selectedContactAppointmentData);
                }
            }
            contactAppointmentsTableView.setItems(appointmentDataForSelectedContact);
        }
    }


    /**
     * collects division ID with customer first level division ID and prevents double entries
     *
     * @throws SQLException if exception has occurred
     */
    public void fillCustomersWithFirstLevelDivisionDataBase() throws SQLException {
        String firstLevelDivisionToSet ;
        ArrayList<String> customerListToSet;
        memberFirstLevelDivisionReport record;
        String previousSetDivisionName = "";

        ObservableList<FirstLevelDivisions> allFirstLevelDivisionData = DBFirstLevelDivisions.selectAllFirstLevelDivisionsInfo();
        ObservableList<Customers> allCustomerData = DBCustomers.selectAllCustomersDB();
        ObservableList<memberFirstLevelDivisionReport> observableListToSet = FXCollections.observableArrayList();

        for (FirstLevelDivisions firstLevelDivision : allFirstLevelDivisionData) {
            customerListToSet = new ArrayList<>();
            for (Customers customer : allCustomerData) {
                if (firstLevelDivision.getDivisionID() == customer.divisionID) {
                    firstLevelDivisionToSet = firstLevelDivision.getDivisionName();
                    customerListToSet.add(customer.getCustomerName());
                    record = new memberFirstLevelDivisionReport(firstLevelDivisionToSet, customerListToSet);
                    if (!record.getFirstLevelDivision().equals(previousSetDivisionName)) {
                        observableListToSet.add(record);
                        previousSetDivisionName = record.getFirstLevelDivision();
                    }
                }
            }
        }
        customerFirstLevelDivisionTableView.setItems(observableListToSet);
    }

    // fxml form changes

    /**
     *
     * @param event with user Interaction will change from ReportForm to CustomerForm
     * @throws IOException if exception has occurred
     */
    public void buttonToCustomerChangeFXMLScene(ActionEvent event) throws IOException {
        //fxml change scene
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Main/CustomerForm.fxml")));
        //stage window scene
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        //window size screen
        primaryStage.setScene(new Scene(root, 900, 600));
    }

    /**
     *
     * @param event with user interaction will switch from ReportForm to AppointmentForm
     * @throws IOException if exception has occurred
     */
    public void buttonToAppointmentChangeScene(ActionEvent event) throws IOException {
        //fxml scene change
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Main/AppointmentForm.fxml")));
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        //screen window size
        primaryStage.setScene(new Scene(root, 900, 600));
    }
}