package zhang.algorithm.modelUtil.Exercise.Contest.LeetCode.Third;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/9/4
 * Time: 下午8:19
 * To change this template use File | Settings | File Templates.
 */
public class question393_UTF8_Validation {
    /**
     * this way is ok, but a little bug
     * 45 / 45 test cases passed
     * Status: Accepted
     * Runtime: 7 ms
     *
     * @param data
     * @return
     */
    public boolean validUtf8(int[] data) {
        int follow = 0;
        for (int digit : data) {
            if (follow != 0) {
                if ((digit & 0xC0) == 0x80) {
                    follow--;
                } else {
                    return false;
                }
            } else {
                if (digit >= 248) return false;
                if ((digit & 0xF8) == 0xF0) follow = 3;
                else if ((digit & 0xF0) == 0xE0) follow = 2;
                else if ((digit & 0xE0) == 0xC0) follow = 1;
                else if ((digit & 0x80) == 0x80) return false;
            }
        }

        return follow == 0;
    }

    public static void main(String[] args) {
        question393_UTF8_Validation test = new question393_UTF8_Validation();
        int[] datas = {197, 130, 1};
        System.out.println(test.validUtf8(datas));
    }
}
