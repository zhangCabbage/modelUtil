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
public class Rotate_Function {

    /**
     * Time Limit Exceeded
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


    public static void main(String[] args) {
        Rotate_Function test = new Rotate_Function();
        int[] A = {4, 3, 2, 6};
        System.out.println(test.maxRotateFunction(A));
    }
}
