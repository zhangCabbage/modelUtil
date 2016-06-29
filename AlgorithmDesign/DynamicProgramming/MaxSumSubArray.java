package zhang.algorithm.modelUtil.AlgorithmDesign.DynamicProgramming;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_Lenovo
 * Date: 2016/6/29
 * Time: 19:39
 * To change this template use File | Settings | File Templates.
 *
 * This class is to deal the problem that find the max sum of sub array in the total array.
 */
public class MaxSumSubArray {
    /**
     * 背程序是行不通的，过一段时间又会忘记，你需要真正了解这个程序的运行逻辑才行！
     * @param array
     * @return
     */
    public static int findMaxSum(int[] array){
        int len = array.length;
        if(len < 1) return 0;
        int start = array[len-1];
        //注意主要是弄清楚start的含义！！
        int max = start;
        for(int i=len-2; i>=0; i--){
            start = Math.max(array[i], array[i]+start);
            max = Math.max(max, start);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {1, -2, 3, 5, -3, 2};
        System.out.println(findMaxSum(nums));
    }
}
