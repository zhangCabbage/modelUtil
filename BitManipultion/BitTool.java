package zhang.algorithm.modelUtil.BitManipultion;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/7/14
 * Time: 上午10:17
 * To change this template use File | Settings | File Templates.
 * <p>
 * Review Time: 2017-03-02 11:43:24
 * <p>
 * 常见位操作
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
            num &= (num - 1);
        }
        return result;
    }

    public static void showBinary(int n) {
        System.out.println(Integer.toBinaryString(n));
    }

    public static void showBinary(long n) {
        System.out.println(Long.toBinaryString(n));
    }

    /**
     * this method reference the native way of java Int numberOfTrailingZeros
     * find the highest 1 bit in the binary num, no negative number
     * 求数二进制位数最高的位数, eg: 9 --> 1001, 返回3
     * <p>
     * The Java API: Integer.highestOneBit() is to return 2^highest
     * eg:
     * Integer.highestOneBit(9) = 8
     * Integer.highestOneBit(12) = 8
     *
     * @param num
     * @return
     */
    public static int numOfHigh1Bit(int num) {
        num = Math.abs(num);
        if (num == 0) return 0;

        int tmp, bit = 1;
        if ((tmp = num >> 16) != 0) {
            bit += 16;
            num = tmp;
        }
        if ((tmp = num >> 8) != 0) {
            bit += 8;
            num = tmp;
        }
        if ((tmp = num >> 4) != 0) {
            bit += 4;
            num = tmp;
        }
        if ((tmp = num >> 2) != 0) {
            bit += 2;
            num = tmp;
        }

        return bit + (num >> 1);
    }

    /**
     * The function of this function is same to Integer.highestOneBit()
     *
     * @param num
     * @return
     */
    public static int numOfHighestOne(int num) {
        int pre = 0;
        while (num != 0) {
            pre = num;
            num &= (num - 1);
        }
        return pre;
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
        int tmp, n = 31;
        if ((tmp = num << 16) != 0) {
            n -= 16;
            num = tmp;
        }
        if ((tmp = num << 8) != 0) {
            n -= 8;
            num = tmp;
        }
        if ((tmp = num << 4) != 0) {
            n -= 4;
            num = tmp;
        }
        if ((tmp = num << 2) != 0) {
            n -= 2;
            num = tmp;
        }
        return n - ((num << 1) >>> 31);
    }

    public static void main(String[] args) {
//        showBitInteger();
        System.out.println(numOfHigh1Bit(Integer.MAX_VALUE));//30
    }
}
