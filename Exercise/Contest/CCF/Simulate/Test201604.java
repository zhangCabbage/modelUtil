package zhang.algorithm.modelUtil.Exercise.Contest.CCF.Simulate;

import java.util.*;
import java.util.List;
import java.util.Queue;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/9/10
 * Time: 下午1:43
 * To change this template use File | Settings | File Templates.
 */
public class Test201604 {
    /**
     * @param in
     */
    public static void one(Scanner in) {
        while (in.hasNext()) {
            int n = in.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = in.nextInt();
            }

            int count = 0;
            for (int i = 1; i < n - 1; i++) {
                if ((nums[i + 1] > nums[i] && nums[i] > nums[i - 1]) || (nums[i + 1] < nums[i] && nums[i] < nums[i - 1])) {

                } else {
                    count++;
                }
            }
            System.out.println(count);
        }
    }

    /**
     * 编译出错
     *
     * @param in
     */
    public static void two(Scanner in) {
        while (in.hasNext()) {
            int[][] map = new int[15][10];
            int[][] block = new int[4][4];
            int start = 0;
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    map[i][j] = in.nextInt();
                }
            }
            for (int i = 0; i < block.length; i++) {
                for (int j = 0; j < block[0].length; j++) {
                    block[i][j] = in.nextInt();
                }
            }
            start = in.nextInt();

            //从第5行开始比较
            int mapBegin = 4;
            int blockBegin = block.length - 1;
            boolean stop = false;

            for (int i = block.length - 1; i >= 0; i--) {
                boolean allZero = true;
                for (int j = 0; j < block[0].length; j++) {
                    if (block[i][j] == 1) {
                        allZero = false;
                        break;
                    }
                }
                if (allZero) blockBegin--;
                else break;
            }

            end:
            while (mapBegin < map.length) {
                int j = mapBegin;
                for (int i = blockBegin; i >= 0; i--) {
                    if (j < 4) break;
                    for (int k = 0; k < block[0].length; k++) {
                        if ((map[j][k + start - 1] & block[i][k]) == 1) {
                            stop = true;
                            break end;
                        }
                    }
                    j--;
                }
                mapBegin++;
            }

            mapBegin--;
            for (int i = 0; i <= blockBegin; i++) {
                for (int j = 0; j < block[0].length; j++) {
                    map[mapBegin - i][start + j - 1] |= block[blockBegin - i][j];
                }
            }

            //输出
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    System.out.print(map[i][j]);
                    if (j != map[0].length - 1)
                        System.out.print(" ");
                }
                System.out.println();
            }
        }
    }

    /**
     * 编译出错
     *
     * @param in
     */
    public static void three(Scanner in) {
        while (in.hasNext()) {
            int n = in.nextInt();
            String[] paths = new String[n];
            String curPath = getAbsolutePath(in.next());
            String[] curPaths = curPath.split("/"); //第一个为''空

            for (int i = 0; i < n; i++) {
                paths[i] = in.next();
            }

            for (int i = 0; i < n; i++) {
                if (paths[i].charAt(0) == '/') {
                    System.out.println(getAbsolutePath(paths[i]));
                } else {
                    paths[i] = paths[i].replaceAll("\\/{2,}|\\/\\.\\/", "/");
                    String[] ps = paths[i].split("/");

                    List<String> list = new ArrayList(Arrays.asList(curPaths));
                    list.remove(0);

                    for (int j = 0; j < ps.length; j++) {
                        if (ps[j].equals(".")) {
                            //nothing to do
                        } else if (ps[j].equals("..")) {
                            if (list.size() > 0)
                                list.remove(list.size() - 1);
                        } else {
                            list.add(ps[j]);
                        }
                    }

                    StringBuffer sb = new StringBuffer();
                    sb.append("/");
                    for (int k = 0; k < list.size(); k++) {
                        sb.append(list.get(k));
                        if (k != list.size() - 1)
                            sb.append("/");
                    }

                    System.out.println(sb.toString());
                }
            }


        }
    }

    /**
     * 绝对路径
     *
     * @param path
     * @return
     */
    public static String getAbsolutePath(String path) {
        path = path.replaceAll("\\/{2,}|\\/\\.\\/", "/");
        String[] paths = path.split("/");
        List<String> list = new ArrayList<>();
        for (int i = 1; i < paths.length; i++) {
            if ("..".equals(paths[i])) {
                if (list.size() > 0)
                    list.remove(list.size() - 1);
            } else {
                list.add(paths[i]);
            }
        }
        StringBuffer sb = new StringBuffer();
        sb.append("/");
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i != list.size() - 1)
                sb.append("/");
        }

        return sb.toString();
    }

    /**
     * @param in
     */
    public static void four(Scanner in) {
        while (in.hasNext()) {
            int n = in.nextInt();
            int m = in.nextInt();
            int t = in.nextInt();
            boolean[][][] isDown = new boolean[300][n + 1][m + 1];
            boolean[][][] map = new boolean[300][n + 1][m + 1];

            for (int i = 0; i < t; i++) {
                int r = in.nextInt();
                int c = in.nextInt();
                int a = in.nextInt();
                int b = in.nextInt();
                for (int j = a; j <= b; j++) {
                    isDown[j][r][c] = true;
                }
            }

            bfs(1, 1, n, m, isDown, map);
        }
    }

    /**
     * @param x
     * @param y
     */
    private static void bfs(int x, int y, int n, int m, boolean[][][] isDown, boolean[][][] map) {
        int[][] location = {
                {1, -1, 0, 0},
                {0, 0, -1, 1}
        };//上,下,左,右
        Queue<Point> queue = new LinkedList();
        Point cur = new Point(x, y, 0);
        queue.offer(cur);

        while (!queue.isEmpty()) {
            cur = queue.poll();
            if (cur.x == n && cur.y == m) {
                //ok
                System.out.println(cur.step);
                break;
            }
            for (int i = 0; i < 4; i++) {
                Point next = new Point();
                next.x = cur.x + location[0][i];
                next.y = cur.y + location[1][i];
                next.step = cur.step + 1;

                if (next.x < 1 || next.y < 1 || next.x > n || next.y > m) continue;
                if (map[next.step][next.x][next.y]) continue;

                map[next.step][next.x][next.y] = true;
                if (isDown[next.step][next.x][next.y]) continue;
                queue.offer(next);
            }
        }

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        one(in);
        two(in);
//        three(in);
//        four(in);
    }
}

class Point {
    int x;
    int y;
    int step;

    public Point() {
    }

    public Point(int x, int y, int step) {
        this.x = x;
        this.y = y;
        this.step = step;
    }
}
