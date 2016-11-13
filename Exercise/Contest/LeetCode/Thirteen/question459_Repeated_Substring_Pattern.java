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
     * 能立马想到的方法, 最普通直接
     * 100 / 100 test cases passed.
     * Status: Accepted
     * Runtime: 30 ms
     * <p>
     * 如何利用只有小写字母来做文章提升呢?
     *
     * @param str
     * @return
     */
    public boolean repeatedSubstringPattern(String str) {
        boolean res = false;
        int len = 1, pre = 0;
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(pre)) {
                pre++;
                if (pre == len) {
                    pre = 0;
                    res = true;
                }
            } else {
                res = false;
                len = i - pre + 1;
                if (len > str.length() / 2) break;
                pre = 0;
                i = len - 1;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        question459_Repeated_Substring_Pattern test = new question459_Repeated_Substring_Pattern();
        String str = "ababbaaaaaababbaaaaaababbaaaaaababbaaaaaababbaaaaaababbaaaaaababbaaaaaababbaaaaaababbaaaaaababbaaaaa";
        System.out.println(test.repeatedSubstringPattern(str));
    }
}
