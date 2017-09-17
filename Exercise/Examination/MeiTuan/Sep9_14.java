package zhang.algorithm.modelUtil.Exercise.Examination.MeiTuan;

import zhang.algorithm.modelUtil.NumberTheory.MathTools;

import java.util.Scanner;

/**
 * Created by jiahua on 2017/9/17.
 */
public class Sep9_14 {
    /**
     * 给n个数，他们随机组合的数能被7整除的个数
     * 暴力70%
     *
     * @param in
     */
    public static void test1(Scanner in) {
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                long tmp = Long.parseLong(nums[i] + "" + nums[j]);
                if (tmp % 7 == 0) cnt++;
            }
        }
        System.out.println(cnt);
    }

    /**
     * 输入：
     * 3
     * 127 1996 12
     * 返回：4 (1996-127;  12-1996; 127-12; 1996-12)
     *
     * @param in
     */
    public static void test1_2(Scanner in) {
        int n = in.nextInt();
        int[] nums = new int[n];
        int[][] bit_flag = new int[11][7];

        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
            bit_flag[MathTools.decimalBit(nums[i])][nums[i] % 7]++;
        }

        for (int i = 0; i < bit_flag.length; i++) {
            for (int j = 0; j < bit_flag[i].length; j++) {

            }
        }

        int cnt = 0;


        System.out.println(cnt);
    }

    /**
     * 一个0-1数组：0为关，1为开，
     * 两个人做游戏，每次需要把某一位右边所有的灯全部关掉，最后谁第一个把灯全部关掉谁赢
     * 思路：最后一位为1，那么第一人赢；否则第二人赢。
     *
     * @param in
     */
    public static void test2(Scanner in) {

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            test1(in);
        }
    }
}
