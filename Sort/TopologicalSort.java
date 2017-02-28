package zhang.algorithm.modelUtil.Sort;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/7/26
 * Time: 下午11:13
 * To change this template use File | Settings | File Templates.
 * 实现拓扑排序
 */
public class TopologicalSort {
    /**
     * main函数中matrix实例,引用[http://blog.csdn.net/dm_vincent/article/details/7714519]中的图
     *
     * @param args
     */
    public static void main(String[] args) {
        int[][] matrix = {
                {0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        System.out.println(sort1(matrix));
        System.out.println(sort2(matrix));
        //两种方法结果不同, 但是都正确
        //2 -> 8 -> 0 -> 3 -> 7 -> 1 -> 5 -> 6 -> 4 -> 9 -> 10 -> 11 -> 12 ->
        //8 -> 7 -> 2 -> 3 -> 0 -> 6 -> 9 -> 11 -> 12 -> 10 -> 5 -> 4 -> 1 ->
    }

    /**
     * 方法一: 使用入度
     *
     * @param maxtrix 有向图的邻接矩阵, m[i][j]=1表示:顶点i到顶点j有有向边
     * @return
     */
    public static String sort1(int[][] maxtrix) {
        int n = maxtrix.length;
        int[] indegrees = new int[n]; //表示顶点对应的入度
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                if (maxtrix[i][j] == 1) {
                    indegrees[j]++;
                }
            }
        }
        Queue queue = new LinkedList();
        for (int i = 0; i < n; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        int num = 0;
        while (!queue.isEmpty()) {
            int v = (Integer) queue.poll();
            sb.append(v);
            sb.append(" -> ");
            num++;

            for (int i = 0; i < n; i++) {
                if (maxtrix[v][i] == 1) {
                    indegrees[i]--;
                    if (indegrees[i] == 0) {
                        queue.offer(i);
                    }
                }
            }
        }
        if (num == n) return sb.toString();
        else return "";
    }

    /**
     * 方法二: 使用出度, DFS, 这里需要借助辅助数据结构stack
     *
     * @param matrix
     * @return
     */
    public static String sort2(int[][] matrix) {
        Stack stack = new Stack();

        boolean[] isVisited = new boolean[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            if (!isVisited[i]) {
                dfs(i, matrix, isVisited, stack);
            }
        }

        if (stack.size() != matrix.length) return "";
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
            sb.append(" -> ");
        }
        return sb.toString();
    }

    private static void dfs(int v, int[][] matrix, boolean[] isVisited, Stack stack) {
        for (int i = 0; i < matrix.length; i++) {
            if (!isVisited[i] && matrix[v][i] == 1) {
                dfs(i, matrix, isVisited, stack);
            }
        }
        isVisited[v] = true;
        stack.push(v);
    }
}
