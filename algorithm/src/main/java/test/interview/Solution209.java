package test.interview;

/**
 * @author chenkechao
 * @date 2020/3/3 8:06 下午
 */
public class Solution209 {
    public static void main(String[] args) {

        int[] nums = {2, 3, 1, 2, 4, 3};
        int min = new Solution209().minSubArrayLen(7, nums);
//        int[] nums = {1, 4, 4};
//        int min = new Solution209().minSubArrayLen(4, nums);
//        int[] nums = {1, 1};
//        int min = new Solution209().minSubArrayLen(3, nums);
        System.out.println(min);
    }

    public int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int head = 0;
        int tail = -1;
        int sum = 0;
        int res = nums.length + 1;

        while (head < nums.length) {
            if (tail + 1 < nums.length && sum < s) {
                sum += nums[++tail];
            } else {
                sum -= nums[head++];
            }

            if (sum >= s) {
                res = Math.min(res, tail - head + 1);
            }
        }
        if (res == nums.length + 1) {
            return 0;
        }
        return res;
    }
}
