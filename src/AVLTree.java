import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AVLTree implements Iterable<String> {
    private Node root;

    private class Node {
        String key;
        int height;
        Node left;
        Node right;

        Node(String key) {
            this.key = key;
            this.height = 1;
        }
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int balanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    private Node rotateRight(Node node) {
        Node leftChild = node.left;
        Node rightGrandchild = leftChild.right;

        leftChild.right = node;
        node.left = rightGrandchild;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        leftChild.height = Math.max(height(leftChild.left), height(leftChild.right)) + 1;

        return leftChild;
    }

    private Node rotateLeft(Node node) {
        Node rightChild = node.right;
        Node leftGrandchild = rightChild.left;

        rightChild.left = node;
        node.right = leftGrandchild;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        rightChild.height = Math.max(height(rightChild.left), height(rightChild.right)) + 1;

        return rightChild;
    }

    private Node balance(Node node) {
        if (balanceFactor(node) > 1) {
            if (balanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }
        if (balanceFactor(node) < -1) {
            if (balanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        return node;
    }

    public void insert(String key) {
        root = insert(root, key);
    }

    private Node insert(Node node, String key) {
        if (node == null) {
            return new Node(key);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = insert(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            node.right = insert(node.right, key);
        } else {
            return node;
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;

        return balance(node);
    }

    public void delete(String key) {
        root = delete(root, key);
    }

    private Node delete(Node node, String key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = delete(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            node.key = minValue(node.right);
            node.right = delete(node.right, node.key);
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;

        return balance(node);
    }

    private String minValue(Node node) {
        String minValue = node.key;
        while (node.left != null) {
            minValue = node.left.key;
            node = node.left;
        }
        return minValue;
    }

    public void print() {
        print(root);
    }

    private void print(Node node) {
        if (node != null) {
            print(node.left);
            System.out.print(node.key + " ");
            print(node.right);
        }
    }

    public String getTree() {
        return getTreeFromNode(root);
    }

    private String getTreeFromNode(Node node) {
        if (node == null) {
            return "";
        }
        return getTreeFromNode(node.left) + node.key + " " + getTreeFromNode(node.right);
    }

    public void buildTreeFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] keys = line.trim().split("\\s+");
                for (String key : keys) {
                    insert(key);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getLonger(String key) {
        List<String> result = new ArrayList<>();
        getLongerFromNode(root, key, result);
        return result;
    }

    private void getLongerFromNode(Node node, String key, List<String> result) {
        if (node == null) {
            return;
        }
        if (node.key.compareTo(key) > 0) {
            result.add(node.key);
            getLongerFromNode(node.left, key, result);
            getLongerFromNode(node.right, key, result);
        } else {
            getLongerFromNode(node.right, key, result);
        }
    }

    public List<String> getShorter(String key) {
        List<String> result = new ArrayList<>();
        getShorterFromNode(root, key, result);
        return result;
    }

    private void getShorterFromNode(Node node, String key, List<String> result) {
        if (node == null) {
            return;
        }
        if (node.key.compareTo(key) < 0) {
            result.add(node.key);
            getShorterFromNode(node.left, key, result);
            getShorterFromNode(node.right, key, result);
        } else {
            getShorterFromNode(node.left, key, result);
        }
    }

    public List<String> getEqual(String key) {
        List<String> result = new ArrayList<>();
        getEqualFromNode(root, key, result);
        return result;
    }

    private void getEqualFromNode(Node node, String key, List<String> result) {
        if (node == null) {
            return;
        }
        if (node.key.equals(key)) {
            result.add(node.key);
        } else if (node.key.compareTo(key) < 0) {
            getEqualFromNode(node.right, key, result);
        } else {
            getEqualFromNode(node.left, key, result);
        }
    }

    public List<String> getInRange(String min, String max) {
        List<String> result = new ArrayList<>();
        getInRangeFromNode(root, min, max, result);
        return result;
    }

    private void getInRangeFromNode(Node node, String min, String max, List<String> result) {
        if (node == null) {
            return;
        }
        if (node.key.compareTo(min) >= 0 && node.key.compareTo(max) <= 0) {
            result.add(node.key);
            getInRangeFromNode(node.left, min, max, result);
            getInRangeFromNode(node.right, min, max, result);
        } else if (node.key.compareTo(min) < 0) {
            getInRangeFromNode(node.right, min, max, result);
        } else {
            getInRangeFromNode(node.left, min, max, result);
        }
    }

    public String getFirst() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        Node current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.key;
    }

    public String getLast() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        Node current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.key;
    }

    @Override
    public Iterator<String> iterator() {
        return new AVLTreeIterator();
    }

    private class AVLTreeIterator implements Iterator<String> {
        private Stack<Node> stack = new Stack<>();

        AVLTreeIterator() {
            pushLeftBranch(root);
        }

        private void pushLeftBranch(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node node = stack.pop();
            String result = node.key;
            if (node.right != null) {
                pushLeftBranch(node.right);
            }
            return result;
        }
    }

}
