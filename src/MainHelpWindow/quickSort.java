package MainHelpWindow;

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

    public static String[] getOrderedCities() {
        if (orderedCities==null){
            System.out.println("Ejecutado");
            orderedCities = quickSort(unsortedCities,0,unsortedCities.length-1);
            return orderedCities;
        }
        return orderedCities;
    }

    static void swap(String[] cities, int minIndex, int otherIndex) {
        String temp = cities[minIndex];
        cities[minIndex] = cities[otherIndex];
        cities[otherIndex] = temp;
    }

    /* This function takes last element as pivot, places
       the pivot element at its correct position in sorted
       array, and places all smaller (smaller than pivot)
       to left of pivot and all greater elements to right
       of pivot */
    static int partition(String[] arr, int minIndex, int maxIndex) {
        String pivot = arr[maxIndex];

        int i = (minIndex - 1);
        for(int j = minIndex; j <= maxIndex - 1; j++) {
            if (arr[j].compareTo(pivot) < 0) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, maxIndex);
        return (i + 1);
    }

    static String[] quickSort(String[] cities, int minIndex, int maxIndex) {
        if (minIndex < maxIndex) {
            int pi = partition(cities, minIndex, maxIndex);
            // Separately sort elements before
            // partition and after partition
            quickSort(cities, minIndex, pi - 1);
            quickSort(cities, pi + 1, maxIndex);
        }
        return cities;
    }

    // Driver Code
    public static void main(String[] args) {
        String[] arr = {"Akiruno","Akishima","Chōfu","Fuchū","Fussa", "Hachiōji", "Hamura",
                "Higashikurume","Higashimurayama","Higashiyamato","Hino","Inagi","Kiyose",
                "Kodaira","Konagei","Kokubunji","Komae","Kunitachi","Machida","Mitaka","Musashimurayama",
                "Musashino","Nishitokyo","Ome","Tachikawa","Tama"};
        int n = arr.length;
        quickSort(arr, 0, n - 1);
    }
}
