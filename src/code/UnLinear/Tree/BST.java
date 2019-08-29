package code.UnLinear.Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
* 虽然是泛型，但是条件是此泛型具有可比较性
* */
public class BST<E extends Comparable<E>> {

    //节点类
    private class Node{
        public E e;
        public Node left, right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    /*
    * 添加元素
    * 注意：二分搜索树不包含重复（等值）的元素
    * 若想包含等值元素，则将相等定义放入大于或小于的定义中，但是仅且只能放入一个中
    * */
    public void add(E e){
        if (root == null){
            root = new Node(e);
            size++;
        }else {
            add(root, e);
        }
    }
    //内部实现接口，区别于外部接口，实现了添加的节点和元素值
    private void add(Node node, E e){
        /*递归终止条件++++++++++++++++++++++++++++++++++++++++++++*/
        if (e.equals(node.e)){
            return;
        }else if (e.compareTo(node.e) < 0 && node.left == null){
            node.left = new Node(e);
            size++;
            return;
        }else if (e.compareTo(node.e) > 0 && node.right == null){
            node.right = new Node(e);
            size++;
            return;
        }
        /*递归终止条件++++++++++++++++++++++++++++++++++++++++++++*/

        /*递归规模函数*/
        if (e.compareTo(node.e) < 0){
            add(node.left, e);
        }else {
            add(node.right, e);
        }
    }
    //改进版：插入操作
    //返回插入新节点后二分搜索树的根
    private Node addPlus(Node node, E e){
        if (node == null){
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0){
            node.left = addPlus(node.left, e);
        }else if (e.compareTo(e) > 0){
            node.right = addPlus(node.right, e);
        }
        return node;
    }
    /*
    * 改动后的add函数
    *
    public void add(E e){
        root = addPlus(root, e);
    }*/

    /*
    * 看二分搜索树中是否包含元素e
    * */
    public boolean contains(E e) {
        return contains(root, e);
    }

    /*
    * 看以node为节点的二分搜索树中是否包含元素e
    * */
    private boolean contains(Node node, E e){
        if (node == null){
            return false;
        }
        if (e.compareTo(node.e) == 0){
            return true;
        }else if (e.compareTo(node.e) < 0){
            return contains(node.left, e);
        }else if (e.compareTo(node.e) > 0){
            return contains(node.right, e);
        }
        return false;
    }

    /*
    * 二分搜索树的前序遍历
    * */
    public void preOrder(){
        preOrder(root);
    }

    /*
    * 以node为节点的二分搜索树的遍历
    * */
    private void preOrder(Node node){
        if (node == null){
            return ;
        }

        //访问该节点的操作，可随意自定义
        System.out.println(node.e);

        preOrder(node.left);
        preOrder(node.right);
    }

    /*
    ***********************
    * 二分搜索树的非递归实现
    * *********************
    * */
    public void preOrderNR(){
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e);

            if (cur.right != null){
                stack.push(cur.right);
            }
            if (cur.left != null){
                stack.push(cur.left);
            }
        }
    }

    /*
     * 二分搜索树的中序遍历
     * */
    public void inOrder(){
        inOrder(root);
    }

    /*
     * 以node为节点的二分搜索树的遍历
     * */
    private void inOrder(Node node){
        if (node == null){
            return ;
        }

        inOrder(node.left);

        //访问该节点的操作，可随意自定义
        System.out.println(node.e);

        inOrder(node.right);
    }

    /*
     * 二分搜索树的后序遍历
     * */
    public void postOrder(){
        postOrder(root);
    }

    /*
     * 以node为节点的二分搜索树的遍历
     * */
    private void postOrder(Node node){
        if (node == null){
            return ;
        }

        postOrder(node.left);
        postOrder(node.right);

        //访问该节点的操作，可随意自定义
        System.out.println(node.e);
    }

    /*
    * 二分搜索树的层序遍历(广度优先遍历)
    * */
    public void levelOrder(){
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()){
            Node cur = q.remove();
            System.out.println(cur.e);

            if (cur.left != null){
                q.add(cur.left);
            }
            if (cur.right != null){
                q.add(cur.right);
            }
        }
    }

    /*
    * 寻找二分搜索树的最小元素
    * */
    public E mininum(){
        if (size == 0){
            throw new IllegalArgumentException("BSI is Empty");
        }
        return mininum(root).e;
    }

    /*
    * 返回以node为根的二分搜索树的最小值所在的节点
    * */
    private Node mininum(Node node){
        if (node.left == null){
            return node;
        }
        return mininum(node.left);
    }

    /*
     * 寻找二分搜索树的最大元素
     * */
    public E maxnum(){
        if (size == 0){
            throw new IllegalArgumentException("BSI is Empty");
        }
        return mininum(root).e;
    }

    /*
     * 返回以node为根的二分搜索树的最大值所在的节点
     * */
    private Node maxnum(Node node){
        if (node.right == null){
            return node;
        }
        return mininum(node.right);
    }

    /*
    * 删除二分搜索树中的最小元素
    * */
    public E removeMin(){
        E ret = mininum();
        root = removeMin(root);
        return ret;
    }

    /*
    * 删掉以node为根的树中最小节点
    * 返回删除节点以后的新的树的根
    * */
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

    /*
     * 删除二分搜索树中的最大元素
     * */
    public E removeMax(){
        E ret = mininum();
        root = removeMax(root);
        return ret;
    }

    /*
     * 删掉以node为根的树中最大节点
     * 返回删除节点以后的新的树的根
     * */
    private Node removeMax(Node node){
        if (node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    /*
    * 在树中删除任意节点
    * Hibbard Deletion算法
    * */
    public void remove(E e){
        root = remove(root, e);
    }

    private Node remove(Node node, E e){
        if (node == null){
            return null;
        }
        if (e.compareTo(node.e) < 0){
            node.left = remove(node.left, e);
        }else if (e.compareTo(node.e) > 0){
            node.right = remove(node.right, e);
        }else {
            //e == node.e
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            //left && != null
            Node successor = mininum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = null;
            node.right = null;
            return successor;
        }
        return null;
    }

    public String toString(){
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null){
            res.append(generateDepthString(depth) + "null\n");
            return;
        }
        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private java.lang.String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++){
            res.append("--");
        }
        return res.toString();
    }
}
