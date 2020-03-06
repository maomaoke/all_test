package test.interview;

/**
 * todo description
 *
 * @author CHEN-KE-CHAO
 * @date 2020-03-05-9:49 下午
 */
public class Solution76 {

    //给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。

    public static void main(String[] args) {
//        String s = new Solution76().minWindow("ADOBECODEBANC", "ABC");
        String s = new Solution76().minWindow("ab", "b");
        System.out.println(s);
    }

    public String minWindow(String s, String t) {


        int head = 0;
        int tail = -1;
        char[] totalArr = s.toCharArray();
        char[] subStringArr = t.toCharArray();
        int[] needs = new int[256];
        int[] tmp = new int[256];

        int stringCount = totalArr.length + 1;
        String string = null;

        for (int i = 0; i < subStringArr.length; i++) {
            needs[subStringArr[i]]++;
        }

        while (head < totalArr.length) {
            //判断
            if (same(tmp, needs)) {
                //相等  截取字符串 并记录最小的字符串
                int len = tail - head + 1;
                if (len < stringCount) {
                    stringCount = len;
                     string = s.substring(head, tail + 1);
                }
                //
                tmp[totalArr[head++]]--;
            } else if (tail + 1 < totalArr.length) {
                tmp[totalArr[++tail]]++;
            } else {
                tmp[totalArr[head++]]--;
            }
        }
        if (stringCount == totalArr.length + 1) {
            string = "";
        }
        return string;
    }

    private boolean same(int[] a, int[] b) {

        for (int i = 0; i < 256; i++) {
            // a 是总串 b是子串 如果 a要包含b, 那么 a中的元素数量肯定要大于等于b中的元素
            if (a[i] < b[i]) {
                return false;
            }
        }
        return true;
    }

}
