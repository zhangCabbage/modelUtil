package zhang.algorithm.modelUtil.BitManipultion;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/8/16
 * Time: 上午9:34
 * To change this template use File | Settings | File Templates.
 */
public class XorRange {
    /**
     * left xor (left+1) xor (left+2)... (right-1) xor right
     *
     * @param left
     * @param right
     * @return
     */
    public long xorRange(long left, long right) {
        return xorRange(right) ^ (left > 1 ? xorRange(left - 1) : 0);
    }

    /**
     * calculation the xor from 0 to n
     * <p>
     * 00 --> 00000
     * 01 --> 00001
     * 02 --> 00010
     * 03 --> 00011  // 0
     * 04 --> 00100
     * 05 --> 00101
     * 06 --> 00110
     * 07 --> 00111  // 0
     * 08 --> 01000
     * 09 --> 01001
     * 10 --> 01010
     * 11 --> 01011  // 0
     * 12 --> 01100
     * 13 --> 01101
     * 14 --> 01110
     * 15 --> 01111
     * 16 --> 10000
     *
     * @param n
     * @return
     */
    public long xorRange(long n) {
        long res = 0;
        if (n % 4 == 1 || n % 4 == 2) res = 1;//special hand in first position

        long temP = 2;//first num in second position
        long temQ = 4;//second position cycle period(循环周期)
        while (n >= temP) {
            long t = n % temQ;
            t = t - temP + 1;//the number of 1
            if (t > 0 && t % 2 == 1) res |= temP;
            temP <<= 1;
            temQ <<= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        XorRange test = new XorRange();
        long left = 11;
        long right = 12;
        System.out.println(test.xorRange(left, right));
    }
}
