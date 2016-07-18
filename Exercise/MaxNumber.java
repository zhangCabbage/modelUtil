package zhang.algorithm.modelUtil.Exercise;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/7/18
 * Time: 下午6:47
 * To change this template use File | Settings | File Templates.
 */
public class MaxNumber {
    public static int findMaxNumber(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int[] array = new int[n + 1];
        array[1] = 1;
        array[2] = 1;
        int max = 1;

        int index = 1, cur = 3;
        while (cur < n + 1) {
            array[cur] = array[index] + array[++index];
            if (array[cur] > max) {
                max = array[cur];
            }
            if (++cur < n + 1) {
                array[cur++] = array[index];
            }
        }
        return max;
    }

    public static void main(String[] args) {

        System.out.println(findMaxNumber(1));
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 1 << 16);
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out), 1 << 16);
//        try {
//            int n = Integer.parseInt(reader.readLine());
//            int[] array = new int[n];
//
//            for (int i = 0; i < n; i++)
//                array[i] = findMaxNumber(Integer.parseInt(reader.readLine()));
//
//            for (int i = 0; i < n; i++)
//                writer.write(array[i] + "\r\n");
//
//            writer.flush();
//        } catch (IOException e) {
//
//        }

    }
}
