package zhang.algorithm.modelUtil.Sort;

import zhang.algorithm.modelUtil.Array.ArrayTool;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_Lenovo
 * Date: 2016/6/23
 * Time: 19:21
 * To change this template use File | Settings | File Templates.
 * <p>
 * use to realize heap Sort(堆排序)
 * <p>
 * about heap:
 * 1、堆一般通过数组来存储
 * 2、堆的插入
 * 3、堆的删除，堆中每次只能删除第1个数据
 * 4、堆化数组
 * <p>
 * 时间复杂度分析：
 * 建堆O(2N)
 * 调整堆：O(logN)
 * (N-1)2*logN + 2N ==> 最后时间复杂度为O(N*logN)
 * [【白话经典算法系列之七 堆与堆排序】](http://blog.csdn.net/morewindows/article/details/6709644/)
 * <p>
 * Review Time: 2017-02-28 21:39:08
 * <p>
 * 参照: 算法导论 第六章
 * 堆排序应该建立一个大顶堆
 */
public class HeapSort {
    //------------------------------------------------------------------------------------
    // Rebuild code to implement Big Heap.
    //------------------------------------------------------------------------------------
    private int bound;

    private void makeHeap(int[] nums) {
        bound = nums.length;

        int n = bound;
        for (int i = n / 2 - 1; i >= 0; i--) {
            fixdown(nums, i);
        }
    }

    private void fixdown(int[] nums, int i) {
        int curIndex = 2 * i + 1;
        int tmp = nums[i];

        while (curIndex < bound) {
            if (curIndex + 1 < bound && nums[curIndex] < nums[curIndex + 1])
                curIndex++;
            if (tmp >= nums[curIndex])
                break;
            nums[i] = nums[curIndex];
            i = curIndex;
            curIndex = 2 * i + 1;
        }
        nums[i] = tmp;
    }

    private void deleteSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            ArrayTool.swap(nums, 0, --bound);
            fixdown(nums, 0);
        }
    }

    public static int[] sort(int[] nums) {
        HeapSort heap = new HeapSort();
        heap.makeHeap(nums);
        heap.deleteSort(nums);
        return nums;
    }

    //------------------------------------------------------------------------------------
    // Rebuild code to implement Small Heap.
    //------------------------------------------------------------------------------------

    /**
     * 建立一个最小堆
     *
     * @param array
     */
    public static void makeMinHeap(int[] array) {
        int n = array.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            minHeapFixdown(array, i, n);
        }
    }

    /**
     * 关于小顶堆的调整，从i开始，总个数为num
     * 由于删除后，只是第1个位置发生了变化，所以这个调整的过程是从顶至底的
     * 注意别人的编码逻辑，这样使得代码非常简洁！
     *
     * @param array
     * @param i     起始下标
     * @param num   小顶堆的元素个数，最大下标
     */
    public static void minHeapFixdown(int[] array, int i, int num) {
        int curIndex = 2 * i + 1;
        int temp = array[i];
        while (curIndex < num) {
            if (curIndex + 1 < num && array[curIndex + 1] < array[curIndex]) {
                curIndex++;
            }
            if (temp <= array[curIndex]) {
                break;
            }
            array[i] = array[curIndex];
            i = curIndex;
            curIndex = 2 * i + 1;
        }
        array[i] = temp;
    }

    /**
     * 删除小顶堆最顶上元素
     *
     * @param array
     * @param n     表示小顶堆的元素个数
     */
    public static void minHeapDeleteNumber(int[] array, int n) {
        ArrayTool.swap(array, 0, n - 1);
        minHeapFixdown(array, 0, n - 1);
    }

    public static void heapSort(int[] array) {
        makeMinHeap(array);
        for (int i = array.length; i > 1; i--) {
            minHeapDeleteNumber(array, i);
        }
    }

    public static void main(String[] args) {
//        int[] nums = {9, 12, 17, 30, 50, 20, 60, 65, 4, 49};
        int[] nums = {1, 3, 4, 2};
//        System.out.println(BinaryTree.instance(nums));
//        makeMinHeap(nums);
//        System.out.println(BinaryTree.instance(nums));

        sort(nums);
        ArrayTool.printArray(nums);


    }
}
