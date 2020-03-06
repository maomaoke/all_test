package test.interview;

import java.util.LinkedList;
import java.util.List;

/**
 * todo description
 *
 * @author CHEN-KE-CHAO
 * @date 2020-03-05-9:12 下午
 */
public class OldSolution438 {
    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() == 0 || p.length() > s.length()) {
            return new LinkedList<>();
        }
        int head = 0;
        int tail = head + p.length() - 1;
        LinkedList<Integer> list = new LinkedList<>();
        char[] sCharArray = s.toCharArray();
        char[] pCharArray = p.toCharArray();
        int[] needs = new int[26];
        int[] tmp = new int[26];

        for (int i = head; i <= tail; i++) {
            tmp[convertChar(sCharArray[i])]++;
        }
        for (int i = 0; i < pCharArray.length; i++) {
            needs[convertChar(pCharArray[i])]++;
        }

        while (tail < s.length()) {
            //验证
            boolean flag = true;
            for (int i = 0; i < 26; i++) {
                if (needs[i] != tmp[i]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                //正确
                //记录当前head下标
                list.add(head);
            }
            if (tail + 1 < s.length()) {
                tmp[convertChar(sCharArray[head++])]--;
                tmp[convertChar(sCharArray[++tail])]++;
            } else {
                return list;
            }
        }
        return list;
    }

    private int convertChar(char a) {
        return a - 'a';
    }
}
