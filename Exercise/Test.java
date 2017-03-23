package zhang.algorithm.modelUtil.Exercise;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/8/6
 * Time: 下午11:10
 * To change this template use File | Settings | File Templates.
 */
public class Test {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNext()) {
//            int n = in.nextInt();
//            for (int i = 0; i < n; i++) {
//                if (isPrimeByMillerRabin(in.nextLong()))
//                    System.out.println("Yes");
//                else
//                    System.out.println("No");
//            }
//        }
//    }

    /**
     * this is probability method, but more faster
     *
     * @param n
     * @return
     */
    public static boolean isPrimeByMillerRabin(long n) {
        if (n == 2) return true;
        if (n < 2 || n % 2 == 0) return false;

        int times = 40;
        for (int i = 0; i < times; i++) {
            long a = (long) (Math.random() * (n - 2)) + 2;
            if (!witness(a, n))
                return false;
        }

        return true;
    }

    /**
     * use to judge n whether a prime number
     * if n is a prime, return true, or return false.
     * <p>
     * reference : http://www.cnblogs.com/TenosDoIt/p/3398112.html
     *
     * @param a
     * @param n
     * @return
     */
    private static boolean witness(long a, long n) {
        long r = n - 1;
        int j = 0;
        while (r % 2 == 0) {
            r /= 2;
            j++;
        }

        long x = fastPowMod(a, r, n);
        if (x == 1 || x == n - 1) return true;//满足①or②。
        for (int i = 1; i < j; i++) {
            x = fastMultiMod(x, 2, n);
            if (x == n - 1) return true;//满足②
        }
        return false;
    }

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
     * 给一个数n, 其范围是2 - 10^18, 求 n 是否为 p^q, 其中p为素数, q > 1
     * 如果不是, 输出No; 如果是, 输出p和q
     *
     * @param in
     */
    private static void one(Scanner in) {
        long n = in.nextLong();
        long sqrt = (long) Math.sqrt(n);

        long tmp = n;
        while (sqrt * sqrt == tmp) {
            tmp = sqrt;
            sqrt = (long) Math.sqrt(tmp);
        }

        long p = tmp, q = 0;
        for (int i = 2; i <= sqrt; i++) {
            if (tmp % i == 0) {
                p = i;
                break;
            }
        }

        //tmp is a prime num
        tmp = 1;
        while (tmp < n) {
            tmp *= p;
            q++;
        }

        if (tmp > n || q == 1) System.out.println("No");
        else System.out.println(p + " " + q);
    }

    /**
     * 给两个数n和l, 求是否存在大于或等于l个连续非负整数和为n。
     * 如果不止一种情况, 则输出连续非负整数个数最少,同时大于等于l的这一组
     * 少考虑一种情况, 当l就为100时
     *
     * @param in
     */
    private static void two(Scanner in) {
        int n = in.nextInt();
        int l = in.nextInt();

        int end = n / l + l - 1; //这里从end = n / l 改为 n / l + l - 1, 测试用例通过率80% -> 90%
        int size = l;
        int sum = (end - size + 1 + end) * size / 2;
        while (sum != n) {
            if (end - size < 0) break;  //注意, 这里可能很需要这个0, 继续往下运行, 去掉最大一个数的结果
            if (sum > n) {
                sum = sum + (end - size) - end;
                end--;
            } else {
                //sum < n
                sum += (end - size);
                size++;
                if (size > 100) break;
            }
        }
        if (sum != n || size > 100) System.out.println("No");
        else {
            for (int i = end - size + 1; i < end; i++) {
                System.out.print(i + " ");
            }
            System.out.println(end);
        }
    }

    /**
     * 80%
     *
     * @param in
     */
    private static void three(Scanner in) {
        int L = in.nextInt();
        int R = in.nextInt();


        while (L > 2 * Math.PI * R) {
            L -= 2 * Math.PI * R;
        }

        double p = 180 * L / (Math.PI * R);
        p = (p / 180) * Math.PI;

        DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance();
        format.applyPattern("0.000");
        double x = R * Math.cos(p);
        double y = R * Math.sin(p);

        System.out.println(format.format(x) + " " + format.format(-y));
        System.out.println(format.format(x) + " " + format.format(y));
    }

    /**
     * 60%
     *
     * @param in
     */
    private static void four(Scanner in) {
        int n = in.nextInt();
        int[] dp = new int[n + 1];
        int[] maps = new int[10];

        for (int i = 1; i <= n; i++) {
            int num = in.nextInt();
            if (maps[num] == 0) {
                maps[num] = 1;
                if (i == 1) dp[i] = 1;
                else dp[i] = 2 * dp[i - 1] % 1000000007;
            } else {
                dp[i] = 3 * dp[i - 1] / 2 % 1000000007;
            }
        }

        System.out.println(dp[n]);
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {//注意while处理多个case
            four(in);
        }
    }
}
