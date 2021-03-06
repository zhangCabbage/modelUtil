package zhang.algorithm.modelUtil.Sort;

import java.util.Arrays;

/**
 * 归并排序的实现
 * <p>
 * <p>
 * 时间复杂度分析(采用递推公式的方法进行分析)：
 * T(n) = 2T(n-1) + 2^(n-1)
 * <p>
 * 时间复杂度分析：
 * 最好：O(nlogn)
 * 最坏：O(nlogn)
 * 平均：O(nlogn)
 *
 * Review Time: 2017-02-28 17:05:12
 *
 * @author zhang_zack
 */
public class MergeSort {
    /**
     * 方法一：采用递归的方式实现归并排序
     *
     * @param corArr
     * @param arr
     * @param start
     * @param end
     */
    private static void mergeRecursive(int[] corArr, int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        int start1 = start;
        int end1 = mid;
        int start2 = mid + 1;
        int end2 = end;
        mergeRecursive(corArr, arr, start1, end1);
        mergeRecursive(corArr, arr, start2, end2);

        //然后合并前后两个半数组
        int k = start;
        while (start1 <= end1 && start2 <= end2) {
            corArr[k++] = (arr[start1] < arr[start2]) ? arr[start1++] : arr[start2++];
        }
        while (start1 <= end1) {
            corArr[k++] = arr[start1++];
        }
        while (start2 <= end2) {
            corArr[k++] = arr[start2++];
        }

        //最后把合并后的数组复制给原数组
        for (int i = start; i <= end; i++) {
            arr[i] = corArr[i];
        }

    }

    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int[] array, int start, int end) {
        if (start < end) {
            int mid = start + (end - start >> 1);
            mergeSort(array, start, mid);
            mergeSort(array, mid + 1, end);

            int[] res = new int[end - start + 1];
            int i = start, j = mid + 1, k = 0;
            while (i <= mid && j <= end)
                res[k++] = (array[i] < array[j]) ? array[i++] : array[j++];
            while (i <= mid) res[k++] = array[i++];
            while (j <= end) res[k++] = array[j++];

            System.arraycopy(res, 0, array, start, res.length);
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 4, 1, 8, 6, 9};
        MergeSort.mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
