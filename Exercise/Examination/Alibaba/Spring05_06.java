package zhang.algorithm.modelUtil.Exercise.Examination.Alibaba;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/5/5
 * Time: 下午8:34
 * To change this template use File | Settings | File Templates.
 * <p>
 * 阿里校招二次笔试 -- 中间件笔试
 */
public class Spring05_06 {
    /**
     * 内存数据块拷贝， 将一组内存缓存区数据拷贝到另外一组内存缓冲区，
     * 将输入缓冲区的数据List< Buffer > input拷贝到输出缓冲区List< Buffer > output.
     * 需要注意的是：输入input中的每一个buffer大小不确定， output中每一个buffer大小不确定， 都不是固定大小，并且相互之间不相等。
     * <p>
     * typedef struct _buffer {
     * char * buf;
     * unsigned long length;
     * }Buffer;
     */
    class Buffer {
        public char[] buf;
        public long length;

        public Buffer() {
        }

        public Buffer(char[] buf, long length) {
            this.buf = buf;
            this.length = length;
        }

        @Override
        public String toString() {
            return "Buffer{" +
                    "buf=" + String.valueOf(buf) +
                    ", length=" + length +
                    '}';
        }
    }

    public void fun1(List<Buffer> input, List<Buffer> output) {
        int m = 0; //表示output第几个元素
        int n = 0; //表示output对应Buf数组, 哪个内容位置
        loop:
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length; j++) {
                Buffer tmp = output.get(m);
                tmp.buf[n++] = input.get(i).buf[j];
                if (n == tmp.length) {
                    m++;
                    n = 0;
                }
                if (m == output.size()) {
                    System.out.println("output buffer list is small!");
                    break loop;
                }
            }
        }
        System.out.println("input --> " + input);
        System.out.println("output --> " + output);
    }

    /**
     * 用来测试fun1
     */
    public void testFun1() {
        List<Buffer> input = construtBufferList("Hello zhang jia hua");
        List<Buffer> output = construtBufferList("today is Friday");
        fun1(input, output);
    }

    public List<Buffer> construtBufferList(String str) {
        List<Buffer> input = new ArrayList<>();
        String[] strs = str.split(" ");
        for (int i = 0; i < strs.length; i++) {
            input.add(new Buffer(strs[i].toCharArray(), strs[i].length()));
        }
        return input;
    }

    //-------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------

    /**
     * 2 代表abc，3代表def，4代表ghi，5代表jkl，6代表mno，7代表pqrs，8代表tuv，9代表wxyz， 1就代表1,0就代表0
     * 假设按了2键，那么可能产生a字符或b或c，
     * 设计一种算法，当用户按了任意个数字键，则输出该数字串对应的所有字符或数字组合
     */
    public char[][] strs = {
            {'0'},
            {'1'},
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'},
    };

    char[] output = null;

    public void fun2(String input) {
        output = new char[input.length()];
        List<String> result = new ArrayList<>(16);
        fun2(input, 0, result);
        System.out.println(result);
    }

    public void fun2(String input, int i, List<String> result) {
        if (i == input.length()) {
            result.add(String.valueOf(output));
        } else {
            for (int k = 0; k < strs[input.charAt(i) - '0'].length; k++) {
                output[i] = strs[input.charAt(i) - '0'][k];
                fun2(input, i + 1, result);
            }
        }
    }

    public static void main(String[] args) {
        Spring05_06 test = new Spring05_06();
        Scanner in = new Scanner(System.in);
        System.out.println("请输入数字");
        while (in.hasNext()) {
            test.fun2(in.next());
        }
    }
}
