package zhang.algorithm.modelUtil.Exercise.Examination.NetEase163;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/3/25
 * Time: 下午5:29
 * To change this template use File | Settings | File Templates.
 * <p>
 * 网易2018年春招笔试
 * [经验教训]:
 * 1) 网易的题目是固定的从某几道中随机生成3道, 可能难题在前面, 简单的题在后面。以后做题,考虑5分钟还没有思路, 那就看下一题!
 * 2) 限时测试时, 优先写能想出来的思路! 暴力能做出来的优先尝试(比如第3题)
 */
public class Spring3_25 {

    /**
     * 1)  -->  30%(开始时假设每块砖都必须使用)
     *
     * @param in
     */
    public static void fun1(Scanner in) {
        int n = in.nextInt();
        int[] nums = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
            sum += nums[i];
        }

        int[][] dp = new int[n + 1][Math.min(sum / 2, 500000) + 1];


    }

    //-------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------

    /**
     * 2)  -->  AC
     * B表示男生, G表示女生, 男女排队靠在一起会发生冲突
     * 现在给一个队列, 让你以最小的移动使得冲突最小, 即男生都在一块, 女生都在一块
     *
     * @param in
     */
    public static void fun2(Scanner in) {
        String s = in.next();
        int cnt = Math.min(left(s, 'B'), left(s, 'G'));
        System.out.println(cnt);
    }

    /**
     * c放在左边
     *
     * @param s
     * @param c
     * @return
     */
    public static int left(String s, char c) {
        int res = 0;
        int l = 0;
        int i = l;
        while (i < s.length()) {
            if (s.charAt(i) == c) {
                res += (i - l);
                l++;
            }
            i++;
        }

        return res;
    }

    //-------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------

    /**
     * 3)  -->  80%  -->  AC
     * 这道题没有AC, 当把 while (in.hasNext()) 去掉之后, 即可AC, 非常奇怪!!
     *
     * @param in
     */
    public static void fun3(Scanner in) {
        int n = in.nextInt();
        int m = in.nextInt();
        Set<String> mySets = new HashSet<>();
        Set<String> dirSet = new HashSet<>();

        for (int i = 0; i < n; i++)
            mySets.add(in.next());
        for (int i = 0; i < m; i++)
            dirSet.add(in.next());

        int total = 0;
        for (String s : mySets) {
            if (dirSet.contains(s)) {
                int cnt = s.length();
                total += (cnt * cnt);
            }
        }

        System.out.println(total);
    }

    //-------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------

    /**
     * 4)  -->  AC
     * n 个任务给 2个CPU处理, 求所需最小处理时间
     * 处理思想:
     * sum 为 n 个任务总和, 一个cpu处理时间为 n1, 第二个cpu处理时间为 sum - n1
     * 最终处理时间为 max(n1, sum - n1), 使得 n1 尽可能接近 sum/2 即可。
     * 因此转换为01背包问题: 在 sum/2 时间内, 尽可能多的完成任务
     *
     * @param in
     */
    public static void fun4(Scanner in) {
        int n = in.nextInt();
        int[] nums = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt() >> 10;
            sum += nums[i];
        }
        int[][] dp = new int[n + 1][sum / 2 + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (nums[i - 1] <= j)
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i - 1]] + nums[i - 1]);
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }

        System.out.println(Math.max(dp[n][sum / 2], sum - dp[n][sum / 2]) << 10);
    }

    //-------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------

    /**
     * 5)  -->  AC
     *
     * @param in
     */
    public static void fun5(Scanner in) {
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++)
            nums[i] = in.nextInt();

        int[] map = new int[1001];
        int j = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (map[nums[i]] == 0) {
                map[nums[i]] = 1;
                nums[j] = nums[i];
                j--;
            }
        }

        for (int i = j + 1; i < n - 1; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println(nums[n - 1]);
    }

    //-------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------

    /**
     * 6)  -->  30%
     * 我把int[] map = new int[n] 改为 int[] map = new int[6]  -->  AC
     * 看清楚题意
     *
     * @param in
     */
    public static void fun6(Scanner in) {
        int n = in.nextInt();
        String[] strs = new String[n];
        for (int i = 0; i < n; i++)
            strs[i] = in.next();

        int[] map = new int[6];
        System.out.println(helper(0, strs, map));
    }

    private static int helper(int index, String[] strs, int[] map) {
        int cnt = 0;
        if (index == strs.length) return ++cnt;

        for (int i = 0; i < strs[index].length(); i++) {
            int c = strs[index].charAt(i) - '0';
            if (map[c] == 1) continue;
            map[c] = 1;
            cnt += helper(index + 1, strs, map);
            map[c] = 0;
        }
        return cnt;
    }

    //-------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------

    /**
     * 7)  --> AC
     *
     * @param in
     */
    public static void fun7(Scanner in) {
        String s = in.next();
        int total = 0;
        char operate = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                switch (operate) {
                    case '+':
                        total += (c - '0');
                        break;
                    case '-':
                        total -= (c - '0');
                        break;
                    case '*':
                        total *= (c - '0');
                        break;
                }
            } else operate = c;
        }

        System.out.println(total);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            fun6(in);
        }
    }
}
