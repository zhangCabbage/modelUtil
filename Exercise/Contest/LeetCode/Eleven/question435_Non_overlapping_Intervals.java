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
     * 没有审查!!
     * <p>
     * 16 / 16 test cases passed
     * Status: Accepted
     * Runtime: 11 ms
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.end != o2.end) return o1.end - o2.end;
                return o2.start - o1.start;
            }
        });

        int start = Integer.MIN_VALUE, end = start;
        int count = 0;
        for (Interval interval : intervals) {
            if (interval.start >= end) {
                count++;
                end = interval.end;
            }
        }

        return intervals.length - count;
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
