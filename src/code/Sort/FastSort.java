package code.Sort;

public class FastSort {

    public static void main(String[] args) {

    }

    /*
     * 快速排序主方法
     * */
    public static void quickSort(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }

        int base = arr[left];
        int i = left;
        int j = right;

        while (i != j) {
            while (arr[j] >= base && i < j) {
                j--;
            }
            while (arr[i] <= base && i < j) {
                i++;
            }

            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        arr[left] = arr[i];
        arr[i] = base;

        quickSort(arr, left, i - 1);
        quickSort(arr, j + 1, right);
    }
}
