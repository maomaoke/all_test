package test.interview;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author chenkechao
 * @date 2020/3/16 9:05 下午
 */
public class Solution349 {


    public static void main(String[] args) {

    }

    public int[] intersection(int[] nums1, int[] nums2) {

        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        for (int i : nums1) {
            set1.add(i);
        }

        for (int i : nums2) {

        }

        int[] result = new int[set2.size()];
        int i = 0;
        for (Integer item : set2) {
            result[i++] = item;
        }
        return result;
    }
}
