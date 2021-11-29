package MainHelpWindow;

import API.API;
import GraphAndDijkstra.Dijkstra;
import GraphAndDijkstra.Graph;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    Label expectedTime;

    @FXML
    Spinner<Double> delaySpinner;

    Double currentDelay;
    quickSort orderList;
    @FXML
    public void initialize() {
        orderList = quickSort.getInstance();
        ctcDeparture.getItems().addAll(orderList.getOrderedCities());
        ctcArrival.getItems().addAll(orderList.getOrderedCities());
        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0,100);
        valueFactory.setValue(0.0);
        delaySpinner.setValueFactory(valueFactory);
    }

    /**
     * This method calls the Dijkstra's algorithm to find the shortest path.
     * @param event is an action event when the button is clicked.
     */
    public <V, K> void callDijkstra(ActionEvent event) throws IOException {
        String[] Cities = (orderList.getOrderedCities());
        List<String> ListCities = Arrays.stream(Cities).toList();
        int indexDeparture = ListCities.indexOf(ctcDeparture.getValue());
        int indexArrival = ListCities.indexOf(ctcArrival.getValue());
        System.out.println("indexD = " + indexDeparture);
        System.out.println("indexA = " + indexArrival);
        API api = new API();

        try {
            Graph graph = new Graph();

            Double[][] distances = api.callApi(); // must start at [1][1] but ignoring the [i][i] because they are 0.0
            int matrixSize = distances.length;

            Double time = 0.0;
            String timeString = "";
            Double timeRounded = 0.0;
            DecimalFormat df = new DecimalFormat("#.00");
            /* Create the nodes */
            for(int k = 0; k <= 25; k++){
                graph.addNode(k);
            }

            /* Here we create the edges */
            for (int i = 0; i < matrixSize; i++) {
                for (int j = 0; j < matrixSize; j++) {
                    time = distances[i][j]; // time in minutes
                    if(i != j){
                        timeString = df.format(time);

                        if(timeString.indexOf(",")!=-1){
                            timeString = timeString.replace(',', '.');
                        }

                        timeRounded = Double.valueOf(timeString);
                        graph.addEdge(i, j, timeRounded);
                    }
                }
            }
            Dijkstra dijkstra = new Dijkstra();

            Map<K, Double> route = dijkstra.calcShortestPath(graph,indexDeparture, indexArrival);
            Double totalTime = route.get(indexArrival);

            System.out.println("\n" + "Para llegar a " + Cities[indexArrival] + " desde " + Cities[indexDeparture] + " :" + "\n" + route
                    + "\n" + "Tiempo total: "+ totalTime + " min");

            Double delayTime = delaySpinner.getValue();
            expectedTime.setText(String.valueOf(totalTime + delayTime));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

