package zhang.algorithm.modelUtil.String;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/7/11
 * Time: 下午7:07
 * To change this template use File | Settings | File Templates.
 */
public class StringTool {
    /**
     * 把一个string类型的字符串转换成int类型, 并且能识别首尾+、-号
     *
     * @param str
     * @return
     */
    public static int string2int(String str) {
        char first = str.charAt(0);
        int flag = 1;
        int result = 0;
        if (first == '-') {
            flag = -1;
        }else if (first != '+') {
            result = first - '0';
        }

        for (int i = 1; i < str.length(); i++) {
            result = result * 10 + (str.charAt(i) - '0');
        }
        return flag * result;
    }
}
