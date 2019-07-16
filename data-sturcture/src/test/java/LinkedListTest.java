import linkedlist.LinkedList;

/**
 * @author chenkechao
 * @date 2019-07-15 21:01
 */
public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addLast(i);
            System.out.println(linkedList);
        }
        linkedList.add(2, 666);
        System.out.println(linkedList);

        linkedList.remove(0);
        System.out.println(linkedList);
    }
}
