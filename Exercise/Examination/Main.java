package zhang.algorithm.modelUtil.Exercise.Examination;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/3/31
 * Time: 上午10:32
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println();
        StringBuffer sb = new StringBuffer();
        sb.append("Hello World");

        System.out.println(Integer.toBinaryString(-3));
        System.out.println(Power(2, -3));


    }

    public static double Power(double base, int exponent) {
        //方法一
        //return Math.pow(base, exponent);
        int n = Math.abs(exponent);
        double res = 1;
        while (n != 0) {
            if ((n & 1) == 1)
                res *= base;
            base *= base;
            n >>>= 1;
        }
        return exponent < 0 ? 1 / res : res;
    }

}


