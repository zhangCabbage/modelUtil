package zhang.algorithm.modelUtil.Exercise.Examination.NetEase163;

import java.util.Scanner;

/**
 * Created by jiahua on 2017/9/25.
 */
public class Autumn9_25 {
    /**
     * @param in
     */
    public static void test1(Scanner in) {

    }

    /**
     * 针对范围较小的题目，在做笔试题时，能暴力尽量暴力。
     * 但是注意：前提还是要好好审题，千万不要找错思路！！
     *
     * @param in
     */
    public static void test2(Scanner in) {

    }

    /**
     * 有s份工作需要完成，需要分给3个人
     * 每份工作最少1个人来做，也可以2-3个人共同参与，求共有多少种分配方案，答案对1000000007取模
     * 输入：s a b c ( 1 <= s <= 50 )
     * 输出：x
     * eg:
     * -> 3 3 1 1
     * -> 9
     * <p>
     * 这道题我没有做出来，参考别人答案，使用暴力动态规划的方式
     *
     * @param in
     */
    public static void test3(Scanner in) {
        int s = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();

        int[][][][] dp = new int[s + 1][a + 1][b + 1][c + 1];
        System.out.println(solve(dp, s, a, b, c));
    }

    private static int solve(int[][][][] dp, int s, int a, int b, int c) {
        if (s == 0)
            if (a + b + c == 0) return 1;
            else return 0;
        if (s < 0) return 0;
        if (dp[s][a][b][c] != 0) return dp[s][a][b][c];

        int mod = 1000000007;
        if (a != 0) dp[s][a][b][c] = (dp[s][a][b][c] + solve(dp, s - 1, a - 1, b, c)) % mod;
        if (b != 0) dp[s][a][b][c] = (dp[s][a][b][c] + solve(dp, s - 1, a, b - 1, c)) % mod;
        if (c != 0) dp[s][a][b][c] = (dp[s][a][b][c] + solve(dp, s - 1, a, b, c - 1)) % mod;

        if (a != 0 && b != 0) dp[s][a][b][c] = (dp[s][a][b][c] + solve(dp, s - 1, a - 1, b - 1, c)) % mod;
        if (a != 0 && c != 0) dp[s][a][b][c] = (dp[s][a][b][c] + solve(dp, s - 1, a - 1, b, c - 1)) % mod;
        if (b != 0 && c != 0) dp[s][a][b][c] = (dp[s][a][b][c] + solve(dp, s - 1, a, b - 1, c - 1)) % mod;

        if (a != 0 && b != 0 && c != 0) dp[s][a][b][c] = (dp[s][a][b][c] + solve(dp, s - 1, a - 1, b - 1, c - 1)) % mod;

        return dp[s][a][b][c];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            test3(in);
        }
    }
}
