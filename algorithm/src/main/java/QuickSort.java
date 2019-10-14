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

        if (left >= right) {
            return;
        }

        int p = partition(arr, left, right);

        sort(arr, left, p - 1);
        sort(arr, p + 1, right);
    }

    private static <E> int partition(Comparable<E>[] arr, int left, int right) {

        Comparable<E> v = arr[left];

        int j = left;
        for (int i = left + 1; i <= right; i++) {
            if (arr[i].compareTo((E) v) < 0) {
                swap(arr, i, j + 1);
                j++;
            }
        }
        swap(arr, left, j);
        return j;
    }

    private static void swap(Comparable[] arr, int i, int j) {
        Comparable tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        Integer[] testArray = SortTestHelper.generateRandomArray(1000, 0, 10000);

        QuickSort.sort(testArray);
        SortTestHelper.printArray(testArray);
    }
}
