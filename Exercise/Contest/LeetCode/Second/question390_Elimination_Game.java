package zhang.algorithm.modelUtil.Exercise.Contest.LeetCode.Second;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/8/28
 * Time: 下午9:58
 * To change this template use File | Settings | File Templates.
 */
public class question390_Elimination_Game {
    /**
     * but this way Memory Limit Exceeded when n = 100000000
     *
     * @param n
     * @return
     */
    public int lastRemaining(int n) {
        if (n == 1) return 1;
        if (n < 6) return 2;

        int[] res = new int[n + 1];
        res[1] = 1;
        for (int i = 2; i < 6; i++) {
            res[i] = 2;
        }
        for (int i = 6; i <= n; i++) {
            if (i % 2 != 0) res[i] = res[i - 1];
            res[i] = 2 * (i / 2 + 1 - res[i / 2]);
        }

        return res[n];
    }

    /**
     * so I change in this way to solve this problem. Basically same way
     * <p>
     * <strong>result of test:</strong>
     * 3377 / 3377 test cases passed
     * Status: Accepted
     * Runtime: 105 ms
     *
     * @param n
     * @return
     */
    public int lastRemaining2(int n) {
        if (n == 1) return 1;
        if (n < 6) return 2;
        if (n % 2 != 0) return lastRemaining2(n - 1);
        return 2 * (n / 2 + 1 - lastRemaining2(n / 2));
    }

    public static void main(String[] args) {
        question390_Elimination_Game test = new question390_Elimination_Game();
    }
}
