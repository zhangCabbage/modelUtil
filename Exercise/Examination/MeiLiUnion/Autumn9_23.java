package zhang.algorithm.modelUtil.Exercise.Examination.MeiLiUnion;

import java.util.Scanner;

/**
 * Created by jiahua on 2017/9/23.
 */
public class Autumn9_23 {
    /**
     * 输入年月日，输出是当年第几天
     *
     * @param in
     */
    private static void test1(Scanner in) {
        int year = in.nextInt();
        int month = in.nextInt();
        int day = in.nextInt();
        int[] days = {0, 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};
        int[] new_days = {0, 0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335, 366};

        if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
            days = new_days;

        int res = days[month] + day;
        System.out.println(res);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            test1(in);
        }
    }
}
