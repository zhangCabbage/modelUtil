package zhang.algorithm.modelUtil.Array;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/7/8
 * Time: 下午10:19
 * To change this template use File | Settings | File Templates.
 */
public class FindSumSequence {

    /**
     * @param sum
     * @return
     */
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        int r = sum / 2 + 1;
        int len = 2;
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        while (r - len + 1 > 0) {
            int tmp_sum = (r + r - len + 1) * len / 2;
            if (tmp_sum > sum) {
                r--;
            } else if (tmp_sum < sum) {
                len++;
            } else {
                ArrayList<Integer> list = new ArrayList<Integer>();
                int i = r - len + 1;
                while (i <= r) {
                    list.add(i++);
                }
                res.add(0, list);
                r--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        FindSumSequence test = new FindSumSequence();
        System.out.println(test.FindContinuousSequence(100));
    }
}
