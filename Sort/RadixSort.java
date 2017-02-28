package zhang.algorithm.modelUtil.Sort;

import zhang.algorithm.modelUtil.Array.ArrayTool;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/7/13
 * Time: 下午9:08
 * To change this template use File | Settings | File Templates.
 * <p>
 * [基数排序]的实现
 * <p>
 * Review Time: 2017-02-28 20:50:39
 */
public class RadixSort {
    /**
     * 計數排序, 注意和基數排序區分, 它倆是不同的兩個排序方法
     * 计数排序是稳定的排序算法
     * <p>
     * 参见: 算法导论8.2
     * <p>
     * 计数排序的主要思想是: 确定比 x 小的元素个数, 利用这把 x 放入正确位置!
     * 计数排序是基数排序的子过程, 所以其必须是稳定的!
     *
     * @param data
     * @param k
     */
    public static void countingSort(int[] data, int k) {
        System.out.println();
        System.out.println("countiong Sort");
        int[] buckets = new int[k + 1];
        int[] orderedData = new int[data.length];
        int[] orderedIndex = new int[data.length]; //记录排序后的下标, 可不用要
        //计数
        for (int i = 0; i < data.length; i++) {
            buckets[data[i]]++;
        }

        //保证为稳定排序的条件一
        for (int i = 2; i <= k; i++) {
            buckets[i] += buckets[i - 1];
        }

        //保证为稳定排序的条件二
        for (int i = data.length - 1; i >= 0; i--) {
            orderedData[buckets[data[i]] - 1] = data[i];
            orderedIndex[--buckets[data[i]]] = i;
        }

        System.out.print("原始数组 -->");
        ArrayTool.printArray(data);

        System.out.print("排序后数组 -->");
        ArrayTool.printArray(orderedData);

        System.out.print("排序后下标 -->");
        ArrayTool.printArray(orderedIndex);
    }

    /**
     * 对于现实中的人判断数之间的大小的思维习惯是 : 从高位到低位, 高位大的数据大, 高位相同的再比较低位
     * 而计算机这样做是增加难度的, 在比较低位的时候还需要考虑高位的大小。
     * 这里参考了[【Java排序算法（十一）：基数排序】](http://blog.csdn.net/apei830/article/details/6596104)
     * <p>
     * 参见: 算法导论8.3
     * <p>
     * 对于36和39, 当比较十位数都为3时, 这时需要保证其卫星数据(低位数据)顺序不变。
     * 即 6 仍在 9 之前(低位排序的结果保留)!
     * 桶排序 + 计数排序(每个桶可以使用其他排序算法) => 基数排序
     *
     * @param data
     * @param radix 基数
     * @param d     最大的数的位数
     */
    public static void radixSort(int[] data, int radix, int d) {
        System.out.println();
        System.out.println("radix Sort");

        int[] buckets = new int[radix];
        int[] temp = new int[data.length];

        for (int i = 0, rate = 1; i < d; i++) {
            Arrays.fill(buckets, 0);//因为并不只是循环一次

            for (int k = 0; k < data.length; k++) {
                int subKey = (data[k] / rate) % radix;
                buckets[subKey]++;
            }

            //这里如此做, 只是为了从前到后进行定位, 获得当前bucket基数桶内的元素的下标
            for (int k = 1; k < radix; k++) {
                buckets[k] = buckets[k] + buckets[k - 1];
            }

            //注意,要知道这里为什么一定需要从后向前遍历!!
            //对于14、16:
            //第一次遍历时 6 > 4, 上面的定位步骤机制,也导致桶中后面的数的下标一定比前面大
            //然后当第二次遍历: 16和14在同一个桶时, 从后向前遍历, 那么此时就先获得16的下标
            //能确保16的下标比14的下标大
            for (int k = data.length - 1; k >= 0; k--) {
                int index = (data[k] / rate) % radix;
                temp[--buckets[index]] = data[k];
                //注意这里--buckets[index]就是操作在bucket数组上的!!
            }

            System.arraycopy(temp, 0, data, 0, data.length);
            rate *= radix;
        }


    }

    /**
     * 桶排序
     * 上面计数排序中, 当我们算出buckets时, 我们可以不用再buckets[i] += buckets[i - 1], 直接遍历即可
     * 这是一种特殊的桶排序, 更通俗化的桶排序是, 每个桶中放 K * i / M 至 K * (i + 1) / M 之间的数
     * 而每个桶中用什么排序算法都可以, 然后依次收集每个桶中元素
     *
     * @param data
     * @param k
     */
    public static void bucketSort(int[] data, int k) {
        //省略...
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 8, 5, 1, 10, 5, 9, 9, 3, 5, 6, 6, 2, 8, 2};
        countingSort(nums1, 10);

        int[] nums2 = {1100, 192, 221, 12, 23};
        radixSort(nums2, 10, 4);
        ArrayTool.printArray(nums2);
    }
}
