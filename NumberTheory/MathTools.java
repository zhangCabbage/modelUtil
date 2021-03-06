package zhang.algorithm.modelUtil.NumberTheory;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/8/6
 * Time: 下午8:20
 * To change this template use File | Settings | File Templates.
 * <p>
 * 加减乘除的实现
 */
public class MathTools {

    /**
     * fast calculate the pow of a and b, result is a^b.
     * Time complexity from O(n) to O(logn)
     * <p>
     * 整个过程就相当于指数进行一次log(N)级别的变换
     * 详细解说,以及后续[矩阵快速幂]的问题请参见
     * (http://blog.csdn.net/alps1992/article/details/42131581)
     *
     * @param a
     * @param b
     * @return
     */
    public static long fastPow(int a, int b) {
        long ans = 1;
        while (b > 0) {
            if ((b & 1) == 1)
                ans *= a;

            a *= a;
            b >>= 1;
        }

        return ans;
    }

    /**
     * fast calculate (a*b) % mod
     *
     * @param a
     * @param b
     * @param mod
     * @return
     */
    public static long fastMultiMod(long a, long b, long mod) {
        long ans = 0;
        while (b > 0) {
            if ((b & 1) == 1)
                ans = (ans + a) % mod;

            a = (a + a) % mod;
            b >>= 1;
        }

        return ans;
    }

    /**
     * fast calculate (a^b) % mod
     *
     * @param a
     * @param b
     * @param mod
     * @return
     */
    public static long fastPowMod(long a, long b, long mod) {
        long ans = 1;
        while (b > 0) {
            if ((b & 1) == 1)
                ans = fastMultiMod(ans, a, mod);

            b >>= 1;
            a = fastMultiMod(a, a, mod);
        }

        return ans;
    }

    /**
     * 特殊情况:
     * 1) 超界问题, return 0
     * 2) 50/500 -> 5
     *
     * @param num
     * @return
     */
    public static int reverseInt(int num) {
        long res = 0;
        while (num != 0) {
            res = res * 10 + num % 10;
            if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) return 0;
            num /= 10;
        }
        return (int) res;
    }


    /**
     * @param x
     * @return
     */
    public static int sumBitNum(int x) {
        x = Math.abs(x);
        int sum = 0;
        while (x != 0) {
            sum += x % 10;
            x /= 10;
        }
        return sum;
    }

    public static int sumBitNums(int... nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += sumBitNum(nums[i]);
        }

        return sum;
    }

    /**
     * 实现一个解析字符串表达式的函数, 其中只有+、-、*、\/四则运算, 并对除法进行截断取整
     * 参见: 227 Basic Calculator II
     *
     * @param str
     * @return
     */
    public int calculate(String str) {

        return 0;
    }

    /**
     * 比较first和second两个整数
     * if first < second, return -1
     * if first > second, return 1
     * if first = second, return 0
     *
     * @param first
     * @param second
     * @return
     */
    public static int intCompare(int first, int second) {
        return first < second ? -1 : (first > second ? 1 : 0);
    }

    /**
     * 十进制数字num有多少位
     * 0 - 9  ->  1
     * 10- 99 ->  2
     *
     * @param num
     * @return
     */
    public static int decimalBit(int num) {
        if (num == Integer.MIN_VALUE || num == Integer.MAX_VALUE) return 10;
        long n = Math.abs(num);
        if (n < 10) return 1;

        int l = 1, r = 10;

        while (l <= r) {
            int mid = l + (r - l >> 1);
            if (judgeBit(n, mid)) return mid;
            else if (n < Math.pow(10, mid - 1)) r = mid - 1;
            else l = mid + 1;
        }
        return l;
    }

    private static boolean judgeBit(long n, int x) {
        long tmp = (long) Math.pow(10, x - 1);
        if (n >= tmp && n / tmp < 10) return true;
        return false;
    }

    public static void main(String[] args) {
//        System.out.println(fastPow(3, 5));
//        System.out.println(fastMultiMod(3, 0, 7));
//        System.out.println(fastPowMod(3, 3, 7));
//        System.out.println(reverseInt(-10));
        System.out.println(decimalBit(-2147483647));
    }
}
