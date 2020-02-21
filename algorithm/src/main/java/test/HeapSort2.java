package test;

/**
 * @author chenkechao
 * @date 2019/10/22 10:37 下午
 */
public class HeapSort2 {
    private HeapSort2() {
    }

    public static void sort(Comparable[] arr) {
        int count = arr.length;
        for (int i = (count - 1) / 2; i > 0; i--) {
            shiftDown(arr, i);
            swap(arr, i, 0);
        }
    }

    private static void shiftDown(Comparable[] arr, int k) {

        int count = arr.length;
//        while (k < count - 1) {
//            if (arr[k].compareTo())
//        }
    }

    private static void swap(Comparable[] arr, int i, int j) {
        Comparable tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
