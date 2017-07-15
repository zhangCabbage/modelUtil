package zhang.algorithm.modelUtil.Array;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/7/14
 * Time: 下午9:33
 * To change this template use File | Settings | File Templates.
 */
public class MaxInArrayWindows {
    /**
     * 当Deque实现类为有限容量时，优先使用offerFirst和offerLast。
     * 因为addFirst在队列满的时候可能会插入失败而抛出异常。
     * <p>
     * LinkedList和ArrayDeque均为可变双端队列
     *
     * @param num
     * @param size
     * @return
     */
    public static ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        if (num == null || num.length < size || size < 1) return res;

        Deque<Integer> deque = new LinkedList<>();   //双端队列!

        for (int i = 0; i < size; i++) {   //先获取第一个窗口最大值!
            while (!deque.isEmpty() && num[i] >= num[deque.getLast()])
                deque.pollLast();
            deque.offerLast(i);
        }
        for (int i = size; i < num.length; i++) {
            res.add(num[deque.getFirst()]);

            while (!deque.isEmpty() && num[i] >= num[deque.getLast()])
                deque.pollLast();

            if (!deque.isEmpty() && i - deque.getFirst() >= size)
                deque.pollFirst();

            deque.offerLast(i);
        }
        res.add(num[deque.getFirst()]);   //最后一个窗口不在循环中!

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 4, 2, 6, 2, 5, 1};
        System.out.println(maxInWindows(nums, 3));
    }
}
