package zhang.algorithm.modelUtil.search;

/**
 * Created by zhang_zack on 16/5/29.
 * 用来表示快速查找
 */
public class BinarySearch {
    /**
     * 基于快速查找返回给定数在目标数组中的位置,如果没有则返回-1
     * @param nums
     * @param target
     * @return 返回的是目标值在目标数组当前的下标
     */
    public static int binarySearch(int[] nums, int target){
        int start = 0;
        int end = nums.length-1;
        if(target < nums[start] || target > nums[end]){
            return -1;
        }
        while(start <= end){
            int mid = (start+end)/2;
            if(target < nums[mid]){
                end = mid-1;
            }else if(target > nums[mid]){
                start = mid+1;
            }else{
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9};
        System.out.println(binarySearch(nums, 7));
    }
}
