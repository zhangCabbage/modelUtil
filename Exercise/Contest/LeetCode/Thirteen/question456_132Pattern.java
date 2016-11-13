package zhang.algorithm.modelUtil.Exercise.Contest.LeetCode.Thirteen;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/11/13
 * Time: 下午3:41
 * To change this template use File | Settings | File Templates.
 */
public class question456_132Pattern {
    /**
     * 每次上升的时候, 造就一个区间, 看之后的点, 有没有落入此区间的, 如有则为true, 否则为false.
     * 如何保留这么多区间呢? 可以使用线段树的方式, 但是由于题中未给出nums元素的取值范围, 所以不好预估存储空间。
     * <p>
     * 果然, 我太懒了。
     *
     * @param nums
     * @return
     */
    public boolean find132pattern(int[] nums) {
        if (nums.length < 3) return false;

        List<Point> list = new ArrayList<>();
        for (int i = 1; i < nums.length; i++) {
            if (search(list, nums[i])) return true;
            if (nums[i] > nums[i - 1]) {
                insert(list, new Point(nums[i - 1], nums[i]));
            }
        }

        return false;
    }

    private void insert(List<Point> list, Point point) {
        int size = list.size();

        if (size < 1 || point.x > list.get(size - 1).y) list.add(point);
        if (point.y < list.get(0).x) list.add(0, point);

        int l = 0, r = size - 1;
        while (l <= r) {
            int mid = l + (r - l >> 1);
        }
    }

    private boolean search(List<Point> list, int x) {
        if (list.size() < 1) return false;

        return false;
    }

    public static void main(String[] args) {
        question456_132Pattern test = new question456_132Pattern();
        int[] nums = {};
        System.out.println(test.find132pattern(nums));
    }
}
