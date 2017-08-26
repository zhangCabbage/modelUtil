package zhang.algorithm.modelUtil.Exercise.Examination.NetEase163;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by jiahua on 2017/8/26.
 * <p>
 * 8月26，帮李蕾做的校招网易笔试
 */
public class Autumn8_26 {

    /**
     * 找勇士打龙，龙的生命值为x, 勇士的杀伤力为y，需要 y > x 才能把龙杀死，并且我们需要付给勇士y金币。
     * 现给 n 条龙，m 个勇士，问杀死龙，需要雇佣勇士花费的最小金币数。
     * 比较简答。AC
     *
     * @param in
     */
    public static void test1(Scanner in) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] dragons = new int[n];
        int[] peoples = new int[m];
        for (int i = 0; i < n; i++) {
            dragons[i] = in.nextInt();
        }
        for (int i = 0; i < m; i++) {
            peoples[i] = in.nextInt();
        }

        Arrays.sort(dragons);
        Arrays.sort(peoples);
        int k = 0;
        int res = 0;
        for (int i = 0; i < n; i++) {
            while (k < m && peoples[k] < dragons[i]) k++;
            if (k == m) {
                System.out.println(-1);
                return;
            }
            res += peoples[k];
        }
        System.out.println(res);
    }

    /**
     * AB两点一个人，开始相向运动，速度分别为 v1 和 v2，问给一个时间 t，当前时间在端点相遇的次数
     * 感觉也比较简答。70%, why? 开始没有控制是否取余为0
     * 80%，更改tmp为long，是否可行未验证
     *
     * @param in
     */
    public static void test2(Scanner in) {
        int s = in.nextInt();
        int v1 = in.nextInt();
        int v2 = in.nextInt();
        int t = in.nextInt();

        int firstT = s / Math.abs(v1 - v2);
        int cnt = 0;
        if (s % Math.abs(v1 - v2) == 0 && t >= firstT) {
            cnt++;
            long tmp = Math.abs(v1 - v2) * (t - firstT); //待验证
            if (tmp > 0) cnt += tmp / (2L * s);
        }

        System.out.println(cnt);
    }

    /**
     * LCS，动态规划，没啥挑战难度
     * dp(i, j) = if(s(i) = t(j)) dp(i-1, j-1) + 1
     * .......... if(s(i) != t(j)) max(dp(i-1, j), dp(i, j-1))
     *
     * @param in
     */
    public static void test3(Scanner in) {
        String s1 = in.next();
        String s2 = in.next();

    }

    /**
     * 字符串解压缩算法： 3a{2{c}} -> accaccacc
     * 跟leetcode 394中的一道题有点像，但是不完全相同：d2[ac] -> dacac，这里数组一定在[]前
     * 但是这道题却不是，处理起来有点麻烦
     * test case: 3a{2{c}}
     *
     * @param in
     */
    public static void test4(Scanner in) {

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            test2(in);
        }
    }
}
