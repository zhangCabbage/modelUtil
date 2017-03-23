package zhang.algorithm.modelUtil.Exercise.CodeInterview;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/3/23
 * Time: 下午7:48
 * To change this template use File | Settings | File Templates.
 */
public class Code65_MaxInWindows {

    /**
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxInWindows(int[] nums, int k) {
        if (nums.length < k) return null;

        int[] res = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>(); //deque双端队列中存放数组元素下标

        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.getLast()])
                deque.removeLast();
            deque.addLast(i);
        }

        for (int i = k; i < nums.length; i++) {
            res[i - k] = nums[deque.getFirst()];

            while (!deque.isEmpty() && nums[i] >= nums[deque.getLast()])
                deque.removeLast();

            if (!deque.isEmpty() && deque.getFirst() <= i - k)
                deque.removeFirst();

            deque.addLast(i);
        }
        res[nums.length - k] = nums[deque.getFirst()];

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 4, 2, 6, 2, 5, 1};  //res is [4, 4, 6, 6, 6, 5]
        int k = 3;
        System.out.println(Arrays.toString(maxInWindows(nums, k)));
    }
}
