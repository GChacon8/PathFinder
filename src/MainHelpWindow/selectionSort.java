package MainHelpWindow;

public class selectionSort {
    public String[] selectionSort(String[] cities,int citiesLength) {
        // Swapping the elements to convert the unsorted array to a sorted one.
        for(int numberCity = 0; numberCity < citiesLength- 1; numberCity++) {
            // Find the minimum element in unsorted array
            int minElementIndex = numberCity;
            String minStr = cities[numberCity];
            for(int j = numberCity + 1; j < citiesLength; j++) {
                if(cities[j].compareTo(minStr) < 0) {
                    minStr = cities[j];
                    minElementIndex = j;
                }
            }

            // Swapping the minimum element
            if(minElementIndex != numberCity) {
                String temp = cities[minElementIndex];
                cities[minElementIndex] = cities[numberCity];
                cities[numberCity] = temp;
            }
        }
        return cities;
    }
}
