package zhang.algorithm.modelUtil.Exercise;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/9/9
 * Time: 下午7:43
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            int[] nums = new int[n];

            for (int i = 0; i < n; i++) {
                nums[i] = in.nextInt();
            }

            int max = 0;
            int lastPopInd = 0; //!!

            Stack<Integer> numStack = new Stack<>();
            Stack<Integer> indexStack = new Stack<>();

            numStack.push(nums[0]);
            indexStack.push(0);

            for (int i = 1; i < n; i++) {

                if (nums[i] > numStack.peek()) {
                    numStack.push(nums[i]);
                    indexStack.push(i);
                } else if (nums[i] < numStack.peek()) {
                    while (!numStack.isEmpty() && nums[i] < numStack.peek()) {
                        int num = numStack.pop();
                        lastPopInd = indexStack.pop();
                        max = Math.max((i - lastPopInd) * num, max);
                    }
                    if (numStack.isEmpty() || nums[i] > numStack.peek()) {
                        numStack.push(nums[i]);
                        //这里不能存当前的 i index
                        indexStack.push(lastPopInd);
                    }
                }
            }

            while (!numStack.isEmpty()) {
                int num = numStack.pop();
                int index = indexStack.pop();
                max = Math.max(max, (n - index) * num);
            }

            System.out.println(max);
        }
    }
}

