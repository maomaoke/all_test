package test.interview;

import test.SortTestHelper;

/**
 * @author chenkechao
 * @date 2020/2/23 9:41 下午
 */
public class SortColor2 {
    public static void main(String[] args) {
        Integer[] nums = SortTestHelper.generateRandomArray(8, 1, 30);

        SortColor2.Solution solution = new SortColor2.Solution();
        solution.quickSort(nums, 0, nums.length - 1);
        SortTestHelper.printArray(nums);
    }

    private static class Solution {

        // [l...r]
        public void quickSort(Integer[] nums, int l, int r) {

            if (r <= l) {
                return;
            }

            int p = partition(nums, l, r);
            quickSort(nums, l, p - 1);
            quickSort(nums, p + 1, r);
        }

        // [l...r]
        private int partition(Integer[] nums, int l, int r) {

            swap(nums, l, (int) (Math.random() * (r - l + 1)) + l);
            int p = nums[l];
            //[l+1...i) <= p
            int i = l + 1;
            //(j...r] >= p
            int j = r;
            while (true) {

                while (j >= l + 1 && nums[j] > p) {
                    j--;
                }

                while (i <= r && nums[i] < p) {
                    i++;
                }

                if (j <= i) {
                    break;
                }

                swap(nums, i++, j--);
            }

            swap(nums, l, j);

            return j;
        }

        private void swap(Integer[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
}
