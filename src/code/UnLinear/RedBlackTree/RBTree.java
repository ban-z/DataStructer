package code.UnLinear.RedBlackTree;

public class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            color = RED;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public RBTree(){

    }

    public void add(K key, V value) {
        root = add(root, key, value);
        root.color = BLACK;
    }

    //辅助过程
    private Node leftRotate(Node node){
        Node x = node.right;
        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    private Node rightRotate(Node node){
        Node x = node.left;
        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    //3节点颜色反转
    private void filpColors(Node node){
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    private Node add(Node node, K key, V value){
        if (node == null){
            size++;
            return new Node(key, value);//默认插入红色节点
        }

        if (key.compareTo(node.key) < 0){
            node.left = add(node.left, key, value);
        }else if (key.compareTo(node.key)> 0){
            node.right = add(node.right, key, value);
        }else {
            node.value = value;
        }

        if (isRed(node.right) && !isRed(node.left)){
            node = leftRotate(node);
        }
        if (isRed(node.left) && isRed(node.left.left)){
            node = rightRotate(node);
        }
        if (isRed(node.left) && isRed(node.right)){
            filpColors(node);
        }

        return node;
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (node == null){
            throw new IllegalArgumentException("");
        }
        node.value = value;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private Node getNode(Node node, K key){
        if (node == null){
            return null;
        }

        if (key.compareTo(node.key) == 0){
            return node;
        }else if (key.compareTo(node.key) < 0){
            return getNode(node.left, key);
        }else {
            return getNode(node.right, key);
        }
    }

    public boolean isRed(Node node){
        return node.color == RED ? true : false;
    }

    public boolean isBlack(Node node){
        return node.color == BLACK ? true : false;
    }
}
