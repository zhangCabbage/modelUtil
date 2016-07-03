package zhang.algorithm.modelUtil.NumberTheory;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/7/3
 * Time: 上午11:42
 * To change this template use File | Settings | File Templates.
 * <p>
 * 《编程之美》2.8章节-找符合条件的整数的Java实现
 */
public class ConditionalInteger {

    public double findFitInteger(int N) {
        //最小余数数组, 用来存放余数为 i 的最小的整数,并且其中只存放此整数一个位置
        ArrayList<Integer>[] remainders = new ArrayList[N];
        //起始值
        int i = 1;
        int j = 10 % N;

        remainders[1] = new ArrayList();
        remainders[1].add(0);

        int noUpdate = 0;
        while (true) {
            boolean flag = false;

            //更新 10^i 对应数的余数
            if (remainders[j] == null) {
                remainders[j] = new ArrayList<Integer>();
                remainders[j].add(i);
                flag = true;
            }

            //更新 1 ~ (10^i-1) 对应数的余数
            for (int k = 1; k < N; k++) {
                if (remainders[k] != null && remainders[(k + j) % N] == null && i > remainders[k].get(remainders[k].size() - 1)) {
                    //为什么当我没加后面的判断时,remainders数组中会有很多重复的1,2等
                    //避免同级之间相互污染!!!
                    remainders[(k + j) % N] = new ArrayList(remainders[k]);
                    remainders[(k + j) % N].add(i);
                    flag = true;
                }
            }

            if (flag == false) noUpdate++;
            else noUpdate = 0;

            //Why?
            if (noUpdate == N || remainders[0] != null) {
                break;
            }

            //更新i, j的值
            i++;
            j = (j * 10) % N;
            //这里需要明白一件事
            //10^(i+1) % N == ((10^i % N) *10) % N
        }

        //最后的表示好像有点问题
        //最后remainders[0]就是我们要找的最小的 M*N 的值
        double result = 0;
        for (int k = 0; k < remainders[0].size(); k++) {
            result += Math.pow(10, remainders[0].get(k));
        }
        return result;
    }

    public static void main(String[] args) {

        ConditionalInteger test = new ConditionalInteger();
        int N = 99;
        double res = test.findFitInteger(N);
        double M = res / N;
        System.out.println("M -->" + M);
        System.out.println("M * N --> " + res);
    }
}
