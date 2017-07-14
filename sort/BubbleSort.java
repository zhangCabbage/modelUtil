package zhang.algorithm.modelUtil.Sort;

import zhang.algorithm.modelUtil.Array.ArrayTool;

/**
 * 交换排序，通过交换相邻项，直至有序。其中典型的实现是冒泡排序
 * <p>
 * 时间复杂度分析：
 * 最好：比较 n-1 次，O(n)
 * 最坏：比较 n(n-1)/2 次，O(n^2)
 * 平均：O(n^2)
 * <p>
 * Review Time: 2017-03-01 10:06:10
 *
 * @author zhang_zack
 */
public class BubbleSort {
    /**
     * 冒泡排序,最大的放在下面
     *
     * @param nums
     */
    public static void bubbleSort(int[] nums) {
        //第二次写, 上面的代码更简洁流畅
//        for (int i = 0; i < nums.length - 1; i++) {
//            boolean flag = false;
//            for (int j = 1; j < nums.length - i; j++) {
//                if (nums[j - 1] > nums[j]) {
//                    ArrayTool.swap(nums, j - 1, j);
//                    flag = true;
//                }
//            }
//            if (!flag) break;
//        }

        for (int i = 0; i < nums.length - 1; i++) {
            //至少需要两个才能进行比较
            boolean flag = true;
            for (int j = nums.length - 1; j > i; j--) {
                if (nums[j] < nums[j - 1]) {  //这里可以进行替换
                    ArrayTool.swap(nums, j - 1, j);
                    flag = false;
                }
            }
            if (flag) break;
        }
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 4};
        bubbleSort(nums);
        ArrayTool.printArray(nums);

    }
}
