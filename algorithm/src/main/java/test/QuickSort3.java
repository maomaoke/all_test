package test;

/**
 * @author chenkechao
 * @date 2019/10/17 8:04 下午
 */
public class QuickSort3 {
    private QuickSort3() {
    }

    public static void sort(Comparable[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(Comparable[] arr, int l, int r) {
        if (r - l <= 15) {
            insertionSort(arr, l, r);
            return;
        }
        swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);
        Comparable v = arr[l];
        int i = l, lt = l, gt = r + 1;
        while (i < gt) {
            if (arr[i].compareTo(v) == 0) {
                i++;
            } else if (arr[i].compareTo(v) < 0) {
                swap(arr, i, lt++);
                i++;
            } else {
                swap(arr, i, gt--);
            }
        }
        swap(arr, l, lt);

        sort(arr, l, lt - 1);
        sort(arr, gt, r);
    }


    private static void swap(Comparable[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        Comparable tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void insertionSort(Comparable[] arr, int l, int r) {

        int j;
        for (int i = l + 1; i <= r; i++) {
            Comparable v = arr[i];
            for (j = i - 1; j > 0 && arr[j].compareTo(v) > 0; j-- ) {
                arr[j + 1] = arr[j];
            }
            arr[j] = v;
        }
    }

    public static void main(String[] args) {
        Integer[] testArray = SortTestHelper.generateRandomArray(10, 0, 10000);

        QuickSort.sort(testArray);
        SortTestHelper.printArray(testArray);
    }
}
