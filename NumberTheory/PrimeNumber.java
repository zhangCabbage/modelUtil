package zhang.algorithm.modelUtil.NumberTheory;

import java.util.ArrayList;
import java.util.List;

/**
 * 求素数(Prime Number) <br/>
 * 素数的概念：只能被1和本身整除，最小的素数为2.
 *
 * @author zhang_zack
 */
public class PrimeNumber {
    /**
     * 给定一个数，求2-number中所有的素数, 包括number此数 <br/>
     * 使用筛选法求number以内的所有素数，从小到大的筛选 <br/>
     * this place has some optimize in leetcode question204_Count_Primes
     *
     * @param number
     */
    public static List<Integer> getTotalPrime(int number) {
        List<Integer> ans = new ArrayList<>();
        boolean[] flag = new boolean[number + 1];
        int sqrt = (int) Math.sqrt(number);

        for (int i = 2; i < number + 1; i++) {
            if (!flag[i]) {
                ans.add(i);
                if (i > sqrt) continue;
                int k = i;
                while (k * i <= number) {
                    flag[k * i] = true;
                    k++;
                }
            }
        }

        return ans;
    }

    //----------------------------------------------------------
    // use to judge whether a number is prime number
    // normal has three way to solve this problem
    // 1) 试除法, 时间复杂度 O(n^0.5)
    // 2) 素数筛选法, 时间复杂度略高于 O(n)
    // 3) Miller rabin
    //
    // [【素数判定Miller_Rabin 算法详解】](http://blog.csdn.net/maxichu/article/details/45458569)
    //----------------------------------------------------------

    /**
     * judge a numer is a prime number by using filter method
     * while judging a number low 2^10, it can be run very well,
     * but if current number is bigger, it time complexity is exponentially(指数级的)
     *
     * @param number
     * @return
     */
    public static boolean isPrimeByFilter(int number) {
        if (number == 2) return true;
        if (number < 2 || number % 2 == 0) return false;

        boolean[] flag = new boolean[number + 1];
        int sqrt = (int) Math.sqrt(number);

        for (int i = 2; i <= sqrt; i++) {
            if (!flag[i]) {
                int k = i;
                while (k * i <= number) {
                    flag[k * i] = true;
                    k++;
                }
                if (flag[number]) return false;
            }
        }
        return true;
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
            long a = Random.random(2, n - 1);
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

        long x = MathOperation.fastPowMod(a, r, n);
        if (x == 1 || x == n - 1) return true;//满足①or②。
        for (int i = 1; i < j; i++) {
            x = MathOperation.fastMultiMod(x, 2, n);
            if (x == n - 1) return true;//满足②
        }
        return false;
    }

    public static void main(String[] args) {
        int n = 1000000111;
        System.out.println(isPrimeByFilter(n));
        System.out.println(isPrimeByMillerRabin(n));
    }
}
