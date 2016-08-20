package zhang.algorithm.modelUtil.Search;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/8/20
 * Time: 下午2:21
 * To change this template use File | Settings | File Templates.
 * <p>
 * [detail]:
 * 有一个长度为 n 的单调上升队列，我们要找到距离 N 最近的 K 个数。
 * 输出这 K 个数中最小与最大的值。如果存在多个答案，输出更小的。if 5-15 and 7-16 ok, print 5-15
 * 例子 1 2 3 4 8 10 11， N = 7, K = 3, 与7最近的3个数是 4,8,10,输出[4, 10]。
 * <p>
 * [reference]:
 * [http://www.zhentiyuan.com/news/98248_3023.html]
 */
public class KNumNearestN {
    /**
     * this way the time complexity is O(logN + k), when k = N, it is O(N)
     *
     * @param arrays
     * @param n
     * @param k
     * @return
     */
    public List<Integer> findRangeNearestN1(int[] arrays, int n, int k) {
        List<Integer> range = new ArrayList<>();

        if (k >= arrays.length) {
            range.add(arrays[0]);
            range.add(arrays[arrays.length - 1]);
            return range;
        }

        int index = BinarySearch.insertIndex(arrays, n);
        if (index != 0 && arrays[index] - n >= n - arrays[index - 1]) index = index - 1;

        int i = index, j = index;
        for (k = k - 1; k > 0; k--) {
            if (i > 0 && (j == arrays.length - 1 || arrays[j + 1] - n >= n - arrays[i - 1])) i--;
            else j++;
        }
        range.add(arrays[i]);
        range.add(arrays[j]);

        return range;
    }

    /**
     * 改进第二部分, 不遍历k遍, 仍然使用二分法。这样,时间复杂度为O(logN + logK)
     *
     * @param arrays
     * @param n
     * @param k
     * @return
     */
    public List<Integer> findRangeNearestN2(int[] arrays, int n, int k) {
        List<Integer> range = new ArrayList<>();

        if (k >= arrays.length) {
            range.add(arrays[0]);
            range.add(arrays[arrays.length - 1]);
            return range;
        }
        //first, make sure that the nearest number index
        int index = BinarySearch.insertIndex(arrays, n);
        if (index != 0 && arrays[index] - n >= n - arrays[index - 1]) index = index - 1;

        int i = index, j = index;
        int tempK = k;
        //if k = 1, do not need the follow hand
        if (k > 1) {
            //if k = 2, the follow k is 0, only need the extra last one add
            k = (k - 1) >> 1;
            while (k >= 1) {
                int left = i - k >= 0 ? i - k : 0;
                int right = j + k < arrays.length ? j + k : arrays.length - 1;

                if (left == i) { j = i + tempK - 2; break; }
                if (right == j) { i = j - tempK + 2; break; }
                if (n - arrays[left] <= arrays[right] - n) i = left;
                else j = right;
                k >>= 1;
            }
            //the extra last one need to add, left or right
            if (i > 0 && (j == arrays.length - 1 || n - arrays[i - 1] <= arrays[j + 1] - 1)) i--;
            else j++;
        }

        range.add(arrays[i]);
        range.add(arrays[j]);

        return range;
    }

    public static void main(String[] args) {
        KNumNearestN test = new KNumNearestN();

        int[] arrays = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 15, 17, 19, 21, 100};
        int n = 21;
        int k = 9;
        System.out.println(test.findRangeNearestN1(arrays, n, k));
        System.out.println(test.findRangeNearestN2(arrays, n, k));
    }
}
