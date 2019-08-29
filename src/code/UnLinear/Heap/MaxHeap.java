package code.UnLinear.Heap;

import java.util.ArrayList;
import java.util.Arrays;

public class MaxHeap<E extends Comparable<E>>  {

    private ArrayList<E> data;

    public MaxHeap(int capacity){
        data = new ArrayList<>(capacity);
    }

    public MaxHeap(){
        data = new ArrayList<>();
    }

    public MaxHeap(E[] arr){
        data = new ArrayList<E>(Arrays.asList(arr));
        for (int i = parent(arr.length - 1); i >= 0; i--){
            siftDown(i);
        }
    }

    public int getSize(){
        return data.size();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    private int parent(int index){
        if (index == 0){
            throw new IllegalArgumentException("index = 0 没有父节点");
        }
        return (index - 1) / 2;
    }

    private int leftChild(int index){
        return index * 2 + 1;
    }

    private int rightChild(int index){
        return index * 2 + 2;
    }

    public void add(E e){
        data.add(e);
        siftUp(data.size() - 1);
    }

    private void siftUp(int k) {
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0){
            swap(k, parent(k));
            k = parent(k);
        }
    }

    public E findMax(){
        if (data.size() == 0){
            throw new IllegalArgumentException("Heap is Empty");
        }
        return data.get(0);
    }

    public E extractMax(){
        E ret = findMax();
        swap(0, data.size() - 1);
        data.remove(data.size() - 1);

        siftDown(0);

        return  ret;
    }

    private void siftDown(int k) {
        while (leftChild(k) < data.size() - 1){
            int j = leftChild(k);
            if (j + 1 < data.size() - 1 && data.get(j + 1).compareTo(data.get(j)) > 0){
                j = rightChild(k);
            }
            if (data.get(k).compareTo(data.get(j)) > 0){
                break;
            }
            swap(k, j);
            k = j;
        }
    }

    public  E replace(E e){
        E ret = findMax();

        data.set(0, e);
        siftDown(0);

        return ret;
    }

    private void swap(int i, int j){
        E t = data.get(i);
        data.set(i, data.get(j));
        data.set(j, t);
    }
}
