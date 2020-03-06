package test.interview;

import java.util.LinkedList;
import java.util.List;

/**
 * todo description
 *
 * @author CHEN-KE-CHAO
 * @date 2020-03-04-8:34 下午
 */
public class Solution438 {
    public static void main(String[] args) {
        //取长度和
        List<Integer> list = new Solution438().findAnagrams("cbaebabacd", "abc");
        System.out.println(list);
    }

    public List<Integer> findAnagrams(String s, String p) {
        if (p.length() > s.length()) {
            return new LinkedList<>();
        }
        int head = 0;
        int tail = -1;
        LinkedList<Integer> list = new LinkedList<>();
        char[] sCharArray = s.toCharArray();
        char[] pCharArray = p.toCharArray();
        int[] needs = new int[26];
        int[] tmp = new int[26];

        for (int i = 0; i <= pCharArray.length; i++) {
            tmp[convertChar(sCharArray[i])]++;
        }
        for (int i = 0; i < pCharArray.length; i++) {
            needs[convertChar(pCharArray[i])]++;
        }

        while (tail + 1 < s.length()) {

            //1. 移动tail 将tail下标对应的值++
            tmp[convertChar(sCharArray[++tail])]++;

            //2. 判断tail - head 是否超出窗口大小
            if (tail - head + 1 > pCharArray.length) {
                tmp[convertChar(sCharArray[head++])]--;
            }
            //3. 当窗口大小相等 并确 验证成功后 添加 head坐标
            if (tail - head + 1 == pCharArray.length && same(tmp, needs)) {
                list.add(head);
            }
        }
        return list;
    }


    private boolean same(int[] freqS, int[] freqP) {
        for (int i = 0; i < 26; i++) {
            if (freqP[i] != freqS[i]) {
                return false;
            }
        }
        return true;
    }

    private int convertChar(char a) {
        return a - 'a';
    }
}
