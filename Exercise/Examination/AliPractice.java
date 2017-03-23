package zhang.algorithm.modelUtil.Exercise.Examination;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/3/8
 * Time: 下午10:33
 * To change this template use File | Settings | File Templates.
 */
public class AliPractice {

    public static void main(String[] args) {
        int[] A = {10, 2, 11, 13, 1, 1, 1, 1, 1};
        System.out.println(resolve(A));
    }

    /**
     * 阿里3月份实习编程测试, 分割一个全为正数的数组为4等份, 分割下标不算入每组之和
     * eg: {2，5，1，1，1，1，4，3，7，5，7}分为四份，每份的和相同，分割点不算，分为{2，5} {1，1，1，4}，{7}，{7}
     * 参考bbs论坛: https://bbs.byr.cn/#!article/Java/55292
     *
     * @param A
     * @return
     */
    static boolean resolve(int[] A) {
        int[] sums = new int[A.length];
        Map<Integer, Integer> map = new HashMap<>();
        int tmp = 0;
        for (int i = 0; i < A.length; i++) {
            tmp += A[i];
            sums[i] = tmp;
            map.put(tmp, i);
        }

        for (int i = 1; i < A.length; i++) {
            int s = sums[i] - A[i];
            if (map.containsKey(s + sums[i])) {
                int pos2 = map.get(s + sums[i]) + 1;
                if (pos2 < A.length && map.containsKey(sums[pos2] + s)) {
                    int pos3 = map.get(sums[pos2] + s) + 1;
                    if (pos3 < A.length && sums[A.length - 1] - sums[pos3] == s) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
