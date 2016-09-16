package zhang.algorithm.modelUtil.Exercise.Contest.LeetCode.Fourth;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/9/11
 * Time: 上午11:23
 * To change this template use File | Settings | File Templates.
 */
public class question398_Random_Pick_Index {
    //方法一
    private int[] nums;
    private Random random;
    //方法二
    private Map<Integer, List<Integer>> map;

    public question398_Random_Pick_Index(int[] nums) {
        this.nums = nums;
        random = new Random();

        map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> res = map.get(nums[i]);
            if (res == null) {
                List<Integer> list = new ArrayList();
                list.add(i);
                map.put(nums[i], list);
            } else {
                res.add(i);
            }
        }
    }

    /**
     * First
     * <p>
     * Why this can run?
     * For the nth target, ++count is n.
     * Then the probability that rnd.nextInt(++count)==0 is 1/n.
     * Thus, the probability that return nth target is 1/n.
     * For (n-1)th target, the probability of returning it is (n-1)/n * 1/(n-1)= 1/n.
     * <p>
     * 13 / 13 test cases passed
     * Status: Accepted
     * Runtime: 339 ms
     *
     * @param target
     * @return
     */
    public int pick(int target) {
        int res = -1;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target && random.nextInt(++count) == 0) {
                res = i;
            }
        }

        return res;
    }

    /**
     * Second
     * Time Limit Exceeded, can not use map and list to storage the array nums
     *
     * @param target
     * @return
     */
    public int pick2(int target) {
        List<Integer> res = map.get(target);
        return res.get(random.nextInt(res.size()));
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 3, 3};
        question398_Random_Pick_Index test = new question398_Random_Pick_Index(nums);
        System.out.println(test.pick2(3));
    }
}
