package zhang.algorithm.modelUtil.Random;

import zhang.algorithm.modelUtil.Array.ArrayTool;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/9/11
 * Time: 下午8:51
 * To change this template use File | Settings | File Templates.
 * <p>
 * Review Time: 2017-02-28 20:31:01
 */
public class RandomTools {
    /**
     * Generator one number [0, n)
     *
     * @param n
     * @return
     */
    public static int random(int n) {
        return new Random().nextInt(n);
    }

    //--------------------------------------------------------------------------------------------
    // How to shuffle one array? Here we use int[] Array to do this demo.
    //--------------------------------------------------------------------------------------------

    public static void shuffle1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            ArrayTool.swap(nums, i, random(nums.length));
        }
    }

    public static void shuffle2(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            ArrayTool.swap(nums, i, i + random(nums.length - i));
        }
    }

    public static void shuffle3(int[] nums) {
        //实现起来略麻烦, 暂时先不实现:)
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6};
        System.out.println("Before shuffle1, num --> " + Arrays.toString(nums));
        shuffle1(nums);
        System.out.println("After shuffle1, num --> " + Arrays.toString(nums));
    }
}
