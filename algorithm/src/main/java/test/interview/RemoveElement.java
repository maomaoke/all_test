package test.interview;

/**
 * todo description
 *
 * @author CHEN-KE-CHAO
 * @date 2020-02-20-2:46 下午
 */
public class RemoveElement {

    public static void main(String[] args) {
        int[] nums = {3,2,2,3};
        int count = removeElement(nums, 2);
        System.out.println(count);
    }

    public static int removeElement(int[] nums, int val) {

        int count = 0;
        int p = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                if (p != i) {
                    swap(p, i, nums);
                }
                p++;
            } else {
                count++;
                if (p != i && nums[p] != nums[i]) {
                    p++;
                }
            }
        }
        return nums.length - count;
    }

    private static void swap(int i, int j, int[] nums) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
