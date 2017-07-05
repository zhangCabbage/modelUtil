package zhang.algorithm.modelUtil.Stack;

import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/7/5
 * Time: 下午10:34
 * To change this template use File | Settings | File Templates.
 */
public class JudgePushPopStack {
    /**
     * 本题中的控制语句还是很值得学习的
     *
     * @param pushA
     * @param popA
     * @return
     */
    public static boolean IsPopOrder(int[] pushA, int[] popA) {
        int aI = 0, bI = 0;
        Stack<Integer> stack = new Stack();
        while (aI < pushA.length) {
            while (aI < pushA.length && pushA[aI] != popA[bI]) {
                stack.push(pushA[aI++]);
            }
            if (aI == pushA.length) return false;
            aI++;
            bI++;
            if (bI == popA.length) return true;
            while (!stack.isEmpty() && stack.peek() == popA[bI]) {
                stack.pop();
                bI++;
            }
        }

        return bI == popA.length;
    }

    public static void main(String[] args) {
        int[] pushA = {1};
        int[] popA = {2};
        System.out.println(IsPopOrder(pushA, popA));
    }
}
