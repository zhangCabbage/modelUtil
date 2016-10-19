package zhang.algorithm.modelUtil.Exercise;

import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/8/6
 * Time: 下午11:10
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            for (int i = 0; i < n; i++) {
                if (isPrimeByMillerRabin(in.nextLong()))
                    System.out.println("Yes");
                else
                    System.out.println("No");
            }
        }
    }

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
}
