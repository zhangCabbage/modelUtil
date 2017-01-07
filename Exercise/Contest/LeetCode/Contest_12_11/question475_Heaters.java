package zhang.algorithm.modelUtil.Exercise.Contest.LeetCode.Contest_12_11;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/12/11
 * Time: 上午10:36
 * To change this template use File | Settings | File Templates.
 */
public class question475_Heaters {

    /**
     * 不得不说, 隔一段时间再来看自己写的代码, 真是写的挺不错
     * 30 / 30 test cases passed.
     * Status: Accepted
     * Runtime: 22 ms, bit 76.98%
     *
     * @param houses
     * @param heaters
     * @return
     */
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int radius = 0;
        int m = 0, i = 0;
        while (m < houses.length) {
            while (i != heaters.length - 1 && houses[m] > heaters[i + 1]) i++;
            int dis = Math.abs(houses[m] - heaters[i]);
            if (i != heaters.length - 1) {
                dis = Math.min(dis, Math.abs(houses[m] - heaters[i + 1]));
            }
            radius = Math.max(radius, dis);
            m++;
        }
        return radius;
    }


    /**
     * Every time to find current closest heater i.
     * This method similar like me, try to implement it.
     * <p>
     * 30 / 30 test cases passed.
     * Status: Accepted
     * Runtime: 20 ms
     *
     * @param houses
     * @param heaters
     * @return
     */
    public int findRadius2(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int radius = 0;
        int m = 0, i = 0;
        while (m < houses.length) {
            //here we can add a inf value as a guard
            while (i < heaters.length - 1 && houses[m] > (heaters[i] + heaters[i + 1]) / 2)
                i++;
            radius = Math.max(radius, Math.abs(houses[m++] - heaters[i]));
        }
        return radius;
    }


    /**
     * Method to use binary search.
     * We can only to sort the heaters array, and then traversal the houses array by using binary search.
     * <p>
     * 30 / 30 test cases passed.
     * Status: Accepted
     * Runtime: 30 ms, bit 40.99%
     * The binary search function is too much Judgment condition
     *
     * @param houses
     * @param heaters
     * @return
     */
    public int findRadius3(int[] houses, int[] heaters) {
        Arrays.sort(heaters);

        int radius = 0;
        for (int i = 0; i < houses.length; i++) {
            int j = binarySearch(heaters, houses[i]);
            radius = Math.max(radius, Math.abs(houses[i] - heaters[j]));
        }

        return radius;
    }

    /**
     * 这里随便提一句, 在Arrays.binarySearch中返回的是 -(low + 1)
     * 所以当我们想要使用时, 需要手动把返回的结果i, 进行-(i + 1)
     * 计算机中数字是以补码形式存储的
     * 正数: 原码 = 反码 = 补码
     * 负数: 原码 -> 反码(除符号位, 各位取反), 原码 -> 补码(除符号位, 各位取反, +1)
     * ----- 补码 -> 原码(除符号位, 各位取反, +1)
     * 所以可知, 如果i为负数, 符号位不变, 其余各位取反, 得 i+1, 再在二进制基础上+1, 得 i的原码
     * 此时我们 ~i, 即为包括符号位取反, 得 -(i+1)
     * 【 ~i = -(i+1) 】
     *
     * @param array
     * @param x
     * @return
     */
    private int binarySearch(int[] array, int x) {
        int l = 0, r = array.length - 1;
        while (l <= r) {
            int mid = l + (r - l >> 1);
            if (x > array[mid]) {
                l = mid + 1;
            } else if (x < array[mid]) {
                r = mid - 1;
            } else {
                return mid;
            }
        }
//        return r; //-1 ~ len - 1
        //太多判断条件了
        if (r == -1) {
            return l;
        } else if (r == array.length - 1) {
            return r;
        } else if (x > (array[r] + array[l]) / 2) {
            return l;
        } else {
            return r;
        }
    }


    public static void main(String[] args) {
        question475_Heaters test = new question475_Heaters();
        int[] houses = {1, 2, 3, 4};
        int[] heaters = {1, 4};
        System.out.println(test.findRadius(houses, heaters));
        System.out.println(test.findRadius2(houses, heaters));
        System.out.println(test.findRadius3(houses, heaters));
    }
}
