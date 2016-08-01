package zhang.algorithm.modelUtil.Array;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/7/2
 * Time: 下午5:40
 * To change this template use File | Settings | File Templates.
 */
public class ArrayTool {

    /**
     * 打印数组
     *
     * @param array
     */
    public static void printArray(int[] array) {
        System.out.println(Arrays.toString(array));
    }


    /**
     * 输出整数型矩阵
     *
     * @param matrix
     */
    public static void printIntMatrix(int[][] matrix) {
        System.out.println("-----matrix start-----");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-----matrix end-----");
    }

    /**
     * 输出整数型矩阵
     *
     * @param matrix
     */
    public static void printIntMatrix(double[][] matrix) {
        System.out.println("-----matrix start-----");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-----matrix end-----");
    }

    /**
     * 输出字符型矩阵
     *
     * @param matrix
     */
    public static void printCharMatrix(char[][] matrix) {
        System.out.println("-----matrix start-----");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-----matrix end-----");
    }

    /**
     * 交换数组中两个数
     */
    public static void swap(int[] nums, int m, int n) {
        int temp = nums[m];
        nums[m] = nums[n];
        nums[n] = temp;
    }

    /**
     * 倒置一个数组起始下标为start，到结束下标为end期间的数
     *
     * @param array
     * @param start
     * @param end
     */
    public static void reverse(int[] array, int start, int end) {
        int i = start, j = end;
        while (i < j) {
            swap(array, i++, j--);
        }
    }

    public static long sum(int[] nums) {
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        return sum;
    }

    public static char[][] string2Char(String[] strs) {
        char[][] c = new char[strs.length][];
        int i = 0;
        for (String str : strs) {
            c[i++] = str.toCharArray();
        }
        return c;
    }

    /**
     * 倒置整个数组
     *
     * @param array
     */
    public static void reverse(int[] array) {
        reverse(array, 0, array.length - 1);
    }
}
