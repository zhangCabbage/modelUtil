package zhang.algorithm.modelUtil.AlgorithmDesign.Greedy;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/8/22
 * Time: 下午10:35
 * To change this template use File | Settings | File Templates.
 */
public class MinimumStepToOne {
    /**
     * 给一个数,只能进行+1、-1、/2的操作,问你最少步骤到1是多少?
     * 这里只考虑int范围内的数据,并考虑 负数 和 0 的情况
     * this way when input is 100_000_000 the output 32 is wrong.
     * the right answer is 31
     * please turn to the leetcode contest four first problem 397 Integer Replacement
     * =======
     * ×
     * =======
     *
     * @param num
     * @return
     */
    public int minStepToOne(int num) {
        if (num == 0) return 1;
        int count = 0;
        while (Math.abs(num) != 1) {
            count++;
            if (num % 2 == 0) num /= 2;
            else if ((num & 7) == 7) num += 1; //the count of 1 at tail of binary number is over 3
            else num -= 1;
        }
        if (num < 0) count += 2;
        return count;
    }

    public static void main(String[] args) {
        MinimumStepToOne test = new MinimumStepToOne();
        int num = 100_000_000;
        System.out.println(test.minStepToOne(num));
    }
}
