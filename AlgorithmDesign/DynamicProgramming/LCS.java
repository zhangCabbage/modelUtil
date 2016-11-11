package zhang.algorithm.modelUtil.AlgorithmDesign.DynamicProgramming;

import java.util.List;

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
 * TODO 未看完
 * 節省內存的方法 --> https://forgoal.gitbooks.io/-/content/longest_common_subsequencelcs.html
 * LCS <=> 2D LIS
 */
public class LCS {
    //---------------------------------------------------------------------
    //1、longest common subsequence ---- len
    //---------------------------------------------------------------------

    /**
     * 方法一:
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
     * 方法二:
     * 可以通過某些技巧只使用一個一維數組, [滚动数组]的形式来節省內存
     * 这种技巧在使用了二维数组的动态规划中, 都可以适用!!
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
     * LCS 轉化成 LIS 來求解
     * TODO
     *
     * @param s1
     * @param s2
     * @return
     */
    public static int lenOfLCS3(String s1, String s2) {
        return 12;
    }

    //---------------------------------------------------------------------
    //2、longest common subsequence ---- one string
    //---------------------------------------------------------------------

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

    //---------------------------------------------------------------------
    //3、longest common subsequence ---- all string
    //---------------------------------------------------------------------

    /**
     * 找出所有的 Longest Common Subsequence
     * TODO
     *
     * @param s1
     * @param s2
     * @return
     */
    public static List<String> allSubSeqOfLCS(String s1, String s2) {
        return null;
    }

    //---------------------------------------------------------------------
    //4、Longest Common Increasing Subsequence ---- lens
    //---------------------------------------------------------------------

    /**
     * lens of LCIS
     * Time Complexity - O(m*n)， Space Complexity - O(m*n)。
     *
     * @param s1
     * @param s2
     * @return
     */
    public static int lenOfLCIS(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m + 1][n + 1];
        //这里 dp[i][j] 表示字符串 s1(0-i) 和字符串 s2(0-j) 以 s2[j] 为结尾的 LCIS 的值
        // 1) 当s1[i] != s2[j] 时, 则有 dp[i][j] = dp[i-1][j]
        // 2) 当s1[i] == s2[j] 时, 那么先考虑字符串s2, 找其中最长的升序子串, 并且使得 <s2[j],
        //    这个思路可以参考LIS中dp的方法。而对于s1, 我们当然是要的串越多越好了。
        //    所以, 此时 dp[i][j] = max(dp[i-1][k]), 其中1 <= k <= j-1 && s2[k] < s2[j]
        //可以看出这是一个O(n^3)的动态规划方法, 如何进行优化呢?

        int max;
        //从循环的顺序着手, s1--行在前, s2--列在后, 每次顺序遍历计算dp[i][j]时, 顺便记录下 所有小于 s1[j] 的最大值
        for (int i = 1; i <= m; i++) {
            max = 0;  //优化所在!!!
            for (int j = 1; j <= n; j++) {
                char a = s1.charAt(i - 1);
                char b = s2.charAt(j - 1);

                dp[i][j] = dp[i - 1][j];  //when s1[i] != s2[j]
                if (a > b && max < dp[i - 1][j]) max = dp[i - 1][j];
                if (a == b) dp[i][j] = max + 1;
            }
        }

        max = 0;
        //因为dp[i][j]并不是所有s1(0-i) s2(0-j)的最大LCIS的值, 【而只是以s2[j]结尾的最大的LCIS】
        for (int i = 0; i <= n; i++)
            if (max < dp[m][i])
                max = dp[m][i];

        return max;
    }

    //---------------------------------------------------------------------
    //5、Longest Common Increasing Subsequence ---- one String
    //---------------------------------------------------------------------

    /**
     * TODO
     * 可以参考: http://www.geeksforgeeks.org/longest-common-increasing-subsequence-lcs-lis/
     *
     * @param s1
     * @param s2
     * @return
     */
    public static String subSeqOfLCIS(String s1, String s2) {
        return null;
    }

    public static void main(String[] args) {
        String s1 = "1234567";
        String s2 = "3247";
        System.out.println("len of lcs --> " + lenOfLCS(s1, s2));
        System.out.println("string of lcs --> " + subSeqOfLCS(s1, s2));
        System.out.println("len of lcis --> " + lenOfLCIS(s1, s2));
    }
}
