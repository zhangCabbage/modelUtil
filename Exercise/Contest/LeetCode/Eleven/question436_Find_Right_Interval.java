package zhang.algorithm.modelUtil.Exercise.Contest.LeetCode.Eleven;

import zhang.algorithm.modelUtil.Array.ArrayTool;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/10/30
 * Time: 上午11:56
 * To change this template use File | Settings | File Templates.
 */
public class question436_Find_Right_Interval {
    /**
     * 没有审查!!
     * <p>
     * 15 / 15 test cases passed.
     * Status: Accepted
     * Runtime: 391 - 418 ms
     *
     * @param intervals
     * @return
     */
    public int[] findRightInterval(Interval[] intervals) {
        int[] map = new int[intervals.length];
        for (int i = 0; i < map.length; i++) {
            map[i] = i;
        }
        fastSort(intervals, map, 0, intervals.length - 1);

        int[] res = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            int index = -1;
            for (int j = i + 1; j < intervals.length; j++) {
                if (intervals[j].start >= intervals[i].end) {
                    index = map[j];
                    break;
                }
            }
            res[map[i]] = index;
        }
        return res;
    }

    public void fastSort(Interval[] intervals, int[] map, int start, int end) {
        if (start > end) return;
        int l = start;
        int r = end;
        Interval provit = intervals[start];
        int index = map[start];
        while (l < r) {
            while (r > l && intervals[r].start >= provit.start) r--;
            intervals[l] = intervals[r];
            map[l] = map[r];
            while (l < r && intervals[l].start <= provit.start) l++;
            intervals[r] = intervals[l];
            map[r] = map[l];
        }
        intervals[l] = provit;
        map[l] = index;
        fastSort(intervals, map, start, l - 1);
        fastSort(intervals, map, r + 1, end);
    }

    public static void main(String[] args) {
        question436_Find_Right_Interval test = new question436_Find_Right_Interval();
        Interval[] intervals = {new Interval(1, 2)};
        ArrayTool.printArray(test.findRightInterval(intervals));
    }
}

class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }
}