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
        System.out.println("Max Integer --> " + Integer.toBinaryString(Integer.MAX_VALUE)); //1111111111111111111111111111111
        System.out.println("one number of Max is --> " + numOfOne(Integer.MAX_VALUE)); //31
        System.out.println("Min Integer --> " + Integer.toBinaryString(Integer.MIN_VALUE)); //10000000000000000000000000000000
        System.out.println("one number of Min is --> " + numOfOne(Integer.MIN_VALUE)); //1
        System.out.println("-1 --> " + Integer.toBinaryString(0)); //0
        System.out.println("-1 --> " + Integer.toBinaryString(-1)); //11111111111111111111111111111111
        System.out.println("one number of -1 is --> " + numOfOne(-1)); //32
    }

    /**
     * find the count of 1 in the binary number
     *
     * @param num
     * @return
     */
    public static int numOfOne(int num) {
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

    /**
     * this method reference the native way of java Int numberOfTrailingZeros
     * find the highest 1 bit in the binary num, no negative number
     * 求数二进制位数最高的位数, eg: 9 --> 1001, 返回3
     *
     * @param num
     * @return
     */
    public static int numOfHigh1Bit(int num) {
        num = Math.abs(num);
        if(num < 2) return 0;
        int temp, n = 0;
        temp = num >> 16; if(temp != 0){ n += 16; num = temp;}
        temp = num >> 8; if(temp != 0){ n += 8; num = temp;}
        temp = num >> 4; if(temp != 0){ n += 4; num = temp;}
        temp = num >> 2; if(temp != 0){ n += 2; num = temp;}
        return n + (num >> 1);
    }

    /**
     * 数num尾部有多少个0
     * 采用二分法
     *
     * @param num
     * @return
     */
    public static int numOfTailZeros(int num) {
//        Integer.numberOfTrailingZeros(num);
        if (num == 0) return 32;
        int temp, n = 31;
        temp = num << 16; if(temp != 0){ n -= 16; num = temp;}
        temp = num << 8; if(temp != 0){ n -= 8; num = temp;}
        temp = num << 4; if(temp != 0){ n -= 4; num = temp;}
        temp = num << 2; if(temp != 0){ n -= 2; num = temp;}
        return n - ((num << 1) >>> 31);
    }

    public static void main(String[] args) {
//        showBitInteger();
        System.out.println(numOfHigh1Bit(Integer.MAX_VALUE));//30
    }
}
