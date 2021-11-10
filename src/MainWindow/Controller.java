package MainWindow;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * Controller for the main window
 *
 * @author Justin Fern&aacute;ndez, Gabriel Chac&#243;n, Jimena León and Abraham Venegas.
 *
 */

public class Controller {

    @FXML
    ComboBox ctcDeparture;
    @FXML
    ComboBox ctcArrival;
    @FXML
    TextArea delayTraffic;
    @FXML
    Button HelpButton;
    @FXML
    Button findPath;
    @FXML
    Pane pane;

    TextArea t = new TextArea();

    @FXML
    public void initialize() {
        String[] citiesTokyo1 = {"Akiruno","Akishima","Chofu","Fuchu","Fussa", "Hachi"};
        String[] citiesTokyo2 = {"Akiruno","Akishima","Chofu","Fuchu","Fussa", "Hachi"};
        ctcDeparture.getItems().addAll(citiesTokyo1);
        ctcDeparture.setPromptText("Choose the city");
        ctcArrival.getItems().addAll(citiesTokyo2);
        ctcArrival.setPromptText("Choose the city");
    }

}
