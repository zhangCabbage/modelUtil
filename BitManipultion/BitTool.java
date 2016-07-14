package zhang.algorithm.modelUtil.BitManipultion;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/7/14
 * Time: 上午10:17
 * To change this template use File | Settings | File Templates.
 */
public class BitTool {
    /**
     * 二进制整数
     * 00000000 - 01111111为正数
     * 10000001 - 11111111为负数,以10000000为最大负数
     */
    public static void showBitInteger() {
        System.out.println("Max Integer --> " + Integer.toBinaryString(Integer.MAX_VALUE));
        System.out.println("one number of Max is --> " + numOne(Integer.MAX_VALUE));
        System.out.println("Min Integer --> " + Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println("one number of Min is --> " + numOne(Integer.MIN_VALUE));
    }

    public static int numOne(int num) {
        int result = 0;
        while (num != 0) {
            result++;
            num = num & (num - 1);
        }
        return result;
    }

    public static void showBinary(int n) {
        System.out.println(Integer.toBinaryString(n));
    }

    public static void main(String[] args) {
        showBitInteger();
    }
}
