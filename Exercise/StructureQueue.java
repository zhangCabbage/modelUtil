package zhang.algorithm.modelUtil.Exercise;

import zhang.algorithm.modelUtil.ZhangUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/1/14
 * Time: 下午4:03
 * To change this template use File | Settings | File Templates.
 */
public class StructureQueue {

    public static void main(String[] args) {
        int n = 10000;
        ZhangUtil.setStartTime();
        generateQueue1(n);
        System.out.println("generateQueue1 time --> " + ZhangUtil.getIntervalTime() + "ms");
        generateQueue2(n);
        System.out.println("generateQueue2 time --> " + ZhangUtil.getIntervalTime() + "ms");
        generateQueue3(n);
        System.out.println("generateQueue3 time --> " + ZhangUtil.getIntervalTime() + "ms");

        //when n = 10000
        //generateQueue1 time --> 28ms
        //generateQueue2 time --> 65ms
        //generateQueue3 time --> 630ms
    }


    /**
     * 题目来源: https://mp.weixin.qq.com/s?__biz=MzI1MTIzMzI2MA==&mid=2650560645&idx=2&sn=ec172f9cb1c285e059968113fde82f26&chksm=f1feee06c68967100e784edc654dad7159824928e2ec1a5e18098f382ca93cb80755ccc8e43a&mpshare=1&scene=1&srcid=0114ck7i2xkfVScjQx6Mm06t&key=9c64d5a8772eec3aec9552d5ae4ac84bc772c05373398da1f8fed1fad96c528da8ec28729805170dd695c865d8c4bddbdbb5735698d8f6594936fdd53537143ab6cb8bcd02df398358fe3e9ce828156c&ascene=0&uin=NDMwMzMyNTU1&devicetype=iMac+MacBookPro12%2C1+OSX+OSX+10.11+build(15A284)&version=11020201&pass_ticket=aDV1FPVSRJxEwcbJ%2F8Oz390xS6xOTM6B72zY98orIVbGjpxcLl0AYYdj6%2BucWyp9
     * 按照我自己的思路进行迭代
     * 把原始获取结果的逻辑逆转 --> 从大到小进行插入列表头位置, 每次插入之后把列表最后一位移至列表头
     * 当n = 100000时, time is 122ms
     *
     * @param n
     */
    public static void generateQueue1(int n) {
        List<Integer> list = new LinkedList<>();
        for (int i = n; i > 0; i--) {
            list.add(0, i);
            list.add(0, list.get(list.size() - 1));
            list.remove(list.size() - 1);
        }
        System.out.println(list.indexOf(n));
        System.out.println(list.toString());
    }

    /**
     * 以1-n为原始队列元素,按照题目获取数据逻辑操作得到输出结果, 之后赋值输出结果对应的下标给对应数组元素相应于输出结果位置
     * 最后输出结果, 这种方法最终效果没有我的方法好
     *
     * @param n
     */
    public static void generateQueue2(int n) {
        List<Integer> original = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            original.add(i);
        }
        int[] tmp = new int[n];
        int i = 0;
        while (original.size() != 0) {
            original.add(original.get(0));
            original.remove(0);
            tmp[i++] = original.get(0);
            original.remove(0);
        }

        int[] res = new int[n];
        for (int j = 0; j < n; j++) {
            res[tmp[j] - 1] = j + 1;
        }
        for (int j = 0; j < n; j++) {
            System.out.print(res[j] + " ");
        }
        System.out.println();
    }

    /**
     * 采用[约瑟夫环]的方式来解决此问题
     * 我们目的是为了求第i次遍历的是原数组中哪个下标对应的元素
     * 0 - n-2 第1次遍历的是 0 - n-1 中第2遍历的下标, 约瑟夫方式迭代1次
     * 0 - n-3 第1次遍历的是 0 - n-1 中第3遍历的下标, 约瑟夫方式迭代2次
     * .
     * .
     * .
     * 0 - 1 第1次遍历的是 0 - n-1 中第n-1遍历的下标, 约瑟夫方式迭代n-2次
     * 0 第1次遍历的是 0 - n-1 中第n遍历的下标, 约瑟夫方式迭代n-1次
     * <p>
     * 通过验证, 上面的分析是正确的。
     * index = 0, 迭代n-1次, 为n元素的下标
     * 其余情况index=1, 迭代(1 - n-2)次, 为(2 - n-1)个元素的位置
     * 因此, 导致这种情况下, 时间复杂度为O(n^2)
     *
     * @param n
     */
    public static void generateQueue3(int n) {
        int[] res = new int[n];
        int x = 1;

        if (n > 1) res[1] = x++;

        int index;
        for (int i = 0; i < n - 2; i++) {
            index = 1;
            for (int j = n - i; j <= n; j++) {
                index = (index + 2) % j;
            }
            res[index] = x++;
        }

        index = 0;
        for (int i = 2; i <= n; i++) {
            index = (index + 2) % i;
        }
        res[index] = x;

        for (int i = 0; i < n; i++) {
            System.out.print(res[i] + " ");
        }
        System.out.println();
    }
}
