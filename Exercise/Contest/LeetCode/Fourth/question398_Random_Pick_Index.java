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
    //方法三
    private int[] index;
    private Map<Integer, Long> startEnd;//用来存放起始和结束下标

    public question398_Random_Pick_Index(int[] nums) {
        //方法一
        this.nums = nums;
        random = new Random();

        //方法二
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

        //方法三
        Map<Integer, Long> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            Long res = map.get(nums[i]);
            res = res == null ? 1 : res + 1;
            map.put(nums[i], res);
        }

        startEnd = map;
        Iterator<Integer> iterator = map.keySet().iterator();
        long start = 0;
        while (iterator.hasNext()) {
            int key = iterator.next();
            long value = map.get(key);
            startEnd.put(key, start << 32 | start);
            start += value;
        }

        index = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            Long value = startEnd.get(nums[i]);
            index[(int) (value & 0xFFFFFFFF)] = i;
            startEnd.put(nums[i], ++value);
        }
    }

    /**
     * First
     * <p>
     * Why this can run?
     * For the nth target, ++count is n.
     * Then the probability that rnd.nextInt(++count)==0 is 1/n.
     * Thus, the probability that return nth target is 1/n.
     * 说明不清晰, 对于第i个下标, 要想选中, 必须满足第i个选中, 之后 n - i 个都不选中
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
     * Memory Limit Exceeded, can not use map and list to storage the array nums
     *
     * @param target
     * @return
     */
    public int pick2(int target) {
        List<Integer> res = map.get(target);
        return res.get(random.nextInt(res.size()));
    }

    /**
     * Third
     * Memory Limit Exceeded
     *
     * @param target
     * @return
     */
    public int pick3(int target) {
        long res = startEnd.get(target);
        int start = (int) (res >> 32);
        int end = (int) (res & 0xFFFFFFFF);
        return index[random.nextInt(end - start) + start];
    }

    /**
     * 这里还有一位使用treeMap的解决方案, 虽然最后结果仍是 [Memory Limit Exceeded]
     * 但是解决问题的思路, 仍是有启发作用。
     * <p>
     * 使用 HashMap<Integer, TreeMap<Integer, Integer>> pos
     * num -> start_position -> number of the same successive digit(相同连续数字)
     * <num -> index> <index -> count of successive> (default is index -> 1)
     * the author want to use this way to optimized the memory consuming
     * <p>
     * [https://discuss.leetcode.com/topic/58322/what-on-earth-is-meant-by-too-much-memory/5]
     *
     * @param target
     * @return
     */
    public int pick4(int target) {
        return 0;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 3, 3};
        question398_Random_Pick_Index test = new question398_Random_Pick_Index(nums);
        System.out.println(test.pick2(3));
        System.out.println(test.pick3(3));
    }
}
