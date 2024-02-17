package Main;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

/**@author Billy Payne
 *
 *  */

import javafx.application.Application;
import javafx.stage.Stage;
import java.util.Objects;
import DBUtility.DBConnection;


public class Main extends Application {

    /**
     *
     * @param primaryStage fxml stage setter
     * @throws Exception if exception has occurred
     */
    @Override
    //main start method
    public void start(Stage primaryStage) throws Exception{
        //FXML scene loader LogInForm
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Main/LogInForm.fxml")));
        //program title
        primaryStage.setTitle("Wgu Software 2 Final");
        //screen size
        primaryStage.setScene(new Scene(root, 900, 600));
        //populate screen
        primaryStage.show();
    }

    /**
     *
     * @param args program method
     */
    //main program function
    public static void main(String[] args) {
        DBConnection.startConnection();
        launch(args);
        DBConnection.stopConnection();
        System.out.println("\nProgram has be terminated!");

    }
}