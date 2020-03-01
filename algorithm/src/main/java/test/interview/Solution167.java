package test.interview;

/**
 * @author chenkechao
 * @date 2020/2/29 3:00 下午
 */
public class Solution167 {

    public static void main(String[] args) {

        int[] nums = {-1, 0};
        int[] result = new Solution167().twoSum(nums, -1);
    }

    public int[] twoSum(int[] numbers, int target) {

        //1. 因为数组是有序的, 所以 从数组尾部找到第一个小于等于target的值
        int tail = numbers.length - 1;
        int head = 0;
        if (target > 0) {
            while (numbers[tail] >= target) {
                tail--;
            }
        }
        while (head < tail) {
            if (numbers[head] + numbers[tail] == target) {
                return new int[]{++head, ++tail};
            } else if (numbers[head] + numbers[tail] > target) {
                tail--;
            } else {
                head++;
            }
        }
        return new int[]{};
    }
}
