package test.interview;

/**
 * todo description
 *
 * @author CHEN-KE-CHAO
 * @date 2020-03-04-7:12 下午
 */
public class Solution3 {

    public static void main(String[] args) {

    }

    public int lengthOfLongestSubstring(String s) {

        int head = 0;
        int tail = -1;
        int length = s.length();
        int[] store = new int[256];
        int result = 0;

        while (head < length) {
            if (tail + 1 < length && store[s.charAt(tail + 1)] == 0) {
                store[s.charAt(++tail)]++;
            } else {
                store[s.charAt(head++)]--;
            }
            result = Math.max(result, tail - head + 1);
        }
        return result;
    }
}
