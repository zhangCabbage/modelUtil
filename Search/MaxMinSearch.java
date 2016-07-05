package zhang.algorithm.modelUtil.Search;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_Lenovo
 * Date: 2016/6/27
 * Time: 19:10
 * To change this template use File | Settings | File Templates.
 * This class is to realize special Search.
 */
public class MaxMinSearch {
    public int min;
    public int max;

    /**
     * use the Minimum comparison times to find the max and min number in the same time.
     * the time complexity only O(3n/2)
     * <p>
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

    /**
     * 采用分治法的思想来编码
     * <p>
     * 编码实践证明还是使用分治法, 好过精巧设计的4次比较转3次比较
     *
     * @param array
     */
    public void findMaxAndMin2(int[] array) {
        int[] res = partitionSearch(array, 0, array.length - 1);
        min = res[0];
        max = res[1];
    }

    private int[] partitionSearch(int[] array, int start, int end) {
        int[] res = new int[2];
        if (end - start <= 1) {
            if (array[start] < array[end]) {
                res[0] = array[start];
                res[1] = array[end];
                return res;
            }
            res[0] = array[end];
            res[1] = array[start];
            return res;
        }

        int mid = (start + end) >> 1;
        int[] res1 = partitionSearch(array, start, mid);
        int[] res2 = partitionSearch(array, mid + 1, end);

        res[0] = Math.min(res1[0], res2[0]);
        res[1] = Math.max(res1[1], res2[1]);

        return res;
    }


    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 8, 4};
        MaxMinSearch test = new MaxMinSearch();
        test.findMaxAndMin2(nums);
        System.out.println("Max --> " + test.max + ", Min --> " + test.min);
    }
}
