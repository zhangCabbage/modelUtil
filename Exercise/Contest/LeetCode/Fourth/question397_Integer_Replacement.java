package zhang.algorithm.modelUtil.Exercise.Contest.LeetCode.Fourth;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/9/11
 * Time: 上午10:55
 * To change this template use File | Settings | File Templates.
 */
public class question397_Integer_Replacement {
    /**
     * why this method is wrong!!!!
     * when input is 100_000_000
     * the reason is below.
     *
     * @param n
     * @return
     */
    public int integerReplacement(int n) {
        int count = 0;
        while (n != 1) {
            if (n % 2 == 0) {
                n /= 2;
            } else if ((n & 7) == 7) {
                n += 1;
            } else {
                n -= 1;
            }
            count++;
        }
        return count;
    }

    /**
     * the right answer
     *
     * @param n
     * @return
     */
    public int integerReplacement2(int n) {
        long N = n;
        long small, big;
        int cnt = 0;
        while (N != 1) {
            small = (N & (N - 1));
            big = (N & (N + 1));
            if ((N & 1) == 0) {
                N >>= 1;
            } else if ((small & (small - 1)) <= (big & (big - 1))) {
                N = N - 1;
            } else {
                N = N + 1;
            }
            cnt++;
        }
        return cnt;
    }

    //----------------------------------------------------------------------
    //101111101011110000100000000
    //101111101011110000100000000
    //
    //10111110101111000010000000
    //10111110101111000010000000
    //
    //1011111010111100001000000
    //1011111010111100001000000
    //
    //101111101011110000100000
    //101111101011110000100000
    //
    //10111110101111000010000
    //10111110101111000010000
    //
    //1011111010111100001000
    //1011111010111100001000
    //
    //101111101011110000100
    //101111101011110000100
    //
    //10111110101111000010
    //10111110101111000010
    //
    //1011111010111100001
    //1011111010111100001
    //
    //1011111010111100000
    //1011111010111100000
    //
    //101111101011110000
    //101111101011110000
    //
    //10111110101111000
    //10111110101111000
    //
    //1011111010111100
    //1011111010111100
    //
    //101111101011110
    //101111101011110
    //
    //10111110101111
    //10111110101111
    //
    //10111110110000
    //10111110110000
    //
    //1011111011000
    //1011111011000
    //
    //101111101100
    //101111101100
    //
    //10111110110
    //10111110110
    //
    //1011111011  763-1
    //1011111011  763+1, 当二进制尾数1的个数少于3时,有时也需要+1
    //-----------> 在这个地方起了变化
    //1011111010
    //1011111100
    //
    //101111101
    //101111110
    //
    //101111100
    //10111111
    //
    //10111110
    //11000000
    //
    //1011111
    //1100000
    //
    //1100000
    //110000
    //
    //110000
    //11000
    //
    //11000
    //1100
    //
    //1100
    //110
    //
    //110
    //11
    //
    //11
    //10
    //
    //10
    //1
    //----------------------------------------------------------------------

    /**
     * method one that +1 or -1 by judge the (N & 2) == 2
     * 47 / 47 test cases passed
     * Status: Accepted
     * Runtime: 3 ms
     *
     * @param n
     * @return
     */
    public int integerReplacement3(int n) {
        long N = n;
        int cnt = 0;
        while (N != 1) {
            if ((N & 1) == 0) {
                N >>= 1;
            } else {
                if (N == 3) {
                    cnt += 2;
                    break;
                }
                N = (N & 2) == 2 ? N + 1 : N - 1;
            }
            cnt++;
        }

        return cnt;
    }

    /**
     * method two that use the recursive way
     * 47 / 47 test cases passed
     * Status: Accepted
     * Runtime: 8 ms
     *
     * @param n
     * @return
     */
    public int integerReplacement4(int n) {
        return recursive(n);
    }

    public int recursive(long n) {
        if (n == 1) return 0;  //the condition to stop.
        if ((n & 1) == 0) {
            return recursive(n >> 1) + 1;
        } else {
            return Math.min(recursive(n + 1), recursive(n - 1)) + 1;
        }
    }

    /**
     * use divide by 2 to mark a step,
     * 39 -> 40 -> 20
     * 39 -> 38 -> 19 -> 20 or 18
     * 39 -> 19/20 only use two step.
     * the same method as integerReplacement3
     * <p>
     * 47 / 47 test cases passed
     * Status: Accepted
     * Runtime: 4 ms
     * <p>
     * reference:
     * https://discuss.leetcode.com/topic/58733/java-4ms-iterative-greedy-explained-in-detail
     *
     * @param n
     * @return
     */
    public int integerReplacement5(int n) {
        int cnt = 0;
        while (n != 1) {
            cnt++;
            if ((n & 1) != 0) {
                cnt++;
                n >>= 1;
                if (n != 1 && (n & 1) != 0) n++; //this step actually to deal with the 3 outlier.
            } else {
                n >>= 1;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        question397_Integer_Replacement test = new question397_Integer_Replacement();
        int n = 100_000_000; //31 ?
//        System.out.println(test.integerReplacement(n));
        System.out.println(test.integerReplacement2(n));
        System.out.println(test.integerReplacement3(n));
        System.out.println(test.integerReplacement4(n));
        System.out.println(test.integerReplacement5(n));
    }
}
