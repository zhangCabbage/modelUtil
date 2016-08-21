package zhang.algorithm.modelUtil.Exercise.Contest.LeetCode;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/8/21
 * Time: 下午1:14
 * To change this template use File | Settings | File Templates.
 */
public class LongestAbsolutePath {
    /**
     * leetcode question388. Longest Absolute File Path
     * 25 / 25 test cases passed
     * Status: Accepted
     * Runtime: 4 ms
     *
     * @param input
     * @return
     */
    public int lengthLongestPath(String input) {
        int longest = 0;
        int curLen = 0;

        int curStart = 0, curEnd = curStart;
        boolean isFile = false;

        int[] levels = new int[100];
        int curLevel = 0;

        while (curEnd < input.length()) {
            char c = input.charAt(curEnd);
            if (c == '.') isFile = true;

            if (c == '\n' || curEnd == input.length() - 1) {
                //two situation to stop
                if (isFile) {
                    for (int i = 0; i < curLevel; i++) {
                        curLen += levels[i];
                        curLen++;
                    }
                    curLen += c == '\n' ? (curEnd - curStart) : (curEnd - curStart + 1);
                    longest = Math.max(longest, curLen);
                    curLen = 0;
                    isFile = false;
                }
                if (curEnd == input.length() - 1) break;

                levels[curLevel] = curEnd - curStart;
                curLevel = 0;
                curEnd += 1;
                while (input.charAt(curEnd) == '\t') {
                    curEnd += 1;
                    curLevel++;
                }
                curStart = curEnd;
            } else {
                curEnd++;
            }
        }

        return longest;
    }

    public static void main(String[] args) {
        LongestAbsolutePath test = new LongestAbsolutePath();
        String s = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        System.out.println(test.lengthLongestPath(s));
    }
}
