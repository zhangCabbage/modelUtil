package zhang.algorithm.modelUtil.Exercise.Contest.LeetCode.Contest_12_11;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/12/11
 * Time: 下午12:01
 * To change this template use File | Settings | File Templates.
 * <p>
 * 不知道这道题最后以什么情况结束，应该是没有回顾。
 * 8/15号重新进行回顾。
 */
public class question474_Ones_and_Zeroes {
    /**
     * 这道题可以用动态规划,或者直接使用递归
     * <p>
     * 但是我的做法是错误的！注意，每个01只能使用一次。
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

        for (int k = 0; k < strs.length; k++) {
            // 遍历数组
            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    if (i == 0 && j == 0) continue;
                    if (i >= zeros[k] && j >= ones[k]) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - zeros[k]][j - ones[k]] + 1);
                    }
                }
            }
        }
        return dp[m][n];
    }


    //======================================================================================
    // 正确的方法
    //======================================================================================

    /**
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm2(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String s : strs) {
            int[] count = count(s);
            for (int i = m; i >= count[0]; i--)
                for (int j = n; j >= count[1]; j--)
                    dp[i][j] = Math.max(1 + dp[i - count[0]][j - count[1]], dp[i][j]);
        }
        return dp[m][n];
    }

    public int[] count(String str) {
        int[] res = new int[2];
        for (int i = 0; i < str.length(); i++)
            res[str.charAt(i) - '0']++;
        return res;
    }

    public static void main(String[] args) {
        question474_Ones_and_Zeroes test = new question474_Ones_and_Zeroes();
        String[] strs = {"10", "0001", "111001", "1", "0"};
        int m = 5, n = 3;
        System.out.println(test.findMaxForm2(strs, m, n));
    }
}
