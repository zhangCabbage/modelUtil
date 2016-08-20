package zhang.algorithm.modelUtil.BitManipultion;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/8/20
 * Time: 上午11:50
 * To change this template use File | Settings | File Templates.
 * <p>
 * 有这样的一个游戏，首先，我们有若干个器皿，每个器皿里面都没有细胞，我们有下面两种操作：
 * 1.往某一个器皿中加入一个新的细胞。
 * 2.用一个特殊射线让所有器皿中的所有细胞一分为二。（假设细胞都可以分裂，且不会死掉）。
 * 现在，我们一开始有n个器皿，通过一个系列的操作，我们让器皿中的细胞个数分别为a[1],a[2],a[3]..a[n]。
 * 如果用户用最少的操作完成目标，我们给他3颗星，现在我们想知道最少需要多少次操作。
 * <p>
 * 例子1： n = 2, a = {1, 2} 结果为3
 * 例子2： n = 2, a = {4, 8} 结果为5步，使用3次操作1，变成{1, 2}， 然后进行2次操作2。
 * [http://toutiao.com/a6320049599194398978/]
 * <p>
 * 一步步把问题简单化, 我们可以开始想如何分解问题, 一个器皿需要多少次操作能快速达到要求?
 * 貌似不好想从 0 开始, + 或 * 到 n 的方式,是不是?
 * 那么我们从如何从 n 快速, 分解到 0, 肯定是越有除越好, eg: (((15 - 1)/2 - 1) /2 - 1) /2 - 1 = 0
 * 我们可以通过分解知道, 算出其中 + 和 * 的个数。
 */
public class MinimumOperation {
    /**
     * the root of this problem is to find sum(binary of array 1) + high1Bit(array)
     *
     * @param num
     * @param arrays
     * @return
     */
    public int minOperation(int num, int[] arrays) {
        int min = 0;
        int highBit = 0;
        for (int array : arrays) {
            min += BitTool.numOfOne(array);
            highBit = Math.max(highBit, BitTool.numOfHigh1Bit(array));
        }
        return min + highBit;
    }

    public static void main(String[] args) {
        MinimumOperation test = new MinimumOperation();
        int num = 2;
        int[] arrays = {4, 8};
        System.out.println("minOperation --> " + test.minOperation(num, arrays));
    }
}
