package code.Sort;

public class InsertSort  {

    public static void main(String[] args) {
        int[] arr = {19, 3, 56, 8, 55, 89, 1};
        sort(arr);
        print(arr);
    }

    public static void sort(int[] arr){
        for (int i  = 0; i < arr.length; i++){
            for (int j = i; j > 0; j--){
                if (arr[j] < arr[j - 1]){
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }else {
                    break;
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
