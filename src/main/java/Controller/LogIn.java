package Controller;

import javafx.fxml.FXML;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/** @author Billy Payne
 *
 * */

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Node;

import javax.swing.JOptionPane;


public class LogIn {

    @FXML
    public Label logInLabel;
    @FXML public TextField userIDField;
    @FXML public TextField passwordField;
    @FXML public Button enterButton;
    @FXML public Label userLocationLabel;
    //loginError Message
    String logInErrorMessage ="Invalid user ID or password";
    //system.out.print error
    String logInErrorTitle = "Log-In Was Unsuccessful";
    boolean successfulLogIn = false;

    public void initialize() {
        officialLanguageCheckResource();
    }

    /**
     * puts location as userLocationLabel variable
     * will display resource bundle French if user has french settings
     * gathers message text from rb properties file
     * formatting for all fields
     */
    public void officialLanguageCheckResource() {
        ZoneId currentZone = ZoneId.systemDefault();
        userLocationLabel.setText("User Location: " + currentZone);
        //translation
        Locale French = new Locale("fr", "FR");
        //resource file location
        ResourceBundle rb = ResourceBundle.getBundle("Main/Nat", Locale.FRENCH);
        //language: French
        if(Locale.getDefault().getLanguage().equals("fr")) {
            //if user has french system that will be the default
            Locale.setDefault(French);
            //Title to be translated
            logInLabel.setText((rb.getString("Log-In,Form")).replaceAll(",", " "));
            //UserID Translation
            userIDField.setPromptText((rb.getString("user,ID")).replaceAll(",", " "));
            //Password Translation
            passwordField.setPromptText(rb.getString("password").replaceAll(",", " "));
            //Submit Translation
            enterButton.setText(rb.getString("Submit"));
            int indexOfSeparation = (currentZone.toString()).indexOf("/");
            String countryToPrint = (currentZone.toString()).substring(0, indexOfSeparation);

            /**French Translation
             * Title
             * UserID
             * Password
             * Submit
             * TimeZone*/

            String countryToPrintFR;
            //User Location to be Translated in French
            if (countryToPrint.equals("Pacific") || countryToPrint.equals("America") || countryToPrint.equals("Europe")) {
                //prints countryName in FR
                countryToPrintFR = (rb.getString(countryToPrint));
                //currentZone in FR
                int indexOfEnd = (currentZone.toString()).length();
                //currentLocation inFR
                String locationToPrint = (currentZone.toString()).substring(indexOfSeparation, indexOfEnd);
                //French User Location Variable String
                userLocationLabel.setText((rb.getString("User,Location")).replaceAll(",", " ") +
                        //FR translation
                        ": " + countryToPrintFR + locationToPrint);
            }
            else {
                //if User has time zone outside of parameters
                userLocationLabel.setText((rb.getString("User,Location")).replaceAll(",", " ") +
                        ": " + currentZone);
            }
            //Translated error UserId or Password
            logInErrorMessage = rb.getString("Invalid,user,ID,or,password").replaceAll(",", " ");
            //Translated error Login Failure
            logInErrorTitle = rb.getString("Log-In,Failed").replaceAll(",", " ");
            //Login layout
            logInLabel.setLayoutX(190);
            //userLocation layout
            userLocationLabel.setLayoutX(350);
        }
    }

    /**
     *
     * @param event interacting with enter button, checks for user verification
     * if logIn is accessed, will switch fxml scenes
     * if logIn failed will display error message
     *
     * @throws IOException if exception has occurred
     * @throws SQLException if exception has occurred
     */
    public void userLogInAccess(ActionEvent event) throws IOException, SQLException {
        String userID = userIDField.getText();
        String password = passwordField.getText();
        //username and password login
        if (userID.equals("test") && password.equals("test")) {
            //fxml customerForm scene change
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Main/CustomerForm.fxml")));
            //gathers scene
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            //window size
            primaryStage.setScene(new Scene(root, 900, 600));
            //on login will provide new customer
            Customer login = new Customer();
            //login will check for upcoming appointment and offer alert
            login.lookInUpcomingAppointmentInfo();
            successfulLogIn = true;
        }
        else {
            JOptionPane.showMessageDialog(null,
                    logInErrorMessage,
                    logInErrorTitle,
                    JOptionPane.ERROR_MESSAGE);
            successfulLogIn = false;
        }
        forwardTrackActivityLogger();
    }

    /**
     * every login will be forwarded to text file in root with timestamps
     *
     * @throws IOException if exception has occurred
     */
    public void forwardTrackActivityLogger() throws IOException {
        //tracks Date user logged into program
        LocalDate attemptDate = LocalDateTime.now().toLocalDate();
        //tracks Time user logged into program
        Timestamp attemptTimestamp = Timestamp.valueOf(LocalDateTime.now());
        //logger .txt activity file


        //new file
        FileWriter fileWriter = new FileWriter("login_activity.txt", true);
        //write file
        PrintWriter outputFile = new PrintWriter(fileWriter);
        //Date Print
        outputFile.print("Date: " + attemptDate + " -- ");
        //TimeStamp Print
        outputFile.print("Timestamp: " + attemptTimestamp + " -- ");
        if (successfulLogIn) {
            //System.print Successful login
            outputFile.print("Attempt Successfully Completed\n");
        }
        else {
            outputFile.print("Attempt Failed Please Try Again\n");
        }

        outputFile.close();
    }
}