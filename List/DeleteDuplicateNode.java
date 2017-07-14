package zhang.algorithm.modelUtil.List;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/7/12
 * Time: 下午11:05
 * To change this template use File | Settings | File Templates.
 */
public class DeleteDuplicateNode {

    public static void main(String[] args) {
        DeleteDuplicateNode test = new DeleteDuplicateNode();
    }

    /**
     * 参见leetcode 82 - RemoveDuplicatesSortedListII
     * 思路略复杂!
     *
     * @param head
     * @return
     */
    public static ListNode deleteDuplication(ListNode head) {
        //第一次思路
//        ListNode head = new ListNode(0);
//        ListNode tail = head;
//        while (pHead != null) {
//            ListNode cur = pHead;
//            int duplicate = cur.val + 1;
//            while (pHead.next != null && cur.val == pHead.next.val) {
//                pHead = pHead.next;
//                duplicate = cur.val;
//            }
//            if (cur.val != duplicate) {
//                tail.next = cur;
//                tail = tail.next;
//            }
//            pHead = pHead.next;
//        }
//        tail.next = null;
//
//        return head.next;

        ListNode empty = new ListNode(0);
        ListNode tail = empty;

        while (head != null) {
            ListNode cur = head;
            boolean flag = false;
            while (head.next != null && cur.val == head.next.val) {
                head = head.next;
                flag = true;
            }
            if (!flag) {
                tail.next = cur;
                tail = tail.next;
            }
            head = head.next;
        }
        tail.next = null;
        return empty.next;
    }
}
