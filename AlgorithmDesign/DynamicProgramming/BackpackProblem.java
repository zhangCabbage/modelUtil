package zhang.algorithm.modelUtil.AlgorithmDesign.DynamicProgramming;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_Lenovo
 * Date: 2016/6/24
 * Time: 15:47
 * To change this template use File | Settings | File Templates.
 * <p>
 * This class is to realize 01 backpack problem by dynamic programming.
 * 0-1背包问题符合最优子结构特征(最优解的子结构), 分析如下:
 * 对于i大小的背包, j个商品的问题,
 * 对最优解有: 当商品k在最优解中时, 原问题的最优解 = k商品的价值 + 除k重量后的背包在余下(j-1)个商品的最优解
 * 可以用反证法证明成立, 即原问题的最优解包含子问题的最优解, 否则的话可以替换获得更优解。
 * 不过如果如此随意的选择商品, 不方便我们进行问题分析求解。
 * 我们可以按下标顺序选择商品, 把问题归化为 V[i, j] = max(V[i - w[j], j-1] + v[j], V[i, j-1])
 * <p>
 * 注意:贪心算法和动态规划算法的区别, 对0-1背包问题只能使用动态规划, 而分数背包问题则使用贪心算法即可。
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
    public static int solve(int maxW, int[] weights, int[] values) {
        int[][] res = new int[maxW + 1][weights.length + 1];
        for (int i = 1; i <= maxW; i++) {
            //first traversal the capacity of the backpack
            for (int j = 1; j <= weights.length; j++) {
                //second traversal the number of the goods
                if (weights[j - 1] > i) {
                    res[i][j] = res[i][j - 1];
                } else {
                    res[i][j] = Math.max(res[i][j - 1], res[i - weights[j - 1]][j - 1] + values[j - 1]);
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
