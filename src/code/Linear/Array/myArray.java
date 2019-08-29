package code.Linear.Array;

/*
* 封装自己的数组，实现动态的增删改查
* capacity控制容量，size控制实际占用量
* */


/*
* 使用泛型不能放置基本类型，只能是类对象
* 所以，对应于基本类型使用包装类
* */
public class myArray<E> {

    //封装性
    private E[] data;
    private int size;

    //默认构造
    public myArray(){
        this(10);
    }

    //有参构造
    public myArray(int capacity){
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public E get(int index){
        if (index < 0 || index > size){

        }
        return data[index];
    }

    public void set(int index, E e){
        if (index < 0 || index > size){

        }
        data[index] = e;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity(){
        return data.length;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    //向末尾添加元素
    public void addLast(int e){

        /*if (size == data.length){

        }
        data[size] = e;
        size++;*/
        add(size, e);
    }

    public void addFirst(int e){
        add(0, e);
    }

    public void add(int index, int e){

        if (size == data.length){

        }
        if (index < 0 || index > size){

        }

        for (int i = size - 1; i >= index; i--){
            data[i + 1] = data[i];
        }
        data[index].equals(e);
        size++;
    }

    public boolean contains(int e){
        for (int i = 0; i < size; i++){
            if (data[i].equals(e)){
                return true;
            }
        }
        return false;
    }

    //若找不到，返回-1
    public int find(int e){
        for (int i = 0; i < size; i++){
            if (data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    public E remove(int index){
        if (index < 0 || index > size){

        }
        E ret = data[index];

        for (int i = index + 1; i < size; i++){
            data[i - 1] = data[i];
            size--;
            data[size] = null; // loitering objects != memory leak
        }

        return ret;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size - 1);
    }

    //若存在多个，则只删除找到的第一个
    //由此可以看出find函数也可以完善
    public void removeElement(int e){
        int index = find(e);

        if (index != -1){
            remove(index);
        }
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d \n", size, getCapacity()));
        res.append("[");
        for (int i = 0; i < size; i++){
            res.append(data[i]);
            if (i != size){
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }
}
