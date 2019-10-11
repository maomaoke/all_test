/**
 * @author chenkechao
 * @date 2019/10/10 8:40 下午
 */
public class InsertionSort {
    private InsertionSort() {

    }

    public static void sort(Comparable[] arr) {
        int length = arr.length;
        for (int i = 1; i < length; i++) {

            Comparable tmp = arr[i];
            int j;
            for ( j = i; j > 0 && arr[j-1].compareTo(tmp) > 0; j--) {
                    arr[j] = arr[j -1];
            }
            arr[j] = tmp;
        }

    }

    private static void swap(Comparable[] arr, int i, int j) {
        Comparable tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        Integer[] testArray = SortTestHelper.generateRandomArray(10, 0, 100);

        Student[] students = new Student[testArray.length];

        for (int i = 0; i < testArray.length; i++) {
            students[i] = new Student("小明" + i, testArray[i]);
        }

        InsertionSort.sort(students);
        SortTestHelper.printArray(students);
    }
}
