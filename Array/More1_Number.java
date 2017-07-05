package zhang.algorithm.modelUtil.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/6/10
 * Time: 下午7:16
 * To change this template use File | Settings | File Templates.
 * <p>
 * 有一类题目, 求数组中大于1/2、1/3...元素, 并且时间复杂度O(n), 空间复杂度O(1)
 */
public class More1_Number {
    /**
     * 求数组中个数超过1/2的元素
     *
     * @param nums
     * @return
     */
    public static int more_1_2(int[] nums) throws Exception {
        int x = 0;
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if (cnt == 0) {
                x = nums[i];
                cnt++;
            } else {
                if (x != nums[i]) cnt--;
                else cnt++;
            }
        }
        //判断是否有这样的数
        cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == x) cnt++;
        }
        if (cnt > nums.length / 2) return x;
        else throw new Exception("数组不存在超过1/2的元素");
    }

    /**
     * 举一反三, 这里计算数组中出现次数超过1/3元素, 我们知道最多有两个
     * 66 / 66 test cases passed.
     * Status: Accepted
     * Runtime: 5 ms, bit 29.28%
     *
     * @param nums
     * @return
     */
    public static List<Integer> more_1_3(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums.length < 3) {
            for (int i = 0; i < nums.length; i++) {
                if (i == 1 && nums[1] == nums[0]) continue;
                res.add(nums[i]);
            }
            return res;
        }

        int[] tmp = new int[2];
        int[] cnt = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == tmp[0]) cnt[0]++;
            else if (nums[i] == tmp[1]) cnt[1]++;
            else if (cnt[0] == 0) {
                tmp[0] = nums[i];
                cnt[0]++;
            } else if (cnt[1] == 0) {
                tmp[1] = nums[i];
                cnt[1]++;
            } else {
                cnt[0]--;
                cnt[1]--;
            }
        }

        //验证两数是否为超过1/3元素
        cnt = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < tmp.length; j++) {
                if (nums[i] == tmp[j]) {
                    cnt[j]++;
                    break;  //这里加break, 防止连续两次+1的情况。eg: {0, 0, 0}
                }
            }
        }
        for (int i = 0; i < tmp.length; i++) {
            if (cnt[i] > nums.length / 3) {
                res.add(tmp[i]);
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        int[] nums = {1, 2, 2, 3, 2, 1, 1, 3};
//        System.out.println(more_1_2(nums));
        System.out.println(more_1_3(nums));
    }
}
