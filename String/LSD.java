package zhang.algorithm.modelUtil.String;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/11/11
 * Time: 下午5:34
 * To change this template use File | Settings | File Templates.
 * <p>
 * 1) [编辑距离算法(Edit Distance)], 又叫[Levenshtein Distance], 也叫[字符串相似度算法]
 * 是指两个字串之间，由一个转成另一个所需的最少编辑操作次数。
 * 编辑操作包括将一个字符替换成另一个字符，插入一个字符，删除一个字符
 * 2) [字符串相似度] = 1 - 它们的距离/两个字符串长度的最大值
 * <p>
 * 【关于解法】
 * http://itindex.net/detail/40637-%E5%AD%97%E7%AC%A6%E4%B8%B2-%E7%9B%B8%E4%BC%BC-%E7%AE%97%E6%B3%95
 * 只讲解了算法步骤及代码, 没有说明算法思想
 * <p>
 * http://www.dreamxu.com/books/dsa/dp/edit-distance.html
 * 属于动态规划的方式
 */
public class LSD {
    /**
     * @param s1
     * @param s2
     * @return
     */
    public static int levenshteinDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m + 1][n + 1];
        //init
        for (int i = 1; i <= m; i++) dp[i][0] = i;
        for (int i = 1; i <= n; i++) dp[0][i] = i;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int tmp = 0;
                if (s1.charAt(i - 1) != s2.charAt(j - 1)) tmp = 1;
                dp[i][j] = Math.min(dp[i - 1][j - 1] + tmp, Math.min(dp[i - 1][j], dp[i][j - 1]) + 1);
            }
        }

        return dp[m][n];
    }


    public static void main(String[] args) {
        String s1 = "intention";
        String s2 = "execution";
        System.out.println(levenshteinDistance(s1, s2));
    }
}
