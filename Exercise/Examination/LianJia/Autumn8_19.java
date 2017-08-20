package zhang.algorithm.modelUtil.Exercise.Examination.LianJia;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by jiahua on 2017/8/19.
 */
public class Autumn8_19 {

    /**
     * 第一道：给一个有重复数的数组，输出去重排序后的结果
     * AC，送分
     *
     * @param cin
     */
    public static void test1(Scanner cin) {
        int n = cin.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = cin.nextInt();
        }
        Arrays.sort(nums);

        int i = 0, j = 0;
        int cnt = 0;
        while (i < n) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                cnt++;
                nums[j++] = nums[i];
            }
            i++;
        }

        System.out.println(cnt);
        for (int k = 0; k < j; k++) {
            System.out.print(nums[k]);
            if (k != j - 1)
                System.out.print(" ");
        }
        System.out.println();
    }

    //===================================================================================
    //
    //===================================================================================

    /**
     * 题意好像没有看懂哎
     * <p>
     * 后来读懂了，2016网易笔试题（奖学金）一样的一道题
     * 使用贪心算法并不难
     *
     * @param cin
     */
    public static void test2(Scanner cin) {
        int n = cin.nextInt();
        int r = cin.nextInt();
        int avg = cin.nextInt();

        int[][] a = new int[n][2];
        int diff = avg * n;

        for (int i = 0; i < n; i++) {
            a[i][0] = cin.nextInt();
            diff -= a[i][0];
            a[i][1] = cin.nextInt();
        }

        Arrays.sort(a, new Comparator<int[]>() {
            @Override
            public int compare(int[] x, int[] y) {
                return x[1] - y[1];
            }
        });
        int i = 0, j = 0; // i表示努力得来的分数， j表示a[x][1]的下标
        int cnt = 0;
        while (i < diff) {
            while (i < diff && a[j][0] < r) {
                int tmp = Math.min(diff - i, r - a[j][0]);
                a[j][0] += tmp;
                i += tmp;
                cnt += a[j][1] * tmp;
            }
            j++;
        }

        System.out.println(cnt);
    }

    //===================================================================================
    //
    //===================================================================================

    /**
     * 给一个数组，只包含[1 2 3]，最后需要1在前，3在后排序
     * 问最少次数移动使得按序排列的交换次数
     * 56%
     * <p>
     * [特殊用例]:
     * 5
     * 1 2 1 1 1
     * 2
     * 但是只用1次交换就可以
     * 忘记考虑如果没有3的情况，
     * <p>
     * [解决方案]:
     * 貌似可以采用单独考虑这种异常情况，在牛客上看到另外一种思路
     * 把原数组复制,然后排序,然后两个数组对比,一样的就不用换,
     * 如果 a[i] = b[i+x]; a[i+x] = b[i] 可以两两配对的就是交换一次, 然后剩下不能两两配对的就是三个一组交换两次
     *
     * @param cin
     */
    public static void test3(Scanner cin) {
        int n = cin.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = cin.nextInt();
        }

        test3_2(nums);

        int l = 0, r = n - 1;
        int cur = l;
        int cnt = 0;
        while (cur < r) {
            while (cur < r && l == cur && nums[cur] == 1) {
                l++;
                cur++;
            }
            while (cur < r && nums[cur] == 2) cur++;
            while (cur < r && nums[r] == 3) r--;

            if (cur < r && nums[cur] == 1 && cur > l) {
                swap(nums, l++, cur);
                cnt++;
            } else if (cur < r && nums[cur] == 3 && cur < r) {
                swap(nums, cur, r--);
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    public static void swap(int[] nums, int m, int n) {
        int temp = nums[m];
        nums[m] = nums[n];
        nums[n] = temp;
    }

    /**
     * 具体公式参见：
     * http://www.dewen.net.cn/q/7967/%E5%85%B3%E4%BA%8E%E2%80%9C%E7%BB%99%E5%AE%9A%E7%9A%84%E4%B8%80%E4%B8%AA1%2C2%2C3%E7%BB%84%E6%88%90%E7%9A%84%E6%95%B0%E5%AD%97%E5%BA%8F%E5%88%97%EF%BC%8C%E6%B1%82%E6%8E%92%E6%88%90%E5%8D%87%E5%BA%8F%E6%89%80%E9%9C%80%E7%9A%84%E6%9C%80%E5%B0%91%E4%BA%A4%E6%8D%A2%E6%AC%A1%E6%95%B0%E2%80%9D%E7%9A%84%E7%AE%97%E6%B3%95%E9%A2%98%E6%9C%89%E4%B8%A4%E4%B8%AA%E5%BE%88%E5%B7%A7%E5%A6%99%E7%9A%84%E8%A7%A3%E7%AD%94%EF%BC%8C%E4%BD%86%E6%98%AF%E6%88%91%E6%B2%A1%E7%9C%8B%E6%87%82%EF%BC%8C%E6%B1%82%E5%A4%A7%E7%A5%9E%E8%A7%A3%E9%87%8A%EF%BC%81
     * 感觉是正确的
     * 再推荐一个分享位置：https://paste.ubuntu.com/25351891/
     *
     * @param nums
     */
    public static void test3_2(int[] nums) {
        int[] sortNums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sortNums);

        // cx_y表示本该放x的位置之前放的是y的个数
        int c1_2 = 0, c2_1 = 0, c1_3 = 0, c2_3 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sortNums[i] == 1) {
                if (nums[i] == 2) c1_2++;
                if (nums[i] == 3) c1_3++;
            }
            if (sortNums[i] == 2) {
                if (nums[i] == 1) c2_1++;
                if (nums[i] == 3) c2_3++;
            }
        }

        int cnt = Math.max(c1_2, c2_1) + c1_3 + c2_3;
        System.out.println(cnt);
    }
    //===================================================================================
    // Main
    //===================================================================================

    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        while (cin.hasNextInt()) {
            test2(cin);
        }
    }
}
