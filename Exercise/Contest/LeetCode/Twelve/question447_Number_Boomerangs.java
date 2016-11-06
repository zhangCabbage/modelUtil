package zhang.algorithm.modelUtil.Exercise.Contest.LeetCode.Twelve;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/11/6
 * Time: 下午2:53
 * To change this template use File | Settings | File Templates.
 */
public class question447_Number_Boomerangs {
    /**
     * 方法一:
     * 使用最笨的办法, 时间复杂度为O(n ^ 3)
     * 但是超时 --> Time Limit Exceeded
     *
     * @param points
     * @return
     */
    public int numberOfBoomerangs(int[][] points) {
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i != j) {
                    long dis_i_j = (long) (Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2));
                    for (int k = 0; k < points.length; k++) {
                        if (k != i && k != j) {
                            long dis_i_k = (long) (Math.pow(points[i][0] - points[k][0], 2) + Math.pow(points[i][1] - points[k][1], 2));
                            if (dis_i_j == dis_i_k) res++;
                        }
                    }
                }
            }
        }

        return res;
    }

    /**
     * 方法二:
     * 这里我对最开始的方法进行了优化, 从每一个点出发, 逐渐成圈扩展出去
     * 每次以某一个节点为中心向外扩展, 使用一个map来存储<dis, count>
     * 因为这里只是求可能的组合个数, 所以我们可以如此来降低时间复杂度。
     * 使用数组resMap来记录满足条件的个数, 或者直接计算
     * <p>
     * 30 / 30 test cases passed.
     * Status: Accepted
     * Runtime: 246 - 257 ms
     *
     * @param points
     * @return
     */
    public int numberOfBoomerangs2(int[][] points) {
        int res = 0;

        for (int i = 0; i < points.length; i++) {
            Map<Long, Integer> map = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                long dis = (long) (Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2));
                map.put(dis, map.getOrDefault(dis, 0) + 1);
            }

            for (Map.Entry<Long, Integer> entry : map.entrySet()) {
                int num = entry.getValue();
                if (num >= 2) {
                    res += num * (num - 1);
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        question447_Number_Boomerangs test = new question447_Number_Boomerangs();
        int[][] points = {{0, 0}, {1, 0}, {2, 0}};
        System.out.println(test.numberOfBoomerangs(points));
        System.out.println(test.numberOfBoomerangs2(points));
    }
}
