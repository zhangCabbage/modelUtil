package zhang.algorithm.modelUtil.Exercise.Contest.LeetCode;

import java.util.ArrayList;
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

    public static void main(String[] args) {
        LexicographicalNumbers test = new LexicographicalNumbers();
        System.out.println(test.lexicalOrder2(123));
    }
}
