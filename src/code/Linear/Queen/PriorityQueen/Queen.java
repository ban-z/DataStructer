package code.Linear.Queen.PriorityQueen;

public interface Queen<E> {
    int getSize();
    boolean isEmpty();
    void enqueen(E e);
    E dequeen();
    E getFront();
}
