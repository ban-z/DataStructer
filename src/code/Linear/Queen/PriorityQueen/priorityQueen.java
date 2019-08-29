package code.Linear.Queen.PriorityQueen;

import code.UnLinear.Heap.MaxHeap;

public class priorityQueen<E extends Comparable<E>> implements Queen<E> {

    private MaxHeap<E> maxHeap;

    public priorityQueen(){
        maxHeap = new MaxHeap<>();
    }

    @Override
    public int getSize() {
        return maxHeap.getSize();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public void enqueen(E e) {
        maxHeap.add(e);
    }

    @Override
    public E dequeen() {
        return maxHeap.extractMax();
    }

    @Override
    public E getFront() {
        return maxHeap.findMax();
    }
}
