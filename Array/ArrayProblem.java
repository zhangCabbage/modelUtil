package zhang.algorithm.modelUtil.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/10/19
 * Time: 下午7:58
 * To change this template use File | Settings | File Templates.
 */
public class ArrayProblem {
    //--------------------------------------------------------------------------
    //1) 求数组中最长递增子序列
    //--------------------------------------------------------------------------

    /**
     * 由于此问题的无后效特性, 我们可以使用动态规划的方法
     *
     * @param arrays
     * @return
     */
    public int maxIncreaseSubArray(int[] arrays) {
        int maxLen = 0;
        int[] increLens = new int[arrays.length];
        for (int i = 0; i < arrays.length; i++) {
            increLens[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arrays[i] > arrays[j] && increLens[j] + 1 > increLens[i]) {
                    increLens[i] = increLens[j] + 1;
                }
            }
            if (increLens[i] > maxLen) {
                maxLen = increLens[i];
            }
        }

        return maxLen;
    }

    /**
     * 假如我们考虑第i+1个元素前i个元素的关系, 那么该怎么办呢?
     * 我们需要找到前i个元素中最长的递增子序列中, 最大的元素中的最小值, 此最大值小于arrays[i+1]
     * 这里我们使用maxV[i]来存, 长度为i的递增子序列其最大元素的最小值!
     *
     * @param arrays
     * @return
     */
    public int maxIncreaseSubArray2(int[] arrays) {
        int[] maxV = new int[arrays.length + 1];
        maxV[1] = arrays[0];  //init

        int[] Lis = new int[arrays.length]; //Lis[i]表示包含当前下标i对应数组元素的最长递增子序列的长度
        Arrays.fill(Lis, 1);
        int maxLen = 1;  //历史最大长度

        //traversal from left to right.
        for (int i = 1; i < arrays.length; i++) {
            for (int j = maxLen; j > 0; j--) {
                if (arrays[i] > maxV[j]) {
                    Lis[i] = j + 1;
                    break;
                }
            }
            if (Lis[i] > maxLen) {  //新增的递增子序列长度, 直接更新
                maxLen = Lis[i];
                maxV[Lis[i]] = arrays[i];
            } else if (arrays[i] < maxV[Lis[i]]) {  //以前有的递增子序列, 只有新的值比原来的小, 才会更新
                maxV[Lis[i]] = arrays[i];
            }
        }

        return maxLen;
    }

    //--------------------------------------------------------------------------
    //2) 一个源区间, 若干N无序目标区间, 判断源区间是否在目标区间中
    // (http://blog.csdn.net/tianshuai1111/article/details/7828961)
    //方法一: 并查集, 把每个区间合并到一个子树上, 以区间起点为准
    //方法二: 排序(起点为准) -> 合并 -> 查找(二分查找, 以起点为准, 看源区间起点终点查找结果是否在同一个区间)
    //--------------------------------------------------------------------------

    /**
     * 方法二
     *
     * @param sourceSect
     * @param destSect
     * @return
     */
    public boolean sectionConcide(int[] sourceSect, int[][] destSect) {
        List<Section> lines = new ArrayList<>();  //不重合的线段
        //先根据线段起始坐标 x 排序
        PriorityQueue<Section> queue = new PriorityQueue<>();
        for (int[] sect : destSect) {
            queue.offer(new Section(sect));
        }
        lines.add(queue.poll());

        //合并
        while (!queue.isEmpty()) {
            Section cur = queue.poll();
            Section tmp = lines.get(lines.size() - 1);
            if (cur.sect[0] <= tmp.sect[1]) {
                if (cur.sect[1] > tmp.sect[1])
                    tmp.sect[1] = cur.sect[1];
            } else {
                lines.add(cur);
            }
        }

        //二分查找源线段, 以线段起点为依据
        int m = binarySearch(sourceSect[0], lines);
        int n = binarySearch(sourceSect[1], lines);
        if (m == n && sourceSect[1] <= lines.get(m).sect[1]) {
            return true;
        } else {
            return false;
        }
    }

    private int binarySearch(int source, List<Section> lines) {
        int left = 0;
        int right = lines.size() - 1;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (lines.get(mid).sect[0] > source) {
                right = mid - 1;
            } else if (lines.get(mid).sect[0] < source) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return right;
    }

    /**
     * 线段类
     */
    class Section implements Comparable {
        int[] sect;

        public Section(int[] sect) {
            this.sect = sect;
        }

        @Override
        public int compareTo(Object other) {
            return this.sect[0] - ((Section) other).sect[0];
        }
    }

    /**
     * 方法一: 并查集
     *
     * @param sourceSect
     * @param destSect
     * @return
     */
    public boolean sectionConcide2(int[] sourceSect, int[][] destSect) {
        makeSet(100);
        for (int[] sect : destSect) {
            for (int i = sect[0] + 1; i < sect[1]; i++) {
                union(sect[0], i);
            }
        }

        if (find(sourceSect[0]) == find(sourceSect[1])) {
            return true;
        }
        return false;
    }

    private int[] father;
    private int[] rank;

    private void makeSet(int size) {
        father = new int[size];
        rank = new int[size];
        for (int i = 0; i < father.length; i++) {
            father[i] = i;
            rank[i] = 1;
        }
    }

    /**
     * 查找
     *
     * @param x
     * @return
     */
    private int find(int x) {
        if (x != father[x]) {
            father[x] = find(father[x]);
        }
        return father[x];
    }

    private void union(int start, int end) {
        int x = find(start);
        int y = find(end);

        if (x != y) {
            if (rank[x] < rank[y]) {
                father[x] = y;
            } else {
                father[y] = x;
                if (rank[x] == rank[y])
                    rank[x]++;
            }
        }
    }

    //--------------------------------------------------------------------------
    // main
    //--------------------------------------------------------------------------
    public static void main(String[] args) {
        ArrayProblem test = new ArrayProblem();
        int[] nums = {1, -1, 2, -3, 4, -5, 6, -7};
        System.out.println(test.maxIncreaseSubArray(nums));
        System.out.println(test.maxIncreaseSubArray2(nums));

        int[][] sects = {{2, 3}, {1, 2}, {3, 9}};
        int[] source = {1, 6};
        System.out.println(test.sectionConcide(source, sects));
        System.out.println(test.sectionConcide2(source, sects));
    }
}
