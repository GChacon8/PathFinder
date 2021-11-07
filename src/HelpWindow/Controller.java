package HelpWindow;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

/**
 * Control the functions and the interactivity between the user and the help window.
 *
 * @author Justin Fern&aacute;ndez, Gabriel Chac&#243;n, Jimena León and Abraham Venegas.
 * @version 1
 */
public class Controller {
    @FXML
    ComboBox cmbCities;
    @FXML
    TextArea infoCities;
    @FXML
    Button returnBtn;
    @FXML
    Circle circleCity;
    @FXML
    Pane pane;

    TextArea t = new TextArea();
    /**
     * This method occurs when the window is opened.
     */
    @FXML
    public void initialize() {
        String[] citiesTokyo1 = {"Akiruno","Akishima","Chofu","Fuchu","Fussa", "Hachi"};
        cmbCities.getItems().addAll(citiesTokyo1);
        cmbCities.setPromptText("Escoja una ciudad");
    }


    /**
     * Displays the information about a city in the text area.
     * @param e is an action event when the city is changed.
     */
    public void showInformation(ActionEvent e){
        if (cmbCities.getValue().equals("Akiruno")){
            infoCities.setText("Akiruno Población: 5 000 000");
        }else if (cmbCities.getValue().equals("Cartago")){
            infoCities.setText("Cartago Población: 500 000");
        }else{
            infoCities.setText("San José Población: 700 000");
        }
    }

    /**
     * This method returns to the main window.
     * @param e is an action event when the button is clicked.
     */
    public void returnToMainWindow(ActionEvent e){
        System.out.println("Okay");
    }

    public void displayText(MouseEvent M){
        t.setText("Logradooooo");
        t.setPrefSize(100,50);
        t.setLayoutX(circleCity.getLayoutX());
        t.setLayoutY(circleCity.getLayoutY());
        pane.getChildren().add(t);
        infoCities.setText("CIUDAD 1");
    }

    public void unDisplayText(MouseEvent e){
        ObservableList<Node> children = pane.getChildren();
        if(children.size()>5){
            pane.getChildren().remove(children.size()-1);
        }
    }
}
