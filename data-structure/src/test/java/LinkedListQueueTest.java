import queue.LinkedListQueue;
import queue.Queue;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-07-17-21:34
 */
public class LinkedListQueueTest {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedListQueue<>();
        for (int i = 0; i < 100; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
        while (!queue.isEmpty()) {
            queue.dequeue();
            System.out.println(queue);
        }
        System.out.println(queue);
        System.out.println("end");
    }
}
