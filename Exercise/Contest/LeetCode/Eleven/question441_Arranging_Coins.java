package zhang.algorithm.modelUtil.Exercise.Contest.LeetCode.Eleven;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/10/30
 * Time: 上午11:46
 * To change this template use File | Settings | File Templates.
 * <p>
 * Deal
 */
public class question441_Arranging_Coins {

    /**
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

    /**
     * 直接解方程式: x * (x + 1)/2 = num
     * <p>
     * 1336 / 1336 test cases passed.
     * Status: Accepted
     * Runtime: 48 ms
     *
     * @param n
     * @return
     */
    public int arrangeCoins2(int n) {
        return (int) ((-1 + Math.sqrt(1 + 8 * (long) n)) / 2);
    }

    /**
     * 使用二分查找的方法
     * 1336 / 1336 test cases passed.
     * Status: Accepted
     * Runtime: 75 ms
     *
     * @param n
     * @return
     */
    public int arrangeCoins3(int n) {
        int start = 0;
        int end = n;
        //因为当只有两个数的时候(a, b), 那么mid只能是a, 会导致出错。
        while (start <= end) {
            int mid = start + (end - start >> 1);
            long total = (long) mid * (mid + 1) / 2;  //这里注意, 一定需要转型成long!!
            if (total > n) end = mid - 1;
            else if (total < n) start = mid + 1;
            else return mid;
        }
        return end;
    }

    public static void main(String[] args) {
        question441_Arranging_Coins test = new question441_Arranging_Coins();
        int n = 1804289383;
        System.out.println(test.arrangeCoins(n));
        System.out.println(test.arrangeCoins2(n));
        System.out.println(test.arrangeCoins3(n));
    }
}
