package zhang.algorithm.modelUtil.Search;

import zhang.algorithm.modelUtil.Array.ArrayTool;
import zhang.algorithm.modelUtil.Random.RandomTools;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/3/1
 * Time: 上午11:35
 * To change this template use File | Settings | File Templates.
 */
public class FastSearch {
    /**
     * 用以找到数组中第k小的元素，时间复杂度为O(n)
     * 忽略k不在[start, end]中的情况
     *
     * @param nums
     * @param start
     * @param end
     * @param k
     * @return
     */
    public static int search(int[] nums, int start, int end, int k) {
        int povit = nums[end];
        int i = start - 1;
        for (int j = start; j < end; j++) {
            if (nums[j] < povit)
                ArrayTool.swap(nums, ++i, j);
        }
        ArrayTool.swap(nums, ++i, end);
        int d = i - start + 1;  //注意: 这里一定不能只用i, 需要i - start + 1

        if (k == d) return nums[i];
        else if (k < d) return search(nums, start, i - 1, k);
        else return search(nums, i + 1, end, k - d);
    }

    /**
     * 进行检查, 如果不满足条件, 则返回MIN_VALUE
     *
     * @param nums
     * @param k    查找nums数组中第k小的元素, k>0
     * @return
     */
    public static int search(int[] nums, int k) {
        if (k <= 0 || k > nums.length) return Integer.MIN_VALUE;
        return search(nums, 0, nums.length - 1, k);
    }

    //----------------------------------------------------------------------------
    // Second Implement Way!
    //----------------------------------------------------------------------------

    /**
     * 用以找到数组中第k大的元素，时间复杂度为O(n)
     *
     * @param array
     * @param start
     * @param end
     * @param k     means the kth number of the array
     * @return
     */
    public static int findKth(int[] array, int start, int end, int k) {
        if (k < 1 || k > array.length) {
            return Integer.MIN_VALUE;
        }
        int l = start;
        int r = end;
        int povitKey = array[start];

        //借鉴快速排序的逻辑方式
        while (l < r) {
            while (l < r && array[r] >= povitKey) {
                r--;
            }
            array[l] = array[r];

            while (l < r && array[l] <= povitKey) {
                l++;
            }
            array[r] = array[l];
        }
        int distance = l - start + 1;
        if (distance == k) {
            return povitKey;
        } else if (distance > k) {
            return findKth(array, start, l - 1, k);
        } else {
            return findKth(array, l + 1, end, k - distance);
        }
    }


    public static void main(String[] args) {
        FastSearch test = new FastSearch();
        int[] array = {8, 6, 5, 2, 3, 4, 1, 7};
        RandomTools.shuffle1(array);
        System.out.println("array --> " + Arrays.toString(array));
        System.out.println(search(array, 5));
    }
}
