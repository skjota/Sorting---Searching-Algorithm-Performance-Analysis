package Search;


import java.util.Objects;

// A node of chains
public class HashNode<K, V> {
    K key;
    V value;
    final int hashCode;

    // Reference to next node
    HashNode<K, V> next;

    // Constructor
    public HashNode(K key, V value, int hashCode) {
        this.key = key;
        this.value = value;
        this.hashCode = hashCode;
    }
}

