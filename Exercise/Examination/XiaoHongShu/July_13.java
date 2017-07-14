package zhang.algorithm.modelUtil.Exercise.Examination.XiaoHongShu;

import zhang.algorithm.modelUtil.List.LinkedListTools;
import zhang.algorithm.modelUtil.List.ListNode;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/7/14
 * Time: 上午10:14
 * To change this template use File | Settings | File Templates.
 */
public class July_13 {
    /**
     * 最长01串: 一个01数组, 求其中01串相等的最长序列长度,如何使用O(n)时间复杂度解决
     * 前缀和解法, 使用一个map存储为某一个值的最小下标
     * 参考: http://www.voidcn.com/blog/zhang_di233/article/p-4499156.html
     * <p>
     * [和最大的子数组、和为0的子数组、和最接近0的子数组] 都是使用前缀和来解决
     *
     * @param in
     */
    public static void test2(Scanner in) {
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            nums[i] = x == 1 ? 1 : -1;
        }

        int sum = 0, max = 0;
        int[] map = new int[2 * n + 1];
        Arrays.fill(map, -1);
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (sum == 0) {
                max = Math.max(max, i + 1);  //从开头开始的序列
                continue;
            }
            if (map[sum + n] == -1) map[sum + n] = i;
            else max = Math.max(max, i - map[sum + n]);  //注意这里不是 a - b + 1
        }

        System.out.println(max);
    }

    /**
     * 4->5->6->7->NULL如此字符串，不申请额外的空间，完成奇数位在前，偶数位在后
     * 如：4->6->5->7->NULL，前后顺序不变
     * 链表的重排序
     * <p>
     * 注意: Java的字符串比较使用equals
     *
     * @param in
     */
    public static void test3(Scanner in) {
        String[] strs = in.next().split("->");
        ListNode head = new ListNode(0);
        ListNode tail = head;
        for (int i = 0; i < strs.length; i++) {
            if (!"NULL".equals(strs[i])) {
                tail.next = new ListNode(Integer.parseInt(strs[i]));
                tail = tail.next;
            }
        }
        head = LinkedListTools.oddEvenList(head.next);
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            sb.append("->");
            head = head.next;
        }
        sb.append("NULL");
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            test3(in);
        }
    }
}
