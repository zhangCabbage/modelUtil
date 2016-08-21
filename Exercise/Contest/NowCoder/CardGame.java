package zhang.algorithm.modelUtil.Exercise.Contest.NowCoder;

import zhang.algorithm.modelUtil.Array.ArrayTool;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/8/10
 * Time: 下午4:07
 * To change this template use File | Settings | File Templates.
 * <p>
 * [纸牌博弈](http://www.nowcoder.com/questionTerminal/7036f62c64ba4104a28deee98a6f53f6)
 * A、B依次从纸牌堆中拿纸牌, 每次只能拿走最左边或者最右边的纸牌, 求最后拿的纸牌上数和最多的
 */
public class CardGame {
    /**
     * this is the worry method
     * although I found the right thought, but do not solve this problem finally.
     *
     * @param A
     * @param n
     * @return
     */
    public int cardGame(int[] A, int n) {
        int[][] max = new int[n][n];//表示先选的情况下[i...j]能获得的分数
        int[][] min = new int[n][n];//表示后选的情况下[i...j]能获得的分数
        for (int i = 0; i < n; i++) {
            max[i][i] = A[i];
            min[i][i] = A[i];
        }

        for (int len = 1; len < n; len++) {
            ArrayTool.printIntMatrix(max, "max");
            ArrayTool.printIntMatrix(min, "min");
            for (int i = 0; i < n - len; i++) {
                if (len == 1) {
                    max[i][i + len] = Math.max(max[i][i], max[i + len][i + len]);
                    min[i][i + len] = Math.min(min[i][i], min[i + len][i + len]);
                    continue;
                }
                int rMax = Math.max(max[i][i + len - 1], min[i][i + len - 1] + A[i + len]);
                int rMin = Math.min(max[i][i + len - 1], min[i][i + len - 1] + A[i + len]);

                int lMax = Math.max(max[i + 1][i + len], min[i + 1][i + len] + A[i]);
                int lMin = Math.min(max[i + 1][i + len], min[i + 1][i + len] + A[i]);

                max[i][i + len] = Math.max(lMax, rMax);
                min[i][i + len] = Math.min(lMin, rMin);
            }
        }

        return max[0][n - 1];
    }

    /**
     * 这个解题思路跟我的还是有挺大区别的,虽然都是用两个数组来动态规划
     * 注意,这种形式循环的写法, 上三角动态规划递归
     *
     * @param A
     * @param n
     * @return
     */
    public int cardGame2(int[] A, int n) {
        //f[i][j]表示我先选的情况下[i...j]能获得的分数
        //s[i][j]表示我后选的情况下[i...j]能获得的分数
        int[][] f = new int[n][n];
        int[][] s = new int[n][n];
        for (int j = 0; j < n; j++) {
            //先选得到的分数f[i][i]即为arr[i],此处隐藏了s[i][i]=0,由于默认值就是零，所以可以省略
            f[j][j] = A[j];
            for (int i = j - 1; i >= 0; i--) {
                f[i][j] = Math.max(A[i] + s[i + 1][j], A[j] + s[i][j - 1]);
                s[i][j] = Math.min(f[i + 1][j], f[i][j - 1]);
            }
        }

        return Math.max(f[0][n - 1], s[0][n - 1]);
    }

    public static void main(String[] args) {
        CardGame test = new CardGame();
        int[] A = {26, 51, 91, 97, 28, 84};
        System.out.println(test.cardGame2(A, A.length));
    }
}
