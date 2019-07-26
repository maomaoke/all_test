package linkedlist;

import java.util.Objects;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-07-23-21:08
 */
public class Test {

    private class ListNode<T> {
        T data;
        ListNode<T> next;

        public ListNode(T data) {
            this.data = data;
        }

        public ListNode() {
            this(null);
        }

    }

    public static <T> ListNode<T> updateElement(ListNode<T> head, T oldData, T newData) {
        if (Objects.isNull(head)) {
            return null;
        }
        //子问题的解来构建原问题的解

        ListNode<T> subHead = updateElement(head.next, oldData, newData);
        if (Objects.nonNull(head.data) && head.data.equals(oldData)) {
            head.data = newData;
        }
        head.next = subHead;
        return head;
    }

    public static void main(String[] args) {

    }

}
