package zhang.algorithm.modelUtil.Exercise.Examination.DiDi;

import java.util.Scanner;

/**
 * Created by jiahua on 2017/8/26.
 */
public class Autumn8_26 {

    /**
     * 最大连续子数组问题
     *
     * @param in
     */
    public static void test1(Scanner in) {
        String[] s = in.nextLine().split(" +");
        int[] nums = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            nums[i] = Integer.parseInt(s[i]);
        }
        if (nums.length >= 1) {
            long max = nums[0], pre = max;
            for (int i = 1; i < nums.length; i++) {
                pre = Math.max(nums[i], pre + nums[i]);
                max = Math.max(max, pre);
            }
            System.out.println(max);
        } else {
            System.out.println(0);
        }
    }

    //===================================================================================
    //
    //===================================================================================

    /**
     * 找第K大的数
     *
     * @param in
     */
    public static void test2(Scanner in) {
        String[] s = in.nextLine().split(" +");
        int[] nums = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            nums[i] = Integer.parseInt(s[i]);
        }

        int n = in.nextInt();
        System.out.println(findKthLargest(nums, n));
    }

    private static int findKthLargest(int[] nums, int k) {
        return findKthLargest(nums, 0, nums.length - 1, k);
    }

    private static int findKthLargest(int[] nums, int start, int end, int k) {
        int l = start;
        int r = end;
        int mid = l + (r - l >> 1);
        choicePovit(nums, l, mid, r);
        int povit = nums[l];

        while (l < r) {
            while (l < r && nums[r] <= povit) r--;
            nums[l] = nums[r];
            while (l < r && nums[l] >= povit) l++;
            nums[r] = nums[l];
        }
        nums[l] = povit;

        if (l - start + 1 == k) return povit;
        else if (l - start + 1 < k) return findKthLargest(nums, l + 1, end, k - (l - start + 1));
        else return findKthLargest(nums, start, l - 1, k);
    }

    /**
     * 选择中间的一个值
     *
     * @param nums
     * @param l
     * @param m
     * @param r
     */
    private static void choicePovit(int[] nums, int l, int m, int r) {
        if (nums[l] > nums[m]) {
            if (nums[m] > nums[r]) {
                exchange(nums, m, l);
            } else if (nums[r] > nums[l]) {
                //
            } else {
                exchange(nums, r, l);
            }
        } else {
            if (nums[l] > nums[r]) {
                //
            } else if (nums[r] > nums[m]) {
                exchange(nums, m, l);
            } else {
                exchange(nums, r, l);
            }
        }
    }

    private static void exchange(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    //===================================================================================
    //
    //===================================================================================

    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        test2(in);
    }
}
