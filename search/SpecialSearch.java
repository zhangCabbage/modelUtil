package zhang.algorithm.modelUtil.search;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_Lenovo
 * Date: 2016/6/27
 * Time: 19:10
 * To change this template use File | Settings | File Templates.
 * This class is to realize special search.
 */
public class SpecialSearch {
    public int min;
    public int max;

    /**
     * use the Minimum comparison times to find the max and min number in the same time.
     * the time complexity only O(3n/2)
     *
     * How to code in a beautiful way.
     * The below way I do not like, I think too much repeated code!
     *
     * @param array
     */
    public void findMaxAndMin(int[] array) {
        if (array.length < 1) {
            return;
        }
        max = array[0];
        min = max;

        int l = 0, r = array.length - 1;
        while (l < r) {
            if (array[l] < array[r]) {
                if (max < array[r]) {
                    max = array[r];
                }
                if (min > array[l]) {
                    min = array[l];
                }
            } else {
                if (max < array[l]) {
                    max = array[l];
                }
                if (min > array[r]) {
                    min = array[r];
                }
            }
            l++;
            r--;
        }
        if (l == r) {
            if (max < array[r]) {
                max = array[r];
            }
            if (min > array[l]) {
                min = array[l];
            }
        }
    }


    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 8, 4};
        SpecialSearch test = new SpecialSearch();
        test.findMaxAndMin(nums);
        System.out.println("Max --> "+test.max+", Min --> "+test.min);
    }
}
