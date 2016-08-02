package zhang.algorithm.modelUtil.NumberTheory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/8/2
 * Time: 上午9:42
 * To change this template use File | Settings | File Templates.
 * <p>
 * 质因数分解
 */
public class PrimeFactorization {

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

    /**
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

    public static PrimeFactorization instance(int num) {
        PrimeFactorization factory = new PrimeFactorization();
        factory.findPrimeFactor(num);
        return factory;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Prime Factorization : " + number + " = ");
        for (int i = 0; i < counts; i++) {
            for (int j = 0; j < nums.get(i); j++) {
                sb.append(primes.get(i));
                if (j != nums.get(i) - 1) sb.append(" * ");
            }
            if (i != counts - 1) sb.append(" * ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        PrimeFactorization test = instance(423);
        System.out.println(test);
    }
}
