package zhang.algorithm.modelUtil.Exercise.AgainTrain;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/8/10
 * Time: 下午8:01
 * To change this template use File | Settings | File Templates.
 */
public class OptimalBinaryTree {

    public void optimalBST(double[] p, int num) {
        double[][] e = new double[num + 2][num + 2];
        double[][] w = new double[num + 1][num + 1];

        int[][] root = new int[num + 1][num + 1];

        for (int d = 0; d < num; d++) {
            for (int i = 1; i <= num - d; i++) {
                int j = i + d;

                e[i][j] = Double.MAX_VALUE;
                w[i][j] = w[i][j - 1] + p[j];

                for (int k = i; k <= j; k++) {
                    double temp = e[i][k - 1] + w[i][j] + e[k + 1][j];
                    if (temp < e[i][j]) {
                        e[i][j] = temp;
                        root[i][j] = k;
                    }
                }
            }
        }
    }

    public void optimalBST(double[] p, double[] q, int num) {
        double[][] e = new double[num + 2][num + 2];
        double[][] w = new double[num + 2][num + 2];

        for (int i = 1; i < num + 2; i++) {
            w[i][i - 1] = q[i - 1];
            e[i][i - 1] = q[i - 1];
        }
        int[][] root = new int[num + 1][num + 1];

        for (int d = 0; d < num; d++) {
            for (int i = 1; i <= num; i++) {
                int j = i + d;
                w[i][j] = w[i][j - 1] + p[j] + q[j];
                for (int k = i; k <= j; k++) {
                    double temp = e[i][k - 1] + w[i][j] + e[k + 1][j];
                    if (temp < e[i][j]) {
                        e[i][j] = temp;
                        root[i][j] = k;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        OptimalBinaryTree test = new OptimalBinaryTree();
    }
}
