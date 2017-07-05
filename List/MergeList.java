package zhang.algorithm.modelUtil.List;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/7/5
 * Time: 上午9:37
 * To change this template use File | Settings | File Templates.
 */
public class MergeList {
    /**
     * @param list1
     * @param list2
     * @return
     */
    public static ListNode Merge(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        if (list1 != null) cur.next = list1;
        if (list2 != null) cur.next = list2;

        return head.next;
    }

    public static void main(String[] args) {
        ListNode list1 = LinkedListTools.factory(new int[]{1, 3});
        ListNode list2 = LinkedListTools.factory(new int[]{2, 4});
        System.out.println(Merge(list1, list2));
    }
}
