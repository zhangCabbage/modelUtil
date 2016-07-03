package zhang.algorithm.modelUtil.List;

/**
 * Created by zhang_zack on 16/6/3.
 */
public class ListTool {
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
}
