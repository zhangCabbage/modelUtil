package zhang.algorithm.modelUtil.Array;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/7/8
 * Time: 下午3:27
 * To change this template use File | Settings | File Templates.
 * <p>
 * 求数组中的逆序对个数
 */
public class InversePairs {

    private static long reversePairs = 0;

    /**
     * 改进的归并排序，注意合并策略
     *
     * @param array
     * @return
     */
    public static int InversePairs(int[] array) {
        reversePairs = 0;
        mergeSort(array, 0, array.length - 1);
        return (int) reversePairs;
    }

    private static void mergeSort(int[] array, int l, int r) {
        if (l >= r) return;
        int l_r = l + (r - l >> 1);
        int r_l = l_r + 1;
        mergeSort(array, l, l_r);
        mergeSort(array, r_l, r);
        //合并
        int[] caches = new int[r - l + 1];
        int i = caches.length - 1;
        while (l <= l_r && r_l <= r) {
            if (array[l_r] > array[r]) {
                reversePairs += (r - r_l + 1);
                reversePairs %= 1000000007;
                caches[i--] = array[l_r--];
            } else {
                caches[i--] = array[r--];
            }
        }
        while (l <= l_r) caches[i--] = array[l_r--];
        while (r_l <= r) caches[i--] = array[r--];
        //复制回原数组
        for (int k = 0; k < caches.length; k++) {
            array[l + k] = caches[k];
        }
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
