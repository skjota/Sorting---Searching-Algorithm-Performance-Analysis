package Search;

class BinarySearchTree {
    // Represents a node in the Binary Search Tree.
    class Node {
        int key;
        Node left, right;

        public Node(int item) {
            key = item;
            left = right = null;
        }
    }

    // The root of the BST.
    Node root;

    // Constructor for an empty BST.
    BinarySearchTree() {
        root = null;
    }

    // Public method to insert a key into the BST.
    void put(int key) {
        root = putRec(root, key);
    }

    // Recursive method to insert a key into the BST.
    Node putRec(Node root, int key) {
        // If the tree is empty, create a new node as the root.
        if (root == null) {
            root = new Node(key);
            return root;
        }

        // Otherwise, recursively traverse the tree to find the correct position.
        if (key < root.key)
            root.left = putRec(root.left, key);
        else if (key > root.key)
            root.right = putRec(root.right, key);

        // Return the (unchanged) node pointer.
        return root;
    }

    // Public method to search for a key in the BST.
    boolean get(int key) {
        return getRec(root, key);
    }

    // Recursive method to search for a key in the BST.
    boolean getRec(Node root, int key) {
        // Base Cases: root is null or key is present at root.
        if (root == null)
            return false;
        if (key == root.key)
            return true;

        // Key is greater than root's key, search in the right subtree.
        if (key < root.key)
            return getRec(root.left, key);

        // Key is smaller than root's key, search in the left subtree.
        return getRec(root.right, key);
    }
}