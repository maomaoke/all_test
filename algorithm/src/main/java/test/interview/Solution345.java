package test.interview;

/**
 * @author chenkechao
 * @date 2020/3/1 1:41 下午
 */
public class Solution345 {
    public static void main(String[] args) {
        String result = new Solution345().reverseVowels("leetcode");
        System.out.println(result);
    }

    public String reverseVowels(String s) {
        if (s.length() == 0) {
            return "";
        }
        //a、e、i、o、u
        char[] chars = s.toCharArray();
        //对撞指针
        int head = 0;
        int tail = chars.length - 1;

        while (head < tail) {
            char i = chars[head];
            char j = chars[tail];
            if (!isVowelChar(i)) {
                head++;
                continue;
            }
            if (!isVowelChar(j)) {
                tail--;
                continue;
            }
            //swap
            swap(chars, head++, tail--);
        }
        return new String(chars);
    }

    private void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

    private boolean isVowelChar(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
