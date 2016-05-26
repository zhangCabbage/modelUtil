package zhang.algorithm.modelUtil;

/**
 * Created by zhang_zack on 16/5/26.
 */
public class ZhangUtil {
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
}
