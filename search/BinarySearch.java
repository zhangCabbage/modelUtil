package zhang.algorithm.modelUtil.Search;

/**
 * Created by zhang_zack on 16/5/29.
 * 用来表示快速查找
 * <p>
 * Review Time: 2017-03-01 20:26:29
 */
public class BinarySearch {
    /**
     * 基于快速查找返回给定数在目标数组中的位置,如果没有则返回-1
     *
     * @param nums
     * @param target
     * @return 返回的是目标值在目标数组当前的下标
     */
    public static int isExist(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        if (target < nums[start] || target > nums[end]) {
            return -1;
        }
        while (start <= end) {
            int mid = (start + end) >> 1;
            if (target < nums[mid]) {
                end = mid - 1;
            } else if (target > nums[mid]) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * if find target in the num array, return the index.
     * or return the index that target should insert
     *
     * @param nums
     * @param target
     * @return
     */
    public static int insertIndex(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        if (target < nums[start]) return start;
        if (target > nums[end]) return end;
        while (start <= end) {
            int mid = (start + end) >> 1;
            if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return end + 1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9};
        System.out.println(isExist(nums, 7));
    }
}
