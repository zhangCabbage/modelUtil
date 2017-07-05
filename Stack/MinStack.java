package zhang.algorithm.modelUtil.Stack;

import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/7/5
 * Time: 下午9:29
 * To change this template use File | Settings | File Templates.
 */
public class MinStack {
    private Stack<Integer> stack = new Stack();
    private Stack<Integer> min = new Stack();

    public void push(int node) {
        stack.push(node);
        //比较 min 和 node, 选择压入min stack的元素
        if (min.isEmpty() || min.peek() > node) min.push(node);
        else min.push(min.peek());
    }

    public void pop() {
        stack.pop();
        min.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return min.peek();
    }

    public static void main(String[] args) {
        MinStack test = new MinStack();
        test.push(3);
        System.out.println(test.min());
        test.push(4);
        System.out.println(test.min());
        test.push(2);
        System.out.println(test.min());
    }
}
