package zhang.algorithm.modelUtil.Exercise.Examination.BaiDu;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_Lenovo
 * Date: 2017/9/27
 * Time: 21:11
 * To change this template use File | Settings | File Templates.
 */
public class Autumn9_27 {

    /**
     * 一个字符串，求其最大字典序子串
     * test -> tt
     * aabaa -> baa
     * aba -> ba
     *
     * @param in
     */
    public static void test1(Scanner in) {
        char[] s = in.next().toCharArray();
        char[] tmp = new char[s.length];
        Arrays.fill(tmp, (char) ('a' - 1));
        int k = 0;
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j <= k; j++) {
                if (s[i] <= tmp[j]) {
                    if (j == k && k != tmp.length - 1) {
                        k++;
                        tmp[k] = 'a' - 1;
                    }
                    continue;
                } else {
                    tmp[j] = s[i];
                    k = j;
                }
            }
        }
        System.out.println(String.valueOf(tmp, 0, k + 1));
    }

    //------------------------------------------------------------------------------------------

    /**
     * f(n) = a7*n^7 + a6*n^6 + a5*n^5 + a4*n^4 + a3*n^3 + a2*n^2 + a1*n + a0
     * 求给的k组中，合并的集合第k小的数
     *
     * @param in
     */
    public static void test2(Scanner in) {
        int k = in.nextInt();
        int[][] ai = new int[k][8];
        int[] index = new int[k];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < 8; j++) {
                ai[i][j] = in.nextInt();
            }
        }
        int n = in.nextInt();
        long res = 0;

        while (n-- > 0)
            res = compare(index, ai);
        System.out.println(res);
    }

    public static long compare(int[] index, int[][] ai) {
        long min = Long.MAX_VALUE;
        int k = 0;
        for (int i = 0; i < index.length; i++) {
            long tmp = fun(index[i] + 1, ai[i]);
            if (tmp < min) {
                min = tmp;
                k = i;
            }
        }
        index[k]++;

        return min;
    }

    public static long fun(int x, int[] ai) {
        long tmp = 1;
        long res = ai[ai.length - 1];
        for (int i = ai.length - 2; i >= 0; i--) {
            tmp = tmp * x;
            res += ai[i] * tmp;
        }

        return res;
    }

    //------------------------------------------------------------------------------------------

    /**
     * 给a\b\c\d 4个分子，每个ab有单原子分子，cd双原子分子，
     * 给出abcd中原子质量，和化合物x的质量，求使用最多的分子组合化合物的分子个数
     *
     * @param in
     */
    public static void test3(Scanner in) {
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt() * 2;
        int d = in.nextInt() * 2;
        int x = in.nextInt();
        int[] dp = new int[x + 1];
        dp[a] = 1;
        dp[b] = 1;
        dp[c] = 1;
        dp[d] = 1;
        for (int i = 2; i < dp.length; i++) {
            for (int j = 1; j < i; j++) {
                if (dp[j] != 0 && dp[i - j] != 0 && (dp[i] == 0 || dp[j] + dp[i - j] > dp[i])) {
                    dp[i] = dp[j] + dp[i - j];
                }
            }
        }

        System.out.println(dp[x]);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            test3(in);
        }
    }
}
