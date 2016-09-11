package zhang.algorithm.modelUtil.Exercise;


import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/9/9
 * Time: 下午7:43
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            int[][] map = new int[15][10];
            int[][] block = new int[4][4];
            int start = 0;
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    map[i][j] = in.nextInt();
                }
            }
            for (int i = 0; i < block.length; i++) {
                for (int j = 0; j < block[0].length; j++) {
                    block[i][j] = in.nextInt();
                }
            }
            start = in.nextInt();

            //从第5行开始比较
            int mapBegin = 4;
            int blockBegin = block.length - 1;
            boolean stop = false;

            for (int i = block.length - 1; i >= 0; i--) {
                boolean allZero = true;
                for (int j = 0; j < block[0].length; j++) {
                    if (block[i][j] == 1) {
                        allZero = false;
                        break;
                    }
                }
                if (allZero) blockBegin--;
                else break;
            }

            end:
            while (mapBegin < map.length) {
                int j = mapBegin;
                for (int i = blockBegin; i >= 0; i--) {
                    if (j < 4) break;
                    for (int k = 0; k < block[0].length; k++) {
                        if ((map[j][k + start - 1] & block[i][k]) == 1) {
                            stop = true;
                            break end;
                        }
                    }
                    j--;
                }
                mapBegin++;
            }

            mapBegin--;
            for (int i = 0; i <= blockBegin; i++) {
                for (int j = 0; j < block[0].length; j++) {
                    map[mapBegin - i][start + j - 1] |= block[blockBegin - i][j];
                }
            }

            //输出
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    System.out.print(map[i][j]);
                    if (j != map[0].length - 1)
                        System.out.print(" ");
                }
                System.out.println();
            }
        }

    }
}