package zhang.algorithm.modelUtil.Array;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/10/19
 * Time: 下午7:58
 * To change this template use File | Settings | File Templates.
 */
public class ArrayProblem {
    //--------------------------------------------------------------------------
    //求数组中最长递增子序列
    //--------------------------------------------------------------------------

    /**
     * 由于此问题的无后效特性, 我们可以使用动态规划的方法
     *
     * @param arrays
     * @return
     */
    public int maxIncreaseSubArray(int[] arrays) {
        int maxLen = 0;
        int[] increLens = new int[arrays.length];
        for (int i = 0; i < arrays.length; i++) {
            increLens[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arrays[i] > arrays[j] && increLens[j] + 1 > increLens[i]) {
                    increLens[i] = increLens[j] + 1;
                }
            }
            if (increLens[i] > maxLen) {
                maxLen = increLens[i];
            }
        }

        return maxLen;
    }

    /**
     * 假如我们考虑第i+1个元素前i个元素的关系, 那么该怎么办呢?
     * 我们需要找到前i个元素中最长的递增子序列中, 最大的元素中的最小值, 此最大值小于arrays[i+1]
     * 这里我们使用maxV[i]来存, 长度为i的递增子序列其最大元素的最小值!
     *
     * @param arrays
     * @return
     */
    public int maxIncreaseSubArray2(int[] arrays) {
        int[] maxV = new int[arrays.length + 1];
        maxV[1] = arrays[0];  //init

        int[] Lis = new int[arrays.length]; //Lis[i]表示包含当前下标i对应数组元素的最长递增子序列的长度
        Arrays.fill(Lis, 1);
        int maxLen = 1;  //历史最大长度

        //traversal from left to right.
        for (int i = 1; i < arrays.length; i++) {
            for (int j = maxLen; j > 0; j--) {
                if (arrays[i] > maxV[j]) {
                    Lis[i] = j + 1;
                    break;
                }
            }
            if (Lis[i] > maxLen) {  //新增的递增子序列长度, 直接更新
                maxLen = Lis[i];
                maxV[Lis[i]] = arrays[i];
            } else if (arrays[i] < maxV[Lis[i]]) {  //以前有的递增子序列, 只有新的值比原来的小, 才会更新
                maxV[Lis[i]] = arrays[i];
            }
        }

        return maxLen;
    }

    //--------------------------------------------------------------------------
    //求数组中最长递增子序列
    //--------------------------------------------------------------------------
    public static void main(String[] args) {
        ArrayProblem test = new ArrayProblem();
        int[] nums = {1, -1, 2, -3, 4, -5, 6, -7};
        System.out.println(test.maxIncreaseSubArray(nums));
        System.out.println(test.maxIncreaseSubArray2(nums));
    }
}
