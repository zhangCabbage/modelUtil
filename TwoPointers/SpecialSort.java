package zhang.algorithm.modelUtil.TwoPointers;

import zhang.algorithm.modelUtil.Array.ArrayTool;

/**
 * Created by jiahua on 2017/8/26.
 * <p>
 * 记录一些特殊的排序方式
 */
public class SpecialSort {
    /**
     * 对一个只含有0、1、2三个数字的数组进行排序，使得按照0、1、2的顺序显示
     * 使用算法在O(n)的时间复杂度、O(1)的空间来完成
     *
     * @param nums
     */
    public static void colorSort(int[] nums) {
        int start = 0, end = nums.length - 1;
        int i = 0;
        while (i <= end) {
            if (nums[i] == 0) {
                ArrayTool.swap(nums, i++, start++);
            } else if (nums[i] == 2) {
                ArrayTool.swap(nums, i, end--);
            } else i++;
        }
    }


    /**
     * 给一个倒序数组, 6 5 4 3 2 1 -> 1 6 2 5 3 4
     * 一个转换空间的前后标记法
     *
     * @param nums
     */
    public static void wiggleSort(int[] nums) {


    }


    public static void main(String[] args) {
        int[] nums = {6, 5, 4, 3, 2, 1};
        wiggleSort(nums);
        ArrayTool.printArray(nums);
    }
}
