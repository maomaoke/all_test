import java.io.PipedReader;

/**
 * @author chenkechao
 * @date 2019/10/14 9:44 下午
 */
public class QuickSort {

    private QuickSort() {
    }

    public static <E> void sort(Comparable<E>[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static <E> void sort(Comparable<E>[] arr, int left, int right) {

        if (right - left <= 15) {
            insertionSort(arr, left, right);
            return;
        }

        int p = partition(arr, left, right);

        sort(arr, left, p - 1);
        sort(arr, p + 1, right);
    }

//    private static <E> int partition(Comparable<E>[] arr, int left, int right) {
//
//        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
//        swap(arr, left, (int) (Math.random() * (right - left + 1)) + left);
//
//        Comparable<E> v = arr[left];
//
//        int j = left;
//        for (int i = left + 1; i <= right; i++) {
//            if (arr[i].compareTo((E) v) < 0) {
//                swap(arr, i, j + 1);
//                j++;
//            }
//        }
//        swap(arr, left, j);
//        return j;
//    }

    /**
     * 双路排序
     *
     * @param arr
     * @param left
     * @param right
     * @param <E>
     * @return
     */
    private static <E> int partition(Comparable<E>[] arr, int left, int right) {

        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        swap(arr, left, (int) (Math.random() * (right - left + 1)) + left);
        Comparable<E> v = arr[left];

        //arr[left + 1 ... i) <= v and arr(j ... r] >= v
        int i = left + 1, j = right;
        for (; ; ) {
            while (i <= right && arr[i].compareTo((E) v) < 0) {
                i++;
            }
            while (j >= left + 1 && arr[j].compareTo((E) v) > 0) {
                j--;
            }
            if (i > j) {
                break;
            }
            swap(arr, i++, j--);
        }
        swap(arr, left, j);
        return j;
    }

    private static void swap(Comparable[] arr, int i, int j) {
        Comparable tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void insertionSort(Comparable[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            Comparable tmp = arr[i];
            int j;
            for (j = i; j > left && arr[j - 1].compareTo(tmp) > 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = tmp;
        }
    }

    public static void main(String[] args) {
        Integer[] testArray = SortTestHelper.generateRandomArray(1000, 0, 10000);

        QuickSort.sort(testArray);
        SortTestHelper.printArray(testArray);
    }
}
