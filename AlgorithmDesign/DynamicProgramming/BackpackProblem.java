package zhang.algorithm.modelUtil.AlgorithmDesign.DynamicProgramming;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_Lenovo
 * Date: 2016/6/24
 * Time: 15:47
 * To change this template use File | Settings | File Templates.
 *
 * This class is to realize 01 backpack problem by dynamic programming.
 */
public class BackpackProblem {
    /**
     * Deal with 01 backpack problem, and return the max profit in the fixed size backpack.
     *
     * @param maxW
     * @param weights
     * @param values
     * @return
     */
    public static int solve(int maxW, int[] weights, int[] values){
        int[][] res = new int[maxW+1][weights.length+1];
        for(int i=1; i<=maxW; i++){
            //first traversal the capacity of the backpack
            for(int j=1; j<=weights.length; j++){
                //second traversal the number of the goods
                if(weights[j-1] > i){
                    res[i][j] = res[i][j-1];
                }else{
                    res[i][j] = Math.max(res[i][j-1], res[i-weights[j-1]][j-1]+values[j-1]);
                }
            }
        }
        return res[maxW][weights.length];
    }

    public static void main(String[] args) {
        int maxW = 10;
        int[] weights = {3, 4, 5};
        int[] valus = {4, 5, 6};
        System.out.println(solve(maxW, weights, valus));
    }
}
