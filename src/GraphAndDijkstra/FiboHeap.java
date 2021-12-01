package GraphAndDijkstra;

import java.util.*;

/**
 * A Fibonacci Heap to help the Dijkstra algorithm to find the minimum time required from city A to city B
 *
 * @param <T>
 * @author Jimena Leon, Justin Fernandez, Gabriel Chacon and Abraham Venegas
 */
public final class FiboHeap<T> {

    /**
     * Entry or particular element of a Fibonacci Heap
     *
     * @param <T> Generic types of Java
     */
    class Node<T> {
        private Node<T> next;
        private Node<T> prev;
        private Node<T> parent;
        private Node<T> child;
        private T elem;
        private double time; // The Priority of a node, in our case would be time or distance
        private int degree = 0; // It´s like the depth of a tree (niveles de un arbol)
        private boolean isMarked = false; //

        public T getValue() {
            return elem;
        }
        public double getTime() {
            return time;
        }

        private Node(T elem, double time) {
            next = prev = this; // Because is a circular list
            this.elem = elem;
            this.time = time;
            //this.citySource = source;
            //this.cityDest = dest;
        }
    }

    private Node<T> minimum = null;
    private int sizeFibo = 0;

    /**
     * Inserts an element (node) in the Fibonacci Heap
     *
     * @param value the name of the city
     * @param time the total time spent to go to that city
     * @return The inserted node
     */
    public Node<T> enqueue(T value, double time) {
        verifyPriority(time);

        /* Create the entry object, which is a circularly-linked list of length one. */
        Node<T> result = new Node<T>(value, time);

        /* Merge this list with the tree list. */
        minimum = uniteLists(minimum, result);

        /* Increase the size of the heap; we just added something. */
        ++sizeFibo;

        /* Return the reference to the new element. */
        return result;
    }

    /**
     * Loses a particular node from the Fibonacci Heap
     *
     * @param node the node we want to lose/erase/delete (the garbage collector does that for us)
     */
    private void deleteNode(Node<T> node) {
        /* We begin by clearing the node's mark, because we just cut it */
        node.isMarked = false;

        /* If the node has no parent, we finished :) */
        if (node.parent == null) {
            return;
        }

        /* Rearrange the node's siblings (punteros) around it, if it has any siblings */
        if (node.next != node) { // Has siblings. Remember this is a circular list!!
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }

        /* If the node is the one identified by its parent as its child, we need to rewrite that pointer to point
         * to some other random child */
        if (node.parent.child == node) {

            /* If there are any other children, pick one of them randomly */
            if (node.next != node) {
                node.parent.child = node.next;
            }
            /* Otherwise, there aren't any children left, so we should clear the pointer and decrease
             * the node's degree also */
            else {
                node.parent.child = null;
            }
        }

        /* Decrease the degree of the parent, because it just lost a child :( */
        --node.parent.degree;

        /* Move this tree into the root list by converting it to a "single root" by invoking the uniteLists function */
        node.prev = node.next = node;
        minimum = uniteLists(minimum, node); // updates and returns the new minimum element :D

        /* Mark the parent and recursively cut it if it's already isMarked */
        if (node.parent.isMarked){
            deleteNode(node.parent);
        } else{
            node.parent.isMarked = true;
        }

        /* Clear the rearranged node's parent, because it's now a root */
        node.parent = null;
    }

    /**
     * Finds the minimum element and then deletes it from the Fibonacci Heap
     *
     * @return The minimum element
     */
    public Node<T> dequeueMinimum() {
        /* Check for whether we're empty */
        if (isEmpty())
            throw new NoSuchElementException("Heap is empty.");

        /* Otherwise, lose an element, so decrement the number of
         * entries in this heap
         */
        --sizeFibo;

        /* Grab the minimum element, so we know what to return */
        Node<T> minElem = minimum;

        /* Now, we need to get rid of this element from the list of roots.  There  are two cases to consider.
         * First, if this is the only element in the list of roots, we set the list of roots to be null by
         * clearing "minimum". Otherwise, if it's not null, then we write the elements next to the min element
         * around the min element to remove it, then arbitrarily reassign the min
         */
        if (minimum.next == minimum) { // Case one
            minimum = null;
        }
        else { // Case two
            minimum.prev.next = minimum.next;
            minimum.next.prev = minimum.prev;
            minimum = minimum.next; // Arbitrary element of the root list
        }

        /* Next, clear the parent fields of all the min element's children, since they're about to become roots.
         * Because the elements are stored in a circular list, the traversal is complex... */
        if (minElem.child != null) {
            /* Keep track of the first visited node */
            Node<?> curr = minElem.child;
            do {
                curr.parent = null;

                /* Walk to the next node, then stop if this is the node we started at */
                curr = curr.next;

            } while (curr != minElem.child);
        }

        /* Next, move the children of the root node into the topmost list, then set "minimum" to point
         * somewhere in that list */
        minimum = uniteLists(minimum, minElem.child); // Here it updates the real minimum, not arbitrarily like before

        /* If there are no entries left, we're done :) */
        if (minimum == null){
            return minElem;
        }

        /* Next, we need to unite all the roots so that there is only one tree of each degree.
         * To track trees of each size, we create an ArrayList where the entry at position i is either null or the
         * unique tree of degree i */
        List<Node<T>> treeList = new ArrayList<Node<T>>();

        /* We need to traverse the entire list, but since we're going to be messing around with it we have to be
         * careful not to break our traversal order in the process.  One major challenge is how to detect whether
         * we're visiting the same node twice.  To do this, we'll spent a bit of overhead adding all the nodes to
         * a list, and then will visit each element of this list in order */
        List<Node<T>> toVisit = new ArrayList<Node<T>>();

        /* To add everything, we'll iterate across the elements until we
         * find the first element twice.  We check this by looping while the
         * list is empty or while the current element isn't the first element
         * of that list.
         */
        for (Node<T> curr = minimum; toVisit.isEmpty() || toVisit.get(0) != curr; curr = curr.next){
            toVisit.add(curr);
        }


        /* Traverse this list and perform the appropriate union steps. */
        for (Node<T> curr: toVisit) {
            /* Keep merging until a match arises */
            while (true) {

                /* Ensure that the list is long enough to hold an element of this degree */
                while (curr.degree >= treeList.size()){
                    treeList.add(null);
                }

                /* If nothing's here, we can record that this tree has this size and are done processing */
                if (treeList.get(curr.degree) == null) {
                    treeList.set(curr.degree, curr);
                    break;
                }

                /* Otherwise, merge with what's there */
                Node<T> other = treeList.get(curr.degree);
                treeList.set(curr.degree, null); // Clear the slot

                /* Determine which of the two trees has the smaller root, storing the two tree accordingly using
                 * an if statement but in another syntax */
                Node<T> min = (other.time < curr.time)? other : curr;
                Node<T> max = (other.time < curr.time)? curr  : other;

                /* Delete max out of the root list, then merge it into min's child list */
                max.next.prev = max.prev;
                max.prev.next = max.next;

                /* Make it everyone the same so that we can merge it */
                max.next = max.prev = max;
                min.child = uniteLists(min.child, max);

                /* Reparent max following heap condition */
                max.parent = min;

                /* Clear max's mark, since it can now lose another child */
                max.isMarked = false;

                /* Increase min's degree; it now has another child */
                ++min.degree;

                /* Continue merging this tree */
                curr = min;
            }

            /* Update the global min based on this node. We compare <= instead of < here because if we just did a
             * reparent operation that merged two different trees of equal time, we need to make sure that
             * the min pointer points to the root-level one */
            if (curr.time <= minimum.time){
                minimum = curr;
            }
        }
        return minElem;
    }

    /**
     * Updates the total time spent from city A to city B
     *
     * @param node the node whose time we want to decrease
     * @param newTime the new value of time we will give to the node
     */
    public void decreaseTimeOfNode(Node<T> node, double newTime) {

        verifyPriority(newTime);

        if (newTime > node.time){
            System.out.println("New time exceeds old, and the point of this method is to decrease it :v ");
            return; // Ends the process
        }

        /* First, change the node's time */
        node.time = newTime;

        /* If the node no longer has a higher time than its parent, cut it */
        if (node.parent != null && node.time <= node.parent.time)
            deleteNode(node);

        /* If our new value is the new min, mark it as the new min!! */
        if (node.time <= minimum.time)
            minimum = node;
    }

    /**
     * Unifies two roots on the FIobnacci Heap. In case they are trees, it deals with all the pointers involved.
     *
     * @param listOne first list
     * @param listTwo second list
     * @param <T> Generic type of Java
     * @return smallest list between those two
     */
    private <T> Node<T> uniteLists(Node<T> listOne, Node<T> listTwo) {
        /* Four cases depending if the lists are null or not */
        if (listOne == null && listTwo == null) { // Both null, resulting list is null
            return null;
        }
        else if (listOne != null && listTwo == null) { // Two is null, result is one
            return listOne;
        }
        else if (listOne == null && listTwo != null) { // One is null, result is two
            return listTwo;
        }
        else { // Both non-null, then unite (merge) the lists
            Node<T> oneNext = listOne.next; // Save this for later because we're going to overwrite it
            listOne.next = listTwo.next;
            listOne.next.prev = listOne;
            listTwo.next = oneNext;
            listTwo.next.prev = listTwo;

            /* Finally this method evaluate which of the lists (could be individual nodes) is smaller and returns that
             * list */

            if (listOne.time < listTwo.time){
                return listOne;
            }else{
                return listTwo;
            }
        }
    }

    /**
     * Verifies if the priority (time) is a number or not
     *
     * @param priority
     */
    private void verifyPriority(double priority) {
        if (Double.isNaN(priority)){
            throw new IllegalArgumentException(priority + " is invalid :(");
        }
    }

    /**
     *  Evaluates if the Fibonacci Heap is empty or not
     *
     * @return whether the Fibonacci Heap is empty or not
     */
    public boolean isEmpty() {
        return minimum == null;
    }
}
