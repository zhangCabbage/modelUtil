package zhang.algorithm.modelUtil.Exercise.Contest.LeetCode.Fourth;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/9/11
 * Time: 上午10:26
 * To change this template use File | Settings | File Templates.
 */
public class question396_Rotate_Function {

    /**
     * Time Limit Exceeded
     * this is the original method that time exceeded without a doubt
     *
     * @param A
     * @return
     */
    public int maxRotateFunction(int[] A) {
        if (A == null || A.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            List<Integer> list = IntStream.of(A).boxed().collect(Collectors.toList());
//            List<Integer> list = new ArrayList<>();
//            for (int j = 0; j < A.length; j++) {
//                list.add(A[j]);
//            }
            Collections.rotate(list, i);
            int temp = 0;
            for (int j = 0; j < list.size(); j++) {
                temp += (j * list.get(j));
            }
            max = Math.max(max, temp);
        }
        return max;
    }

    /**
     * 这种题一看就不能直接使用先转换在求解, 一步步循序渐进的方式, 要注意观察前后两个转换求值之间的差异变化!!
     * <p>
     * 17 / 17 test cases passed
     * Status: Accepted
     * Runtime: 6 ms
     *
     * @param A
     * @return
     */
    public int maxRotateFunction2(int[] A) {
        int candidate = 0;
        int sum = 0;
        int n = A.length;
        for (int i = 0; i < n; i++) {
            sum += A[i];
            candidate += A[i] * i;
        }

        int max = candidate;
        for (int i = n - 1; i > 0; i--) {
            candidate = candidate + sum - A[i] * n;
            max = Math.max(candidate, max);
        }

        return max;
    }


    public static void main(String[] args) {
        question396_Rotate_Function test = new question396_Rotate_Function();
        int[] A = {4, 3, 2, 6};
        System.out.println(test.maxRotateFunction(A));
        System.out.println(test.maxRotateFunction2(A));
    }
}
