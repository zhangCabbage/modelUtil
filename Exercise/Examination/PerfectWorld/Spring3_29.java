package zhang.algorithm.modelUtil.Exercise.Examination.PerfectWorld;

import zhang.algorithm.modelUtil.Array.ArrayTool;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/3/29
 * Time: 下午9:14
 * To change this template use File | Settings | File Templates.
 * <p>
 * 完美世界2018年春招笔试
 * [经验教训]:
 * 1) 第一道题有问题, 当还有一定时间的时候觉察不对劲, 应该继续解决其他问题, 这一点做的还可以。
 * 2) 对动态规划问题, 最近一直没有复习。需要加紧复习!!
 * -> 写01背包问题的时候, 在weights[i - 1]一直出问题, 以后要注意: 非weights[i], 要-1
 */
public class Spring3_29 {
    /**
     * 1)  -->  0%
     * 给一个数组, 表示n种水果的价值, 每种水果个数不限; 再给一个礼物价值, 求用最少个数等价的水果来换取礼物
     * 有这种方式的话, 那么返回最少苹果个数, 没有的话, 返回-1
     *
     * @param in
     */
    public static void fun1(Scanner in) {
        String[] strs = in.next().split(",");
        int[] nums = new int[strs.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(strs[i]);
        }
        int value = in.nextInt();

        Arrays.sort(nums);
        ArrayTool.reverse(nums);

        System.out.println(recursive(nums, 0, value));
    }


    public static int recursive(int[] nums, int start, int value) {
        if (value == 0) return 0;
        int res = -1;
        for (int i = start; i < nums.length; i++) {
            if (nums[i] == 0) break;
            if (nums[i] > value) continue;
            int cnt = 1;
            int tmp = recursive(nums, i, value - nums[i]);
            if (tmp < 0) continue;
            else return cnt + tmp;
        }

        return res;
    }
    //-------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------

    /**
     * 2)  -->  100%
     * 01背包问题
     *
     * @param in
     */
    public static void fun2(Scanner in) {
        int n = in.nextInt();
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = in.nextInt();
        }
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = in.nextInt();
        }
        int total = in.nextInt();

        int[][] dp = new int[n + 1][total + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= total; j++) {
                if (j >= weights[i - 1])  //这里调试出错过一段时间
                    dp[i][j] = Math.max(dp[i - 1][j - weights[i - 1]] + values[i - 1], dp[i - 1][j]);
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        System.out.println(dp[n][total]);
    }

    //-------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            fun2(in);
        }
    }
}
