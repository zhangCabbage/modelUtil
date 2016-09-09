package zhang.algorithm.modelUtil.Exercise.Contest.CCF.Simulate;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/9/9
 * Time: 下午6:33
 * To change this template use File | Settings | File Templates.
 * <p>
 * 需要把所有需要导入的包都带上, 自己考虑全面, 没有很多错误提示!!
 */
public class Test201312 {
    /**
     * 遗漏个数为1的情况
     *
     * @param in
     */
    public static void one(Scanner in) {
        while (in.hasNext()) {
            int num = in.nextInt();
            int[] n = new int[num];
            for (int i = 0; i < num; i++) {
                n[i] = in.nextInt();
            }
            Arrays.sort(n);
            int mostCount = 1;
            int mostNumber = n[0];
            int temp = 1;
            for (int i = 1; i < num; i++) {
                if (n[i] == n[i - 1]) {
                    temp++;
                } else {
                    if (temp > mostCount) {
                        mostCount = temp;
                        mostNumber = n[i - 1];
                    }
                    temp = 1;

                }
            }
            System.out.println(mostNumber);
        }
    }

    /**
     * the easiest to think solve way.
     *
     * @param in
     */
    public static void two(Scanner in) {
        while (in.hasNext()) {
            //0-670-82162-4
            String str = in.next();
            int temp = 0;
            int n = 1;
            char mod = '0';
            for (int i = 0; i < 13; i++) {
                if (i == 12) {
                    if (mod == str.charAt(i)) {
                        System.out.println("Right");
                    } else {
                        System.out.println(str.substring(0, 12) + mod);
                    }
                }
                if (i == 1 || i == 5 || i == 11) {
                    //忽略
                    if (i == 11) {
                        temp %= 11;
                        if (temp == 10) {
                            mod = 'X';
                        } else {
                            mod = (char) (mod + temp);
                        }
                    }
                } else {
                    temp += (str.charAt(i) - '0') * (n++);
                }
            }
        }
    }

    /**
     * wrong, can not solve it.
     * 可以转换问题: 求连续子数组, 其数组最小值与数组长度乘积最大
     * [寻找直方图中面积最大的矩形](http://www.cnblogs.com/mickole/articles/3654280.html)
     * <p>
     * 考虑如下情况:
     * 1) 当高度愈来愈大, 那么其能够构成的矩形的长度就会增加
     * 2) 当高度愈来愈小, 那么其上一个大的数字构成的矩形长度就保持不变了
     * 因此, 我们可以先把保持不变的矩形处理掉
     *
     * @param in
     */
    public static void three(Scanner in) {
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

    public static void four(Scanner in) {
        while (in.hasNext()) {

        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        one(in);
//        two(in);
        three(in);
//        four(in);
    }
}
