package MainHelpWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller to switch between scenes
 *
 * @author Justin Fernandez, Gabriel Chacon, Jimena Leon and Abraham Venegas.
 *
 */

public class Controller {

    public Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * This method shows the help window.
     * @param event is an action event when the button is clicked.
     */
    public void showHelp(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("HelpWindowGUI.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,800,500);
        stage.setTitle("Help");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    /**
     * This method returns to the main window.
     * @param event is an action event when the button is clicked.
     */
    public void returnToMainWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainWindowGUI.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Main");
        stage.setScene(scene);
        stage.show();
    }
}
