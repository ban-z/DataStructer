package code.Sort;

public class SelectSort {

    public static void main(String[] args) {
        int[] arr = {19, 3, 56, 8, 55, 89, 1};
        sort(arr);
        print(arr);
    }

    private static void sort(int[] arr){
        for (int i = 0; i < arr.length; i++){
            for (int j = i; j < arr.length; j++){
                if (arr[i] > arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void print(int[] arr){
        for (int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }
    }
}
