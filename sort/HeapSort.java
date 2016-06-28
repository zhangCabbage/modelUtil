package zhang.algorithm.modelUtil.Sort;

import zhang.algorithm.modelUtil.Tree.BinaryTree;
import zhang.algorithm.modelUtil.ZhangUtil;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_Lenovo
 * Date: 2016/6/23
 * Time: 19:21
 * To change this template use File | Settings | File Templates.
 *
 * use to realize heap Sort(堆排序)
 *
 * about heap:
 * 1、堆一般通过数组来存储
 * 2、堆的插入
 * 3、堆的删除，堆中每次只能删除第1个数据
 * 4、堆化数组
 *
 * 时间复杂度分析：
 * 建堆O(2N)
 * 调整堆：O(logN)
 * (N-1)2*logN + 2N ==> 最后时间复杂度为O(N*logN)
 * [【白话经典算法系列之七 堆与堆排序】](http://blog.csdn.net/morewindows/article/details/6709644/)
 */
public class HeapSort {
    /**
     * 建立一个最小堆
     * @param array
     */
    public static void makeMinHeap(int[] array){
        int n = array.length;
        for(int i=n/2-1; i>=0; i--){
            minHeapFixdown(array, i, n);
        }
    }

    /**
     * 关于小顶堆的调整，从i开始，总个数为num
     * 由于删除后，只是第1个位置发生了变化，所以这个调整的过程是从顶至底的
     * 注意别人的编码逻辑，这样使得代码非常简洁！
     *
     * @param array
     * @param i 起始下标
     * @param num 小顶堆的元素个数，最大下标
     */
    public static void minHeapFixdown(int[] array, int i, int num){
        int curIndex = 2*i+1;
        int temp = array[i];
        while(curIndex < num){
            if( curIndex+1 < num && array[curIndex+1] < array[curIndex]){
                curIndex++;
            }
            if(temp <= array[curIndex]){
                break;
            }
            array[i] = array[curIndex];
            i = curIndex;
            curIndex = 2*i+1;
        }
        array[i] = temp;
    }

    /**
     * 删除小顶堆最顶上元素
     * @param array
     * @param n 表示小顶堆的元素个数
     */
    public static void minHeapDeleteNumber(int[] array, int n){
        ZhangUtil.swap(array, 0, n-1);
        minHeapFixdown(array, 0, n-1);
    }

    public static void heapSort(int[] array){
        makeMinHeap(array);
        for(int i= array.length; i>1; i--){
            minHeapDeleteNumber(array, i);
        }
    }

    public static void main(String[] args) {
//        int[] nums = {9, 12, 17, 30, 50, 20, 60, 65, 4, 49};
        int[] nums = {1, 3, 4, 2};
        System.out.println(BinaryTree.instance(nums));
        makeMinHeap(nums);
        System.out.println(BinaryTree.instance(nums));

        heapSort(nums);
        ZhangUtil.printArray(nums);
    }
}
