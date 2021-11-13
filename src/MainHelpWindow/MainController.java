package MainHelpWindow;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

public class MainController extends Controller{

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

    @FXML
    Spinner<Double> delaySpinner;

    Double currentDelay;

    @FXML
    public void initialize() {
        String[] citiesTokyo1 = {"Akiruno","Akishima","Chōfu","Fuchū","Fussa", "Hachiōji", "Hamura",
                "Higashikurume","Higashimurayama","Higashiyamato","Hino","Inagi","Kyose",
                "Kodaira","Koganei"};
        String[] citiesTokyo2 = citiesTokyo1;
        ctcDeparture.getItems().addAll(citiesTokyo1);
        ctcArrival.getItems().addAll(citiesTokyo2);
        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0,100);
        valueFactory.setValue(0.0);
        delaySpinner.setValueFactory(valueFactory);
    }
}
