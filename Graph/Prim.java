package zhang.algorithm.modelUtil.Graph;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_Lenovo
 * Date: 2016/6/28
 * Time: 9:46
 * To change this template use File | Settings | File Templates.
 * <p>
 * This is belong Greedy method.
 * The main important thing is to master(掌握) how to do deal with code below scene
 * <p>
 * 选取满足条件i∈S，j ∈ V-S，且c[i][j]最小的边
 * 将顶点j添加到S中选取满足条件i∈S，j ∈ V-S，且c[i][j]最小的边，将顶点j添加到S中
 * <p>
 * handle between two set.
 * The way is same with Dijkstra, use a data construct like visited array to make the two
 * set different. and also use a weight array to express the cost to arrive other node in
 * the graph.
 */
public class Prim {
    /**
     * @param matrix
     */
    public static void prim(double[][] matrix) {
        int num = matrix.length;
        double[] lowcost = new double[num];
        int[] closest = new int[num];
        double minSum = 0;

        //init the lowcost and closest array
        for (int i = 1; i < num; i++) {
            lowcost[i] = matrix[0][i];
            closest[i] = 0;
        }

        for (int i = 1; i < num; i++) {

            double min = Double.MAX_VALUE;
            int minIndex = 0;
            //find the node that make min value cost
            for (int k = 0; k < num; k++) {
                if (lowcost[k] != 0 && lowcost[k] < min) {
                    min = lowcost[k];
                    minIndex = k;
                }
            }
            //let minIndex lowcost equal 0, is mean the minIndex node is visited!
            lowcost[minIndex] = 0;
            minSum += min;

            //update the lowcost value array
            for (int j = 0; j < num; j++) {
                if (lowcost[j] != 0 && matrix[minIndex][j] < lowcost[j]) {
                    lowcost[j] = matrix[minIndex][j];
                    closest[j] = minIndex;
                }
            }
        }

        System.out.println("----minimum spanning tree----");
        System.out.println("min sum is --> " + minSum);
        for (int i = 1; i < num; i++) {
            System.out.println("<" + closest[i] + ", " + i + "> --> " + matrix[closest[i]][i]);
        }
        System.out.println("----minimum spanning tree----");
    }

    public static void main(String[] args) {
        double MAX = Double.MAX_VALUE;
        double[][] matrix = {
                {0,10,MAX,MAX,MAX,11,MAX,MAX,MAX},
                {10,0,18,MAX,MAX,MAX,16,MAX,12},
                {MAX,MAX,0,22,MAX,MAX,MAX,MAX,8},
                {MAX,MAX,22,0,20,MAX,MAX,16,21},
                {MAX,MAX,MAX,20,0,26,MAX,7,MAX},
                {11,MAX,MAX,MAX,26,0,17,MAX,MAX},
                {MAX,16,MAX,MAX,MAX,17,0,19,MAX},
                {MAX,MAX,MAX,16,7,MAX,19,0,MAX},
                {MAX,12,8,21,MAX,MAX,MAX,MAX,0}
        };
        prim(matrix);
    }
}
