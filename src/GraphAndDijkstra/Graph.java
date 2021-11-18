package GraphAndDijkstra;

import java.util.*; // For HashMap (diccionario)

/**
 * A directed graph that has weight in every edge (arista) of its nodes.
 * This graph uses maps/dictionaries to represent the connections/edges between its nodes.
 *
 * @author Jimena Leon, Justin Fernandez, Gabriel Chacon and Abraham Venegas.
 * @param <T>
 */
public final class Graph<T> implements Iterable<T> {
    /* A map from nodes in the graph to sets of outgoing edges (aristas salientes).  Each
     * set of edges is represented by a map from edges to doubles.
     * A map in Java is like a dictionary in Python.
     */
    private final Map<T, Map<T, Double>> graph = new HashMap<T, Map<T, Double>>();

    /**
     * Adds a new node to the graph. If the node already exists, it adds nothing.
     *
     * @param node The node to add.
     * @return Whether or not the node was added.
     */
    public boolean addNode(T node) {
        /* If the node already exists, don't do anything. */
        if (graph.containsKey(node))
            return false;

        /* Otherwise, add the node with an empty set of outgoing edges (aristas salientes). */
        graph.put(node, new HashMap<T, Double>());
        return true;
    }

    /**
     * Given a start node, destination, and length, adds an arc from the
     * start node to the destination of the length.  If an arc already
     * existed, the length is updated to the specified value.  If either
     * endpoint does not exist in the graph, throws a NoSuchElementException.
     *
     * @param start  The start node.
     * @param dest   The destination node.
     * @param length The length of the edge.
     * @throws NoSuchElementException If either the start or destination nodes
     *                                do not exist.
     */
    public void addEdge(T start, T dest, double length) {
        /* Confirm both endpoints exist. */
        if (!graph.containsKey(start) || !graph.containsKey(dest))
            throw new NoSuchElementException("Both nodes must be in the graph.");

        /* Add the edge. */
        graph.get(start).put(dest, length);
    }

    /**
     * Given a node in the graph, returns an immutable view of the edges
     * leaving (saliendo de) that node, as a map from endpoints to costs.
     *
     * @param node The node whose edges we want.
     * @return An immutable view of the edges leaving that node.
     * @throws NoSuchElementException If the node does not exist.
     */
    public Map<T, Double> edgesFrom(T node) {
        /* Check that the node exists. */
        Map<T, Double> arcs = graph.get(node);

        if (arcs == null) {
            throw new NoSuchElementException("Source node does not exist.");
        }

        return Collections.unmodifiableMap(arcs);
    }

    @Override
    public Iterator<T> iterator() {
        /* This method allows us to go through the graph (recorrer el grafo) */
        return null;
    }
}
