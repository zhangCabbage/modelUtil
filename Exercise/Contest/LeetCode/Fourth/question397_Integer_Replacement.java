package zhang.algorithm.modelUtil.Exercise.Contest.LeetCode.Fourth;

import zhang.algorithm.modelUtil.BitManipultion.BitTool;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/9/11
 * Time: 上午10:55
 * To change this template use File | Settings | File Templates.
 */
public class question397_Integer_Replacement {

    public int integerReplacement(int n) {
        int count = 0;
        while (n != 1) {
            BitTool.showBinary(n);
            if (n % 2 == 0) {
                n /= 2;
            } else if ((n & 7) == 7) {
                n += 1;
            } else {
                n -= 1;
            }
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        question397_Integer_Replacement test = new question397_Integer_Replacement();
        int n = 100000000; //31 ?
        System.out.println(test.integerReplacement(n));
    }
}
