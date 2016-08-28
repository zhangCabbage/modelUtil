package zhang.algorithm.modelUtil.NumberTheory;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/8/6
 * Time: 下午9:48
 * To change this template use File | Settings | File Templates.
 */
public class Random {
    /**
     * Math.random() --> [0, 1)生成 start
     * @param start
     * @param end
     * @return
     */
    public static long random(long start, long end) {
        return (long) (Math.random() * (end - start + 1)) + start;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            System.out.print(random(1, 9) + "、");
        }
    }
}
