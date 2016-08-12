package zhang.algorithm.modelUtil.NumberTheory;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/8/2
 * Time: 上午9:37
 * To change this template use File | Settings | File Templates.
 */
public class GcdAndLcm {
    /**
     * 通过给定 gcd 和 lcm 求出一个(x, y), 使得gcd(x, y) = gcd, lcm(x, y) = lcm
     *
     * @param gcd
     * @param lcm
     * @return
     */
    public static int[] findNumsByGCDAndLCM(int gcd, int lcm) {
        if (lcm % gcd != 0) return null;

        int[] res = new int[2];
        res[0] = gcd;
        res[1] = lcm;

        return res;
    }

    /**
     * find the count of pair numbers (x, y) that gcd(x, y) = gcd, lcm(x, y) = lcm
     *
     * @param gcd
     * @param lcm
     * @return
     */
    public static int findNumCountsByGCDAndLCM(int gcd, int lcm) {
        if (lcm % gcd != 0) return 0;
        Factorization factor = Factorization.instance(lcm / gcd);
        return (int) Math.pow(2, factor.getCounts());
    }

    public static void main(String[] args) {
        int gcd = 15;
        int lcm = 255;
        System.out.println(findNumCountsByGCDAndLCM(gcd, lcm));
    }
}
