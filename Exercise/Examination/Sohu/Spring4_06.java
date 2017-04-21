package zhang.algorithm.modelUtil.Exercise.Examination.Sohu;

import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/4/7
 * Time: 上午10:11
 * To change this template use File | Settings | File Templates.
 */
public class Spring4_06 {
    /**
     * {1,2,3,4,5,6}集合可以构造多少个二叉排序树 --> 132
     *
     * @param n
     * @return
     */
    public static int numOfBST(int n) {
        if (n < 1) return 0;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            System.out.println(numOfBST(n));
        }
    }
}
