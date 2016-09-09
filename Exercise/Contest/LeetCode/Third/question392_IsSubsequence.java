package zhang.algorithm.modelUtil.Exercise.Contest.LeetCode.Third;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/9/4
 * Time: 下午8:07
 * To change this template use File | Settings | File Templates.
 */
public class question392_IsSubsequence {
    /**
     * judge s is in t
     * <p>
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

    /**
     * 13 / 13 test cases passed
     * Status: Accepted
     * Runtime: 2 ms
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence2(String s, String t) {
        int lastCharPosition = 0;
        for (int i = 0; i < s.length(); i++) {
            int index = t.indexOf(s.charAt(i), lastCharPosition);
            if (index < lastCharPosition) {
                return false;
            } else {
                lastCharPosition = index + 1;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        question392_IsSubsequence test = new question392_IsSubsequence();
        String s = "axc";
        String t = "ahbgdc";
        System.out.println(test.isSubsequence(s, t));
    }
}
