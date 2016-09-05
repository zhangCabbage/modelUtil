package zhang.algorithm.modelUtil.Exercise.Contest.LeetCode.Third;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/9/4
 * Time: 下午8:07
 * To change this template use File | Settings | File Templates.
 */
public class IsSubsequence {
    /**
     *
     * 12 / 12 test cases passed
     * Status: Accepted
     * Runtime: 17 ms
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        int sIndex = 0;
        int tIndex = 0;
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        while (sIndex < sChar.length && tIndex < tChar.length) {
            if (sChar[sIndex] == tChar[tIndex]) {
                sIndex++;
            }
            tIndex++;
        }
        return sIndex == sChar.length;
    }

    public static void main(String[] args) {
        IsSubsequence test = new IsSubsequence();
        String s = "axc";
        String t = "ahbgdc";
        System.out.println(test.isSubsequence(s, t));
    }
}
