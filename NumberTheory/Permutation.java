package zhang.algorithm.modelUtil.NumberTheory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * (Permutation)全排列问题，递归思想的完全体现！！！<br/>
 * 参见：<br/>
 * [【字符串全排列算法】](http://blog.csdn.net/wzy_1988/article/details/8939140) <br/>
 * [【STL系列之十 全排列(百度迅雷笔试题)】](http://blog.csdn.net/morewindows/article/details/7370155)  给出了非递归的全排列方法 <br/>
 * 下面这个解释ok !!
 * [【全排列和全组合实现】](http://wuchong.me/blog/2014/07/28/permutation-and-combination-realize/) 这里总结了关于组合的求法！<br/>
 *
 * @author zhang_zack
 */
public class Permutation {
    private Map<String, Integer> map = new HashMap<String, Integer>();
    private int m;
    public int numCount;//用作记录全排列的个数

    /**
     * 通常解法
     * 1、采用递归的方式，没有限制的列出所有可能情况 <br/>
     * 针对只需要列出全排列的情况：可通过在最后一步，根据字符串的长度来判断是否输出，从而达到全排列输出的效果。<br/>
     * <br/>
     * 但是这种方法，在输入字符串有重复的条件下，不太容易实现去重复的全排列(如：abca)<br/>
     * <br/>
     * <br/>
     * 如何解决可重复字符串的全排列问题？？？？这种方法由于是一个字符一个字符堆叠，所以没法以很好的办法来进行去重。
     * 只能使用map来存放已有的字符串，来去重！！
     *
     * @param str
     */
    public void slowList(String str) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < str.length(); i++) {
            list.add(str.substring(i, i + 1));
        }
        numCount = 0;
        listAll(list, "", str.length());
    }

    public void listAll(List<String> list, String prefix, int len) {
        //输出所有可能的情况
//		if(prefix.length() == len){
//			System.out.println(prefix);
//		}
        //长度足够，并且不重复
        if (prefix.length() == len && !map.containsKey(prefix)) {
//			System.out.println(prefix);
            map.put(prefix, 1);
            numCount++;
        }

        for (int i = 0; i < list.size(); i++) {
            List<String> temp = new LinkedList<String>(list);
            listAll(temp, prefix + temp.remove(i), len);
        }
    }

//--------------------------------------------------------------------------------------------------------------------

    /**
     * 2、同样是采用递归的方法进行全排列，只不过这里没有多使用额外的空间，并且没有使用集合 <br/>
     * <br/>
     * arrange递归方法中end始终是strChar.length-1，如果是在单独类中可以把它提取为成员变量<br/>
     * <strong>值得注意的是：</strong>在递归中其实操作的都是同一个char[]类型的c，因为是一个个递归所以虽然操作的都是同一个，但是不会出现问题！！！
     * 这里是基于交换的思想 <br/>
     *
     * @param str
     */
    public void fastList(String str) {
        char[] strChar = str.toCharArray();
        numCount = 0;
        arrange2(strChar, 0, strChar.length - 1);
    }

    public void arrange2(char[] c, int start, int end) {
        if (start == end) {
            System.out.println(new String(c));
            numCount++;
        } else {
            for (int i = start; i <= end; i++) {
                if (isSwap(c, start, i)) {
                    swap(c, start, i);
                    arrange2(c, start + 1, end);
                    swap(c, i, start);//这里一定要记得重新调转回来！！！！！
                }
            }
        }
    }

    public void swap(char[] c, int head, int tail) {
        char temp = c[head];
        c[head] = c[tail];
        c[tail] = temp;
    }

    /**
     * 对于可重复的输入字符串，用来判断上述逻辑中两个下标对应的字符是否交换 <br/>
     * 如果当前要交换的下标前只要有与它相同的字符，那么就不再进行交换 <br/>
     *
     * @param c
     * @param start 起始下标
     * @param cur   当前要交换的下标
     * @return
     */
    public boolean isSwap(char[] c, int start, int cur) {
        for (int i = start; i < cur; i++) {
            if (c[i] == c[cur]) {
                return false;
            }
        }
        return true;
    }

//--------------------------------------------------------------------------------------------------------------------

    /**
     * 3、表示从str这么多字符中，选出m个做全排列。当m=str.length()时，就是上面全排列的情况<br/>
     * <br/>
     * 我的想法：每次取出m个数，然后进行全排列，但是我自己感觉没法很好的把这个程序写出来<br/>
     * 还是采用交换的思想[【定义一个数组，编程打印它的全排列】](http://blog.csdn.net/sunbingxi_/article/details/6125426)
     *
     * @param str
     * @param m
     */
    public void recursion3(String str, int m) {
        char[] strChar = str.toCharArray();
        if (strChar.length < m) {
            m = strChar.length;
        }
        numCount = 0;
        this.m = m;
        arrange3(strChar, 0, m);
    }

    /**
     * @param c
     * @param start
     * @param count 表示还需要循环打印的字符数
     */
    private void arrange3(char[] c, int start, int count) {
        if (count == 0) {
            //打印
            print(c);
            numCount++;
        } else {
            for (int i = start; i < c.length; i++) {
                if (isSwap(c, start, i)) {
                    swap(c, start, i);//后面的每个数都有机会做老大
                    arrange3(c, start + 1, count - 1);
                    swap(c, i, start);
                }
            }
        }
    }

    private void print(char[] c) {
        System.out.println(new String(c).substring(0, m));
    }

//--------------------------------------------------------------------------------------------------------------------

    /**
     * 4、采用非递归的方法完成全排列，也就是循环的方法啰！！！ <br/>
     * 我们该思考如何才能不使用递归，又能得到下一个排序呢。<br/>
     * 递归则是不管初始状态的循环交换两个数就好，那不使用递归呢？<br/>
     * <br/>
     * 首先把待遍历的数据使用快排进行排序 <br/>
     * 然后从后往前，找到第一个顺序前后数对，如34126543中的26，2即为替换数a <br/>
     * 然后从后往前，找到第一个比替换数a大的最小数，很容易知道a之后的数据都是倒序，所以从后往前找第一个比a大的，即比a大的最小数b。<br/>
     * 然后交换此两个数a、b，根据上面的道理我们知道：交换之后a之后的仍为倒序 <br/>
     * 最后，把a之后的倒序倒置一遍，即变换为顺序。并返回true <br/>
     * <br/>相当巧妙！！！
     *
     * @param str
     */
    public void nonRecursion4(String str) {
        numCount = 0;
        char[] strChar = str.toCharArray();
        Arrays.sort(strChar);//先使用快速排序
        do {
            //打印
            numCount++;
//			System.out.println(strChar);
        } while (hasNextPermutation(strChar));
    }

    public boolean hasNextPermutation(char[] c) {
        int swapIndex = c.length;
        while (swapIndex > 1) {
            swapIndex--;
            //从后往前，找到第一个顺序前后数对
            if (c[swapIndex - 1] < c[swapIndex]) {
                int curIndex = c.length - 1;
                //从后向前找比替换点大的第一个数
                while (c[curIndex] <= c[swapIndex - 1]) {
                    curIndex--;
                }
                swap(c, swapIndex - 1, curIndex);
                reverse(c, swapIndex, c.length - 1);
                return true;
            }
        }

        reverse(c, swapIndex - 1, c.length - 1);
        return false;
    }

    public boolean nextPermutation(char[] c) {
        int x = c.length;
        while (x > 1) { //只有需要排序的字符串长度大于或等于2, 才可以
            x--;
            if (c[x - 1] < c[x]) {
                int y = c.length - 1;
                while (c[y] <= c[x - 1]) y--;
                swap(c, x - 1, y);
                reverse(c, x, c.length - 1);
                return true;
            }

        }

        return false;
    }

    public void reverse(char[] c, int start, int end) {
        int len = end - start + 1;
        for (int i = 0; i < len / 2; i++) {
            char temp = c[start + i];
            c[start + i] = c[end - i];
            c[end - i] = temp;
        }
    }

    /**
     * 总的测试程序
     *
     * @param args
     */
    public static void main(String[] args) {
        Permutation test = new Permutation();
        String str = "123";
//		String str2 = "12345";
//		String str3 = "122";

        long start1 = System.currentTimeMillis();
        test.slowList(str);
        long end1 = System.currentTimeMillis();
        System.out.println("总共" + test.numCount + "种可能！使用map用时----> " + (end1 - start1) + "ms");
        System.out.println("---                                     ---");

        long start2 = System.currentTimeMillis();
        test.fastList(str);
        long end2 = System.currentTimeMillis();
        System.out.println("总共" + test.numCount + "种可能！自身逻辑解决重复问题用时----> " + (end2 - start2) + "ms");

//		从以下运行结果可以看出两者巨大的耗时差异！！
//		当str = "122323544"时
//		使用map用时----> 313ms
//		-------------------------------------
//		自身逻辑解决重复问题用时----> 2ms

//		System.out.println();
//		System.out.println("-------------------------------------------从str中选择m个进行全排列");
//		System.out.println();
//
//		long start3 = System.currentTimeMillis();
//		test.recursion3(str2, 2);
//		long end3 = System.currentTimeMillis();
//		System.out.println("总共"+test.numCount+"种可能！耗时----> "+(end3-start3)+"ms");
//		System.out.println("---                                     ---");
//
//		long start4 = System.currentTimeMillis();
//		test.nonRecursion4(str);
//		long end4 = System.currentTimeMillis();
//		System.out.println("总共"+test.numCount+"种可能！耗时----> "+(end4-start4)+"ms");
    }
}
