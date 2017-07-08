package zhang.algorithm.modelUtil.Array;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/7/8
 * Time: 下午5:43
 * To change this template use File | Settings | File Templates.
 */
public class NumsInArray {
    /**
     * @param array
     * @param k
     * @return
     */
    public static int GetNumberOfK(int[] array, int k) {
        if (array == null || array.length < 1) return 0;

        int l = left(array, 0, array.length - 1, k);
        int r = right(array, 0, array.length - 1, k);
        if (array[l] != k) return 0;
        else return r - l + 1;
    }

    /**
     * 为什么左值可以, 因为[2, 2]求中值天然向左偏移
     *
     * @param array
     * @param l
     * @param r
     * @param k
     * @return
     */
    private static int left(int[] array, int l, int r, int k) {
        while (l < r) {
            int mid = l + (r - l >> 1);
            if (array[mid] > k) r = mid - 1;
            else if (array[mid] < k) l = mid + 1;
            else r = mid;
        }
        return l;
    }

    /**
     * 如果仍以left值方式来求, 必定死循环。所以需要手动加
     *
     * @param array
     * @param l
     * @param r
     * @param k
     * @return
     */
    private static int right(int[] array, int l, int r, int k) {
        while (l < r) {
            int mid = l + (r - l >> 1);
            if (array[mid] < k) l = mid + 1;
            else if (array[mid] > k) r = mid - 1;
            else l = mid == r ? mid : array[mid + 1] == k ? mid + 1 : mid;  //important
        }
        return r;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 3, 3, 3};
        int k = 3;
        System.out.println(GetNumberOfK(nums, k));
    }
}
