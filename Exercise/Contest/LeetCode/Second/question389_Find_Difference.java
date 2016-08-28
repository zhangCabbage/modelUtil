package zhang.algorithm.modelUtil.Exercise.Contest.LeetCode.Second;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/8/28
 * Time: 下午9:22
 * To change this template use File | Settings | File Templates.
 */
public class question389_Find_Difference {
    /**
     * <strong>result of test:</strong>
     * 52 / 52 test cases passed
     * Status: Accepted
     * Runtime: 6 ms
     *
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference(String s, String t) {
        char res = 0;
        for(char c : s.toCharArray()){
            res ^= c;
        }
        for(char c : t.toCharArray()){
            res ^= c;
        }
        return res;
    }

    public static void main(String[] args) {
        question389_Find_Difference test = new question389_Find_Difference();
        String s = "abcd";
        String t = "aebcd";
        System.out.println(test.findTheDifference(s, t));
    }
}
