package zhang.algorithm.modelUtil.AlgorithmDesign.Graph;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/9/25
 * Time: 下午3:26
 * To change this template use File | Settings | File Templates.
 * <p>
 * 结合线段树并利用 [扫描线算法] 求多个矩形的轮廓周长
 * <p>
 * 输入: 平面上N个矩形，第i个矩形用左下顶点坐标(Xi, Yi)和右上顶点坐标(UXi, UYi)表示。
 * 输出: 所有矩形轮廓周长。
 * input: [[x0, y0, x1, y1], [x0, y0, x1, y1], ...]
 * output: len
 * <p>
 * [Picture](http://poj.org/problem?id=1177)
 * <p>
 * <p>
 * [平面扫描思想在ACM竞赛中的应用]
 * (http://openinx.github.io/2013/01/01/plane-sweep-thinking/)
 */
public class RectPerimeter {
    /**
     * 初始想法
     *
     * @param rects
     * @return
     */
    public int calculRect(int[][] rects) {
        PriorityQueue<Event> queue = new PriorityQueue<>();  //优先队列
        int[] bounds = {Integer.MAX_VALUE, Integer.MIN_VALUE};  //y轴上的最小、最大值
        TreeSet<Integer> set = new TreeSet<>();  //x轴上以矩阵竖直方向分割的集合

        for (int i = 0; i < rects.length; i++) {
            int[] rect = rects[i];
            bounds[0] = Math.min(rect[1], bounds[0]);
            bounds[1] = Math.max(rect[3], bounds[1]);

            set.add(rect[0]);
            set.add(rect[2]);

            Event event1 = new Event(rect[0], rect, 1);
            Event event2 = new Event(rect[2], rect, -1);
            queue.add(event1);
            queue.add(event2);
        }

        int res = 0;
        int curTotalLen = 0, preTotalLen = 0;
        SegmentTree segTree = new SegmentTree(bounds[0], bounds[1]);

        Map<Integer, Integer> map = new HashMap<>();
        int index = 0;
        for (int num : set) {
            map.put(num, index++);
        }
        Object[] lines = set.toArray();

        while (!queue.isEmpty()) {
            Event event = queue.poll();
            segTree.insert(0, event.rect[1], event.rect[3], event.mark);
            curTotalLen = segTree.len();
            res += Math.abs(curTotalLen - preTotalLen);
            if (event.x != set.last())
                res += 2 * segTree.lines() * ((Integer) lines[map.get(event.x) + 1] - event.x);
            preTotalLen = curTotalLen;
        }

        return res;
    }

    /**
     * 改进
     *
     * @param rects
     * @return
     */
    public int calculRect2(int[][] rects) {
        PriorityQueue<Event> queue = new PriorityQueue<>();  //优先队列
        int[] bounds = {Integer.MAX_VALUE, Integer.MIN_VALUE};  //y轴上的最小、最大值

        for (int i = 0; i < rects.length; i++) {
            int[] rect = rects[i];
            bounds[0] = Math.min(rect[1], bounds[0]);
            bounds[1] = Math.max(rect[3], bounds[1]);

            Event event1 = new Event(rect[0], rect, 1);
            Event event2 = new Event(rect[2], rect, -1);
            queue.add(event1);
            queue.add(event2);
        }

        int res = 0;
        int curTotalLen = 0, preTotalLen = 0;
        SegmentTree segTree = new SegmentTree(bounds[0], bounds[1]);

        //当当前的矩阵点和之后的矩阵点的x值相同时, 那么它们的差为0, 不加
        //之后肯定会继续遍历到不同的为止, 此时再加, 效果一样
        while (!queue.isEmpty()) {
            Event event = queue.poll();
            segTree.insert(0, event.rect[1], event.rect[3], event.mark);
            curTotalLen = segTree.len();
            res += Math.abs(curTotalLen - preTotalLen);
            if (!queue.isEmpty())
                res += 2 * segTree.lines() * (queue.peek().x - event.x);
            preTotalLen = curTotalLen;
        }

        return res;
    }

    public static void main(String[] args) {
        RectPerimeter test = new RectPerimeter();
        int[][] rects = {
                {-15, 0, 5, 10},
                {-5, 8, 20, 25},
                {15, -4, 24, 14},
                {0, -6, 16, 4},
                {2, 15, 10, 22},
                {30, 10, 36, 20},
                {34, 0, 40, 16},
        };
        System.out.println(test.calculRect(rects));
        System.out.println(test.calculRect2(rects));
    }
}

class Event implements Comparable<Event> {
    public int x;
    public int[] rect;
    public int mark; //表示插入或删除

    public Event(int x, int[] rect, int mark) {
        this.x = x;
        this.rect = rect;
        this.mark = mark;
    }

    @Override
    public int compareTo(Event event) {
        return this.x - event.x;
    }
}
