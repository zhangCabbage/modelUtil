package zhang.algorithm.modelUtil.NumberTheory;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/8/5
 * Time: 下午8:10
 * To change this template use File | Settings | File Templates.
 * <p>
 * uva 10791 - Minimum Sum LCM
 */
public class MinimumSumLCM {

    /**
     * 求几个最小公倍数为n的数的最小和
     *
     * @param n
     * @return
     */
    public long minimumSumLcm(int n) {
        long sum = 0;

        int temp = n;
        int sqrt = (int) Math.sqrt(n);
        int primeNum = 0;

        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0) {
                primeNum++;
                int num = 1;
                while (n % i == 0) {
                    num *= i;
                    n /= i;
                }
                sum += num;
            }
        }
        if (n == temp)
            sum += 1;
        else if (primeNum == 1 || n != 1)
            sum += n;

        return sum;
    }

    public static void main(String[] args) {
        MinimumSumLCM test = new MinimumSumLCM();
        int n = 49;
        System.out.println(test.minimumSumLcm(n));
    }
}
