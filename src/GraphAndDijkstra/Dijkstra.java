package GraphAndDijkstra;

import java.util.*;

public final class Dijkstra {

    public <T> Map<T, Double> calcShortestPath(Graph<T> graph, T source, T destine) {
        /* Create a Fibonacci heap storing the distances of unvisited nodes
         * from the source node.
         */
        FiboHeap<T> priorityQueue = new FiboHeap<T>();

        /* The Fibonacci heap uses an internal representation that hands back Node objects for every stored element.
         * This map associates each node in the graph with its corresponding Node */
        Map<T, FiboHeap<T>.Node<T>> entriesMap = new HashMap<T, FiboHeap<T>.Node<T>>();

        /* Maintain a map from nodes to their distances.  Whenever we expand a node for the first time, we'll put
         * it in here */
        Map<T, Double> timesMap = new HashMap<T, Double>();

        /* This dictionary will contain which city is current of another city to spend the minimum
         * time going there */
        Map<T, T> ancestorsMap = new HashMap<T, T>();

        /* Add each node to the Fibonacci heap at distance +infinity because initially all nodes are unreachable */
        for (T node: graph){
            FiboHeap<T>.Node<T> nnn = priorityQueue.enqueue(node, Double.POSITIVE_INFINITY);
            entriesMap.put(node, nnn);
        }

        /* Update the source so that it's at distance 0.0 from itself because we can get there with a path
         * of length zero!
         */
        priorityQueue.decreaseTimeOfNode(entriesMap.get(source), 0.0);

        /* Keep processing the queue until no nodes remain */
        while (!priorityQueue.isEmpty()) {
            /* Grab the current node. The algorithm guarantees that we now
             * have the shortest distance to it
             */
            FiboHeap<T>.Node<T> currMin = priorityQueue.dequeueMinimum();

            /* Store this in the timesMap table */
            timesMap.put(currMin.getValue(), currMin.getTime()); // (city, time)

            /* Update the priorities (times) of all of its edges */
            for (Map.Entry<T, Double> edge : graph.edgesFrom(currMin.getValue()).entrySet()) {

                /* If we already know the shortest path from the source to
                 * this node, don't add the edge */
                if (timesMap.containsKey(edge.getKey())){
                    continue;
                }

                /* Calculate the cost of the path from the source to this node,
                 * which is the cost of this node plus the cost of this edge */
                double pathCost = currMin.getTime() + edge.getValue();


                /* If the length of the best-known path from the source to
                 * this node is longer than this potential path cost, update
                 * the cost of the shortest path.
                 */
                FiboHeap<T>.Node<T> dest = entriesMap.get(edge.getKey());

                if (pathCost < dest.getTime()){
                    priorityQueue.decreaseTimeOfNode(dest, pathCost); // Updates the time required to go to cityX
                    ancestorsMap.put(edge.getKey(),currMin.getValue());
                }
            }
        }
        //--------------------------------------------------------------------------------------------//

        /* Now we need the results ONLY for destine city, not all cities. We can do that by calling the predecessor of
         * our destine city and iteratively calling the predecessor of that other city. That way we only involve the
         * cities that leads to our DESTINE. We store in a new dictionary (called bestRoute) the cities and its
         * minimum times to get to each of them. Finally, we stop when the city is equal to our source city */

        T current = destine;
        Double predecesorTime;
        Map<T, Double> bestRoute = new HashMap<T, Double>();
        System.out.println(ancestorsMap);
        while(current != source){ // Add times of the cities involved to get to DESTINE
            predecesorTime = timesMap.get(current);
            bestRoute.put(current, predecesorTime);
            current = ancestorsMap.get(current); // Updates current
        }

        bestRoute.put(source, 0.0); // Here we add the time spent to go to SOURCE CITY (which is zero)

        /* Finally, Dijkstra returns the bestRoute dictionary for us to access it. Later, if we want the total time
         * we could write: bestRoute.get(dest); That will give us a "double" number which is equal to the time spent
         * to go to our destine city :) */

        return bestRoute;
    }
}
