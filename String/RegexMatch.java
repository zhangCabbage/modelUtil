package zhang.algorithm.modelUtil.String;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/7/10
 * Time: 下午11:25
 * To change this template use File | Settings | File Templates.
 */
public class RegexMatch {

    public static boolean match(char[] str, char[] pattern) {
        return match(str, 0, pattern, 0);
    }

    /**
     * 这里要非常注意 i < str.length 这个条件, 很重要
     *
     * @param str
     * @param i
     * @param pattern
     * @param j
     * @return
     */
    private static boolean match(char[] str, int i, char[] pattern, int j) {
        if (str.length == i && pattern.length == j) return true;
        if (str.length != i && pattern.length == j) return false;
        //如此只剩下 i 的范围没有被控制

        if (j < pattern.length - 1 && pattern[j + 1] == '*') {
            if (i < str.length && (pattern[j] == '.' || str[i] == pattern[j])) {
                return match(str, i, pattern, j + 2) || match(str, i + 1, pattern, j + 2) || match(str, i + 1, pattern, j);
            } else {
                return match(str, i, pattern, j + 2);
            }
        }

        if (i < str.length && (str[i] == pattern[j] || pattern[j] == '.'))
            return match(str, i + 1, pattern, j + 1);
        return false;
    }

    public static void main(String[] args) {
        char[] str = "bcbbabab".toCharArray();
        char[] pattern = ".*a*a".toCharArray();
        System.out.println(match(str, pattern));
    }
}
