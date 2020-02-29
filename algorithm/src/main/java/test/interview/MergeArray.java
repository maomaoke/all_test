package test.interview;

import java.util.Arrays;

/**
 * todo description
 *
 * @author CHEN-KE-CHAO
 * @date 2020-02-28-1:42 下午
 */
public class MergeArray {

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nums1 = {3,6,8,8,0,0,0,0,0,0};
        int[] nums2 = {4,5,7,9};

        solution.merge(nums1, 4, nums2, nums2.length);
    }

    static class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {

            int[] tmp = new int[m];
            for (int i = 0; i < m; i++) {
                tmp[i] = nums1[i];
            }

            //双指针遍历两个数组
            int p1 = 0;
            int p2 = 0;
            for (int i = 0; i < m + n; i++) {
                if (p1 >= m) {
                    nums1[i] = nums2[p2++];
                    continue;
                }
                if (p2 >= n) {
                    nums1[i] = tmp[p1++];
                    continue;
                }
                if (tmp[p1] >= nums2[p2]) {
                    nums1[i] = nums2[p2++];
                } else if (tmp[p1] < nums2[p2]) {
                    nums1[i] = tmp[p1++];
                }
            }
            System.out.println(nums1.toString());
        }
    }
}
