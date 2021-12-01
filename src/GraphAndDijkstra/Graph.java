package GraphAndDijkstra;

import java.util.*;

/**
 * A directed graph represented with dictionaries
 *
 * @param <T> Generic type of Java
 * @author Jimena Leon, Justin Fernandez, Gabriel Chacon and Abraham Venegas
 */
public final class Graph<T> implements Iterable<T> {

    /* The graph is a dictionary */
    private final Map<T, Map<T, Double>> graph = new HashMap<T, Map<T, Double>>();

    /**
     * Inserts a node in the graph
     * @param node the node we will add on the graph
     * @return returns a boolean indicating if the node wasn´t there before
     */
    public boolean addNode(T node) {
        /* If the node already exists, don't do anything */
        if (graph.containsKey(node)) {
            return false;
        }else{
            /* Otherwise, add the node with an empty dictionary of edges (aristas salientes) */
            graph.put(node, new HashMap<T, Double>()); // (node, {})
            return true;
        }
    }

    /**
     * Insert an edge on the graph from city start to city dest with a time
     * @param start
     * @param dest
     * @param length
     */
    public void addEdge(T start, T dest, double length) {
        /* Confirm both endpoints exist */
        if (!graph.containsKey(start) || !graph.containsKey(dest)){
            System.out.println("Both nodes must be in the graph!!!");
        }else{
            /* Search the value (hashmap of edges) asscoiated to the node on the graph, and adds the edge
             * to that hashmap */
            graph.get(start).put(dest, length);
        }

    }

    /**
     * Returns the edges from a particular node
     * @param node the node we want the edges from
     * @return the edges from the given node
     */
    public Map<T, Double> edgesFrom(T node) {
        /* Check that the node exists. */
        Map<T, Double> edges = graph.get(node);

        if (edges == null) {
            throw new NoSuchElementException("Source node does not exist :(");
        }

        return Collections.unmodifiableMap(edges);
    }

    @Override
    public Iterator<T> iterator() {
        /* This method allows us to go through the graph (recorrer el grafo) */
        return graph.keySet().iterator();
    }
}