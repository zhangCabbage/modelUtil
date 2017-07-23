package zhang.algorithm.modelUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/7/14
 * Time: 下午4:37
 * To change this template use File | Settings | File Templates.
 */
public class HelloWorld {

    public static void main(String[] args) throws InterruptedException {
        char[] c = "aab".toCharArray();
        System.out.println(String.valueOf(c, 1, 2));

        HelloWorld test = new HelloWorld();
        System.out.println(test.generateParenthesis(0));
    }

    private List<String> res = null;

    public List<String> generateParenthesis(int n) {
        res = new ArrayList<>();
        generateParenthesis("", n, n);

        return res;
    }

    /**
     * left、right表示剩下的左右括号数
     *
     * @param left
     * @param right
     */
    private void generateParenthesis(String str, int left, int right) {
        if (left <= right) {
            if (left == 0 && right == 0) res.add(str);
            if (left > 0) generateParenthesis(str + "(", left - 1, right);
            if (right > 0) generateParenthesis(str + ")", left, right - 1);
        }
    }

}
