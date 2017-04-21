package zhang.algorithm.modelUtil.Exercise.Contest.LeetCode.First;

import zhang.algorithm.modelUtil.NumberTheory.MathTools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/8/21
 * Time: 上午10:15
 * To change this template use File | Settings | File Templates.
 */
public class LexicographicalNumbers {
    /**
     * leetcode question386. Lexicographical Numbers
     * attention the order: 1, 10, 100, 11, 12
     *
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            int k = 10;
            int temp = i;
            int num = i;
            while (num <= n) {
                res.add(num);
                if (k == 10) {
                    temp *= k;
                    k = 0;
                }
                num = temp + k;
                k++;
            }
        }
        return res;
    }

    /**
     * if n = 123
     * 1, 10, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109,
     * 11, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119,
     * 12, 120, 121, 122, 123,
     * 13, 14, 15, 16, 17, 18, 19,
     * 2, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29,....
     * <p>
     * 25 / 25 test cases passed
     * Status: Accepted
     * Runtime: 167 ms
     *
     * @param n n <= 5,000,000
     * @return
     */
    public List<Integer> lexicalOrder2(int n) {
        List<Integer> res = new ArrayList<>();

        int num = 1;//begin number is 1
        while (true) {
            while (num <= n) {
                res.add(num);
                num *= 10;
            }
            num = num / 10 + 1;

            int recurNum = 10;//the count of recursive
            recurNum = recurNum - (num - 1) % 10;

            int k = 1;
            while (num <= n && k < recurNum) {
                res.add(num++);
                k++;
            }

            num = (num - 1) / 10 + 1;
            while (num % 10 == 0) {
                num /= 10;
            }

            if (num == 1) break;
        }

        return res;
    }

    /**
     * when input 49999 -> Time Limit Exceeded
     *
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder3(int n) {
        List<Integer> res = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            res.add(i + 1);
        }
        Collections.sort(res, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String num1 = o1.toString();
                String num2 = o2.toString();
                int i = 0, j = 0;
                while (i < num1.length() && j < num2.length()) {
                    char x = num1.charAt(i++);
                    char y = num2.charAt(j++);
                    if (x < y) return -1;
                    else if (x > y) return 1;
                }
                if (i < num1.length()) return 1;
                if (j < num2.length()) return -1;
                return 0;
            }
        });
        return res;
    }

    /**
     * 最初以为是string拖慢了处理速度, 改进之后发现仍然Time Limit Exceeded
     *
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder4(int n) {
        List<Integer> res = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            res.add(i + 1);
        }
        Collections.sort(res, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int num1 = MathTools.reverseInt(o1);
                int num2 = MathTools.reverseInt(o2);
                if (num1 == num2) return o1.intValue() - o2.intValue();

                while (num1 != 0 && num2 != 0) {
                    int i = num1 % 10;
                    int j = num2 % 10;
                    if (i < j) return -1;
                    else if (i > j) return 1;

                    num1 /= 10;
                    num2 /= 10;
                }
                return num1 == 0 ? -1 : 1;
            }
        });
        return res;
    }

    /**
     * Review Time: 2017-04-21 16:16:17
     * 相比第一次做的结果这次思路更加清晰, 代码更加简洁。
     * 注意: 192的情况下, 19, 190, 191, 192, 2, 20
     * 26 / 26 test cases passed.
     * Status: Accepted
     * Runtime: 143 ms, 94.86%
     *
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder5(int n) {
        List<Integer> res = new ArrayList<>(n);
        int cur = 1;
        for (int i = 0; i < n; i++) {
            res.add(cur);
            if (cur * 10 <= n) cur *= 10;
            else {
                while (cur >= n || cur % 10 == 9) cur /= 10;  //here attention please
                cur++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LexicographicalNumbers test = new LexicographicalNumbers();
//        System.out.println(test.lexicalOrder2(123));
//        System.out.println(test.lexicalOrder3(123));
        System.out.println(test.lexicalOrder4(192));
        System.out.println(test.lexicalOrder5(192));
    }
}
