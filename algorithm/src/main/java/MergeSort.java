import java.util.Arrays;

/**
 * @author chenkechao
 * @date 2019/10/11 8:28 下午
 */
public class MergeSort {
    private MergeSort() {
    }

    public static void sort(Comparable[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(Comparable[] arr, int left, int right) {
        //终止条件
        if (left >= right) {
            //只有一个数据
            return;
        }

        int middle = left + (right - left) / 2;
        mergeSort(arr, left, middle);
        mergeSort(arr, middle + 1, right);
        merge(arr, left, middle, right);
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

    public static void main(String[] args) {
        Integer[] testArray = SortTestHelper.generateRandomArray(8, 0, 10000);

        MergeSort.sort(testArray);
        SortTestHelper.printArray(testArray);
    }
}
