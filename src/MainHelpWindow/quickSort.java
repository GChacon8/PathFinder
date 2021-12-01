package MainHelpWindow;


/**
 * Algorithm to order the cities alphabetically
 *
 * @author Justin Fernandez, Gabriel Chacon, Jimena Leon and Abraham Venegas.
 * @version 1
 */
public class quickSort {
    private static quickSort instance;
    private static String[] orderedCities;
    private static String[] unsortedCities;
    /**
     * This is the constructor method of the class
     */
    private quickSort() {
        this.orderedCities = null;
        this.unsortedCities = new String[]{"Akiruno", "Akishima", "Chōfu", "Fuchū", "Fussa", "Hachiōji", "Hamura",
                "Higashikurume", "Higashimurayama", "Higashiyamato", "Hino", "Inagi", "Kiyose",
                "Kodaira", "Koganei", "Kokubunji", "Komae", "Kunitachi", "Machida", "Mitaka", "Musashimurayama",
                "Musashino", "Nishitokyo", "Ome", "Tachikawa", "Tama"};
    }

    /**
     * This method verificates if exists any instance of the class, and return the existing one if there is
     * or create one to return it.
     * @return instance the object to get access to the method to throw dice.
     */
    public static quickSort getInstance() {
        if (instance == null){
            instance = new quickSort();
        }
        return instance;
    }

    /**
     * This method calls the quickSort if it has never been run, otherwise it
     * returns the already sorted array
     * @return the sorted list with the cities
     */
    public static String[] getOrderedCities() {
        if (orderedCities==null){
            System.out.println("Ejecutado");
            orderedCities = quickSort(unsortedCities,0,unsortedCities.length-1);
            return orderedCities;
        }
        return orderedCities;
    }

    /**
     * This method swap elements to sort the array.
     * @param cities the array with the cities, to swap the elements
     * @param minIndex  the minimun Index to make the swap
     * @param otherIndex the other index to make the swap
     */
    static void swap(String[] cities, int minIndex, int otherIndex) {
        String temp = cities[minIndex];
        cities[minIndex] = cities[otherIndex];
        cities[otherIndex] = temp;
    }

    /**
     * This method takes last city as pivot, places the pivot element
     * at its correct position in sorted array. After that, places all smaller than pivot
     * in its left and all greater cities to its right.
     * @param cities the array with the cities to sort.
     * @param minIndex the minimum index to consider for the partition
     * @param maxIndex the maximum index to consider for the partition
     * @return
     */
    static int partition(String[] cities, int minIndex, int maxIndex) {
        String pivot = cities[maxIndex];

        int i = (minIndex - 1);
        for(int j = minIndex; j <= maxIndex - 1; j++) {
            if (cities[j].compareTo(pivot) < 0) {
                i++;
                swap(cities, i, j);
            }
        }
        swap(cities, i + 1, maxIndex);
        return (i + 1);
    }

    /**
     * Implements the sort array applying the partition and recursion
     * @param cities the array with the cities to sort.
     * @param minIndex the minimum index to consider for the partition
     * @param maxIndex the maximum index to consider for the partition
     * @return the sort cities array
     */
    static String[] quickSort(String[] cities, int minIndex, int maxIndex) {
        if (minIndex < maxIndex) {
            int partitionIndex = partition(cities, minIndex, maxIndex);
            quickSort(cities, minIndex, partitionIndex - 1);
            quickSort(cities, partitionIndex + 1, maxIndex);
        }
        return cities;
    }
}
