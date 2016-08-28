package zhang.algorithm.modelUtil.AlgorithmDesign.BinarySearch;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/8/27
 * Time: 下午1:48
 * To change this template use File | Settings | File Templates.
 * <p>
 * 【problem detail】
 * 给一个初始数组 h[0],h[1],h[2],..,h[n-1](都是正整数)
 * 现在我们想把队列变成一个严格上升的队列 (h[0]<h[1]<h[2]<..<h[n - 1])
 * 把队列中的每个元素任意加上一个数，或者减去一个数，且变换后的数仍为正整数, 求使得数组转换成递增数组所有数中最小的
 * eg:
 * n = 4, h = {1, 1, 1, 1} 结果为3，队列为 {1,2,3,4}
 * n = 3, h = {9, 5 ,11}, 结果为3， 队列为 {9-2，5+3，11} = {7, 8, 11}
 * <p>
 * 对于任意一组数组, 其实都通过上述加减都可以转换成[1, 2, 3, 4...]
 * 但是如此那么需要加减的数就是 数组中最大的数 - 其位置(起始为1)
 * <p>
 * 【solve】
 * 我们考虑最后求的解ans, 任何大于ans的数都能满足条件, 而任何小于ans的解则不能将此数组转换递增,
 * 这是这道题能利用二分法解决的根本所在。
 * <p>
 * 【reference】
 * http://toutiao.com/i6322012498446778882/?tt_from=weixin&utm_campaign=client_share&app=news_article&utm_source=weixin&iid=5184307697&utm_medium=toutiao_android&wxshare_count=1
 */
public class MinSortArray {
    /**
     * 根据上面的分析, 我们只需要找到一个能使得数组转换为递增的right, 即可在 [0, right] 之间使用二分法
     *
     * @param n
     * @param nums
     * @return
     */
    public int minAnsChangeSort(int n, int[] nums) {
        int num = 0, index = 0; //the number of the array and it's index
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] - i - 1 > max) {
                max = nums[i] - i - 1;
            }
        }
        int l = 0;
        int r = max;
        while (l < r) {
            int mid = l + (r - l >> 1); //注意位操作符的优先级
            if (checkOk(nums, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return r;
    }

    /**
     * 任给一个maxchange, 看这个maxchange下能否使得nums转换为递增
     *
     * @param nums
     * @param maxChange
     * @return
     */
    public boolean checkOk(int[] nums, int maxChange) {
        int last = 0; //last number
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > last) {
                last = Math.max(last + 1, nums[i] - maxChange);
            } else {
                if (nums[i] + maxChange <= last) {
                    return false;
                }
                last += 1;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MinSortArray test = new MinSortArray();
        int[] nums = {9, 5, 11};
        System.out.println(test.minAnsChangeSort(3, nums));

    }
}
