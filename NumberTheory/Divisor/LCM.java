package zhang.algorithm.modelUtil.NumberTheory.Divisor;

import zhang.algorithm.modelUtil.Array.ArrayTool;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/8/1
 * Time: 下午4:29
 * To change this template use File | Settings | File Templates.
 * <p>
 * 求数的最小公倍数 (Least Common Multiple)
 */
public class LCM {
    /**
     * 利用最大公约数求最小公倍数
     * a、b的最大公约数即:a*b/gcd(a, b)
     *
     * @param a
     * @param b
     * @return
     */
    public static int lcm(int a, int b) {
        return a / GCD.EuclidGCD(a, b) * b;
    }

    /**
     * 求一个数组的最小公倍数
     *
     * @param nums
     * @return
     */
    public static int lcm(int[] nums) {
        return lcm(nums, 0, nums.length - 1);
    }

    public static int lcm(int[] nums, int start, int end) {
        if (start == end) return nums[start];
        return lcm(nums[start], lcm(nums, start + 1, end));
    }


    public static void main(String[] args) {
        // 10天干 :
        // 12地支 : 与12个时辰、12生肖对应
        // 10天干和12地支同时回到初始状态需要60年,所以一甲子为60年
        System.out.println(lcm(12, 10));
        int[] nums = {1, 2, 6, 18, 54, 81};
        System.out.println("数组nums的LCM --> " + lcm(nums));
        System.out.println("数组nums的Sum --> " + ArrayTool.sum(nums));
    }
}
