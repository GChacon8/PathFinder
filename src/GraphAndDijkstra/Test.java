package GraphAndDijkstra;

import API.API;

import java.text.DecimalFormat;
import java.util.Map;

public class Test {

    public static <V, K> void main(String[] args) {

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


                        timeRounded = Double.valueOf(timeString);
                        graph.addEdge(i, j, timeRounded);
                    }
                }
            }

            Dijkstra dijkstra = new Dijkstra();

            int source = 5;
            int destine = 17;
            Map<K, Double> route = dijkstra.calcShortestPath(graph,source, destine);
            Double totalTime = route.get(destine);

            System.out.println("\n" + "Para llegar a " + destine + " desde " + source + " :" + "\n" + route
                    + "\n" + "Tiempo total: "+ totalTime + " min");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}