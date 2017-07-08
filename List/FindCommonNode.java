package zhang.algorithm.modelUtil.List;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/7/8
 * Time: 下午4:07
 * To change this template use File | Settings | File Templates.
 */
public class FindCommonNode {
    /**
     * 查找第一个公共节点
     * 避免冗余判断
     *
     * @param pHead1
     * @param pHead2
     * @return
     */
    public static ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode cur1 = pHead1, cur2 = pHead2;
        while (cur1 != cur2) {
            cur1 = cur1 == null ? pHead2 : cur1.next;
            cur2 = cur2 == null ? pHead1 : cur2.next;
        }
        return cur1;
    }

    public static void main(String[] args) {

    }
}
