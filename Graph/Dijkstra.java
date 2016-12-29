package zhang.algorithm.modelUtil.Graph;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_Lenovo
 * Date: 2016/6/28
 * Time: 9:46
 * To change this template use File | Settings | File Templates.
 *
 * This is belong Greedy method.
 *
 * use one int array to storage the pre node index of cur node.
 *
 */
public class Dijkstra {
    private int[] path;//use to storage the pre node of current node in the shortest path
    private int[] dist;
    private int start;

    /**
     *
     * @param start start node
     * @param v 邻接矩阵
     */
    public void dijkstraFindPath(int start, int[][] v){
        int len = v.length;

        this.start = start;
        path = new int[len];
        dist = new int[len];

        boolean[] visited = new boolean[len];//means is this node has been used

        //init dist matrix, not to handle the original matrix v
        for(int i=0; i<len; i++){
            if(i!=start && v[start][i]>0){
                dist[i] = v[start][i];
                path[i] = start;
            }else{
                dist[i] = Integer.MAX_VALUE;
                path[i] = -1;
            }
            visited[i] = false;
        }

        path[start] = start;
        dist[start] = 0;
        visited[start] = true;

        for(int i=1; i<v.length; i++){
            //to find the len-1 node path

            //first, to find the smallest path in current path dist.
            int min = Integer.MAX_VALUE;
            int cur = 0;
            for(int j=0; j<len; j++){
                if(!visited[j] && dist[j]<min){
                    min = dist[j];
                    cur = j;
                }
            }

            visited[cur] = true;
            //second, to update the dist array that start from the cur node.
            for(int k=0; k<len; k++){
                if(!visited[k] && v[cur][k]>0 && min+v[cur][k] < dist[k]){
                    dist[k] = min+v[cur][k];
                    path[k] = cur;
                }
            }

        }
    }

    /**
     * to show the shortest path
     */
    public void showPath(){
        for(int i=0; i<path.length; i++){
            StringBuffer sb = new StringBuffer();

            sb.insert(0, i);
            while(path[i] != start){
                sb.insert(0, " --> ");
                sb.insert(0, path[i]);
                path[i] = path[path[i]];
            }
            sb.insert(0, " --> ");
            sb.insert(0, start);

            sb.append(", the shortest path is: ");
            sb.append(dist[i]);
            System.out.println(sb.toString());
        }
//        print:
//        0 --> 0, the shortest path is: 0
//        0 --> 4 --> 3 --> 1, the shortest path is: 70
//        0 --> 2, the shortest path is: 30
//        0 --> 4 --> 3, the shortest path is: 60
//        0 --> 4, the shortest path is: 10
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 100, 30, 0, 10},
                {0, 0, 0, 0, 0},
                {0, 60, 0, 60, 0},
                {0, 10, 0, 0, 0},
                {0, 0, 0, 50, 0}
        };
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.dijkstraFindPath(0, matrix);
        dijkstra.showPath();
    }
}
