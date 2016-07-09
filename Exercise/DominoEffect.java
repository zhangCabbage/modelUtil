package zhang.algorithm.modelUtil.Exercise;

import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/7/9
 * Time: 下午7:55
 * To change this template use File | Settings | File Templates.
 */
public class DominoEffect {
    /**
     * 帮助闻老四做的IT校招在线笔试题
     * <p>
     * 题目大意:
     * 输入一个字符数组,字符数组可能为R、C、?三种情况。
     * 像推多米诺骨牌一样的推这个字符数组
     * 如果(i, j)是R, 那么(i, j+1)也顺着被推倒
     * 如果(i, j)是C, 那么(i+1, j)也顺着被推倒
     * 现在此字符数组0<i, j<51
     * ?可以等概率的取值为R或C, ?最多不超过10个
     * <p>
     * 问平均需要推多少次才能把整个字符数组都给推倒?
     * AC,但是花的时间有点长,测试382ms, 代码不过简洁, 可以对代码进行重构!!
     *
     * @param arrays
     * @return
     */
    public double find(char[][] arrays) {
        int m = arrays.length;//行
        int n = arrays[0].length;//列

        double[][] result = new double[m][n];
        result[0][0] = 1;
        double max = result[0][0];

        char leftPre, topPre;
        //第一行
        for (int j = 1; j < n; j++) {
            leftPre = arrays[0][j - 1];

            if (leftPre == 'R') {
                result[0][j] = result[0][j - 1];
            } else if (leftPre == 'C') {
                result[0][j] = result[0][j - 1] + 1;
                max += 1;
            } else {
                result[0][j] = result[0][j - 1] + 0.5;
                max += 0.5;
            }
        }
        //第一列
        for (int i = 1; i < m; i++) {
            topPre = arrays[i - 1][0];

            if (topPre == 'R') {
                result[i][0] = max + 1;
                max += 1;
            } else if (topPre == 'C') {
                result[i][0] = result[i - 1][0];
            } else {
                result[i][0] = max + 0.5;
                max += 0.5;
            }

        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                topPre = arrays[i - 1][j];
                leftPre = arrays[i][j - 1];

                if (topPre == 'C') {
                    result[i][j] = result[i - 1][j];
                } else if (topPre == 'R') {
                    if (leftPre == 'R') {
                        result[i][j] = result[i][j - 1];
                    } else if (leftPre == 'C') {
                        result[i][j] = max + 1;
                        max += 1;
                    } else {
                        result[i][j] = max + 0.5;
                        max += 0.5;
                    }
                } else {
                    if (leftPre == 'R') {
                        result[i][j] = result[i][j - 1];
                    } else if (leftPre == 'C') {
                        result[i][j] = max + 0.5;
                        max += 0.5;
                    } else {
                        result[i][j] = max + 0.25;
                        max += 0.25;
                    }
                }
            }
        }
        return max;
    }

    public static char[][] string2Char(String[] strs) {
        char[][] c = new char[strs.length][];
        int i = 0;
        for (String str : strs) {
            c[i++] = str.toCharArray();
        }
        return c;
    }

    public static void main(String[] args) {
        DominoEffect test = new DominoEffect();
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int m = in.nextInt();//行
            int n = in.nextInt();
            String[] strs = new String[m];
            for (int i = 0; i < m; i++) {
                strs[i] = in.next();
            }
            char[][] array = string2Char(strs);
            System.out.println(test.find(array));
        }
    }
}
