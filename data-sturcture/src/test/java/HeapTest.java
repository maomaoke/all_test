import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import linkedlist.LinkedList;
import queue.PriorityQueue;

import java.util.List;
import java.util.TreeMap;


/**
 * @author chenkechao
 * @date 2019-08-05 18:44
 */
public class HeapTest {

    private static class Frequent implements Comparable<Frequent> {
        private Integer e;
        private Integer freq;

        private Frequent(Integer e, Integer freq) {
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Frequent o) {
            if (o.freq.compareTo(this.freq) < 0) {
                return 1;
            } else if (this.freq.equals(o.freq)) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        TreeMap<Integer, Integer> map = Maps.newTreeMap();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        PriorityQueue<Frequent> pq = new PriorityQueue<>();

        for (Integer key : map.keySet()) {
            if (pq.getSize() < k) {
                pq.enqueue(new Frequent(key, map.get(key)));
            } else if (map.get(key).compareTo(pq.getFront().freq) > 0) {
                pq.dequeue();
                pq.enqueue(new Frequent(key, map.get(key)));
            }
        }

        //1.频次越低,就会放在优先队列的最前面(最大堆中,指定相反的 compare)

        List<Integer> list = Lists.newLinkedList();

        while (!pq.isEmpty()) {
            list.add(pq.dequeue().e);
        }
        return list;
    }
}
