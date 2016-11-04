package zhang.algorithm.modelUtil.Exercise.Contest.LeetCode.Eleven;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/10/30
 * Time: 上午10:55
 * To change this template use File | Settings | File Templates.
 */
public class question435_Non_overlapping_Intervals {
    /**
     * 16 / 16 test cases passed
     * Status: Accepted
     * Runtime: 9 - 10 ms
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.end != o2.end) return o1.end - o2.end;  //先以end作为排序的依据, end越小在前
                return o2.start - o1.start;  //当end相同以start作为排序依据, start越大在前
            }
        });

        int end = Integer.MIN_VALUE;
        int count = 0;
        for (Interval interval : intervals) {
            if (interval.start >= end) end = interval.end;
            else count++;
        }

        return count;
    }

    /**
     * we only sort by end is ok !!
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals2(Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.end - o2.end;  //先以end作为排序的依据, end越小在前
            }
        });

        int end = Integer.MIN_VALUE;
        int count = 0;
        for (Interval interval : intervals) {
            if (interval.start >= end) end = interval.end;
            else count++;
        }

        return count;
    }

    /**
     * 开始第一次, 我也是以start进行排序, 但是可能我的分析逻辑不够清晰, 导致没有做出来。
     * 不过这种方式, 没有我上面一种方法逻辑清晰。
     * <p>
     * 16 / 16 test cases passed.
     * Status: Accepted
     * Runtime: 10 - 12 ms
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals3(Interval[] intervals) {
        if (intervals.length < 2) return 0;
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.start != o2.start) return o1.start - o2.start;
                else return o1.end - o2.end;
            }
        });

        Interval pre = intervals[0];
        int count = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (pre.end <= intervals[i].start) pre = intervals[i];
            else {
                count++;
                if (pre.end > intervals[i].end) pre = intervals[i];
            }
        }

        return count;
    }

    public static void main(String[] args) {
//        [[1,2],[2,3],[3,4],[-100,-2],[5,7]]
        question435_Non_overlapping_Intervals test = new question435_Non_overlapping_Intervals();
        Interval[] intervals = {};
        System.out.println(test.eraseOverlapIntervals(intervals));
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
}
