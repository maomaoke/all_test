package test.interview;

import test.SortTestHelper;

/**
 * todo description
 *
 * @author CHEN-KE-CHAO
 * @date 2020-02-20-7:56 下午
 */
public class SortColor {

    public static void main(String[] args) {

        Integer[] nums = SortTestHelper.generateRandomArray(2000, 1, 10000);

        Solution solution = new Solution();
        solution.quickSort(nums, 0, nums.length - 1);
        SortTestHelper.printArray(nums);
    }

    static class Solution {
        private void quickSort(Integer[] nums, int left, int right) {
            if (left > right) {
                return;
            }
            //计算 p
            int p = partition(nums, left, right);

            //递归调用
            quickSort(nums, left, p - 1);
            quickSort(nums, p + 1, right);
        }

        private int partition(Integer[] nums, int left, int right) {
            //j标记为小于等于p的节点

            int p = nums[left];
            int j = left;
            for (int i = left + 1; i <= right ; i++) {
                if (nums[i] <= p) {
                    j++;
                    swap(nums, j, i);
                }
            }
            swap(nums, j, left);
            return j;
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
