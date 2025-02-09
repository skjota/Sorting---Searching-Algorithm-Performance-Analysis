package Search;

import java.util.ArrayList;

// Represents a node in the hash map, storing a key-value pair and a hash code.
class HashNode<K, V> {
    K key;
    V value;
    final int hashCode;
    HashNode<K, V> next; // Reference to the next node in the chain (for collision handling)

    // Constructor to create a new HashNode.
    public HashNode(K key, V value, int hashCode) {
        this.key = key;
        this.value = value;
        this.hashCode = hashCode;
    }
}

// Represents the hash map implementation.
public class Map<K, V> {
    private ArrayList<HashNode<K, V>> bucketArray;
    private int numBuckets;
    private int size;

    // Constructor to create a new Map with an initial capacity of 10.
    public Map() {
        bucketArray = new ArrayList<>();
        numBuckets = 10;
        size = 0;

        // Initialize all buckets to null (empty).
        for (int i = 0; i < numBuckets; i++)
            bucketArray.add(null);
    }

    // Returns the current number of key-value pairs in the map.
    public int size() {
        return size;
    }

    // Checks if the map is empty.
    public boolean isEmpty() {
        return size() == 0;
    }

    // Calculates the hash code for a given key.
    // Returns 0 if the key is null, otherwise the key's hash code.
    private final int hashCode(K key) {
        return (key == null) ? 0 : key.hashCode();
    }

    // Returns the bucket index for a given key.
    // Uses the modulo operator (%) to map the hash code to a bucket index.
    // Ensures the index is always positive.
    private int getBucketIndex(K key) {
        int hashCode = hashCode(key);
        int index = hashCode % numBuckets;
        return index < 0 ? index * -1 : index;
    }

    // Removes the key-value pair associated with the given key from the map.
    // Returns the value associated with the key, or null if the key is not found.
    public V remove(K key) {
        int bucketIndex = getBucketIndex(key);
        int hashCode = hashCode(key);
        HashNode<K, V> head = bucketArray.get(bucketIndex);

        // Search for the key in the chain.
        HashNode<K, V> prev = null;
        while (head != null) {
            // If the key is found (and hash code matches), break the loop.
            if (head.key.equals(key) && hashCode == head.hashCode)
                break;

            // Move to the next node.
            prev = head;
            head = head.next;
        }

        // If the key is not found, return null.
        if (head == null)
            return null;

        // Decrement the size of the map.
        size--;

        // Remove the node from the chain.
        if (prev != null)
            prev.next = head.next; // Node is in the middle or at the end.
        else
            bucketArray.set(bucketIndex, head.next); // Node is the head.

        // Return the value of the removed node.
        return head.value;
    }

    // Returns the value associated with the given key, or null if the key is not found.
    public V get(K key) {
        // Get the bucket index for the key.
        int bucketIndex = getBucketIndex(key);
        // Get the hash code of the key.
        int hashCode = hashCode(key);
        // Get the head of the chain at the bucket index.
        HashNode<K, V> head = bucketArray.get(bucketIndex);

        // Search for the key in the chain.
        while (head != null) {
            // If the key is found (and hash code matches), return its value.
            if (head.key.equals(key) && head.hashCode == hashCode)
                return head.value;
            // Move to the next node.
            head = head.next;
        }

        // Key not found, return null.
        return null;
    }

    // Adds a new key-value pair to the map, or updates the value if the key already exists.
    public void add(K key, V value) {
        // Get the bucket index for the key.
        int bucketIndex = getBucketIndex(key);
        // Get the hash code of the key
        int hashCode = hashCode(key);
        // Get the head of the chain at the bucket index.
        HashNode<K, V> head = bucketArray.get(bucketIndex);

        // Check if the key already exists in the chain.
        while (head != null) {
            // If the key is found (and hash code matches), update its value and return.
            if (head.key.equals(key) && head.hashCode == hashCode) {
                head.value = value;
                return;
            }
            // Move to the next node.
            head = head.next;
        }

        // Key does not exist, add a new node to the beginning of the chain.
        // Increment the size of the map.
        size++;
        head = bucketArray.get(bucketIndex);
        // Create a new node.
        HashNode<K, V> newNode = new HashNode<>(key, value, hashCode);
        // Insert the new node at the beginning of the chain.
        newNode.next = head;
        bucketArray.set(bucketIndex, newNode);

        // If the load factor exceeds 0.7, resize the bucket array.
        if ((1.0 * size) / numBuckets >= 0.7) {
            // Create a temporary list to store the existing nodes.
            ArrayList<HashNode<K, V>> temp = bucketArray;
            // Create a new, larger bucket array.
            bucketArray = new ArrayList<>();
            numBuckets = 2 * numBuckets;
            // Reset the size to 0.
            size = 0;
            // Initialize the new buckets to null.
            for (int i = 0; i < numBuckets; i++)
                bucketArray.add(null);

            // Rehash and insert all existing nodes into the new bucket array.
            for (HashNode<K, V> headNode : temp) {
                while (headNode != null) {
                    add(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
    }
}