package code.Linear.Stack;

import code.Linear.Array.myArray;

public class ArrayStack<E> implements Stack {

    myArray<E> array;

    public ArrayStack(int capacity){
        array = new myArray<>(capacity);
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void push() {

    }

    @Override
    public Object pop() {
        return null;
    }

    @Override
    public Object peek() {
        return null;
    }
}
