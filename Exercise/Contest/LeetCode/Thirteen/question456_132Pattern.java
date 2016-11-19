package zhang.algorithm.modelUtil.Exercise.Contest.LeetCode.Thirteen;

import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/11/13
 * Time: 下午3:41
 * To change this template use File | Settings | File Templates.
 * <p>
 * update
 * Date: 16/11/19
 * Time: 下午5:20
 * Deal
 */
public class question456_132Pattern {
    /**
     * 每次上升的时候, 造就一个区间, 看之后的点, 有没有落入此区间的, 如有则为true, 否则为false.
     * 如何保留这么多区间呢? 可以使用线段树的方式, 但是由于题中未给出nums元素的取值范围, 所以不好预估存储空间。
     * 而且略麻烦, 果然, 我太懒了!
     * 有没有替代方法?
     * 想到现在我最后忍不住看了一下本题的tag, the hint is stack. So?
     * 我自己分析的结果跟最后答案几乎很接近了, 只需要再深入分析一下就可以了!!
     * 确实是每次遇到上升区间则保留并合并之前的区间, 每次当与之前的区间有交叉时, 我们只需要一直回退即可!
     * <p>
     * 87 / 87 test cases passed.
     * Status: Accepted
     * Runtime: 30 ms
     *
     * @param nums
     * @return
     */
    public boolean find132pattern(int[] nums) {
        if (nums.length < 3) return false;
        Stack<Interval> stack = new Stack();
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            if (stack.isEmpty() || n <= stack.peek().min) stack.push(new Interval(n, n));
            else {
                Interval top = stack.pop();
                if (n < top.max) return true;
                else {
                    top.max = n;
                    while (!stack.isEmpty() && n >= stack.peek().max)
                        stack.pop();
                    if (!stack.isEmpty() && n > stack.peek().min) return true;
                    stack.push(top);
                }
            }
        }

        return false;
    }

    class Interval {
        int min, max;

        public Interval(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    //-----------------------------------------------------------------------------
    //you can also use the 【Violent method】
    //-----------------------------------------------------------------------------

    /**
     * @param nums
     * @return
     */
    public boolean find132pattern2(int[] nums) {

        return false;
    }

    public static void main(String[] args) {
        question456_132Pattern test = new question456_132Pattern();
        int[] nums = {1, 2, 3, 4};
        System.out.println(test.find132pattern(nums));
    }
}
