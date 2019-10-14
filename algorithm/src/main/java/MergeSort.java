import java.util.Arrays;

/**
 * @author chenkechao
 * @date 2019/10/11 8:28 下午
 */
public class MergeSort {

    private static final int DEFAULT_VALUE = 15;

    private MergeSort() {
    }

    public static void sort(Comparable[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(Comparable[] arr, int left, int right) {
        //终止条件
//        if (left >= right) {
//            //只有一个数据
//            return;
//        }
        if (right - left <= DEFAULT_VALUE) {
            insertionSort(arr, left, right);
            return;
        }

        int middle = left + (right - left) / 2;
        mergeSort(arr, left, middle);
        mergeSort(arr, middle + 1, right);
        if (arr[middle].compareTo(arr[middle + 1]) > 0) {
            merge(arr, left, middle, right);
        }
    }

    private static void merge(Comparable[] arr, int l, int mid, int r) {

        Comparable[] aux = Arrays.copyOfRange(arr, l, r + 1);

        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i > mid) {
                arr[k] = aux[j - l];
                j++;
            } else if (j > r) {
                arr[k] = aux[i - l];
                i++;
            } else if (aux[i - l].compareTo(aux[j - l]) < 0) {
                arr[k] = aux[i - l];
                i++;
            } else {
                arr[k] = aux[j - l];
                j++;
            }
        }
    }

    /**
     * 删除排序
     * @param arr
     * @param left
     * @param right
     */
    private static void insertionSort(Comparable[] arr, int left, int right) {

        for (int i = left + 1; i <= right ; i++) {
            Comparable tmp = arr[i];
            int j;
            for (j = i; j > left && arr[j -1].compareTo(arr[j]) > 0; j --) {
                arr[j] = arr[j - 1];
            }
            arr[j] = tmp;
        }
    }



    public static void main(String[] args) {
        Integer[] testArray = SortTestHelper.generateRandomArray(1000, 0, 10000);

        MergeSort.sort(testArray);
        SortTestHelper.printArray(testArray);
    }
}
