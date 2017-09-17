package zhang.algorithm.modelUtil.Exercise.Examination.JD;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by jiahua on 2017/9/8.
 */
public class Autumn9_8 {
    /**
     * 一个序列：1，2，2，3，3，3 数字K出现K次，求第N项是多少
     *
     * @param in
     */
    public static void test1(Scanner in) {
        int x = in.nextInt();
        double tmp = Math.sqrt(2 * x + 0.25) - 0.5;
        int n = (int) Math.ceil(tmp);
        System.out.println(n);
    }

    /**
     * 给一个数n, 求存在多少组 a^b = c^d，并且 1 <= a, b, c, d <= n
     * eg: n = 2, 那么存在6组, 如下所示
     * 1^1 = 1^1
     * 1^1 = 1^2
     * 1^2 = 1^1
     * 1^2 = 1^2
     * 2^1 = 2^1
     * 2^2 = 2^2
     *
     * 对于一个2^12，我们来算其有多少对应的同等式
     * 2^12 = (2^1)^12、(2^2)^6、(2^3)^4、(2^4)^3、(2^6)^2、(2^12)^1
     * 相等于算12有多少个因子，这里需要加一条：2^x <= n
     * ？？？
     *
     * @param in
     */
    public static void test2(Scanner in) {
        int n = in.nextInt();
        int mod = Integer.MAX_VALUE;
        long res = n * n % mod;

        int[] factors = new int[n + 1];
        Arrays.fill(factors, 1);
        for (int i = 2; i <= n; i++) {
            int k = 1;
            while (k * i <= n && Math.pow(i, k * i) <= n) {
                factors[k * i]++;
                k++;
            }
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                // i ^ j 有多少等式
                res += factors[j];
                res %= mod;
            }
        }
        System.out.println(res);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            test2(in);
        }
    }
}
