package zhang.algorithm.modelUtil.sort;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_Lenovo
 * Date: 2016/6/23
 * Time: 16:20
 * To change this template use File | Settings | File Templates.
 *
 * 时间复杂度分析：
 * 比较 n(n-1)/2 次，O(n^2)
 *
 * 但是由于直接选择排序有非相邻元素之间的交换，所以它不是一个稳定排序算法
 *
 */
public class SelectSort {
    /**
     * 选择排序，每次选择未排序的最小元素，置于相应位置。这里实现直接选择排序
     * @param nums
     */
    public static void selectSort(int[] nums){
        for(int i=0; i<nums.length; i++){
            int k = i;
            int temp = nums[i];
            for(int j = i+1; j<nums.length; j++){
                if(nums[j] < temp){
                    k = j;
                    temp = nums[j];
                }
            }
            nums[k] = nums[i];
            nums[i] = temp;
        }
    }

    public static void main(String[] args) {

    }
}
