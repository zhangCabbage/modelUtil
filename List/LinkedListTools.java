package zhang.algorithm.modelUtil.List;

/**
 * Created by zhang_zack on 16/6/3.
 * 链表的操作
 */
public class LinkedListTools {
    /**
     * build ArrayList by int array and return the ListNode root.
     *
     * @param array
     * @return
     */
    public static ListNode factory(int[] array) {
        if (array == null || array.length == 0) return null;

        ListNode root = new ListNode(array[0]);
        ListNode pre = root;

        for (int i = 1; i < array.length; i++) {
            ListNode cur = new ListNode(array[i]);
            pre.next = cur;
            pre = cur;
        }

        return root;
    }

    /**
     * reverse single linked list
     *
     * @param head
     */
    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode cur = head.next;
        head.next = null;

        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = head;
            head = cur;
            cur = temp;
        }
        return head;
    }

    /**
     * 重排链表, 使得奇数位节点在前, 偶数位节点在后
     *
     * @param head
     * @return
     */
    public static ListNode oddEvenList(ListNode head) {
        if (head != null) {
            ListNode odd = head;
            ListNode even = head.next, evenHead = even;
            while (even != null && even.next != null) {
                odd.next = odd.next.next;
                even.next = even.next.next;
                odd = odd.next;
                even = even.next;
            }
            odd.next = evenHead;
        }
        return head;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4};
        ListNode head = factory(array);
        System.out.println(head.toString());

        head = reverse(head);
        System.out.println(head.toString());
    }
}
