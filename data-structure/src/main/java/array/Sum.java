package array;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-07-19-22:52
 */
public class Sum {

    static int sum(int[] arr) {
        return sum(arr, 0);
    }


    private static int sum(int[] arr, int l) {
        if (l == arr.length - 1) {
            return arr[l];
        }
        return arr[l] + sum(arr, l + 1);
    }

    public static void main(String[] args) {

        int[] arr = {1,2,3,4,5,6,7,8};

        int sum = sum(arr);
        System.out.println(sum);
    }
}
