package test;

/**
 * @author chenkechao
 * @date 2019/10/9 10:37 下午
 */
public class SelectionSort {

    public  void sort(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {

            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            Comparable tmpElement = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = tmpElement;
        }
    }

    public static void main(String[] args) {

        Integer[] arr = {1,6,12,20,99,3};

        SelectionSort selectionSort = new SelectionSort();

        selectionSort.sort(arr);

    }
}
