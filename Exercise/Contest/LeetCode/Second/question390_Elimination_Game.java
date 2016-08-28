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

    /**
     * another way to follow the problem mean(https://discuss.leetcode.com/topic/55870/share-my-solutions-for-contest-2/4)
     * <p>
     * Next observation is that you can calculate the start number of next round in constant time. How?
     * You know how many numbers you are going to skip, which is n / 2, and you know the starting number, and you know the step size.
     * Original: [1, 2, 3, 4, 5, 6, 7, 8, 9], start = 1, step = 2, skipped = n / 2 = 4;
     * Round 1: [ , 2, , 4, , 6, , 8, ], start = 1 + step * skipped - step / 2 = 8, step = 4, skipped = 2;
     * Round 2: [ , 2, , , , 6, , , ], start = 8 - step * skipped + step / 2 = 2, step = 8, skipped = 1;
     * Round 3: [ , , , , , 6, , , ], start = 2 + step * skipped - step / 2 = 6, step = 16, skipped = 0;\
     * Round 4: because skipped == 0, end of iteration, return start, which is 6.
     *
     * @param n
     * @return
     */
    public int lastRemaining3(int n) {
        int remaining = n;
        int start = 1;
        int step = 2;
        boolean isLeft = true;
        while (remaining > 1) {
            remaining /= 2;
            if (isLeft) {
                start = start + remaining * step - step / 2;
            } else {
                start = start - remaining * step + step / 2;
            }
            step *= 2;
            isLeft = isLeft ? false : true;
        }
        return start;
    }

    public static void main(String[] args) {
        question390_Elimination_Game test = new question390_Elimination_Game();
    }
}
