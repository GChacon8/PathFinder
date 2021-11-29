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
        quickSort orderList = quickSort.getInstance();
        ctcDeparture.getItems().addAll(orderList.getOrderedCities());
        ctcArrival.getItems().addAll(orderList.getOrderedCities());
        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0,100);
        valueFactory.setValue(0.0);
        delaySpinner.setValueFactory(valueFactory);
    }
}
