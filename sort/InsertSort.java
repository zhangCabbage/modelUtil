package zhang.algorithm.modelUtil.sort;

import zhang.algorithm.modelUtil.ZhangUtil;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_Lenovo
 * Date: 2016/6/23
 * Time: 15:52
 * To change this template use File | Settings | File Templates.
 *
 * 时间复杂度分析：
 * 最好：比较 n-1 次，O(n)
 * 最坏：比较 n(n-1)/2 次，O(n^2)
 */
public class InsertSort {
    /**
     * 插入排序的不带哨兵的实现方式
     *
     * @param nums
     */
    public static void insertSort(int[] nums){
        for(int i=1; i<nums.length; i++){
            int k = i - 1;
            int temp = nums[i];
            while(k >= 0 && temp < nums[k]){
                nums[k+1] = nums[k--];
            }
            nums[++k] = temp;
        }
    }

    /**
     * 插入排序的带哨兵的实现方式
     * 增加哨兵的作用：
     * 1、暂存待插入数据
     * 2、防止越界。
     * 如果增加哨兵，那么每次循环，程序就只有一次判断，提高了近一半的效率。
     * 注意增加哨兵并不是雕虫小技，要深入理解这种技巧！特别是对于使用频率高，记录数较大时，作用就更加显著
     *
     * 但是这种方法在使用时，因为传入数据并没有空余下标为0的数组位置，所以存在问题！！
     *
     * @param nums
     */
    public static void insertSort2(int[] nums){
        int[] copyNums = new int[nums.length+1];
        System.arraycopy(nums, 0, copyNums, 1, nums.length);

        for(int i=2; i<copyNums.length; i++){
            int k = i - 1;
            copyNums[0] = copyNums[i];
            while(copyNums[0] < copyNums[k]){
                copyNums[k+1] = copyNums[k--];
            }
            copyNums[++k] = copyNums[0];
        }

        System.arraycopy(copyNums, 1, nums, 0, nums.length);
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 4, 7};
//        int[] nums = {1, 2, 3, 4, 5};
        insertSort2(nums);
        ZhangUtil.printArray(nums);
    }
}
