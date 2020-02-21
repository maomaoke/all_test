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

        Integer[] nums = SortTestHelper.generateRandomArray(20, 1, 100);

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

            //中间点
            int v = nums[left];

            int j = left + 1; // [left+1...j)
            int k = right; // (k...right]

            while (true) {

                while (j <= right && nums[j] <= v) {
                    j++;
                }

                while (k >= left + 1 && nums[k] > v) {
                    k--;
                }

                if (j > k) {
                    break;
                }

                swap(nums, j++, k--);
            }

            swap(nums, left, k);

            return k;
        }

        private void swap(Integer[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }

}
