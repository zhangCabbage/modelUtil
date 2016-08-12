package zhang.algorithm.modelUtil.NumberTheory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/8/2
 * Time: 上午9:42
 * To change this template use File | Settings | File Templates.
 * <p>
 * 因数分解
 */
public class Factorization {

    private int number;

    /**
     * 质因数的个数
     */

    private int counts;

    /**
     * 质因数数组
     */
    private List<Integer> primes;

    /**
     * 每个质因数的个数
     */
    private List<Integer> nums;

    /**
     * 因数个数
     */
    private int factorCount;

    /**
     * 因数数组, eg: 12的因数有:1、2、3、4、6、12
     */
    private List<Integer> factors;

    public int getNumber() {
        return number;
    }

    public int getCounts() {
        return counts;
    }

    public List<Integer> getPrimes() {
        return primes;
    }

    public List<Integer> getNums() {
        return nums;
    }

    public int getFactorCount() {
        return factorCount;
    }

    public List<Integer> getFactors() {
        return factors;
    }

    /**
     * 质因数分解!
     * 使用试除法来求数num的所有质因数
     * 这种试除法对 n < 2^10 的数还是挺有效的, 一旦更大的数, 复杂度将呈指数增长。
     *
     * @param num
     */
    public void findPrimeFactor(int num) {
        primes = new ArrayList<>();
        nums = new ArrayList<>();
        number = num;

        for (int i = 2; i <= (int) Math.sqrt(num); i++) {
            if (num % i == 0) {
                counts++;
                primes.add(i);

                int temp = 0;
                while (num % i == 0) {
                    temp++;
                    num = num / i;
                }
                nums.add(temp);
            }
        }
        if (num > 1) {
            counts++;
            primes.add(num);
            nums.add(1);
        }
    }

    /**
     * 用试除法找所有因数
     *
     * @param num
     */
    public void findFactor(int num) {
        factors = new ArrayList<>();

        factors.add(1);
        factors.add(num);
        factorCount += 2;

        int sqrt = (int) Math.sqrt(num);
        for (int i = 2; i <= sqrt; i++) {
            if (num % i == 0) {
                factors.add(i);
                factorCount++;

                if (i != sqrt) {
                    factors.add(num / i);
                    factorCount++;
                }
            }
        }

        Collections.sort(factors);
    }

    public static Factorization instance(int num) {
        Factorization factory = new Factorization();
        factory.findPrimeFactor(num);
        factory.findFactor(num);
        return factory;
    }

    public void showPrimeFactors() {
        StringBuffer sb = new StringBuffer();
        sb.append("Prime Factorization : " + number + " = ");
        for (int i = 0; i < counts; i++) {
            for (int j = 0; j < nums.get(i); j++) {
                sb.append(primes.get(i));
                if (j != nums.get(i) - 1) sb.append(" * ");
            }
            if (i != counts - 1) sb.append(" * ");
        }
        System.out.println(sb.toString());
    }

    public void showFactors() {
        StringBuffer sb = new StringBuffer();
        sb.append("the factor count of " + number + " is " + factorCount);
        sb.append(" --> ");
        for (int i = 0; i < factorCount; i++) {
            sb.append(factors.get(i));
            if (i != factorCount - 1) sb.append("、");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        Factorization test = instance(423);
        test.showPrimeFactors();
        test.showFactors();
    }
}
