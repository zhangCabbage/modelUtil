package zhang.algorithm.modelUtil.AlgorithmDesign.DynamicProgramming;

import zhang.algorithm.modelUtil.ZhangUtil;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/1/6
 * Time: 下午5:02
 * To change this template use File | Settings | File Templates.
 * <p>
 * 算法导论, 动态规划 - 钢条切割问题
 */
public class CuttingSteel {
    /**
     * time complexity is O(n^2)
     *
     * @param n len of steel
     */
    public static int cutting1(int n) {
        int[] values = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        int[] dp = new int[n + 1];

        for (int i = 1; i < dp.length; i++) {
            if (i < values.length)
                dp[i] = values[i];
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], dp[j] + dp[i - j]);
            }
        }

//        System.out.println(dp[n]);
        return dp[n];
    }


    /**
     * 优化改进钢条切割问题
     * Time complexity is almost O(n)
     *
     * @param n len of steel
     */
    public static int cutting2(int n) {
        int[] values = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        int[] dp = new int[n + 1];

        for (int i = 1; i < dp.length; i++) {
            if (i < values.length)
                dp[i] = values[i];
            for (int j = 1; j < Math.min(11, i); j++) {
                dp[i] = Math.max(dp[i], dp[j] + dp[i - j]);
            }
        }

//        System.out.println(dp[n]);
        return dp[n];
    }


    public static void main(String[] args) {
        //[Test 1] -- 方法二的正确性
//        for (int i = 0; i < 1000; i++) {
//            if (cutting1(i) != cutting1(i))
//                System.out.println("Error...");
//        }

        //[Test 2] -- 方法一与方法二的差距
        int n = 100000;
        ZhangUtil.setStartTime();
        System.out.println("cutting1 res --> " + cutting1(n));
        System.out.println("cutting1 time --> " + ZhangUtil.getIntervalTime() + " ms");
        System.out.println("cutting2 res --> " + cutting2(n));
        System.out.println("cutting2 time --> " + ZhangUtil.getIntervalTime() + " ms");
//        cutting1 res --> 300000
//        cutting1 time --> 3991 ms
//        cutting2 res --> 300000
//        cutting2 time --> 9 ms
    }
}
