package test.interview;

/**
 * @author chenkechao
 * @date 2020/3/1 2:01 下午
 */
public class Solution11 {

    public static void main(String[] args) {
        int maxArea = new Solution11().maxArea(new int[]{2, 3, 10, 5, 7, 8, 9});
        System.out.println(maxArea);
    }

    public int maxArea(int[] height) {
        //对撞指针
        int head = 0;
        int tail = height.length - 1;

        int maxArea = 0;
        while (head < tail) {
            int i = height[head];
            int j = height[tail];
            //高
            int min = Math.min(i, j);
            //长 * 高
            int area = min * (tail - head);
            if (area > maxArea) {
                maxArea = area;
            }
            if (i < j) {
                head++;
            } else {
                tail--;
            }
        }
        return maxArea;
    }
}
