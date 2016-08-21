package zhang.algorithm.modelUtil.Exercise.Contest.LeetCode;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/8/21
 * Time: 下午12:54
 * To change this template use File | Settings | File Templates.
 * <p>
 * 中午1点到下午4点半, 加罚时
 * 9 hours 12 minutes 9 seconds,
 */
public class FirstUniqueCharacter {
    /**
     * leetcode question387. First Unique Character in a String
     * 104 / 104 test cases passed
     * Status: Accepted
     * Runtime: 31 ms
     *
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        int[] map = new int[128];//if is 0, no
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)]++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (map[s.charAt(i)] == 1)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        FirstUniqueCharacter test = new FirstUniqueCharacter();
        String s = "";
        System.out.println(test.firstUniqChar(s));
    }
}
