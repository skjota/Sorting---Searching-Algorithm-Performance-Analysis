package Sort;

import java.util.Random;

public class MergeSort {

    // Merge Sort algorithm - sorts the given array segment.
    void mergeSort(int[] arr, int l, int hi) {
        if (l < hi) {
            int mid = (l + hi) / 2;
            mergeSort(arr, l, mid); // Sorts the left subarray
            mergeSort(arr, mid + 1, hi); // Sorts the right subarray
            merge(arr, l, mid, hi); // Merges the sorted subarrays
        }
    }

    // Helper method to merge two sorted subarrays.
    void merge(int[] arr, int l, int mid, int hi) {
        int leftArraySize = mid - l + 1;
        int rightArraySize = hi - mid;

        // Creates temporary arrays
        int[] leftArray = new int[leftArraySize];
        int[] rightArray = new int[rightArraySize];

        // Copies the left and right subarrays into temporary arrays
        for (int i = 0; i < leftArraySize; i++) {
            leftArray[i] = arr[l + i];
        }
        for (int j = 0; j < rightArraySize; j++) {
            rightArray[j] = arr[mid + 1 + j];
        }

        // Merges the subarrays and performs sorting
        int i = 0, j = 0, k = l;
        while (i < leftArraySize && j < rightArraySize) {
            if (leftArray[i] < rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Merges the remaining elements
        while (i < leftArraySize) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < rightArraySize) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }

    // Prints the array to the console
    void printArray(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // Generates a random dataset of a given size
    int[] generateDataset(int size) {
        Random random = new Random();
        int[] dataset = new int[size];
        for (int i = 0; i < size; i++) {
            dataset[i] = random.nextInt(100000); // Random numbers between 0 and 99,999
        }
        return dataset;
    }

    // Main function
    public static void main(String[] args) {
        MergeSort sort = new MergeSort();

        // Input Instance 1: Small Dataset
        int[] smallDataset = {41, 62, 23, 46, 2, 9, 89, 25, 15, 65};
        System.out.println("Small Dataset:");
        sort.printArray(smallDataset);

        // Measures memory usage before sorting
        long smallBeforeMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        // Measures execution time
        long smallStartTime = System.nanoTime();
        sort.mergeSort(smallDataset, 0, smallDataset.length - 1);
        long smallEndTime = System.nanoTime();
        // Measures memory usage after sorting
        long smallAfterMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        System.out.println("Sorted Small Dataset:");
        sort.printArray(smallDataset);
        System.out.println("Execution Time (Small Dataset) (nanoseconds): " + (smallEndTime - smallStartTime));
        System.out.println("Memory Used (Small Dataset) (bytes): " + (smallAfterMem - smallBeforeMem));

        System.out.println();

        // Input Instance 2: Large Dataset
        int[] largeDataset = sort.generateDataset(100000); // 100,000 element dataset
        System.out.println("Large Dataset: (First 10 elements shown)");
        for (int i = 0; i < 10; i++) {
            System.out.print(largeDataset[i] + " ");
        }
        System.out.println("...");

        // Measures memory usage before sorting
        long largeBeforeMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        // Measures execution time
        long largeStartTime = System.nanoTime();
        sort.mergeSort(largeDataset, 0, largeDataset.length - 1);
        long largeEndTime = System.nanoTime();
        // Measures memory usage after sorting
        long largeAfterMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        System.out.println("Sorted Large Dataset: (First 10 elements shown)");
        for (int i = 0; i < 10; i++) {
            System.out.print(largeDataset[i] + " ");
        }
        System.out.println("...");
        System.out.println("Execution Time (Large Dataset) (nanoseconds): " + (largeEndTime - largeStartTime));
        System.out.println("Memory Used (Large Dataset) (bytes): " + (largeAfterMem - largeBeforeMem));

        // Experimental Results:
        System.out.println("\n--- Experimental Results ---");
        System.out.println("1. Small Dataset:");
        System.out.println("   Size: 10 elements");
        System.out.println("   Nature: Random unsorted integers");
        System.out.println("   Execution Time: " + (smallEndTime - smallStartTime) + " nanoseconds");
        System.out.println("   Memory Used: " + (smallAfterMem - smallBeforeMem) + " bytes");

        System.out.println("\n2. Large Dataset:");
        System.out.println("   Size: 100,000 elements");
        System.out.println("   Nature: Random unsorted integers");
        System.out.println("   Execution Time: " + (largeEndTime - largeStartTime) + " nanoseconds");
        System.out.println("   Memory Used: " + (largeAfterMem - largeBeforeMem) + " bytes");

        System.out.println("\nDiscussion:");
        System.out.println("Merge Sort is efficient for large datasets due to its consistent O(n log n) complexity.");
        System.out.println("However, its space complexity (O(n)) can lead to higher memory usage compared to in-place algorithms.");
    }
}