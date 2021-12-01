package MainHelpWindow;

import API.API;
import GraphAndDijkstra.Dijkstra;
import GraphAndDijkstra.Graph;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

/**
 * Control the functions and the interactivity between the user and the app.
 *
 * @author Justin Fernandez, Gabriel Chacon, Jimena Leon and Abraham Venegas.
 * @version 1
 */
public class MainController extends Controller{

    @FXML
    ComboBox ctcDeparture,ctcArrival;
    @FXML
    Button HelpButton;
    @FXML
    Button findPath;
    @FXML
    Pane pane;

    @FXML
    Label expectedTime,peso_23_6,peso_0_23,peso_0_6,peso_0_4,peso_0_5,peso_4_1,peso_4_5,peso_5_10,peso_5_18,peso_18_25,peso_11_25,
            peso_10_25,peso_5_25,peso_1_10,peso_1_5,peso_10_24,peso_10_17,peso_3_10,peso_1_24,peso_4_6,peso_4_20,
            peso_4_24,peso_24_20,peso_9_24,peso_13_24,peso_17_24,peso_15_24,peso_15_17,peso_3_17,peso_3_25,peso_3_11,
            peso_3_2,peso_3_14,peso_3_15,peso_2_16,peso_2_14,peso_2_19,peso_2_11,peso_9_20,peso_19_21,peso_14_21,
            peso_22_21,peso_14_19,peso_14_15,peso_14_13,peso_14_22,peso_13_15,peso_13_22,peso_13_7,peso_13_8,
            peso_13_9,peso_7_8,peso_7_12,peso_7_22,peso_8_12,peso_8_9;


    @FXML
    Circle akiruno, akishima, chofu, fuchu, fussa, hachioji, hamura, higashikurume, higashimurayama, higashiyamato, hino,
            inaji, kiyose, kodaira, konagei, kokubunji, komae, kinitachi, machida, mitaka, musashimurayama, musashino,
            nishitokyo, ome, tachikawa, tama;

    @FXML
    Spinner<Double> delaySpinner;

    quickSort orderList;
    Map<Integer, Circle> circlePlaces = new HashMap<Integer, Circle>();

    /**
     * This method occurs just before the window is opened, sets the comboBox element
     * with the cities in alphabetical order. Also, sets the circles for an easy access.
     */
    @FXML
    public void initialize() {
        orderList = quickSort.getInstance();
        ctcDeparture.getItems().addAll(orderList.getOrderedCities());
        ctcArrival.getItems().addAll(orderList.getOrderedCities());
        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 100);
        valueFactory.setValue(0.0);
        delaySpinner.setValueFactory(valueFactory);
        circlePlaces.put(0, akiruno);
        circlePlaces.put(1, akishima);
        circlePlaces.put(2, chofu);
        circlePlaces.put(3, fuchu);
        circlePlaces.put(4, fussa);
        circlePlaces.put(5, hachioji);
        circlePlaces.put(6, hamura);
        circlePlaces.put(7, higashikurume);
        circlePlaces.put(8, higashimurayama);
        circlePlaces.put(9, higashiyamato);
        circlePlaces.put(10, hino);
        circlePlaces.put(11, inaji);
        circlePlaces.put(12, kiyose);
        circlePlaces.put(13, kodaira);
        circlePlaces.put(14, konagei);
        circlePlaces.put(15, kokubunji);
        circlePlaces.put(16, komae);
        circlePlaces.put(17, kinitachi);
        circlePlaces.put(18, machida);
        circlePlaces.put(19, mitaka);
        circlePlaces.put(20, musashimurayama);
        circlePlaces.put(21, musashino);
        circlePlaces.put(22, nishitokyo);
        circlePlaces.put(23, ome);
        circlePlaces.put(24, tachikawa);
        circlePlaces.put(25, tama);
    }

    /**
     * This method calls the Dijkstra's algorithm to find the shortest path.
     * @param event is an action event when the button is clicked.
     *
     * @throws IOException if something happened wrong with the API
     */
    public <V, K> void callDijkstra(ActionEvent event) throws IOException {
        for(int i = 0; i < 26;i++){
            circlePlaces.get(i).setFill(javafx.scene.paint.Paint.valueOf("#00afff"));
        }
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
            DecimalFormat dformat = new DecimalFormat("#.000");
            /* Create the nodes */
            for (int k = 0; k <= 25; k++) {
                graph.addNode(k);
            }

            /* Here we create the edges */
            for (int i = 0; i < matrixSize; i++) {
                for (int j = 0; j < matrixSize; j++) {
                    time = distances[i][j]; // time in minutes
                    if (i != j) {
                        timeString = dformat.format(time);
                        if (timeString.indexOf(",") != -1) {
                            timeString = timeString.replace(',', '.');
                        }
                        timeRounded = Double.valueOf(timeString);
                        graph.addEdge(i, j, timeRounded);
                    }
                }
            }
            Dijkstra dijkstra = new Dijkstra();

            Map<K, Double> route = dijkstra.calcShortestPath(graph, indexDeparture, indexArrival);
            Double totalTime = route.get(indexArrival);

            System.out.println("\n" + "Para llegar a " + Cities[indexArrival] + " desde " + Cities[indexDeparture] + " :" + "\n" + route
                    + "\n" + "Tiempo total: " + totalTime + " min");

            Double delayTime = delaySpinner.getValue();
            expectedTime.setText(String.valueOf(dformat.format(totalTime + delayTime) + " min"));

            peso_23_6.setText(String.valueOf(dformat.format(((distances[23][6])*60*((float)200/(float)9))/1000)));
            peso_0_23.setText(String.valueOf(dformat.format(((distances[0][23])*60*((float)200/(float)9))/1000)));
            peso_0_6.setText(String.valueOf(dformat.format(((distances[0][6])*60*((float)200/(float)9))/1000)));
            peso_0_4.setText(String.valueOf(dformat.format(((distances[0][4])*60*((float)200/(float)9))/1000)));
            peso_0_5.setText(String.valueOf(dformat.format(((distances[0][5])*60*((float)200/(float)9))/1000)));
            peso_4_1.setText(String.valueOf(dformat.format(((distances[1][4])*60*((float)200/(float)9))/1000)));
            peso_4_5.setText(String.valueOf(dformat.format(((distances[5][4])*60*((float)200/(float)9))/1000)));
            peso_5_10.setText(String.valueOf(dformat.format(((distances[5][10])*60*((float)200/(float)9))/1000)));
            peso_5_18.setText(String.valueOf(dformat.format(((distances[5][18])*60*((float)200/(float)9))/1000)));
            peso_18_25.setText(String.valueOf(dformat.format(((distances[18][25])*60*((float)200/(float)9))/1000)));
            peso_11_25.setText(String.valueOf(dformat.format(((distances[11][25])*60*((float)200/(float)9))/1000)));
            peso_10_25.setText(String.valueOf(dformat.format(((distances[10][25])*60*((float)200/(float)9))/1000)));
            peso_5_25.setText(String.valueOf(dformat.format(((distances[5][25])*60*((float)200/(float)9))/1000)));
            peso_1_10.setText(String.valueOf(dformat.format(((distances[1][10])*60*((float)200/(float)9))/1000)));
            peso_1_5.setText(String.valueOf(dformat.format(((distances[1][5])*60*((float)200/(float)9))/1000)));
            peso_10_24.setText(String.valueOf(dformat.format(((distances[10][24])*60*((float)200/(float)9))/1000)));
            peso_10_17.setText(String.valueOf(dformat.format(((distances[10][17])*60*((float)200/(float)9))/1000)));
            peso_3_10.setText(String.valueOf(dformat.format(((distances[10][3])*60*((float)200/(float)9))/1000)));
            peso_1_24.setText(String.valueOf(dformat.format(((distances[1][24])*60*((float)200/(float)9))/1000)));
            peso_4_6.setText(String.valueOf(dformat.format(((distances[6][4])*60*((float)200/(float)9))/1000)));
            peso_4_20.setText(String.valueOf(dformat.format(((distances[20][4])*60*((float)200/(float)9))/1000)));
            peso_4_24.setText(String.valueOf(dformat.format(((distances[24][4])*60*((float)200/(float)9))/1000)));
            peso_24_20.setText(String.valueOf(dformat.format(((distances[20][24])*60*((float)200/(float)9))/1000)));
            peso_9_24.setText(String.valueOf(dformat.format(((distances[9][24])*60*((float)200/(float)9))/1000)));
            peso_13_24.setText(String.valueOf(dformat.format(((distances[13][24])*60*((float)200/(float)9))/1000)));
            peso_17_24.setText(String.valueOf(dformat.format(((distances[17][24])*60*((float)200/(float)9))/1000)));
            peso_15_24.setText(String.valueOf(dformat.format(((distances[15][24])*60*((float)200/(float)9))/1000)));
            peso_15_17.setText(String.valueOf(dformat.format(((distances[15][17])*60*((float)200/(float)9))/1000)));
            peso_3_17.setText(String.valueOf(dformat.format(((distances[3][17])*60*((float)200/(float)9))/1000)));
            peso_3_25.setText(String.valueOf(dformat.format(((distances[3][25])*60*((float)200/(float)9))/1000)));
            peso_3_11.setText(String.valueOf(dformat.format(((distances[3][11])*60*((float)200/(float)9))/1000)));
            peso_3_2.setText(String.valueOf(dformat.format(((distances[3][2])*60*((float)200/(float)9))/1000)));
            peso_3_14.setText(String.valueOf(dformat.format(((distances[3][14])*60*((float)200/(float)9))/1000)));
            peso_3_15.setText(String.valueOf(dformat.format(((distances[3][15])*60*((float)200/(float)9))/1000)));
            peso_2_16.setText(String.valueOf(dformat.format(((distances[2][16])*60*((float)200/(float)9))/1000)));
            peso_2_14.setText(String.valueOf(dformat.format(((distances[2][14])*60*((float)200/(float)9))/1000)));
            peso_2_19.setText(String.valueOf(dformat.format(((distances[2][19])*60*((float)200/(float)9))/1000)));
            peso_2_11.setText(String.valueOf(dformat.format(((distances[2][11])*60*((float)200/(float)9))/1000)));
            peso_9_20.setText(String.valueOf(dformat.format(((distances[20][9])*60*((float)200/(float)9))/1000)));
            peso_19_21.setText(String.valueOf(dformat.format(((distances[19][21])*60*((float)200/(float)9))/1000)));
            peso_14_21.setText(String.valueOf(dformat.format(((distances[14][21])*60*((float)200/(float)9))/1000)));
            peso_22_21.setText(String.valueOf(dformat.format(((distances[22][21])*60*((float)200/(float)9))/1000)));
            peso_14_19.setText(String.valueOf(dformat.format(((distances[14][19])*60*((float)200/(float)9))/1000)));
            peso_14_15.setText(String.valueOf(dformat.format(((distances[14][15])*60*((float)200/(float)9))/1000)));
            peso_14_13.setText(String.valueOf(dformat.format(((distances[14][13])*60*((float)200/(float)9))/1000)));
            peso_14_22.setText(String.valueOf(dformat.format(((distances[14][22])*60*((float)200/(float)9))/1000)));
            peso_13_15.setText(String.valueOf(dformat.format(((distances[13][15])*60*((float)200/(float)9))/1000)));
            peso_13_7.setText(String.valueOf(dformat.format(((distances[13][7])*60*((float)200/(float)9))/1000)));
            peso_13_8.setText(String.valueOf(dformat.format(((distances[13][8])*60*((float)200/(float)9))/1000)));
            peso_13_9.setText(String.valueOf(dformat.format(((distances[13][9])*60*((float)200/(float)9))/1000)));
            peso_13_22.setText(String.valueOf(dformat.format(((distances[13][22])*60*((float)200/(float)9))/1000)));
            peso_7_8.setText(String.valueOf(dformat.format(((distances[8][7])*60*((float)200/(float)9))/1000)));
            peso_7_12.setText(String.valueOf(dformat.format(((distances[7][12])*60*((float)200/(float)9))/1000)));
            peso_7_22.setText(String.valueOf(dformat.format(((distances[7][22])*60*((float)200/(float)9))/1000)));
            peso_8_12.setText(String.valueOf(dformat.format(((distances[8][12])*60*((float)200/(float)9))/1000)));
            peso_8_9.setText(String.valueOf(dformat.format(((distances[8][9])*60*((float)200/(float)9))/1000)));

            for(K place: route.keySet()){
                Circle CirclestoPaint = circlePlaces.get(place);
                CirclestoPaint.setFill(javafx.scene.paint.Paint.valueOf("RED"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
}


