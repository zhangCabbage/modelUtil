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
 * 基数排序的实现
 */
public class RadixSort {
    /**
     * 对于现实中的人判断数之间的大小的思维习惯是 : 从高位到低位, 高位大的数据大, 高位相同的再比较低位
     * 而计算机这样做是增加难度的, 在比较低位的时候还需要考虑高位的大小。
     * 这里给出基数排序,从低位到高位比较。在这儿我就不详细介绍基数排序的问题了。
     * 这里参考了[【Java排序算法（十一）：基数排序】](http://blog.csdn.net/apei830/article/details/6596104)
     * <p>
     * 下面来实现基数排序。此基数排序实现的相当的巧妙!!!
     *
     * @param data
     * @param radix
     * @param d
     */
    public static void radixSort(int[] data, int radix, int d) {

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

    public static void main(String[] args) {
        int[] nums = {1100, 192, 221, 12, 23};
        radixSort(nums, 10, 4);
        ArrayTool.printArray(nums);

        //Test
        System.out.println(--nums[1]);
        ArrayTool.printArray(nums);
    }
}
