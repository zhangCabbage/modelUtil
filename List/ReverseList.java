package zhang.algorithm.modelUtil.List;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/7/4
 * Time: 下午11:01
 * To change this template use File | Settings | File Templates.
 */
public class ReverseList {
    /**
     * 1、可以使用迭代的方法进行反转链表
     *
     * @param head
     * @return
     */
    public static ListNode ReverseList1(ListNode head) {

        if (head == null || head.next == null) return head;
        ListNode tmp = ReverseList1(head.next);
        head.next.next = head;
        head.next = null;
        return tmp;
    }

    /**
     * 也可使用非迭代方式解决
     *
     * @param head
     * @return
     */
    public static ListNode ReverseList2(ListNode head) {

        return null;
    }

    public static void main(String[] args) {
        int[] arrays = {1, 2, 3};
        ListNode head = LinkedListTools.factory(arrays);
        System.out.println(ReverseList1(head));
    }
}
