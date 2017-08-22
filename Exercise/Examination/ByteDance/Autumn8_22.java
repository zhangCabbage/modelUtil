package zhang.algorithm.modelUtil.Exercise.Examination.ByteDance;

import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_Lenovo
 * Date: 2017/8/22
 * Time: 21:56
 * To change this template use File | Settings | File Templates.
 */
public class Autumn8_22 {

    /**
     * 暴力。超时，O(n^3)不行, 30%
     *
     * @param in
     */
    public static void test2(Scanner in) {
        int n = in.nextInt();
        int[] nums = new int[n];
        long[] sum = new long[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
            if (i != 0)
                sum[i] += sum[i - 1];
            sum[i] += nums[i];
        }

        long max = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                long tmp = 1L * minArray(nums, i, j) * (sum[j] - sum[i] + nums[i]);
                if (tmp > max)
                    max = tmp;
            }
        }
        System.out.println(max);
    }

    public static int minArray(int[] nums, int i, int j) {
        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j; k++) {
            if (nums[k] < min)
                min = nums[k];
        }
        return min;
    }

    /**
     * 算法现在水平很差啊
     * 认真分析题目，这道题其实一个区间内最大的乘积就是，最小的那个数乘以区间和
     * 其实我们遍历数组，往两侧扩展直到小于当前数为止就行了！！
     *
     * @param in
     */
    public static void test2_2(Scanner in) {
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            int right = 0;
            for (int j = i + 1; j < n && nums[j] >= nums[i]; j++) right += nums[j];
            int left = 0;
            for (int j = i - 1; j >= 0 && nums[j] >= nums[i]; j--) left += nums[j];
            max = Math.max(max, (left + right + nums[i]) * nums[i]);
        }
        System.out.println(max);
    }

    /**
     * Main方法
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            test2(in);
        }
    }
}
