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

    public static void main(String[] args) {
        question475_Heaters test = new question475_Heaters();
        int[] houses = {1, 2, 3, 4};
        int[] heaters = {1, 4};
        System.out.println(test.findRadius(houses, heaters));
    }
}
