package zhang.algorithm.modelUtil.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/7/2
 * Time: 下午5:40
 * To change this template use File | Settings | File Templates.
 */
public class ArrayTool {
    //--------------------------------------------------------------
    //1\ handle
    //--------------------------------------------------------------

    /**
     * 交换数组中两个数
     */
    public static void swap(int[] nums, int m, int n) {
        int temp = nums[m];
        nums[m] = nums[n];
        nums[n] = temp;
    }

    public static void swap(char[] nums, int m, int n) {
        char temp = nums[m];
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

    public static void reverse(char[] array, int start, int end) {
        int i = start, j = end;
        while (i < j) {
            swap(array, i++, j--);
        }
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
     * 数组求和
     *
     * @param nums
     * @return
     */
    public static long sum(int[] nums) {
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        return sum;
    }

    /**
     * 重排序数组, 使得奇数在前, 偶数在后
     * 如果要求不使用额外的空间, 可以使用类似冒泡排序的思想
     * <p>
     * 但是如果是奇数位和偶数位的重排序, 就没法用冒泡排序的方式
     *
     * @param nums
     */
    public static void reorder(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            boolean flag = true; //未换序
            for (int j = nums.length - 1; j > i; j--) {
                if ((nums[j] & 1) == 1 && (nums[j - 1] & 1) == 0) {
                    swap(nums, j, j - 1);
                    flag = false;
                }
            }
            if (flag) return;
        }
    }


    //--------------------------------------------------------------
    //2\ transform
    //--------------------------------------------------------------

    /**
     * 把 int[] 数组转换成 list 集合
     * you can use the google library guava, Ints.asList(int...)
     *
     * @param nums      数组
     * @param left      起始下标, 包括left
     * @param right     结束下标, 包括right
     * @param isReverse 是否倒置, false不倒置, true倒置
     * @return
     */
    public static List<Integer> intToList(int[] nums, int left, int right, boolean isReverse) {
        if (left > right) return null;
        //方法一
        List<Integer> res = new ArrayList<>();
        int index = 0;
        for (int i = left; i <= right; i++) {
            res.add(index, nums[i]);
            index++;
            if (isReverse)
                index = 0;
        }

        //方法二
//        List<Integer> res = IntStream.range(left, right+1).boxed().collect(Collectors.toList());
//        if(isReverse){
//            Collections.reverse(res);
//        }
        return res;
    }

    public static List<Integer> intToList(int[] nums) {
        return intToList(nums, 0, nums.length - 1, false);
    }

    /**
     * string[] to char[][]
     *
     * @param strs
     * @return
     */
    public static char[][] string2Char(String[] strs) {
        char[][] c = new char[strs.length][];
        int i = 0;
        for (String str : strs) {
            c[i++] = str.toCharArray();
        }
        return c;
    }

    /**
     * string[] to int[]
     *
     * @param strs
     * @return
     */
    public static int[] string2Int(String[] strs) {
        if (strs == null || strs.length == 0) return null;
        int[] nums = new int[strs.length];
        for (int i = 0; i < nums.length; i++)
            nums[i] = Integer.parseInt(strs[i]);
        return nums;
    }


    //--------------------------------------------------------------
    //3\ print
    //--------------------------------------------------------------

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
        printIntMatrix(matrix, "");
    }

    /**
     * 输出整数型矩阵
     *
     * @param matrix
     */
    public static void printIntMatrix(int[][] matrix, String name) {
        System.out.println("matrix start --->" + name);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---> end");
    }

    /**
     * 输出整数型矩阵
     *
     * @param matrix
     */
    public static void printDoubleMatrix(double[][] matrix) {
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
     * 自己实现join操作
     *
     * @return
     */
    public static String join(Object[] array, String separator) {
        return join(array, separator, 0, array.length - 1);
    }

    public static String join(Object[] array, String separator, int start, int end) {
        if (array == null || start > end || end >= array.length) return null;
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) {
            if (i > start)
                sb.append(separator);
            sb.append(array[i]);
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        reorder(nums);
        System.out.println(Arrays.toString(nums));
    }

}
