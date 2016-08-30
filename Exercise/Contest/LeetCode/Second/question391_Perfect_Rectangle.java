package zhang.algorithm.modelUtil.Exercise.Contest.LeetCode.Second;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/8/28
 * Time: 下午10:06
 * To change this template use File | Settings | File Templates.
 * <p>
 * from 9:22 to 10:18 less than one hour two problem, Just so so
 * the last problem I can not solve by myself.
 */
public class question391_Perfect_Rectangle {
    /**
     * <strong>result of test:</strong>
     * 42 / 42 test cases passed
     * Status: Accepted
     * Runtime: 110 ms
     *
     * @param rectangles
     * @return
     */
    public boolean isRectangleCover(int[][] rectangles) {
        PriorityQueue<RectPoint> queue = new PriorityQueue<>();
        int[] border = {Integer.MIN_VALUE, Integer.MAX_VALUE};//top and bottom border
        for (int[] rect : rectangles) {
            RectPoint leftBottom = new RectPoint(rect[0], rect);
            RectPoint rightTop = new RectPoint(rect[2], rect);
            queue.add(leftBottom);
            queue.add(rightTop);

            if (rect[3] > border[0]) border[0] = rect[3];
            if (rect[1] < border[1]) border[1] = rect[1];
        }

        TreeSet<int[]> set = new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] rect1, int[] rect2) {
                if (rect1[1] >= rect2[3]) return 1;
                else if (rect1[3] <= rect2[1]) return -1;
                else return 0;
            }
        });

        int yRange = 0;
        while (!queue.isEmpty()) {
            int xCoord = queue.peek().xCoord;
            while (!queue.isEmpty() && queue.peek().xCoord == xCoord) {
                RectPoint curPoint = queue.poll();
                int[] curRect = curPoint.rect;
                if (curPoint.xCoord == curRect[2]) {
                    yRange -= curRect[3] - curRect[1];
                    set.remove(curRect);
                } else {
                    if (!set.add(curRect)) return false;
                    yRange += curRect[3] - curRect[1];
                }
            }

            if (!queue.isEmpty() && yRange != border[0] - border[1]) return false;
        }

        return true;
    }

    class RectPoint implements Comparable<RectPoint> {
        public int xCoord;
        public int[] rect;

        public RectPoint(int xCoord, int[] rect) {
            this.xCoord = xCoord;
            this.rect = rect;
        }

        @Override
        public int compareTo(RectPoint that) {
            if (this.xCoord != that.xCoord) return this.xCoord - that.xCoord;
            return this.rect[0] - that.rect[0];
        }
    }

    public static void main(String[] args) {
        question391_Perfect_Rectangle test = new question391_Perfect_Rectangle();
        int[][] rectangles = {
                {1, 1, 3, 3},
                {3, 1, 4, 2},
                {3, 2, 4, 4},
                {1, 3, 2, 4},
                {2, 3, 3, 4},
        };
        System.out.println(test.isRectangleCover(rectangles));
    }
}
