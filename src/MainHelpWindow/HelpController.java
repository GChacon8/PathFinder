package MainHelpWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Control the functions and the interactivity between the user and the help window.
 *
 * @author Justin Fernandez, Gabriel Chacon, Jimena Leo n and Abraham Venegas.
 * @version 1
 */
public class HelpController extends Controller{
    @FXML
    ComboBox cmbCities;
    @FXML
    TextField txtPopulation;
    @FXML
    TextArea txtParks;
    @FXML
    TextArea txtMuseums;
    @FXML
    TextArea txtOthers;
    @FXML
    TextArea txtGasStations;
    @FXML
    TextArea txtRestaurants;
    @FXML
    Button returnBtn;
    @FXML
    Pane pane;


    /**
     * This method occurs when the window is opened.
     */
    @FXML
    public void initialize() {
        quickSort orderList = quickSort.getInstance();
        cmbCities.getItems().addAll(orderList.getOrderedCities());
        cmbCities.setPromptText("Escoja una ciudad");
    }

    public String[] getInfoCity(String Cityname) throws FileNotFoundException {
        try {
            //csv file containing data
            String csvFile = "src/MainHelpWindow/cities.csv";
            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            String line;
            String[] infoForTextAreas = new String[6];
            String InfoCity = "";
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(Cityname)){
                    String[] cols = line.split(",");
                    infoForTextAreas[0] = cols[1]+" habitantes";
                    for(String park : cols[2].split("-")){
                        InfoCity += park+"\n";
                    }
                    infoForTextAreas[1] = InfoCity;
                    InfoCity = "";
                    for(String Museum : cols[3].split("-")){
                        InfoCity += Museum+"\n";
                    }
                    infoForTextAreas[2] = InfoCity;
                    InfoCity = "";
                    for(String other : cols[4].split("-")){
                        InfoCity += other+"\n";
                    }
                    infoForTextAreas[3] = InfoCity;
                    InfoCity = "";
                    for(String rest : cols[5].split("-")){
                        InfoCity += rest+"\n";
                    }
                    infoForTextAreas[4] = InfoCity;
                    InfoCity = "";
                    for(String gasStation : cols[6].split("-")){
                        InfoCity += gasStation+"\n";
                    }
                    infoForTextAreas[5] = InfoCity;
                    InfoCity = "";
                }
            }
            return infoForTextAreas;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[]{};
    }

    /**
     * Displays the information about a city in the text area.
     * @param e is an action event when the city is changed.
     */
    public void showInformation(ActionEvent e) throws FileNotFoundException {
        String[] infoCity = getInfoCity((String) cmbCities.getValue());
        txtPopulation.setText(infoCity[0]);
        txtParks.setText(infoCity[1]);
        txtMuseums.setText(infoCity[2]);
        txtOthers.setText(infoCity[3]);
        txtRestaurants.setText(infoCity[4]);
        txtGasStations.setText(infoCity[5]);
    }
}
