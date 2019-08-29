package code.Sort;

public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {1 ,4, 7, 8, 3, 6, 9};
        sort(arr, 0, arr.length - 1);
        print(arr);
    }

    public static void sort(int[] arr, int left, int right){
        if (left == right){
            return;
        }

        int mid = left + (right - left) / 2;
        sort(arr, left, mid);
        sort(arr, mid + 1, right);
        merge(arr, left, mid + 1, right);
    }

    private static void merge(int[] arr, int leftPtr, int rightPtr, int rightBound){
        int mid = rightPtr;
        int[] temparr = new int[rightBound - leftPtr + 1];

        int i = leftPtr;
        int j = rightPtr + 1;
        int k = 0;

         while (i <= mid && j < rightBound){


             //优化
             //temparr[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];

             if (arr[i] < arr[j]){
                 temparr[k] = arr[i];
                 i++;
                 k++;
             }else if (arr[i] >= arr[j]){
                 j++;
                 k++;
             }
         }
         while (i <= mid){
             temparr[k++] = arr[i++];
         }
         while (j < rightBound){
             temparr[k++] = arr[j++];
         }

         //print(temparr);
        for (int m = 0; m < temparr.length; m++){
            arr[leftPtr + m] = temparr[m];
        }
    }

    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void print(int[] arr){
        for (int i = 0; i < arr.length; i++){
            System.out.println(arr[i] + " ");
        }
    }
 }
