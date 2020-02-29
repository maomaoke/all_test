package test.interview;

/**
 * todo description
 *
 * @author CHEN-KE-CHAO
 * @date 2020-02-28-6:38 下午
 */
public class Solution215 {

    public static void main(String[] args) {
        Solution215 solution215 = new Solution215();
//        int[] nums = {3, 2, 1, 5, 6, 4};
//        int kthLargest = solution215.findKthLargest(nums, 2);
//        int[] nums2 = {2, 1};
//        int kthLargest = solution215.findKthLargest(nums2, 2);
        int[] nums3 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int kthLargest = solution215.findKthLargest(nums3, 4);
        System.out.println(kthLargest);
    }


    public int findKthLargest(int[] nums, int k) {
        return find(nums, 0, nums.length - 1, k);
    }

    private int find(int[] nums, int l, int r, int k) {

        int index = partition(nums, l, r);
        if (index == k - 1) {
            return nums[index];
        } else if (index > k - 1) {
            return find(nums, l, index - 1, k);
        } else {
            return find(nums, index + 1, r, k);
        }
    }

    private int partition(int[] nums, int l, int r) {
        int v = nums[l];
        //j 为 最后一个大于v的数据的index坐标
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (nums[i] > v) {
                swap(nums, i, ++j);
            }
        }
        swap(nums, l, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
