package zhang.algorithm.modelUtil.Exercise.Contest.LeetCode.Thirteen;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/11/13
 * Time: 下午2:43
 * To change this template use File | Settings | File Templates.
 *
 * Deal
 */
public class question455_Assign_Cookies {
    /**
     * How to improve? the solution of Discuss is same as me.
     * <p>
     * 21 / 21 test cases passed.
     * Status: Accepted
     * Runtime: 25 ms
     *
     * @param g child
     * @param s cookies
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0, j = 0;
        while (i < g.length && j < s.length) {
            if (s[j] >= g[i]) i++;
            j++;
        }
        return i;
    }

    public static void main(String[] args) {
        question455_Assign_Cookies test = new question455_Assign_Cookies();
        int[] g = {1, 2, 3};
        int[] s = {8};
        System.out.println(test.findContentChildren(g, s));
    }
}
