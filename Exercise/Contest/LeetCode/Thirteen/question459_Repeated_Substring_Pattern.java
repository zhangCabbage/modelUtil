package zhang.algorithm.modelUtil.Exercise.Contest.LeetCode.Thirteen;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/11/13
 * Time: 下午2:57
 * To change this template use File | Settings | File Templates.
 */
public class question459_Repeated_Substring_Pattern {
    /**
     * 方法一:
     * 能立马想到的方法, 最普通直接, 时间复杂度未知(偶然所得方法)。
     * 100 / 100 test cases passed.
     * Status: Accepted
     * Runtime: 27 - 30 ms
     * <p>
     * 如何利用只有小写字母来做文章提升呢?
     *
     * @param str
     * @return
     */
    public boolean repeatedSubstringPattern(String str) {
        boolean res = false;
        int len = 1, pre = 0; //pre is the repeated subString index
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(pre)) {
                pre++;
                if (pre == len) {
                    pre = 0;
                    res = true;
                }
            } else {
                res = false;
                //if fail, then the repeat len is become the current position
                //because the situation of ababbaaaaa-ababbaaaaa, when pre is 1, and i is 10(the first char of second repeat index is 10)
                //this time we need fallback! here has five a, we need back five times.
                len = i - pre + 1;
                if (len > str.length() / 2) break;
                pre = 0;
                i = len - 1;
            }
        }

        return res;
    }

    //----------------------------------------------------------------------------------------
    //技巧性较强, 使用经典算法巧解。。
    //----------------------------------------------------------------------------------------

    /**
     * 方法二:
     * 这是一个kmp问题, 还记得kmp中算模式串的next数组么, 这个next数组就是一个前后缀相同的个数数组
     * next数组, 除开当前下标的值, 前后缀相同的个数
     * 注意, 这道题的精髓在 n % (n - len) !!
     * <p>
     * 100 / 100 test cases passed.
     * Status: Accepted
     * Runtime: 26 - 31 ms
     *
     * @param str
     * @return
     */
    public boolean repeatedSubstringPattern2(String str) {
        //This is the kmp issue
        int[] next = kmp(str);
        int len = next[str.length() - 1];
        int n = str.length();
        return (len > 0 && n % (n - len) == 0);
    }

    /**
     * 变形的next数组算法, next数组 当前下标cur 对应的next数组所表示的相同前后缀, [包含]当前char[cur]
     *
     * @param str
     * @return
     */
    private int[] kmp(String str) {
        int[] next = new int[str.length()];
        int pre = 0, cur = 1;
        next[0] = pre;

        while (cur < str.length()) {
            if (str.charAt(cur) == str.charAt(pre)) {
                next[cur++] = ++pre;
            } else {
                if (pre == 0) next[cur++] = 0;
                else pre = next[pre - 1];
            }
        }

        return next;
    }

    //----------------------------------------------------------------------------------------
    //暴力方法
    //----------------------------------------------------------------------------------------

    /**
     * 方法三:
     * Slow than me!
     * 先选取重复个数, 然后遍历检查是否正确, 完全试错, 前一次的经验对后没有作用! 时间复杂度O(n^2)
     * 100 / 100 test cases passed.
     * Status: Accepted
     * Runtime: 77 ms
     *
     * @param str
     * @return
     */
    public boolean repeatedSubstringPattern3(String str) {
        int len = str.length();
        if (len < 2) return false;
        for (int i = 2; i <= len / 2; i++) {
            if (len % i != 0) continue;
            if (check(str, i)) return true;
        }
        return false;
    }

    private boolean check(String str, int repeat) {
        int len = str.length();
        String unit = str.substring(0, len / repeat);
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) != unit.charAt(i % (len / repeat))) return false;
        }
        return false;
    }

    //----------------------------------------------------------------------------------------
    //test main
    //----------------------------------------------------------------------------------------

    public static void main(String[] args) {
        question459_Repeated_Substring_Pattern test = new question459_Repeated_Substring_Pattern();
        String str = "abcabcabc";
        System.out.println(test.repeatedSubstringPattern(str));
        System.out.println(test.repeatedSubstringPattern2(str));
    }
}
