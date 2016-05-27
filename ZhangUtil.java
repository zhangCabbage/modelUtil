package zhang.algorithm.modelUtil;

import java.util.Arrays;

/**
 * Created by zhang_zack on 16/5/26.
 */
public class ZhangUtil {
    /**
     * 打印数组
     * @param array
     */
    public static void printArray(int[] array){
        System.out.println(Arrays.toString(array));
    }

    /**
     * 输出整数型矩阵
     * @param matrix
     */
    public static void printIntMatrix(int[][] matrix){
        System.out.println("-----matrix start-----");
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println("-----matrix end-----");
    }

    /**
     * 交换数组中两个数
     */
    public static void swap(int[] nums, int m, int n){
        int temp = nums[m];
        nums[m] = nums[n];
        nums[n] = temp;
    }

    public static void main(String[] args) {

    }
}
