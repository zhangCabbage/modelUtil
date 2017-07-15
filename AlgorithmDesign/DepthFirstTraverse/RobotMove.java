package zhang.algorithm.modelUtil.AlgorithmDesign.DepthFirstTraverse;

import zhang.algorithm.modelUtil.NumberTheory.MathTools;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/7/15
 * Time: 下午12:33
 * To change this template use File | Settings | File Templates.
 */
public class RobotMove {

    public static void main(String[] args) {
        RobotMove test = new RobotMove();
    }

    /**
     * 注意 这里机器人的移动, 如果有被不能经过的小方格围住的能遍历的小方格, 那么被围住的也不能被遍历
     *
     * @param threshold
     * @param rows
     * @param cols
     * @return
     */
    public int movingCount(int threshold, int rows, int cols) {
        int cnt = 0;
        boolean[][] map = new boolean[rows][cols];
        cnt = movingCount(0, 0, map, threshold);
        return cnt;
    }

    private int movingCount(int i, int j, boolean[][] map, int threshold) {
        int cnt = 0;
        if (isOK(i, j, map) && canGo(i, j, threshold)) {
            map[i][j] = true;
            cnt = 1 + movingCount(i - 1, j, map, threshold)
                    + movingCount(i + 1, j, map, threshold)
                    + movingCount(i, j - 1, map, threshold)
                    + movingCount(i, j + 1, map, threshold);
        }
        return cnt;
    }

    private boolean isOK(int i, int j, boolean[][] map) {
        if (i < 0 || j < 0 || i >= map.length || j >= map[0].length || map[i][j]) return false;
        return true;
    }

    private boolean canGo(int i, int j, int threshold) {
        if (MathTools.sumBitNums(i, j) <= threshold) return true;
        return false;
    }
}
