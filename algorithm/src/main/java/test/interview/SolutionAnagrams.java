package test.interview;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * todo description
 *
 * @author CHEN-KE-CHAO
 * @date 2020-03-05-8:38 下午
 */
public class SolutionAnagrams {

    public static void main(String[] args) {
        //取长度和
        List<Integer> list = new SolutionAnagrams().findAnagrams("cbaebabacd", "abc");
        System.out.println(list);
    }

    public List<Integer> findAnagrams(String s, String p) {

        if (s.length() < p.length()) {
            return new LinkedList<>();
        }

        char[] sArr = s.toCharArray();
        char[] pArr = p.toCharArray();

        int[] hash = new int[26];

        for (int i = 0; i < pArr.length; i++) {
            hash[convert(pArr[i])]++;
        }
        List<Integer> results = new ArrayList<>();
        int head = 0, count = 0, pLength = p.length();

        for (int tail = 0; tail < pLength; tail++) {
            hash[convert(sArr[tail])]--;

            if (hash[convert(sArr[tail])] >= 0) {
                count++;
            }

            if (tail >= pLength) {
                hash[convert(sArr[head])]++;
                if (hash[convert(sArr[head])] > 0) {
                    count--;
                }
                head++;
            }
            if (count == pLength) {
                results.add(head);
            }
        }
        return results;
    }

    private int convert(char c) {
        return c - 'a';
    }
}
