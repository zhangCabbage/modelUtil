package zhang.algorithm.modelUtil.Exercise.Examination.PinDuoDuo;

import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_Lenovo
 * Date: 2017/8/1
 * Time: 22:05
 * To change this template use File | Settings | File Templates.
 */
public class Summer_8_1 {

    public static void test1(Scanner in) {
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        if (n == 3) {
            System.out.println(1L * nums[0] * nums[1] * nums[2]);
            return;
        }

        int[] max = {0, 0, 0};
        int[] min = {0, 0};
        long res = 0;

        for (int i = 0; i < n; i++) {
            int tmp = nums[i];
            if (tmp < 0) {
                int j = 0;
                // 从小到大
                for (; j < 2; j++) {
                    if (min[j] > tmp) {
                        int t = min[j];
                        min[j] = tmp;
                        tmp = t;
                    }
                }
            } else {
                int j = 2;
                // 从小到大
                for (; j >= 0; j--) {
                    if (max[j] < tmp) {
                        int t = max[j];
                        max[j] = tmp;
                        tmp = t;
                    }
                }
            }
        }
        if (max[0] * max[1] > min[0] * min[1]) {
            res = 1L * max[0] * max[1] * max[2];
        } else {
            res = 1L * min[0] * min[1] * max[2];
        }
        System.out.println(res);
    }

    /**
     * 字符串大整数乘法
     * 抄袭
     *
     * @param in
     */
    public static void test2(Scanner in) {

    }

    public static String multiply2(String num1, String num2) {
        char[] n1 = toInteger(num1.toCharArray());
        char[] n2 = toInteger(num2.toCharArray());

        char[] result = new char[n1.length + n2.length];
        int carry = 0;
        int index = 1;

        for (int i = n1.length - 1; i >= 0; i--) {
            if (n1[i] > 0) {
                for (int j = n2.length - 1; j >= 0; j--) {
                    index = i + j + 1;
                    int temp = (int) result[index];
                    temp = temp + carry + (n1[i] * n2[j]);
                    result[index] = (char) (temp % 10);
                    carry = temp / 10;
                }
                if (carry != 0) {
                    result[index - 1] = (char) carry;
                    carry = 0;
                }
            }
        }

        String res = new String(toChar(result));
        while (res.length() > 1 && res.charAt(0) == '0') {
            res = res.substring(1);
        }
        return res;
    }

    private static char[] toInteger(char[] c) {
        for (int i = 0; i < c.length; i++) {
            c[i] -= '0';
        }
        return c;
    }

    private static char[] toChar(char[] c) {
        for (int i = 0; i < c.length; i++) {
            c[i] += '0';
        }
        return c;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {

        }
    }
}
