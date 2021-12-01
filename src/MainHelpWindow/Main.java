package MainHelpWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 * The Main Java Class that creates the main window which have the same
 * map and the interface to choose a departe and arrival place, also to go
 * to Help Window.
 *
 * @author Justin Fernandez, Gabriel Chacon, Jimena Leon and Abraham Venegas.
 * @version 1
 */
public class Main extends Application {

    /**
     * Sets the ambient of the windows and shows them.
     * @param primaryStage recieves the Stage which the App will be implemented.
     * @throws Exception if something happened loading the fxml file with the code for the app.
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainWindowGUI.fxml"));
        primaryStage.setTitle("Main");
        primaryStage.setScene(new Scene(root, 778, 635));
        primaryStage.setResizable(false);
        Image image = new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/9/9e/Flag_of_Japan.svg/135px-Flag_of_Japan.svg.png");
        primaryStage.getIcons().add(image);
        primaryStage.show();
    }
    /**
     * Main method of the Main class to open the GUI
     * @param args stores the incomding command line arguments for the program
     */
    public static void main(String[] args) {
        launch(args);
    }
}
