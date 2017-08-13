package zhang.algorithm.modelUtil.Exercise.Examination.NetEase163;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_Lenovo
 * Date: 2017/8/12
 * Time: 18:47
 * To change this template use File | Settings | File Templates.
 * <p>
 * 网易内推笔试，三道题只做了50%、0%、90%
 * 最难的是第二道题，应该做出来两道的
 * 还有一个教训就是，不要跟一块参加笔试的人一起做笔试，不然直接copy，会造成作弊！！
 */
public class Autumn8_12 {
    /**
     * 一个数组每次插入旋转。
     * eg:
     * 1         ->   1
     * 1 2       ->   2 1
     * 1 2 3     ->   3 1 2
     * 1 2 3 4   ->   4 2 1 3
     * <p>
     * 方法一：直接插入、旋转，超时
     * 方法二：每次插的时候就能知道该插入到哪个位置
     * 一直提示内存超出，后来改用这种O(n)的空间复杂度，O(n)的时间复杂度来完成，
     * 我日，线下自己提交，却完全正确！
     *
     * @param in
     */
    public static void fun1(Scanner in) {
        int n = in.nextInt();

        int l = 0, r = 0;
        if (n % 2 == 0) {
            r = n / 2;
            l = r - 1;
        } else {
            l = n / 2;
            r = l + 1;
        }

        int[] nums = new int[n];

        int k = 0;
        if (n % 2 == 0) {
            while (k < n) {
                nums[r++] = in.nextInt();
                k++;
                if (k >= n) break;
                nums[l--] = in.nextInt();
                k++;
            }
        } else {
            while (k < n) {
                nums[l--] = in.nextInt();
                k++;
                if (k >= n) break;
                nums[r++] = in.nextInt();
                k++;
            }
        }

        for (int i = 0; i < n - 1; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println(nums[n - 1]);
    }

    /**
     * 疯狂队列，
     * 一个数组，求如何排列使得相邻之间的差值最大
     * eg:
     * 5 10 40 25 25
     * 先排序，后进行组队。
     * 为什么90%
     * 15
     * 1 1 1 1 1 1 1 500 500 500 500 1000 1000 1000 1000
     * right answer: 10986
     * my answer:    10487  ->  1 [500 1 1000 [1 1000 1 [1000] 1 1000 1] 500 1 500] 500
     * <p>
     * 通过这个特例我们可以看出，其边界特例的问题！
     *
     * @param in
     */
    public static void fun3(Scanner in) {
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        Arrays.sort(nums);

        if (n == 1) System.out.println(0);
        else if (n == 2) System.out.println(nums[1] - nums[0]);
        else {
            int max = 0;
            max += nums[n - 1] - nums[0];
            max += nums[n - 1] - nums[1];
            int l = nums[0], r = nums[1];

            int left = 2, right = n - 2;
            while (left <= right) {
                //大头，l < r
                max += nums[right] - l;
                l = nums[right--];
                if (right < left) break;

                max += nums[right] - r;
                r = nums[right--];
                if (right < left) break;

                //小头，l > r
                max += l - nums[left];
                l = nums[left++];
                if (right < left) break;

                max += r - nums[left];
                r = nums[left++];
                if (right < left) break;
            }

            System.out.println(max);
        }
    }

    /**
     * 思路：每次取队列中的最大值和最小值加入到疯狂队列中，每次只加入两个。
     * 为啥我没想出来这样的方法呢？
     *
     * @param in
     */
    public static void fun3_2(Scanner in) {
        while (in.hasNext()) {
            int n = in.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = in.nextInt();
            }
            Arrays.sort(nums);

            int max = nums[n - 1];
            int min = nums[0];
            int diff = max - min;
            int minIndex = 1;
            int maxIndex = n - 2;
            while (minIndex < maxIndex) {
                diff += max - nums[minIndex];
                diff += nums[maxIndex] - min;
                min = nums[minIndex++];
                max = nums[maxIndex--];
            }

            //最后一个需要判断该放在哪个合适的位置
            diff += Math.max(nums[maxIndex] - min, max - nums[minIndex]);
            System.out.println(diff);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {

        }
    }
}
