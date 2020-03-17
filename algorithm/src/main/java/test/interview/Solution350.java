package test.interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenkechao
 * @date 2020/3/16 10:00 下午
 */
public class Solution350 {
    public static void main(String[] args) {

    }

    public int[] intersect(int[] nums1, int[] nums2) {

        Map<Integer, Integer> map1 = new HashMap<>();
        for (int i : nums1) {
            Integer value = map1.get(i);
            if (value == null) {
                map1.put(i, 1);
            } else {
                map1.put(i, ++value);
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int i : nums2) {
            Integer value = map1.get(i);
            if (value != null) {
                list.add(i);
                value--;
                if (value == 0) {
                    map1.remove(i);
                } else {
                    map1.put(i, value);
                }
            }
        }

        int[] result = new int[list.size()];

        int i = 0;
        for (Integer item : list) {
            result[i++] = item;
        }

        return result;
    }
}
