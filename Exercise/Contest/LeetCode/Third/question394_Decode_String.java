package zhang.algorithm.modelUtil.Exercise.Contest.LeetCode.Third;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/9/4
 * Time: 下午8:53
 * To change this template use File | Settings | File Templates.
 */
public class question394_Decode_String {
    /**
     * 考虑不全面,导致反复在兜圈子,BUG接着BUG
     *
     * @param s
     * @return
     */
    public String decodeString(String s) {
        int[] ends = new int[1];
        return decodeString(0, s, false, ends);
    }

    private String decodeString(int start, String s, boolean isBreak, int[] ends) {

        int num = 0;
        boolean flag = false;
        StringBuffer sb = new StringBuffer();
        String tempStr = "";
        int begin = start;
        int end = 0;
        boolean isFirst = true;

        for (int i = start; i <= s.length(); i++) {
            char c = i == s.length() ? '1' : s.charAt(i);
            if (c >= '0' && c <= '9') {
                if (flag) {
                    end = i;
                    tempStr = decodeString(i, s, true, ends);
                    i = ends[0];
                } else {
                    /////////
                    if (isFirst) {
                        end = i;
                        isFirst = false;
                    }
                    int temp = c - '0';
                    num = num * 10 + temp;
                }

            } else if (c == '[') {
                if (begin != end) {
                    sb.append(s.substring(begin, end));
                    end = 0;
                }
                begin = i + 1;
                flag = true;
                isFirst = true;
            } else if (c == ']') {
                if (end == 0) end = i;
                tempStr = s.substring(begin, end) + tempStr;
                for (int j = 0; j < num; j++) {
                    sb.append(tempStr);
                }

                tempStr = "";
                num = 0;
                flag = false;
                ends[0] = i;
                begin = i + 1;
                end = 0;
                if (isBreak) break;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        question394_Decode_String test = new question394_Decode_String();
        String s = "3[a]2[bc]";
        System.out.println(test.decodeString(s));
    }
}
