package code.UnLinear.AVLTree;

import sun.security.util.ByteArrayLexOrder;

import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>, V> {

    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public int height;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree(){
        root = null;
        size = 0;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value){
        if (node == null){
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0){
            node.left = add(node.left, key, value);
        }else if (key.compareTo(node.key)> 0){
            node.right = add(node.right, key, value);
        }else {
            node.value = value;
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1){
            //此时树不平衡
            if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0){
                //左侧的左侧不平衡
                //进行右旋转
                return rightRotate(node);
            }

            if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0){
                //右侧的右侧不平衡
                //进行左旋转
                return leftRotate(node);
            }

            if (balanceFactor > 1 && getBalanceFactor(node.left) < 0){
                //左侧的右侧不平衡
                //LR
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }

            if (balanceFactor < -1 && getBalanceFactor(node.left) > 0){
                //右侧的左侧不平衡
                //RL
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }

        return node;
    }

    private Node rightRotate(Node y){
        Node x = y.left;
        Node T3 = x.right;
        //start
        x.right = y;
        y.left = T3;

        //update height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    private Node leftRotate(Node y){
        Node x = y.right;
        Node T2 = x.left;

        //start
        x.left = y;
        y.right = T2;

        //update height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null){
            root = remove(root, key);
            return node.value;
        }

        return null;
    }

    private Node remove(Node node, K key){
        if (node == null){
            return null;
        }

        Node retNode;//保存最后平衡之后返回的根节点

        if (key.compareTo(node.key) < 0){
            node.left = remove(node.left, key);
            retNode = node;
        }else if (key.compareTo(node.key) > 0){
            node.right = remove(node.right, key);
            retNode = node;
        }else {
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            }

            if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            }

            Node successor = minimum(node.right);
            successor.right = remove(node.right, successor.key);
            successor.left = node.left;

            node.left = node.right = null;

            retNode = successor;
        }

        if (retNode == null){
            return null;
        }

        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));
        int balanceFactor = getBalanceFactor(retNode);
        //此时树不平衡
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0){
            //左侧的左侧不平衡
            //进行右旋转
            return rightRotate(retNode);
        }

        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0){
            //右侧的右侧不平衡
            //进行左旋转
            return leftRotate(retNode);
        }

        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0){
            //左侧的右侧不平衡
            //LR
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }

        if (balanceFactor < -1 && getBalanceFactor(retNode.left) > 0){
            //右侧的左侧不平衡
            //RL
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode;
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

    private Node minimum(Node node){
        if (node.left == null){
            return node;
        }

        return minimum(node.left);
    }

    private Node removeMin(Node node){
        if (node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int getBalanceFactor(Node node){
        if (node == null){
            return 0;
        }

        return getHeight(node.left) - getHeight(node.right);
    }

    public boolean isBST(){
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++){
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0){
                return false;
            }
        }
        return true;
    }

    private void inOrder(Node node, ArrayList<K> keys){
        if (node == null){
            return;
        }
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    public boolean isBalanced(){
        return isBalanced(root);
    }

    private boolean isBalanced(Node node){
        if (node == null){
            return true;
        }

        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1){
            return false;
        }

        return isBalanced(node.left) && isBalanced(node.right);
    }
}
