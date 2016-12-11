package zhang.algorithm.modelUtil.Exercise.Contest.LeetCode.Contest_12_11;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/12/11
 * Time: 下午12:01
 * To change this template use File | Settings | File Templates.
 */
public class question474_Ones_and_Zeroes {
    /**
     * 这道题可以用动态规划,或者直接使用递归
     *
     * @param strs
     * @param m    --> 0
     * @param n    --> 1
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];

        int[] zeros = new int[strs.length];
        int[] ones = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            int zero = 0;
            int one = 0;
            for (char c : strs[i].toCharArray()) {
                if (c == '0') zero++;
                else if (c == '1') one++;
            }
            zeros[i] = zero;
            ones[i] = one;
        }

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 && j == 0) continue;
                for (int k = 0; k < strs.length; k++) {
                    if (i >= zeros[k] && j >= ones[k]) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - zeros[k]][j - ones[k]] + 1);
                    }
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        question474_Ones_and_Zeroes test = new question474_Ones_and_Zeroes();
        String[] strs = {"10", "0001", "111001", "1", "0"};
        int m = 5, n = 3;
        System.out.println(test.findMaxForm(strs, m, n));
    }
}
