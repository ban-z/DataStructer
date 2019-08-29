package code.Linear.Recursion;

public class recursion {

    public static int sum(int[] arr){
        return sumSure(arr, 0);
    }

    //统一规模公式
    private static int sumSure(int[] arr, int l){
        //最小规模
        if (l == arr.length){
            return  0;
        }

        /*
        *用统一公式完善问题
        * 此统一公式为拆分问题的过程
        * 递归到最小规模回退的过程为完善问题的过程
        * */
        return arr[l] + sumSure(arr, l + 1);
    }

    public static void main(String[] args) {
        int[] sum = {1,2,3,4,5,6,7,8,9};
        System.out.println(sum(sum));
    }
}
