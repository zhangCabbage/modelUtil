package zhang.algorithm.modelUtil.Exercise.Contest.LeetCode.Ten;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/10/23
 * Time: 下午2:46
 * To change this template use File | Settings | File Templates.
 */
public class question438_Find_All_Anagrams_String {
    /**
     * <p>
     * 29 / 29 test cases passed
     * Status: Accepted
     * Runtime: 24 ms  =>  Runtime: 13 ms
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (p.length() > s.length()) return res;

        int[] base = new int[26];
        int len = p.length();
        for (int i = 0; i < len; i++) {
            base[p.charAt(i) - 'a']++;
        }

        int[] count = new int[26];
        boolean pre = false;  //is last combination is a Anagrams
        int start = 0;  //start of len(p) window in s
        int end = start + len - 1;  //end of len(p) window in s
        int loop = len;  //the len that we will loop to calculate the sum in every window.

        for (; start <= s.length() - len; start++, end++) {
            if (!pre) {
                for (int i = loop; i > 0; i--) {
                    count[s.charAt(end - i + 1) - 'a']++;
                }

                int i = 0;
                while (i < base.length) {
                    if (base[i] != count[i]) break;
                    i++;
                }
                if (i == base.length) {
                    res.add(start);
                    pre = true;
                } else {
//                    Arrays.fill(count, 0);  //优化
                    count[s.charAt(start) - 'a']--;
                    loop = 1;
                }
            } else {
                char last = s.charAt(end);
                //Three kinds of situations
                //1、s[start -1] == s[end] direct add
                //2、the new char of s[end] is not in p
                //2、the new char of s[end] is in p, but s[end] is not the s[start - 1]
                if (last == s.charAt(start - 1)) res.add(start);
                else if (base[last - 'a'] == 0) {
                    Arrays.fill(count, 0);
                    loop = len;
                    start = end;
                    end = start + len - 1;
                    pre = false;
                } else {
                    int i = start;
                    count[s.charAt(i - 1) - 'a']--;
                    for (; i < end; i++) {
                        char cur = s.charAt(i);
                        if (cur != last) count[cur - 'a']--;
                        else break;
                    }
                    //space char is i - start + 1, 向后延伸
                    loop = i - start + 1;
                    //i + 1 ==> start
                    start = i;
                    end = start + len - 1;
                    pre = false;
                }
            }

        }
        return res;
    }

    /**
     * 学会如何使用滑动窗口!!
     *
     * 29 / 29 test cases passed
     * Status: Accepted
     * Runtime: 19 ms
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (p.length() > s.length()) return res;

        int[] hash = new int[128];
        for (int i = 0; i < p.length(); i++) {
            hash[p.charAt(i)]++;
        }

        int left = 0, right = 0, count = p.length();
        while (right < s.length()) {
            if (hash[s.charAt(right++)]-- >= 1) count--;
            if (count == 0) res.add(left);
            if (right - left == p.length() && hash[s.charAt(left++)]++ >= 0) count++;
        }

        return res;
    }

    public static void main(String[] args) {
        question438_Find_All_Anagrams_String test = new question438_Find_All_Anagrams_String();
        String s = "abacbabc";
        String p = "abc";
        System.out.println(test.findAnagrams(s, p));
        System.out.println(test.findAnagrams2(s, p));
    }
}
