package zhang.algorithm.modelUtil.Exercise.Examination.Alibaba;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/4/30
 * Time: 上午11:14
 * To change this template use File | Settings | File Templates.
 * <p>
 * 阿里校招笔试
 */
public class Spring04_26 {

    /**
     * ‘?’ 匹配一个字符
     * ‘*’ 匹配任意连串的字符
     * 如上面的例子中，购物车平台订阅方式是pattern=*trade-done，那么
     * filter(100-trade-done, pattern) = 1,
     * filter(200-trade-done, pattern) = 1,
     * filter(200-paid-done, pattern) = 0
     * <p>
     * 如果pattern=200-?*-done :
     * filter(100-trade-done, pattern) = 0,
     * filter(200-trade-done, pattern) = 1,
     * filter(200-paid-done, pattern) = 1
     * <p>
     * 如果pattern=1*trade*done :
     * filter(100-trade-done, pattern) = 1,
     * filter(200-trade-done, pattern) = 0,
     * filter(200-paid-done, pattern) = 0
     * <p>
     * but I only get 25%, why?
     *
     * @param in
     */
    public static int fun1(Scanner in) {
        char[] str = in.nextLine().trim().toCharArray();
        char[] pattern = in.nextLine().trim().toCharArray();
        return filter(str, pattern, 0, 0);
    }

    public static int filter(char[] str, char[] pattern, int m, int n) {
        if (m == str.length && n == pattern.length) return 1;
        if (m == str.length || n == pattern.length) return 0;

        if (pattern[n] == '?') return filter(str, pattern, m + 1, n + 1);
        else if (pattern[n] == '*') {
            int tmp = filter(str, pattern, m + 1, n);
            if (tmp == 1) return 1;
            return filter(str, pattern, m + 1, n + 1);
        } else if (str[m] == pattern[n]) {
            return filter(str, pattern, m + 1, n + 1);
        }

        return 0;
    }

    //-------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------

    /**
     * 一个对于一个单行的逆波兰表达式，由如下元素构成：
     * 数字：十进制数字字符构成的正整数，比如 223
     * 运算符：支持三种运算符^+和*，分别代表自增，加法和乘法
     * 分隔符：一个或者多个空格
     * 例如下面的字符串就是个逆波兰表达式
     * 2 3  4 * ^ 5 +
     * 逆波兰表达式在一个基于栈的虚拟机中求解，虚拟机的栈能保存16个整数，虚拟机从左向右扫描表达式，遇到整数就压栈，
     * 遇到表达式则从栈顶弹出若干个整数进行计算，计算结果重新压回栈中。其中运算符^从栈顶弹出一个整数，增加1之后压栈；
     * 运算符+和*从栈顶弹出两个整数，分别做相加和相乘运算后压栈。
     * 如果遇到运算符的时候，栈内没有足够的整数，称为下溢出，返回-1；
     * 把整数压栈的时候，如果栈没有足够的空间，称为上溢出，返回-2；
     * 如果整个计算过程中没有发生溢出，在整个表达式求解完成后，返回栈顶的整数。
     * <p>
     * get 0%, why?
     *
     * @param expr
     * @return
     */
    public static int fun2(String expr) {
        int[] stack = new int[16];
        int index = 0;
        String[] strs = expr.split(" +");
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].equals("^")) {
                if (index < 1) return -1;
                stack[index - 1] = stack[index - 1] + 1;
            } else if (strs[i].equals("+")) {
                int a = stack[--index];
                int b = stack[--index];
                stack[index++] = a + b;
            } else if (strs[i].equals("*")) {
                int a = stack[--index];
                int b = stack[--index];
                stack[index++] = a * b;
            } else {
                if (index >= stack.length) return -2;
                stack[index++] = Integer.parseInt(strs[i]);
            }
        }
        return stack[--index];
    }

    public static void main(String[] args) {
        ArrayList<Integer> inputs = new ArrayList<Integer>();
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        if (line != null && !line.isEmpty()) {
            int res = fun2(line.trim());
            System.out.println(String.valueOf(res));
        }
    }
}
