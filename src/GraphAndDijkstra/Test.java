package GraphAndDijkstra;

import java.util.Map;

public class Test {

    public static <V, K> void main(String[] args) {

        Graph graph = new Graph();

        graph.addNode("Alajuela");
        graph.addNode("San Jose");
        graph.addNode("Cartago");
        graph.addNode("Guanacaste");

        graph.addEdge("Alajuela", "San Jose", 120);
        graph.addEdge("Alajuela", "Cartago", 360);
        graph.addEdge("Cartago", "Limon", 560);
        graph.addEdge("San Jose", "Cartago", 200);
        graph.addEdge("Alajuela", "Limon", 1000);
        graph.addEdge("Guanacaste", "Alajuela", 200);
        graph.addEdge("Guanacaste", "Cartago", 1300);

        Dijkstra dijkstra = new Dijkstra();

        String source = "Guanacaste";
        String destine = "Cartago";
        Map<K, Double> route = dijkstra.calcShortestPath(graph,source, destine);

        Double totalTime = route.get(destine);

        System.out.println("\n" + "Para llegar a " + destine + " desde "
                + source + " necesita pasar por estas ciudades y se duran estos tiempos: "
                + "\n" + "\n" + route + "\n" + "\n" + "El tiempo total para llegar es " +
                totalTime + " min, más atrasos");

    }
}