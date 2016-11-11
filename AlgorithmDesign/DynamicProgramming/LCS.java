package zhang.algorithm.modelUtil.AlgorithmDesign.DynamicProgramming;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/11/10
 * Time: 下午10:33
 * To change this template use File | Settings | File Templates.
 * <p>
 * Longest Common Subsequence
 * eg:
 * s1: 2 5 7 9 3 1 2
 * s2: 3 5 3 2 8
 * <p>
 * then the LCS is 532
 * <p>
 * 解釋參見:
 * 基本解法 --> http://www.csie.ntnu.edu.tw/~u91029/LongestCommonSubsequence.html
 * 節省內存的方法 --> https://forgoal.gitbooks.io/-/content/longest_common_subsequencelcs.html
 */
public class LCS {

    /**
     * 通過動態規劃的方式, 求得longest common subsequence
     * 如何存儲動態規劃中的數據?
     * ==>
     * array[i][j] 代表著 s1 的第一個到第 i 個元素所形成的 sequence 以及 s2 第一個到第 j 個元素所形成的 sequence,
     * 這兩個 sequence 的 LCS 長度.
     * <p>
     * 普通方法使用二維數組來輔助求解
     *
     * @param s1
     * @param s2
     * @return
     */
    public static int lenOfLCS(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[m][n];
    }

    /**
     * 可以通過某些技巧只使用一個一維數組, 節省內存
     *
     * @param s1
     * @param s2
     * @return
     */
    public static int lenOfLCS2(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[] dp = new int[n + 1];
        int lt = 0;   //means that left top number

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                int top = dp[j];  //top number
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[j] = lt + 1;
                else
                    dp[j] = Math.max(top, dp[j - 1]);

                lt = top;
            }
            lt = 0;
        }

        return dp[n];
    }

    /**
     * 找出并返回一個 Longest Common Subsequence
     *
     * @param s1
     * @param s2
     * @return
     */
    public static String subSeqOfLCS(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        int[][] pre = new int[m + 1][n + 1]; //用來存儲每一格的結果從哪來

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    pre[i][j] = 0; //左上
                } else {
                    if (dp[i - 1][j] > dp[i][j - 1]) {
                        dp[i][j] = dp[i - 1][j];
                        pre[i][j] = 1;  //上
                    } else {
                        dp[i][j] = dp[i][j - 1];
                        pre[i][j] = 2;  //左
                    }
                }

            }
        }

        StringBuffer sb = new StringBuffer();
        int x = m, y = n;
        int index = 0;
        while (index < dp[m][n]) {
            int num = pre[x][y];
            if (num == 0) {
                sb.append(s1.charAt(x - 1));
                index++;
            }
            if (num != 1) y--;
            if (num != 2) x--;
        }

        return sb.reverse().toString();
    }


    public static void main(String[] args) {
        LCS test = new LCS();
        String s1 = "1234567";
        String s2 = "3247";
        System.out.println(lenOfLCS(s1, s2));
        System.out.println(subSeqOfLCS(s1, s2));
    }
}
