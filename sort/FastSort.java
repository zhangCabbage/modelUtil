package zhang.algorithm.modelUtil.Sort;

import zhang.algorithm.modelUtil.Array.ArrayTool;

/**
 * 时间复杂度分析：
 * 最好：比较 n-1 次，O(nlogn)
 * 最坏：比较 n(n-1)/2 次，O(n^2)
 * 平均：O(nlogn)
 * <p>
 * Review Time: 2017-03-01 10:15:52
 */
public class FastSort {
    /**
     * 快速排序算法, 初版
     * 但是在这个实现过程中，我使用的判断太多了，当数据很大时，程序会被拖慢！！如何改进这个程序.
     *
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
     * 快速排序算法, 改版
     *
     * @param nums
     * @param low
     * @param high
     */
    public static void fastSort2(int[] nums, int low, int high) {
        if (low >= high) return;

        int l = low;
        int h = high;
        int povit = nums[l];
        while (l < h) {
            while (l < h && nums[h] >= povit) {
                h--;
            }
            nums[l] = nums[h];

            while (l < h && nums[l] <= povit) {
                l++;
            }
            nums[h] = nums[l];
        }
        nums[l] = povit;

        fastSort2(nums, low, l - 1);
        fastSort2(nums, h + 1, high);
    }

//    /**
//     * Error method !!
//     * <p>
//     * But this is wrong because of this mid index is not right.
//     * so I make a test for this function
//     * {1, 1, 1, 1, 4, 1, 2, 3, 6}
//     * the result is {1, 1, 1, 1, 3, 1, 2, 4, 6}
//     *
//     * @param nums
//     * @param low
//     * @param high
//     */
//    public static void fastSort3(int[] nums, int low, int high) {
//        if (high <= low) return;
//
//        int left = low;
//        int right = high;
//        int mid = (low + high) >> 1;
//        int povitKey = nums[mid];
//
//        while (low < high) {
//            while (nums[low] < povitKey) {
//                low++;
//            }
//            while (nums[high] > povitKey) {
//                high--;
//            }
//            if (low < high) {
//                ArrayTool.swap(nums, low, high);
//            }
//            low++;
//            high--;
//        }
//        fastSort3(nums, left, mid - 1);
//        fastSort3(nums, mid + 1, right);
//    }

    /**
     * Another implement of fast sort
     * 代码组织更加紧凑, 简洁 --《算法导论》,推荐用法!
     *
     * @param nums
     * @param low
     * @param high
     */
    public static void fastSort3(int[] nums, int low, int high) {
        if (low < high) {
            int povit = nums[high];  //这里主元的选择是提高此算法的关键
            int i = low - 1;
            for (int j = low; j < high; j++) {
                if (nums[j] <= povit) {
                    ArrayTool.swap(nums, ++i, j);
                }
            }
            ArrayTool.swap(nums, ++i, high);
            fastSort3(nums, low, i - 1);
            fastSort3(nums, i + 1, high);
        }
    }

    /**
     * 通过双指针单向的方式，分组数组array中从l到r，以array[r]为主元povitKey
     * <p>
     * 这种单向的方式比双向慢了很多，但是它的调整过程很有趣
     * i变量，遇到大于temp的数就不再前进，直到遍历到下一个小于temp的数下边j，才把i,  j交换！
     *
     * @param array 数组
     * @param l     左起始下标
     * @param r     右结束下标
     * @return 返回调整数组后，中间位置下标
     */
    public static int partitation(int[] array, int l, int r) {
        int i = l;
        int temp = array[r];

        for (int j = l; j < r; j++) {
            if (array[j] < temp) {
                ArrayTool.swap(array, i++, j);
            }
        }
        ArrayTool.swap(array, i, r);
        return i;
    }

    public static void main(String[] args) {
        int[] nums = {5, 7, 9, 8, 4, 1, 2, 3, 6};
//        fastSort(nums, 0, nums.length - 1);
//        fastSort2(nums, 0, nums.length - 1);
//        fastSort3(nums, 0, nums.length - 1);

        System.out.println(partitation(nums, 0, nums.length - 1));
        ArrayTool.printArray(nums);
    }
}
