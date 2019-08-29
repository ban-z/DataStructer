package code.Linear.Array;

public class Main {

    public static void main(String[] args){
        //尽量避免无语义的名字
        //数组的索引可以利用固定的语义，和其中值的特性 eg：0，1
        //若不适用有语义的索引，则一般情况下其他的数据结构更适合，但并非所有有语义的数据结构都适应于数组 eg：身份证号
        //
        myArray<Integer> arr = new myArray<Integer>(20);
        /*int[] scores = new int[]{1,2,3,4,5};*/
        myArray<Integer> scores = new myArray<Integer>(5);
        //增加数组的灵活性，避免硬编码
        for (int i = 0; i < arr.getSize(); i++){
            arr.add(i, i);
        }

        /*//for-each
        //数组可遍历的特性，可迭代
        for (int score : scores){

        }*/


        myArray dymarr = new myArray(20);
        for (int i = 0; i < 10; i++){
            dymarr.addLast(i);
        }
        System.out.println(dymarr);

        dymarr.add(2, 100);
        System.out.println(dymarr);
    }
}
