package zhang.algorithm.modelUtil.NumberTheory;

/**
 * 求两个数的最大公约数 (Greatest Common Divisor)
 *
 * @author zhang_zack
 */
public class GCD {
    /**
     * 最大公约数
     */
    public static int gcd;
    /**
     * x的倍数
     */
    public static int x;
    /**
     * y的倍数
     */
    public static int y;

    /**
     * 使用牛顿法求x和y的最大公约数
     * Euclid  欧几里得的辗转相除算法求最大公约数，也就是当前这道题的做法
     * 关于欧几里得辗转法的证明：
     * <p>
     * 命题：对任意 m, n ∈ N，证明gcd(m,n) = gcd(n, m mod n)
     * 证明：
     * 令 k=gcd(m,n),则 k|m 并且 k|n;
     * 令 j=gcd(n, m mod n), 则j|n 并且 j|(m mod n);
     * 对于m, 可以用n 表示为 m=pn+(m mod n);
     * 由引理可知 j|m（其中 x=p,y=1）, 又 j|n，于是 j 是 m 和 n 的公约数（但不一定是最大的）;
     * 因为 k 是 m 和 n 的最大公约数，所以必有 k≥j;
     * 通过另一种表示形式：(m mod n)=m-pn,同理可得：
     * k|(m mod n),又k|n，于是 k 是 (m mod n) 和 n 的公约数（也不一定是最大的）;
     * 同样由 j 是 n 和 (m mod n) 的最大公约数可以得到 j≥k;
     * 由常识，得出结论 k=j,
     * 即gcd(m,n) = gcd(n, m mod n) ，得证。
     * <p>
     * <p>
     * 参考：[【最大公约数(gcd):Euclid算法证明及其它】](http://www.cnblogs.com/ider/archive/2010/11/16/gcd_euclid.html)
     *
     * @param x
     * @param y
     * @return
     */
    public static int EuclidGCD(int x, int y) {
        int bigger = Math.max(x, y);
        int smaller = Math.min(x, y);
        if (smaller <= 0) {
            return 0;
        }
        while (smaller > 0) {
            int temp = smaller;
            smaller = bigger % smaller;
            bigger = temp;
        }
        return bigger;
    }

    /**
     * 扩展的欧几里得算法，用来求 a*x + b*y = gcd，有多少整数解。
     * 把欧几里得算法算一遍之后，从后往前倒回去，加和便是扩展欧几里得最后的结果。
     * <p>
     * 一定要思路清晰！！可以把递归过程用笔在本子上推导一遍，然后在码程序。
     *
     * @return
     */
    public static void ExtEuclidGCD(int a, int b) {
        if (b == 0) {
            gcd = a;
            x = 1;
            y = 0;
        } else {
            ExtEuclidGCD(b, a % b);
            int temp = x;
            x = y;
            y = temp - y * (a / b);
        }
    }

    public static void main(String[] args) {
//        int a = 47;
//        int b = 30;
        int a = 15;
        int b = 11;
        System.out.println(EuclidGCD(a, b));
        System.out.println("Extend Euclid GCD");
        ExtEuclidGCD(a, b);
        System.out.println("a*x+b*y = gcd(a, b) ==> " + a + " * " + x + " + " + b + " * " + y + " = " + gcd);
    }
}
