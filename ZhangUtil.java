package zhang.algorithm.modelUtil;

import java.util.Arrays;

/**
 * Created by zhang_zack on 16/5/26.
 */
public class ZhangUtil {
    private static long startTime = 0;

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
        int i, j;
        for (i = start, j = end; i < j; i++, j--) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
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

    /**
     * 设置起始时间
     */
    public static void setStartTime() {
        startTime = System.currentTimeMillis();
    }

    /**
     * 获取中间间隔时间
     *
     * @return
     */
    public static long getIntervalTime() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {

    }
}
