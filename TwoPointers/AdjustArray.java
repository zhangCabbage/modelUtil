package zhang.algorithm.modelUtil.TwoPointers;

import zhang.algorithm.modelUtil.Array.ArrayTool;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/7/4
 * Time: 下午10:02
 * To change this template use File | Settings | File Templates.
 */
public class AdjustArray {
    /**
     * 调整数组, 使奇数在前, 偶数在后, 不考虑原数组次序
     *
     * @param array
     */
    public void reOrderArray(int[] array) {
        int l = 0, r = array.length - 1;
        while (l < r) {
            while (l < r && (array[l] & 1) == 1)
                l++;
            while (l < r && (array[r] & 1) == 0)
                r--;
            ArrayTool.swap(array, l, r);
        }
    }

    public static void main(String[] args) {
        AdjustArray test = new AdjustArray();
    }
}
