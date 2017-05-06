package zhang.algorithm.modelUtil.Exercise.Examination.Indeed;

import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/4/22
 * Time: 下午9:02
 * To change this template use File | Settings | File Templates.
 */
public class Spring4_22 {

    /**
     * @param in
     */
    public static void fun1(Scanner in) {
        int n = in.nextInt();
        int m = in.nextInt();

        int left = 1, right = n, top = 1, bottom = n;
        int i = 1, j = 1;
        int direction = 1;
        for (int k = 1; k < m; k++) {
            if (direction == 1) {
                if (j < right) {
                    j++;
                    if (j == right) {
                        top++;
                        direction = 2;
                    }
                }
            } else if (direction == 2) {
                if (i < bottom) {
                    i++;
                    if (i == bottom) {
                        right--;
                        direction = 3;
                    }
                }
            } else if (direction == 3) {
                if (j > left) {
                    j--;
                    if (j == left) {
                        bottom--;
                        direction = 4;
                    }
                }
            } else {
                if (i > top) {
                    i--;
                    if (i == top) {
                        left++;
                        direction = 1;
                    }
                }
            }
        }
        System.out.println(i + " " + j);
    }


    /**
     * 有N个机器人, 需要在T时刻内, 每个时刻N个机器人都在(Cij, Dij)位置中一个, (0 <= i <= T, 0 <= j <= N)
     * 使用|xi - xj| + |yi - yj|来表示距离
     * 问T时刻能N个机器人最小移动距离
     *
     * @param in
     */
    public static void fun2(Scanner in) {

    }


    /**
     * N i
     * a1 b1
     * a2 b2
     * ...
     * ai bi
     * N个点之间互联通的连接方式有多少种, 其中(ai, bi)表示ai和bi之间不能直接连通
     *
     * @param in
     */
    public static void fun3(Scanner in) {

    }

    /**
     * i A B
     * N1
     * N2
     * ....
     * Ni
     * i表示怪兽的个数, Ni表示怪物血量, A表示人能进行魔法攻击的最多次数, B表示魔法和普攻总共最多次数
     * 其中魔法攻击力为K, 普攻攻击力为1
     * 求能否把i个小怪兽都消灭, 如果不能消灭输出-1, 如果能够消灭输出魔法攻击力最小值
     *
     * @param in
     */
    public static void fun4(Scanner in) {

    }

    public static void main(String[] args) {
        
    }
}
