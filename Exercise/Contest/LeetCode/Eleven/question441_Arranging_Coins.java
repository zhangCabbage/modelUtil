package zhang.algorithm.modelUtil.Exercise.Contest.LeetCode.Eleven;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/10/30
 * Time: 上午11:46
 * To change this template use File | Settings | File Templates.
 */
public class question441_Arranging_Coins {

    /**
     * 没有审查!!
     * <p>
     * 1336 / 1336 test cases passed.
     * Status: Accepted
     * Runtime: 55 ms
     *
     * @param n
     * @return
     */
    public int arrangeCoins(int n) {
        int count = 0;
        while (n > count) {
            count++;
            n -= count;
        }
        return count;
    }

    public static void main(String[] args) {
        question441_Arranging_Coins test = new question441_Arranging_Coins();
        int n = 19999;
        System.out.println(test.arrangeCoins(n));
    }
}
