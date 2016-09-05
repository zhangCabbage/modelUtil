package zhang.algorithm.modelUtil.AlgorithmDesign.DynamicProgramming;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/9/5
 * Time: 上午9:50
 * To change this template use File | Settings | File Templates.
 * <p>
 * [detail]:
 * 森林里有一排树，数量为n，第i颗树上有a[i]个香蕉（1 <= i <=n，a[i] >=0）。
 * 有一只猴子，一开始在第0颗树上（这棵树上没有香蕉）。这只猴子每次仅能向右跳[1，r]棵树，
 * 每次到一颗树上，他都会把树上的香蕉吃掉。
 * 猴子到达最后一颗树的时候跳跃次数必须小于等于k(k <= n)，求猴子最多能吃多少香蕉。（保证猴子k步能跳到终点）
 * <p>
 * [reference]:
 * http://toutiao.com/a6323546358032630017/?tt_from=weixin&utm_campaign=client_share&app=news_article&utm_source=weixin&iid=5184307697&utm_medium=toutiao_android&wxshare_count=1
 */
public class MaxInKStep {
    /**
     * we can easy to find that we'd better to use dynamic programming
     * 1) 定义 f[i][j] 表示到达第 i 位置, 用 j 次移动, 得到的 maxValue
     * 2) 划分子问题, 求到达第 i 个位置, 最大的值 maxValue
     * 3) 找出状态转移方程, 我们知道有 k 种可能到达 i 位置的方式, 则 maxValue = max{f[i-x][r-1] + nums[i]}
     * 4) 考虑边界条件, 求 f[n][k], 初始位置 f[0][0]
     *
     * @param nums
     * @param r    一次最多跳 1-r 距离
     * @param k    总共最多跳 k 次
     * @return
     */
    public int maxInKStep(int[] nums, int r, int k) {
        int[][] maxValue = new int[nums.length + 1][k + 1];

        //位置 i
        for (int i = 1; i < nums.length + 1; i++) {
            //最小步骤 j
            for (int j = i / r + ((i % r == 0) ? 0 : 1); j < k + 1; j++) {
                //步骤不能比距离还长
                if (i < j) break;

                for (int l = 1; l <= r; l++) {
                    if (i - l < 0) break;
                    maxValue[i][j] = Math.max(maxValue[i][j], maxValue[i - l][j - 1] + nums[i - 1]);
                }
            }
        }
        return maxValue[nums.length][k];
    }

    public static void main(String[] args) {
        MaxInKStep test = new MaxInKStep();
        int[] nums = {5, 3, 4, 7, 6, 9, 1, 2, 8};
        int r = 3;
        int k = 2;
        System.out.println(test.maxInKStep(nums, r, k));
    }
}
