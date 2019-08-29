package code.UnLinear.SegmentTree;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.Objects;

public class SegmentTree<E> {

    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger){
        data = (E[]) new Object[arr.length];
        this.merger = merger;
        for (int i = 0; i < arr.length; i++){
            data[i] = arr[i];
        }
        tree = (E[]) new Object[4 * arr.length];

        buildSegmentTree(0, 0, data.length - 1);
    }

    /*
    * 在treeIndex的位置创建表示区间[left, right]的线段树
    * */
    private void buildSegmentTree(int treeIndex, int left, int right){
        if (left == right){
            data[treeIndex] = data[left];
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        int mid = left + (right - left) / 2;
        buildSegmentTree(leftTreeIndex, left, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, right);

        /*
        * 此处与业务相关,综合两个子树线段的值而得出更高一层的值
        * eg：等于子树的相加和
        * 还可以为最大值，最小值等
        * */
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    //返回以queryL，和queryR为边界的闭区间的内容
    public E query(int queryL, int queryR){
        //边界检查略
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    private E query(int treeindex, int l, int r, int queryL, int queryR){
        if (l == r && r == queryR){
            return tree[treeindex];
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeindex);
        int rightTreeIndex = rightChild(treeindex);

        if (queryL >= mid + 1){
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        }else if (queryR <= mid){
            return query(leftTreeIndex, l, mid, queryL, queryR);
        }else {
            E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
            E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
            return merger.merge(leftResult, rightResult);
        }
    }

    //更新操作
    public void set(int index, E e){
        //边界检查
        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    private void set(int treeIndex, int l, int r, int index, E e){
        if (l == r){
            tree[treeIndex] = e;
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (index >= mid + 1){
            set(rightTreeIndex, mid + 1, r, index, e);
        }else if (index <= mid){
            set(leftTreeIndex, l, mid, index, e);
        }

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public E get(int index) {
        //应该有合法性判断
        return data[index];
    }

    public int getSize(){
        return data.length;
    }

    private int leftChild(int index){
        return 2 * index + 1;
    }

    private int rightChild(int index){
        return 2 * index + 2;
    }

    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0; i < tree.length; i++){
            if (tree[i] != null){
                res.append(tree[i]);
            }else {
                res.append("null");
            }

            if (i != tree.length - 1){

            }
        }
        return res.toString();
    }
}
