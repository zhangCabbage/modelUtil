package zhang.algorithm.modelUtil.Exercise.Examination.HUAWEI;

import java.util.*;

/**
 * Created by jiahua on 2017/8/16.
 * <p>
 * 总共3道题，这天晚上做的时候头有点晕。状态不好
 */
public class Autumn8_16 {

    /**
     * 送分题，判断两个字符串连续相同的最大长度。如果小于2，那么输出0；否则输出最大长度
     * AC
     *
     * @param in
     */
    public static void test1(Scanner in) {
        char[] str1 = in.next().toCharArray();
        char[] str2 = in.next().toCharArray();
        if (str1.length != str2.length) {
            System.out.println(-1);
            return;
        }
        int max = 0, cur = 0;
        for (int i = 0; i < str1.length; i++) {
            if (Character.isDigit(str1[i]) && Character.isDigit(str2[i])) {
                if (str1[i] == str2[i]) {
                    cur++;
                } else {
                    max = Math.max(cur, max);
                    cur = 0;
                }
            } else {
                System.out.println(-1);
                return;
            }
        }

        max = Math.max(cur, max);
        if (max < 2) System.out.println(0);
        else System.out.println(max);
    }


    /**
     * A->B, B->C, C->D, D->A, A->E那么就说ABCD是循环的, E不是
     * 输入数据格式如下：
     * {0x00, 0x01},
     * {0x01, 0x03},
     * {0x01, 0x00}
     * <p>
     * 输出格式：
     * {0x00, true},
     * {0x01, true},
     * {0x03, false}
     * 20%
     *
     * @param in
     */
    public static void test2(Scanner in) {
        Map<String, String> map = new HashMap<>();
        Set<String> keys = new HashSet<>();
        while (true) {
            String s = in.nextLine();
            String[] a_b = s.split(", *");
            String a = a_b[0].substring(1);
            String b = a_b[1].substring(0, a_b[1].length() - 1);
            map.put(a, b);
            keys.add(a);
            keys.add(b);

            if (s.charAt(s.length() - 1) != ',') break;
        }

        String[] keystr = new String[keys.size()];
        int k = 0;
        for (String key : keys) {
            keystr[k++] = key;
        }

        Arrays.sort(keystr);
        for (int i = 0; i < keystr.length; i++) {
            String key = keystr[i];
            String tmp = map.containsKey(key) ? map.get(key) : null;
            while (tmp != null && tmp != key)
                tmp = map.containsKey(tmp) ? map.get(tmp) : null;
            System.out.println("{" + key + ", " + (tmp == null ? "false" : "true") + "}" + (i == keys.size() - 1 ? "" : ","));
        }
    }


    /**
     * 是否对一个邮箱输入进行加密，根据已知条件写出业务逻辑
     * 注意：这里使用 in.nextLine() 来一行一行的读入输入数据
     * AC
     *
     * @param in
     */
    public static void test3(Scanner in) {
        boolean valid = true;
        char[] c = in.nextLine().toCharArray();
        int beforeAt = 0;//for the munber before @;
        int len = c.length;
        for (int i = 0; i < len; i++) {
            if (c[i] != '@') {
                if (isValue(c[i]) || isValueBefore(c[i])) beforeAt++;
                else beforeAt = 0;
            } else {
                if (beforeAt >= 3 && beforeAt <= 119) {
                    int j = i;
                    while (c[i + 1] != '.') {
                        i++;
                        if (isValue(c[i]) || isValueAfter(c[i])) {

                        } else {
                            valid = false;
                            break;
                        }
                    }

                    if (valid) {
                        c[j - 1] = '*';
                        c[j - 2] = '*';
                        c[j - 3] = '*';
                    } else {
                        beforeAt = 0;
                    }
                } else {
                    beforeAt = 0;
                }
            }
        }
        System.out.println(String.valueOf(c));
    }

    private static boolean isValue(char c) {
        return Character.isLetterOrDigit(c);
    }

    private static boolean isValueBefore(char c) {
        char sp[] = {'&', '=', '+', '$', ',', ';', '?', '/', '-', '_', '.', '!', '~', '*', '\'', '(', ')', '#'};
        for (int i = 0; i < sp.length; i++) {
            if (sp[i] == c) return true;
        }
        return false;
    }

    private static boolean isValueAfter(char c) {
        if (c == '-')
            return true;
        return false;
    }


    /**
     * Main方法
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            test3(in);
        }
    }
}
