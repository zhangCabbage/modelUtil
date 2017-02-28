package zhang.algorithm.modelUtil.NumberTheory;

import zhang.algorithm.modelUtil.Array.ArrayTool;
import zhang.algorithm.modelUtil.ZhangUtil;

/**
 * 约瑟环问题的解决 <br/>
 * 约瑟环问题最大的特点就是：前后状态可以相互迭代推导，下标之间的转换，从而可以导出每次淘汰人的下标之间的递推公式 <br/>
 *
 * @author zhang_zack
 */
public class JosephRing {
    /**
     * <strong>问题描述：</strong><br/>
     * n个人（编号0~(n-1))，从0开始报数，报到(m-1)的退出，剩下的人继续从0开始报数。求最后胜利者的编号。<br/>
     * <br/>
     * [【[约瑟环]圆圈中最后剩下的数字】](http://blog.chinaunix.net/uid-20618535-id-274053.html)<br/>
     * 其中有一段解说不太清楚，对第一个删除的数字是m%n-1，我有疑问，如果n==m，那么算的结果就是-1了啊，可以替换成(m-1)%n
     * 很容易看出迭代关系, 当清楚了这个之后, 我们把注意力只放在最后胜利者上面, 不再去想其他问题
     * f(n,m)=f’(n-1,m)=[f(n-1,m)+m]%n <br/>
     * <p>
     * 注意:
     * 1) 初始值finalWinner = 0
     * 2) 取余操作i为上层(问题规模更大)那个的规模容量值
     *
     * @param n n个人参与游戏
     * @param m 从1开始报数的话，每次报到数m时，此人out。
     * @return
     */
    public static int findFinalWinnerNumber(int n, int m) {
        if (n < 1 || m < 0) {
            return -1;
        }
        int finalWinner = 0;
        for (int i = 2; i <= n; i++) {
            finalWinner = (finalWinner + m) % i;
            System.out.println("----> " + (finalWinner + 1));
        }
        return finalWinner + 1;
    }

//--------------------------------------------------------------------------------------------------------------------

    /**
     * 问题二：有n个好人，n个坏人，找出n个坏人的排法，使得优先把所有坏人给pass掉<br/>
     * 暴力法解决问题,O(n*m)复杂度，没有什么可以参考的价值
     *
     * @param n
     * @param m
     */
    public static void findAllIndex1(int n, int m) {
        int[] flag = new int[2 * n];
        int num = n;
        int index = 0;//当前的下标
        int count = 0;//表示当前这一轮数了多少个数
        while (num > 0) {
            if (flag[index] == 0) {
                count++;
            }
            if (count == m) {
                flag[index] = 1;
                count = 0;
                num--;
            }
            //递增，为下一步做准备
            index++;
            if (index == flag.length) {
                index = 0;
            }
        }
        for (int i = 0; i < 2 * n; i++) {
            if (flag[i] == 1) {
                System.out.println(i + 1);
            }
        }
    }

//--------------------------------------------------------------------------------------------------------------------

    /**
     * 问题三：2n个人围成一圈，前n个人为好人，后n个人为坏人
     * 从第一个人开始报数，报数报到m时，该人退出此圈
     * 现要求确定一个最小m的值使得所有坏人在任意一个好人之前全部出圈。题目来源:【HDU1443_Joseph_约瑟环】<br/>
     * <br/>
     * [【HDU1443_Joseph_约瑟环】](http://www.cnblogs.com/cchun/archive/2012/02/14/2520224.html)<br/>
     *
     * @return
     */
    public static int[] findMinM2KillAllBad() {
        int[] res = new int[14];
        for (int i = 1; i < 14; i++) {
            for (int j = i + 1; ; j++) {
                if (isKillAllBad(i, j)) {
                    res[i] = j;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 前k个为好人，后k个为坏人
     *
     * @param k
     * @param m
     * @return
     */
    private static boolean isKillAllBad(int k, int m) {
        int start = 0;//好人起始位置
        int end = k - 1;//好人结束位置
        int kill;//当前要杀的人位置
        for (int n = 2 * k; n > k; n--) {
            kill = (m - 1) % n;
            if (kill >= start && kill <= end) {
                return false;
            }
            start = ((start - m) % n + n) % n;
            end = ((end - m) % n + n) % n;
        }
        return true;
    }

    public static void main(String[] args) {
//		System.out.println(findFinalWinnerNumber(2, 2));
//		findAllIndex1(4, 3);
        ZhangUtil.setStartTime();
        ArrayTool.printArray(findMinM2KillAllBad());
        System.out.println(ZhangUtil.getIntervalTime());
    }
}
