package zhang.algorithm.modelUtil.Exercise.Contest.HihoCode;

import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/8/7
 * Time: 下午1:35
 * To change this template use File | Settings | File Templates.
 * <p>
 * 给定一组数据,给定一个值,判断能否从数组中选出几个数之和不小于给定值,并且最小
 * 这道题最开始的时候感觉和01背包问题很像,但是没能解出
 * <p>
 * 原题见 : (http://hihocoder.com/contest/hihointerview16/problem/1)
 */
public class MinMaxSum {
    public int minMax = Integer.MAX_VALUE;

    /**
     * this is my first way.
     *
     * @param nums
     * @param max
     * @param sum
     * @param cur
     */
    public void minMax(int[] nums, int max, int sum, int cur) {
        if (cur == -1 || sum >= max) {
            if (sum >= max) {
                minMax = Math.min(minMax, sum);
            } else {
                minMax = (minMax == Integer.MAX_VALUE) ? -1 : minMax;
            }
            return;
        }
        for (int i = cur; i >= 0; i--) {
            minMax(nums, max, sum + nums[i], i - 1);
        }
    }

    /**
     * use dynamic programming, this is ok !!
     *
     * @param nums
     * @param max
     * @return
     */
    public int minMax(int[] nums, int max) {
        boolean[] flag = new boolean[max + 1];
        int min = Integer.MAX_VALUE;
        flag[0] = true;
        for (int i = 0; i < nums.length; i++) {
            for (int k = flag.length - 1; k >= 0; k--) {
                //这里一定要从后往前,要不然当前循环中可能后面有才加的flag==true,影响程序运行
                if (flag[k]) {
                    if (nums[i] + k >= max) {
                        min = Math.min(min, nums[i] + k);
                    } else {
                        flag[nums[i] + k] = true;
                    }
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            int[] nums = new int[n];

            int max = in.nextInt();
            for (int i = 0; i < n; i++) {
                nums[i] = in.nextInt();
            }

            MinMaxSum test = new MinMaxSum();
//            Arrays.sort(nums);
//            test.minMax(nums, max, 0, nums.length - 1);
//            System.out.println(test.minMax);
            System.out.println(test.minMax(nums, max));
        }

    }
}
