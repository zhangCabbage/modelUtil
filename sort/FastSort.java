package zhang.algorithm.modelUtil.sort;

import zhang.algorithm.modelUtil.ZhangUtil;

/**
 * 时间复杂度分析：
 * 最好：比较 n-1 次，O(nlogn)
 * 最坏：比较 n(n-1)/2 次，O(n^2)
 * 平均：O(nlogn)
 */
public class FastSort {
    /**
     * 快速排序算法
     * 但是在这个实现过程中，我使用的判断太多了，当数据很大时，程序会被拖慢！！如何改进这个程序.
     * @param nums
     * @return
     */
    public static void fastSort(int[] nums, int low, int high) {
        int l = low;
        int h = high;
        int povitKey = nums[l];
        int index = 0;
        while (l < h) {
            if (index % 2 == 0) {
                if (nums[h] < povitKey) {
                    nums[l++] = nums[h];
                    index++;
                } else {
                    h--;
                }
            } else {
                if (nums[l] > povitKey) {
                    nums[h--] = nums[l];
                    index++;
                } else {
                    l++;
                }
            }
        }
        nums[l] = povitKey;
        if (l > low) {
            fastSort(nums, low, l - 1);
        }
        if (h < high) {
            fastSort(nums, h + 1, high);
        }
    }

    /**
     * 快速排序算法的简单实现方法
     *
     * @param nums
     * @param low
     * @param high
     */
    public static void fastSort2(int[] nums, int low, int high) {
        int l = low;
        int h = high;
        int povitKey = nums[l];
        while (l < h) {
            while (l < h && nums[h] >= povitKey) {
                h--;
            }
            nums[l] = nums[h];

            while (l < h && nums[l] <= povitKey) {
                l++;
            }
            nums[h] = nums[l];
        }
        nums[l] = povitKey;

        if (l > low) {
            fastSort(nums, low, l - 1);
        }
        if (h < high) {
            fastSort(nums, h + 1, high);
        }
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1};
//        fastSort(nums, 0, nums.length - 1);
        fastSort2(nums, 0, nums.length - 1);
        ZhangUtil.printArray(nums);
    }
}
