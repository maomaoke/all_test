package test.interview;

/**
 * @author chenkechao
 * @date 2020/2/29 10:59 下午
 */
public class Solution344 {

    public static void main(String[] args) {
        char[] chars = {'A',' ','m','a','n',',',' ','a',' ','p','l','a','n',',',' ','a',' ','c','a','n','a','l',':',' ','P','a','n','a','m','a'};
        new Solution344().reverseString(chars);
        System.out.println(chars);
    }

    public void reverseString(char[] s) {
        if (s.length == 0) {
            return;
        }
        for (int i = 0; i <= (s.length - 1) / 2; i++) {
            char tmp = s[i];
            s[i] = s[s.length - 1 - i];
            s[s.length - 1 - i] = tmp;
        }
    }
}
