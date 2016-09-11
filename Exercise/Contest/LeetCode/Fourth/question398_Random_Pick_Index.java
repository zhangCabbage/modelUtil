package zhang.algorithm.modelUtil.Exercise.Contest.LeetCode.Fourth;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/9/11
 * Time: 上午11:23
 * To change this template use File | Settings | File Templates.
 */
public class question398_Random_Pick_Index {

    private int[] nums;

    public question398_Random_Pick_Index(int[] nums) {
        this.nums = nums;
    }

    /**
     * @param target
     * @return
     */
    public int pick(int target) {
        int low = lowPosition(target);
        int high = highPosition(target);
        return low + (int) (Math.random() * (high - low + 1));
    }

    private int lowPosition(int target) {
        int l = 0;
        int r = this.nums.length - 1;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (target > nums[mid]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    private int highPosition(int target) {
        int l = 0;
        int r = this.nums.length - 1;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (target >= nums[mid]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 3, 3};
        question398_Random_Pick_Index test = new question398_Random_Pick_Index(nums);
        System.out.println(test.lowPosition(3));
        System.out.println(test.highPosition(3));
    }
}
