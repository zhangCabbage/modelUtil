package zhang.algorithm.modelUtil.Exercise.Examination.ByteDance;

import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/3/31
 * Time: 上午10:02
 * To change this template use File | Settings | File Templates.
 * <p>
 * 今日头条2018年春招笔试
 * [经验教训]:
 * 1) 今日头条的四道编程题, 除了最后一道题是算法思想的挑战, 其他几道题都只是[细节]、[速度]的考验。
 * 2) 思路要清晰, 写代码要细致! 比如第一道题遍历一遍即可, 很简单的最后竟然整了半天。
 * -> 有时想不到的测试用例,可能是代码逻辑的问题!!
 */
public class Spring3_30 {
    /**
     * 1)
     * 给一个数组, 此数组严格单调, 即左右值不等; 求出最大宽的先上升后下降区间
     *
     * @param in
     */
    public static void fun1(Scanner in) {
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }

        if (n < 3) System.out.println(-1 + " " + -1);
        else {
            int max = 0;
            int left = 0, right = 0;
            int pre = 0;
            boolean hasPeek = false;
            for (int i = 1; i < n; i++) {
                if (i < n - 1 && nums[i] > nums[i - 1] && nums[i] > nums[i + 1])
                    hasPeek = true;
                else if ((i < n - 1 && nums[i] < nums[i - 1] && nums[i] < nums[i + 1]) || (i == n - 1 && nums[i] < nums[i - 1])) {
                    if (!hasPeek) {
                        pre = i;
                    } else {
                        if (i - pre + 1 > max) {
                            max = i - pre + 1;
                            left = pre;
                            right = i;
                        }
                        pre = i;
                        hasPeek = false;
                    }
                }
            }

            if (left == right)
                System.out.println(-1 + " " + -1);
            else
                System.out.println(left + " " + right);
        }
    }

    //-------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------

    /**
     * 3)
     * 给一个字符串, 让打印成某种格式的字符串。eg:[][]、[[[]]][]、[[][]]
     * 字符串操作, 可能略麻烦, 一定要思路清晰, 否则自己会无限死循环
     *
     * @param in
     */
    public static void fun3(Scanner in) {
        String str = in.next();
        int max = 0;  //最大的括号深度
        int cur = 0;  //当前的深度
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '[') cur++;
            else {
                max = Math.max(cur, max);
                cur--;
            }
        }

        cur = max;
        int i = 0;

        StringBuilder tmp1 = new StringBuilder();
        tmp1.append("+");
        for (int j = 0; j < 2 * cur - 1; j++) {
            tmp1.append("-");
        }
        tmp1.append("+");
        System.out.println(tmp1);
        cur--;

        while (i < str.length()) {
            if (i > 0 && str.charAt(i) == '[' && str.charAt(i - 1) == ']') {
                StringBuilder tmp = new StringBuilder();
                for (int j = 0; j < max - cur; j++) {
                    tmp.append(" ");
                }
                tmp.append("+");
                for (int j = 0; j < 2 * cur - 1; j++) {
                    tmp.append("-");
                }
                tmp.append("+");
                System.out.println(tmp);
                System.out.println(tmp);
                cur--;
            }

            if (i != str.length() - 1 && str.charAt(i) == '[' && str.charAt(i + 1) == ']') {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < max - cur - 1; j++) {
                    sb.append(" ");
                }
                sb.append("|");
                for (int j = 0; j < 2 * cur + 1; j++) {
                    sb.append(" ");
                }
                sb.append("|");
                System.out.println(sb);
                System.out.println();
                System.out.println(sb);
                i += 2;
                cur++;
            } else {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < max - cur - 1; j++) {
                    sb.append(" ");
                }
                sb.append("|+");
                for (int j = 0; j < 2 * cur - 1; j++) {
                    sb.append("-");
                }
                sb.append("+|");
                System.out.println(sb);

                if (str.charAt(i) == '[') cur--;
                else cur++;
                i++;
            }
        }

        StringBuilder tmp2 = new StringBuilder();
        tmp2.append("+");
        for (int j = 0; j < 2 * cur - 1; j++) {
            tmp2.append("-");
        }
        tmp2.append("+");
        System.out.println(tmp2);
    }

    //-------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------

    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            fun3(in);
        }
    }
}
