package Search;

public class Experiment {
    public static void main(String[] args) {
        // Create instances of Map (Hash Table) and BinarySearchTree
        Map<Integer, Integer> hashTable = new Map<>();
        BinarySearchTree bst = new BinarySearchTree();

        // Define two sequences of operations.
        // Each operation is represented by an array:
        // - The first element indicates the operation type (1 for 'put', 2 for 'get').
        // - The second element is the key/value to be used in the operation.
        int[][] sequence1 = {
                {1, 5}, {1, 10}, {1, 3}, {2, 10}, {2, 5}, {2, 7}
        };
        int[][] sequence2 = {
                {1, 2}, {1, 4}, {1, 1}, {2, 3}, {2, 4}, {1, 6}, {2, 6}
        };

        System.out.println("=== Experiment Results ===");

        // Test Sequence 1
        System.out.println("\n--- Sequence 1 ---");
        System.out.println("Description: Randomly ordered data with frequent 'put' operations.");
        // Measure performance for Hash Table and BST with sequence 1.
        long hashTableTime1 = measurePerformance(hashTable, sequence1, "Hash Table");
        long bstTime1 = measurePerformance(bst, sequence1, "Binary Search Tree");
        // Analyze and display the results for sequence 1.
        analyzeResults(hashTableTime1, bstTime1, "Sequence 1", true);

        // Test Sequence 2
        System.out.println("\n--- Sequence 2 ---");
        System.out.println("Description: Mostly sequential data with alternating 'put' and 'get' operations.");
        // Measure performance for Hash Table and BST with sequence 2.
        long hashTableTime2 = measurePerformance(hashTable, sequence2, "Hash Table");
        long bstTime2 = measurePerformance(bst, sequence2, "Binary Search Tree");
        // Analyze and display the results for sequence 2.
        analyzeResults(hashTableTime2, bstTime2, "Sequence 2", false);
    }

    // Measures the performance of a given data structure (Map or BinarySearchTree)
    // with a specific sequence of operations.
    private static long measurePerformance(Object structure, int[][] sequence, String structureName) {
        // Record the start time.
        long startTime = System.nanoTime();

        // Iterate through the sequence of operations.
        for (int[] operation : sequence) {
            // Check if the structure is a Map (Hash Table).
            if (structure instanceof Map) {
                Map<Integer, Integer> map = (Map<Integer, Integer>) structure;
                // Perform 'put' operation if the first element is 1.
                if (operation[0] == 1) {
                    map.add(operation[1], operation[1]);
                // Perform 'get' operation if the first element is 2.
                } else if (operation[0] == 2) {
                    map.get(operation[1]);
                }
            // Check if the structure is a BinarySearchTree.
            } else if (structure instanceof BinarySearchTree) {
                BinarySearchTree bst = (BinarySearchTree) structure;
                // Perform 'put' operation if the first element is 1.
                if (operation[0] == 1) {
                    bst.put(operation[1]);
                // Perform 'get' operation if the first element is 2.
                } else if (operation[0] == 2) {
                    bst.get(operation[1]);
                }
            }
        }

        // Record the end time.
        long endTime = System.nanoTime();
        // Calculate the execution time.
        long executionTime = endTime - startTime;
        // Print the execution time for the given structure.
        System.out.println(structureName + " Execution Time: " + executionTime + " nanoseconds");
        return executionTime;
    }

    // Analyzes and prints the performance comparison between two data structures
    // based on their execution times for a given sequence of operations.
    private static void analyzeResults(long time1, long time2, String sequenceDescription, boolean isRandomData) {
        System.out.println("\nResult Analysis for " + sequenceDescription + ":");
        // Compare the execution times and print the results.
        if (time1 < time2) {
            System.out.println("-> Hash Table is more efficient.");
            System.out.println("   Reason: Hash Table uses constant-time hashing for 'put' and 'get' operations.");
            // Additional explanation for random data.
            if (isRandomData) {
                System.out.println("   In random data, Hash Table performs well because it directly maps keys to memory locations.");
            }
        } else if (time2 < time1) {
            System.out.println("-> Binary Search Tree is more efficient.");
            System.out.println("   Reason: Binary Search Tree maintains sorted order of data, which reduces search and retrieval time.");
            // Additional explanation for sequential data.
            if (!isRandomData) {
                System.out.println("   For sequential data, BST performs better as it avoids hash collisions and ensures efficient traversal.");
            }
        } else {
            System.out.println("-> Both algorithms performed equally.");
            System.out.println("   Reason: The sequence of operations did not highlight differences between the data structures.");
        }
        System.out.println();
    }
}