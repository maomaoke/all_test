package test.interview;

import test.SortTestHelper;

/**
 * @author chenkechao
 * @date 2020/2/24 9:24 下午
 */
public class SortColor3 {

    public static void main(String[] args) {
        Integer[] nums = SortTestHelper.generateRandomArray(100, 1, 20);
        SortColor3.Solution solution = new SortColor3.Solution();
        solution.quickSort(nums, 0, nums.length - 1);
        SortTestHelper.printArray(nums);
    }

    private static class Solution {

        public void quickSort(Integer[] nums, int l, int r) {

            if (l >= r) {
                return;
            }

            swap(nums, l, (int) (Math.random() * (r - l + 1)) + l);
            int p = nums[l];

            int i = l + 1;
            int lt = l;
            int gt = r + 1;

            while (i < gt) {
                if (nums[i] < p) {
                    swap(nums, i++, ++lt);
                } else if (nums[i] == p) {
                    i++;
                } else {
                    swap(nums, i, --gt);
                }
            }
            swap(nums, l, lt);

            quickSort(nums, l, lt - 1);
            quickSort(nums, gt, r);
        }


        private void swap(Integer[] nums, int i, int j) {
            if (i == j) {
                return;
            }
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
}
