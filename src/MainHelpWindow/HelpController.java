package MainHelpWindow;

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

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Control the functions and the interactivity between the user and the help window.
 *
 * @author Justin Fern&aacute;ndez, Gabriel Chac&#243;n, Jimena León and Abraham Venegas.
 * @version 1
 */
public class HelpController extends Controller{
    @FXML
    ComboBox cmbCities;
    @FXML
    TextArea infoCities;
    @FXML
    Button returnBtn;
    @FXML
    Pane pane;

    private Hashtable<String,String> infoAboutCities= new Hashtable();
    /**
     * This method occurs when the window is opened.
     */
    @FXML
    public void initialize() {
        String[] citiesTokyo1 = {"Akiruno","Akishima","Chōfu","Fuchū","Fussa", "Hachiōji", "Hamura",
                                "Higashikurume","Higashimurayama","Higashiyamato","Hino","Inagi","Kyose",
                                "Kodaira","Koganei"};
        cmbCities.getItems().addAll(citiesTokyo1);
        cmbCities.setPromptText("Escoja una ciudad");
        String[] info = {"\nPoblación: 400 000\nLugares de interes: \n \t 1. Nasjjs \n \t 2. Nasjjs \n \t 3. Nasjjs \n Lugares de comida:\n 1. sdhch\n 2. djdjdj \nGasolineras: \n 1. djhd"};
        int j = 0;
        for (String i:citiesTokyo1) {
            infoAboutCities.put(i,i+info[j]);
            //j++;
        }
    }


    /**
     * Displays the information about a city in the text area.
     * @param e is an action event when the city is changed.
     */
    public void showInformation(ActionEvent e){
        infoCities.setText(infoAboutCities.get(cmbCities.getValue()));
    }
}
