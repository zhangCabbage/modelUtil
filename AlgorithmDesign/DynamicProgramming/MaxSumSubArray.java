package zhang.algorithm.modelUtil.AlgorithmDesign.DynamicProgramming;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_Lenovo
 * Date: 2016/6/29
 * Time: 19:39
 * To change this template use File | Settings | File Templates.
 * <p>
 * This class is to deal the problem that find the max sum of sub array in the total array.
 */
public class MaxSumSubArray {

    /**
     * 背程序是行不通的，过一段时间又会忘记，你需要真正了解这个程序的运行逻辑才行！
     * <p>
     * 那么又该如何找到数组中到底是哪个子数组和最大呢, 输出最大子数组的下标??
     *
     * @param array
     * @return
     */
    public static int maxSubArrayNoLoop(int[] array) {
        int len = array.length;
        if (len < 1) return 0;
        int start = array[len - 1];
        //注意主要是弄清楚start的含义！！
        int max = start;
        for (int i = len - 2; i >= 0; i--) {
            start = Math.max(array[i], array[i] + start);
            max = Math.max(max, start);
        }
        return max;
    }

    /**
     * 找到最大子数组的上下标
     * 注意:左闭右开[l, r)
     *
     * @param array
     * @return
     */
    public static void maxSubArrayAndIndexNoLoop(int[] array) {
        int len = array.length;
        if (len < 1) return;

        int startl = len - 1, startr = len;
        //包含 array[i] 的最大子数组的上下标
        int maxl = len - 1, maxr = len;
        //最大子数组的上下标

        int start = array[len - 1];//注意主要是弄清楚start的含义！！
        int max = start;

        //从后向前遍历
        for (int i = len - 2; i >= 0; i--) {
            if (array[i] + start < array[i]) {
                startr = i + 1;
            }
            startl--;
            start = Math.max(array[i], array[i] + start);

            if (start > max) {
                maxl = startl;
                maxr = startr;
            }
            max = Math.max(max, start);
        }
        System.out.println("max sub array --> <" + maxl + ", " + maxr + ">");
        System.out.println("max sub array sum --> " + max);
    }

    /**
     * 如果array数组是[循环数组],那么其最大子数组是多少?
     * 分析:
     * 此变种题的分析跟原始题的分析差不多, 其最大子数组的情况有两种!!
     * 1、正常数组中间的某一段和最大
     * 2、数组首尾相接的某一段和最大, 这种情况因为中间有一段负值很大,那么我们可以把中间一段求出来
     * <p>
     * 参考链接 : [【循环数组的最大子段和】](http://blog.csdn.net/acdreamers/article/details/38760805)
     *
     * @param array
     * @return
     */
    public static int maxSubArrayLoop(int[] array) {
        int curMax = array[0];
        int firstMax = curMax;
        for (int i = 1; i < array.length; i++) {
            curMax = Math.max(array[i], array[i] + curMax);
            firstMax = Math.max(curMax, firstMax);
        }

        //clone array
        int totalSum = 0;
        int[] negativeArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            totalSum += array[i];
            negativeArray[i] = -1 * array[i];
        }
        curMax = negativeArray[0];
        int secondMax = curMax;
        for (int i = 1; i < negativeArray.length; i++) {
            curMax = Math.max(negativeArray[i], negativeArray[i] + curMax);
            secondMax = Math.max(curMax, secondMax);
        }

        return Math.max(firstMax, totalSum + secondMax);
    }

    public static void main(String[] args) {
        int[] nums = {3, -2, -3, 5, 2};
        System.out.println("max sub array sum --> " + maxSubArrayNoLoop(nums));
        maxSubArrayAndIndexNoLoop(nums);
        System.out.println("max sub loop array sum --> " + maxSubArrayLoop(nums));
    }
}
