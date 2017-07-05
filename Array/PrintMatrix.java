package zhang.algorithm.modelUtil.Array;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/7/5
 * Time: 下午8:57
 * To change this template use File | Settings | File Templates.
 */
public class PrintMatrix {

    public static ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (matrix == null || matrix.length < 1) return res;
        int m = matrix.length, n = matrix[0].length;

        for (int i = 0; i < (Math.min(m, n) + 1) / 2; i++) {
            clockwise(i, matrix, res);
        }
        return res;
    }

    /**
     * <i, j> 左上，<right, bottom>右下
     */
    private static void clockwise(int i, int[][] matrix, ArrayList<Integer> res) {
        int x = i, y = i;
        int bottom = matrix.length - i - 1, right = matrix[0].length - i - 1;
        //→
        for (int k = y; k <= right; k++) {
            res.add(matrix[x][k]);
        }
        //↓
        if (x < bottom) {
            for (int k = x + 1; k <= bottom; k++) {
                res.add(matrix[k][right]);
            }
        }
        //←
        if (x < bottom && y < right) {
            for (int k = right - 1; k >= y; k--) {
                res.add(matrix[bottom][k]);
            }
        }
        //↑
        if (x < bottom - 1 && y < right) {
            for (int k = bottom - 1; k > x; k--) {
                res.add(matrix[k][y]);
            }
        }
    }

    public static void main(String[] args) {
        int[][] nums = {{1, 2}, {3, 4}};
        System.out.println(printMatrix(nums));
    }
}
