import java.util.LinkedList;
import java.util.Queue;

class Node {
    int key;
    Node left, right;

    public Node(int key) {
        this.key = key;
        this.left = this.right = null;
    }
}

class BinaryTree {
    Node root;

    public BinaryTree() {
        this.root = null;
    }

    // Insert a node using level order traversal
    public void insert(int key) {
        Node newNode = new Node(key);
        if (root == null) {
            root = newNode;
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node temp = queue.poll();

            if (temp.left == null) {
                temp.left = newNode;
                return;
            } else {
                queue.add(temp.left);
            }

            if (temp.right == null) {
                temp.right = newNode;
                return;
            } else {
                queue.add(temp.right);
            }
        }
    }

    // In-order traversal (Left-Root-Right)
    public void inorderTraversal(Node node) {
        if (node != null) {
            inorderTraversal(node.left);
            System.out.print(node.key + " ");
            inorderTraversal(node.right);
        }
    }

    // Pre-order traversal (Root-Left-Right)
    public void preorderTraversal(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preorderTraversal(node.left);
            preorderTraversal(node.right);
        }
    }

    // Post-order traversal (Left-Right-Root)
    public void postorderTraversal(Node node) {
        if (node != null) {
            postorderTraversal(node.left);
            postorderTraversal(node.right);
            System.out.print(node.key + " ");
        }
    }

    // Search for a node in the tree
    public boolean search(int key) {
        if (root == null) return false;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            if (temp.key == key) {
                return true;
            }
            if (temp.left != null) queue.add(temp.left);
            if (temp.right != null) queue.add(temp.right);
        }
        return false;
    }

    // Delete a node from the tree
    public void delete(int key) {
        if (root == null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node keyNode = null, temp = null;

        while (!queue.isEmpty()) {
            temp = queue.poll();
            if (temp.key == key) {
                keyNode = temp;
            }
            if (temp.left != null) queue.add(temp.left);
            if (temp.right != null) queue.add(temp.right);
        }

        if (keyNode != null) {
            keyNode.key = temp.key; // Replace keyNode with the last node value
            deleteDeepest(temp);
        }
    }

    // Delete the deepest node in the tree
    private void deleteDeepest(Node delNode) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            if (temp == delNode) {
                temp = null;
                return;
            }
            if (temp.right != null) {
                if (temp.right == delNode) {
                    temp.right = null;
                    return;
                } else {
                    queue.add(temp.right);
                }
            }
            if (temp.left != null) {
                if (temp.left == delNode) {
                    temp.left = null;
                    return;
                } else {
                    queue.add(temp.left);
                }
            }
        }
    }
}
public class Main {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        // Insert nodes into the binary tree
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);

        // Perform traversals
        System.out.println("In-order Traversal:");
        tree.inorderTraversal(tree.root);
        System.out.println();

        System.out.println("Pre-order Traversal:");
        tree.preorderTraversal(tree.root);
        System.out.println();

        System.out.println("Post-order Traversal:");
        tree.postorderTraversal(tree.root);
        System.out.println();

        // Search for a node
        System.out.println("Search for 30: " + tree.search(30));
        System.out.println("Search for 60: " + tree.search(60));

        // Delete a node
        tree.delete(20);
        System.out.println("In-order Traversal after deleting 20:");
        tree.inorderTraversal(tree.root);
        System.out.println();
    }
}
