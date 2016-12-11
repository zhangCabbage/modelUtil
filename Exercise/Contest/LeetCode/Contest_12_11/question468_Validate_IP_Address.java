package zhang.algorithm.modelUtil.Exercise.Contest.LeetCode.Contest_12_11;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/12/11
 * Time: 上午11:05
 * To change this template use File | Settings | File Templates.
 */
public class question468_Validate_IP_Address {
    /**
     * @param IP
     * @return
     */
    public String validIPAddress(String IP) {
        Pattern ipv6 = Pattern.compile("^([0-9a-fA-F]{1,4}:){7}([0-9a-fA-F]{1,4})$");
//        System.out.println(ipv6.pattern());
        Matcher matcher = ipv6.matcher(IP);
        if (matcher.matches()) {
            return "IPv6";
        } else {
            Pattern ipv4 = Pattern.compile("^((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9]).){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])$");
//            System.out.println(ipv4.pattern());
            matcher = ipv4.matcher(IP);
            if (matcher.matches()) return "IPv4";
            return "Neither";
        }
    }

    public static void main(String[] args) {
        question468_Validate_IP_Address test = new question468_Validate_IP_Address();
        String IP = "f:f:f:f:f:f:f:f";
        System.out.println(test.validIPAddress(IP));
    }
}
