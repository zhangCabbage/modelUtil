package zhang.algorithm.modelUtil.Exercise.POJ;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/8/1
 * Time: 下午4:17
 * To change this template use File | Settings | File Templates.
 */
public class ExtracurricularSports {
    /**
     * [【C16D:Extracurricular Sports】](http://poj.openjudge.cn/practice/C16D?lang=en_US)
     * reference: (http://www.cnblogs.com/flipped/p/5698919.html)
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        BigInteger[] a = new BigInteger[200];
        a[0] = BigInteger.valueOf(1);
        a[1] = BigInteger.valueOf(2);
        for (int i = 2; i < 200; i++) {
            a[i] = a[i - 1].multiply(BigInteger.valueOf(3));
        }

        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int num = in.nextInt();
            if (num == 2) System.out.println(-1);
            else {
                for (int j = 0; j < num - 1; j++) {
                    System.out.println(a[j]);
                }
                System.out.println(a[num - 1].divide(BigInteger.valueOf(2)));
            }
        }
    }
}