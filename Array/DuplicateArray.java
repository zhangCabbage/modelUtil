package zhang.algorithm.modelUtil.Array;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/7/10
 * Time: 下午9:58
 * To change this template use File | Settings | File Templates.
 */
public class DuplicateArray {
    /**
     * 判断 numbers 数组中是否含有重复数字, 如果有返回true, 并在duplication中放入第一个重复的数字。
     * 可以使用hash, 但是空间复杂度为O(n), 这里空间复杂度为O(1)
     *
     * @param numbers
     * @param length
     * @param duplication
     * @return
     */
    public static boolean duplicate(int[] numbers, int length, int[] duplication) {
        int i = 0;
        while (i < length) {
            int index = numbers[i];
            if (index != i) {
                int tmp = numbers[index];
                if (tmp == index) {
                    duplication[0] = tmp;
                    return true;
                }
                numbers[index] = index;
                numbers[i] = tmp;
                continue;
            }
            i++;
        }

        return false;
    }

    public static void main(String[] args) {
        int[] numbers = {2, 1, 3, 1, 4};
        int[] duplication = new int[1];
        System.out.println(duplicate(numbers, numbers.length, duplication));
        System.out.println(Arrays.toString(duplication));
    }
}
