package zhang.algorithm.modelUtil.Exercise.Examination.HUAWEI;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/4/7
 * Time: 下午6:59
 * To change this template use File | Settings | File Templates.
 * <p>
 * 华为2018年春招笔试
 * [经验教训]:
 * 1) 2个小时3道没有设计难度的题没有做完! 定时AC题目的能力简直太弱了, 训练自己定时做题的能力!
 * 2)
 */
public class Spring4_07 {
    /**
     * 1)  -->  100%
     * 送分题, 没什么好讲
     */
    public static void fun1() {
        int cnt = 0;
        int sum = 0;
        for (int i = 100; i < 1000; i++) {
            int tmp = i;
            int basic = 100;
            int total = 0;
            for (int j = 0; j < 3; j++) {
                total += Math.pow(tmp / basic, 3);

                if (total > i) break;
                tmp %= basic;
                basic /= 10;
            }

            if (total == i) {
                System.out.println("第" + (++cnt) + "个水仙花数: " + i);
                sum += i;
            }
        }
        System.out.println("水仙花数总和为: " + sum);
    }

    //-----------------------------------------------------------------------------------------------------------
    // 思路正确, 但是代码码太水。
    // My error solution  -->  60%
    // 1) 如何判断输入的正确性! 因为输入格式非常固定, 所以可以采用比较简单的方式来进行验证!
    // 2)
    //-----------------------------------------------------------------------------------------------------------

    /**
     * AC Solution
     *
     * @param in
     */
    public static void fun2(Scanner in) {
        String s = in.next();
        //1_ 输入验证
        if (s.length() != 5 || s.charAt(1) != ',' || s.charAt(3) != ',') {
            System.out.println(-1);
            return;
        }

        int[] map = new int[10];
        int[] nums = new int[3]; //存储输入的3个数字
        int[] arrays = new int[5]; //可能的总数字, 包括可替换的, 顶多5个, 1 ~ 9
        int cnt = 0;  //可能的总数字个数
        for (int i = 0; i < 3; i++) {
            int c = s.charAt(i << 1) - '0';
            if (c <= 0 || c > 9 || map[c] == 1) {
                System.out.println(-1);
                return;
            }
            map[c] = 1;
            arrays[cnt++] = nums[i] = c;
            if (c == 2 || c == 6) {
                map[c + 3] = 1;
                arrays[cnt++] = c + 3;
            }
            if (c == 5 || c == 9) {
                map[c - 3] = 1;
                arrays[cnt++] = c - 3;
            }
        }

        //2_ 排列组合, 排序, 可以使用数组来进行存储, 代替使用list! 因为最多5个数
        //使用暴力, 没有啥难度
        Arrays.sort(nums);
        int N = nums[2];
        int[] res = new int[300];
        cnt = 0;
        Arrays.fill(map, 0);
        helper(res, arrays, map, 0);
        int[] lastRes = Arrays.copyOfRange(res, 1, res[0] + 1);
        Arrays.sort(lastRes);
        System.out.println(Arrays.toString(lastRes));
        System.out.println(lastRes[N - 1]);
    }

    /**
     * 使用res[0]来存储res中元素个数, res[1~res[0]+1]是组合数
     *
     * @param res
     * @param arrays
     * @param map
     * @param num
     */
    private static void helper(int[] res, int[] arrays, int[] map, int num) {
        for (int i = 0; i < arrays.length; i++) {
            int tmp = arrays[i];
            if (tmp != 0 && map[tmp] != 1) {
                map[tmp] = 1;
                int addittion = 0;
                if (tmp == 2 || tmp == 6) {
                    addittion = tmp + 3;
                    map[addittion] = 1;
                }
                if (tmp == 5 || tmp == 9) {
                    addittion = tmp - 3;
                    map[addittion] = 1;
                }

                int cur = num * 10 + tmp;
                res[++res[0]] = cur;
                helper(res, arrays, map, cur);

                if (addittion != 0) map[addittion] = 0;
                map[tmp] = 0;
            }
        }
    }

    //-----------------------------------------------------------------------------------------------------------
    // input:  [1.80.1.10]|[2.20.11.15]|[3.50.21.10]|[4.120.31.10]|[5.150.41.10]
    // output: 0.1|1.10|2.10|3.10|4.10|5.10|2.5|0.244
    // 这道题本来我[使用startTime来进行排序], 但是后续根据优先级进行截断、调整任务没有很好的解决方案。
    // 本来预感应该需要[反向先使用优先级, 后用startTime来进行排序], 但是后续操作没有什么想法。
    // 因为题目要求输出前300s的任务情况, 所以可以使用数组来记录
    //-----------------------------------------------------------------------------------------------------------

    static class Task implements Comparable<Task> {
        public int ID;
        public int priority;
        public int startTime;
        public int DuringTime;

        public Task(int ID, int priority, int startTime, int duringTime) {
            this.ID = ID;
            this.priority = priority;
            this.startTime = startTime;
            DuringTime = duringTime;
        }

        @Override
        public int compareTo(Task task) {
            if (this.priority != task.priority) return task.priority - this.priority;
            return this.startTime - task.startTime;
        }

    }

    public static void fun3(Scanner in) {
        String s = in.next();
        String[] strs = s.split("\\|");  //TODO

        Task[] tasks = new Task[strs.length];
        for (int i = 0; i < strs.length; i++) {
            String[] tmps = strs[i].substring(1, strs[i].length() - 1).split("\\.");
            tasks[i] = new Task(Integer.parseInt(tmps[0]), Integer.parseInt(tmps[1]), Integer.parseInt(tmps[2]), Integer.parseInt(tmps[3]));
        }
        Arrays.sort(tasks);

        int[] res = new int[300];
        for (int i = 0; i < tasks.length; i++) {
            int start = tasks[i].startTime;
            int cnt = 0;
            for (int index = start; index < 300; index++) {
                if (res[index] == 0) {
                    res[index] = tasks[i].ID;
                    if (++cnt == tasks[i].DuringTime) break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int flag = 0;
        for (int i = 1; i < res.length; i++) {
            if (res[i] != res[i - 1]) {
                if (sb.length() != 0) sb.append("|");
                sb.append(res[i - 1]).append(".").append(i - flag);
                flag = i;
            }
        }

        if (sb.length() != 0) sb.append("|");
        sb.append(res[res.length - 1]).append(".").append(res.length - flag);

        System.out.println(sb.toString());
    }

    //-----------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            fun2(in);
        }
    }
}
