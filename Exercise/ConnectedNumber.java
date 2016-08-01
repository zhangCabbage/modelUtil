package zhang.algorithm.modelUtil.Exercise;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/7/30
 * Time: 下午7:07
 * To change this template use File | Settings | File Templates.
 */
public class ConnectedNumber {
    /**
     * 这里通过空间换时间,速度已经非常快了
     * 计算公式 : (x+1) * C(x, n)
     * 但是本题测试结果发现有错误的, 一定是我这个理解题意有问题
     *
     * @param n
     * @return
     */
    public static int findExpectedNumber(int n) {
        int MOD = 1_000_000_007;
        float number = 0;
        float[] cn = new float[(n - 1) / 2 + 1]; //use to storage the c(n, i)

        for (int i = 0; i < n; i++) {
            int index = n - i - 1;
            if (i < cn.length) {
                if (i > 0) {
                    cn[i] = (float) (cn[i - 1] * ((n - i) * 1.0 / i)) % MOD;
                } else {
                    cn[i] = 1;
                }

                index = i;
            }

            number = (number + (i + 1) * cn[index]) % MOD;
        }
        return (int) number;
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 1 << 16);
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out), 1 << 16);
//        int n = Integer.parseInt(reader.readLine());
//
//        writer.write(findExpectedNumber(n) + "\r\n");
//
//        writer.flush();
        System.out.println(findExpectedNumber(7));
    }
}
