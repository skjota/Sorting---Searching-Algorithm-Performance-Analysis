package Sort;

import java.util.Random;

public class QuickSort {

    // Pivot determination and splitting process of Quick Sort application
    public int partition(int[] arr, int lowerbound, int upperbound) {
        int pivot = arr[lowerbound];
        int start = lowerbound;
        int end = upperbound;

        while (start < end) {
            while (start <= upperbound && arr[start] <= pivot) {
                start++;
            }

            while (end > lowerbound && arr[end] > pivot) {
                end--;
            }

            if (start < end) {
                swap(arr, start, end);
            }
        }
        swap(arr, lowerbound, end);

        return end;
    }

    // Method of swapping two elements
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Quick Sort algorithm
    public void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(array, low, high);

            quickSort(array, low, partitionIndex - 1); // Sorts the left subarray
            quickSort(array, partitionIndex + 1, high); // Sorts the right subarray
        }
    }

    // Method to print array
    void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // Random data set generation method
    int[] generateDataset(int size) {
        Random random = new Random();
        int[] dataset = new int[size];
        for (int i = 0; i < size; i++) {
            dataset[i] = random.nextInt(100000); // Random numbers between 0 and 99,999
        }
        return dataset;
    }

    public static void main(String[] args) {
        QuickSort sort = new QuickSort();

        // Input Instance 1: Small Dataset
        int[] smallDataset = {41, 62, 23, 46, 2, 9, 89, 25, 15, 65};
        System.out.println("Small Dataset:");
        sort.printArray(smallDataset);

        long smallBeforeMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long smallStartTime = System.nanoTime();
        sort.quickSort(smallDataset, 0, smallDataset.length - 1);
        long smallEndTime = System.nanoTime();
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

        long largeBeforeMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long largeStartTime = System.nanoTime();
        sort.quickSort(largeDataset, 0, largeDataset.length - 1);
        long largeEndTime = System.nanoTime();
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
        System.out.println("Quick Sort is efficient for small and large datasets due to its average-case O(n log n) complexity.");
        System.out.println("In-place nature minimizes memory usage, making it more memory-efficient than Merge Sort.");
        System.out.println("However, Quick Sort's performance can degrade to O(n^2) in the worst-case with poor pivot choices.");
    }
}
