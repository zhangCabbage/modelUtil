package zhang.algorithm.modelUtil.Exercise.Contest.LeetCode.Third;

import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/9/4
 * Time: 下午8:53
 * To change this template use File | Settings | File Templates.
 * =>
 * update: deal
 * Date: 16/11/20
 * Time: 下午4:30
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

    //-----------------------------------------------------------------------------------------------------------------
    //重新做一遍
    //the tag of this problem is [stack] or [depth-first search]
    //当然也可以用两个stack栈来分别存储数字和重复字符串
    //-----------------------------------------------------------------------------------------------------------------

    /**
     * 编程是个精细活, 需要精心设计, 尽管不是一个很难的算法, 如果马虎的话, 反而会反复兜圈子
     * 26 / 26 test cases passed.
     * Status: Accepted
     * Runtime: 3 - 4 ms, bit 78.37%
     *
     * @param s
     * @return
     */
    public String decodeString2(String s) {
        StringBuffer sb = new StringBuffer();
        StringBuffer copy = null;
        int copyNum = 0;

        boolean isInto = false;  //表示是否进入嵌套, 这里因为没有使用stack, 所以...
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                int j = i;
                if (isInto) {
                    int level = 0, cur = 0;
                    while (j < s.length()) {
                        if (s.charAt(j) == '[') level++;
                        else if (s.charAt(j) == ']' && (++cur) == level) break;

                        j++;
                    }

                    copy.append(decodeString2(s.substring(i, j + 1)));
                } else {
                    copyNum = 0;
                    while (j < s.length())
                        if (s.charAt(j) != '[') copyNum = copyNum * 10 + (s.charAt(j++) - '0');
                        else break;

                    isInto = true;
                    copy = new StringBuffer();
                }
                i = j;
            } else if (c == ']') {
                isInto = false;
                for (int j = 0; j < copyNum; j++)
                    sb.append(copy);
            } else {
                if (isInto) copy.append(c);
                else sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * The result is same to decodeString2, what's more, it look likes more easy to understand.
     * 【recommend solution】
     *
     * @param s
     * @return
     */
    public String decodeString3(String s) {
        Stack<Integer> intStack = new Stack<>();
        Stack<StringBuffer> strStack = new Stack<>();
        StringBuffer sb = new StringBuffer();

        int k = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                k = k * 10 + (c - '0');
            } else if (c == '[') {
                intStack.push(k);
                strStack.push(sb);

                sb = new StringBuffer();
                k = 0;
            } else if (c == ']') {
                StringBuffer tmp = sb;
                sb = strStack.pop();
                for (k = intStack.pop(); k > 0; k--) sb.append(tmp);
            } else sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        question394_Decode_String test = new question394_Decode_String();
        String s = "abc3[d2[ac]]";
        System.out.println(test.decodeString2(s));
        System.out.println(test.decodeString3(s));
    }
}
