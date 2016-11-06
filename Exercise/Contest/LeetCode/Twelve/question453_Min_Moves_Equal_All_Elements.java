package zhang.algorithm.modelUtil.Exercise.Contest.LeetCode.Twelve;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/11/6
 * Time: 下午3:48
 * To change this template use File | Settings | File Templates.
 * <p>
 * 花费一个多小时才解决
 * (https://leetcode.com/contest/12/problems/minimum-moves-to-equal-array-elements/)
 */
public class question453_Min_Moves_Equal_All_Elements {
    /**
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        return helper2(nums, 0);
    }

    /**
     * 方法一:
     * 每次操作是, 找到最大的数, 对其余的数进行 +1 操作
     * eg: [1, 1, 2147483647]
     * Error --> java.lang.StackOverflowError
     *
     * @param nums
     * @param count
     * @return
     */
    private int helper(int[] nums, int count) {
        int max = 0;
        int total = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[max]) max = i;
            else if (nums[i] == nums[max]) total++;
        }
        if (total == nums.length) return count;

        for (int i = 0; i < nums.length; i++) {
            if (i != max) nums[i]++;
        }
        return helper(nums, ++count);
    }

    /**
     * 方法二:
     * 改进以上方法: 每次操作时, 找到最大最小值, 对其余数进行 +(max - min) 操作, 但是仍然超时!!
     * 当nums长度为 9999 的时候
     * Error --> Time Limit Exceeded
     *
     * @param nums
     * @param count
     * @return
     */
    private int helper2(int[] nums, int count) {
        int max = 0, min = 0;
        int total = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[max]) max = i;
            else if (nums[i] < nums[min]) min = i;
            else if (nums[i] == nums[max]) total++;
        }
        if (total == nums.length) return count;

        int dis = nums[max] - nums[min];
        for (int i = 0; i < nums.length; i++) {
            if (i != max) nums[i] += dis;
        }
        return helper2(nums, count + dis);
    }

    /**
     * 方法三:
     * 使用非递归的方式, 并且在找最大最小值时, 使用插入排序的方法, 减少不必要的遍历操作, 但是仍然超时!!
     * 当nums长度为 9999 的时候
     * Error --> Time Limit Exceeded
     *
     * @param nums
     * @return
     */
    public int minMoves2(int[] nums) {
        int res = 0;
        int n = nums.length;
        Arrays.sort(nums);

        while (nums[n - 1] != nums[0]) {
            //当最小值和最大值不相同的时候
            int dis = nums[n - 1] - nums[0];
            for (int i = 0; i < n - 1; i++) {
                nums[i] += dis;
            }
            res += dis;

            //前插最大的一个
            int max = nums[n - 1];
            int i = n - 2;
            while (i >= 0) {
                if (nums[i] > max) nums[i + 1] = nums[i--];
                else break;
            }
            nums[i + 1] = max;
        }

        return res;
    }

    /**
     * 方法四:
     * 灵光一闪, 想到这道题是不是可以用动态规划来做?
     * <p>
     * 84 / 84 test cases passed.
     * Status: Accepted
     * Runtime: 37 ms
     *
     * @param nums
     * @return
     */
    public int minMoves3(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;
        int step = 0;  //上一次到达 [all equal] 的状态所用的步骤数
        int finalNum = nums[0];  //上一次到达 [all equal] 的状态时, 最后的平衡相等数的值

        for (int i = 1; i < n; i++) {
            int tmp = finalNum;
            finalNum = nums[i] + step;
            if (finalNum == tmp) continue;   //attention!!
            step = finalNum - tmp + step;
        }

        return step;
    }


    public static void main(String[] args) {
        question453_Min_Moves_Equal_All_Elements test = new question453_Min_Moves_Equal_All_Elements();
        int[] nums = {15, 19, 21, 24, 29, 37, 70};
//        System.out.println(test.minMoves(nums));
//        System.out.println(test.minMoves2(nums));
        System.out.println(test.minMoves3(nums));
    }
}
