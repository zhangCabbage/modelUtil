package zhang.algorithm.modelUtil.Tree;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/7/5
 * Time: 下午11:08
 * To change this template use File | Settings | File Templates.
 */
public class JudgeBST {
    /**
     * 判断一个数组是否为二叉搜索树的后序遍历
     * 边界值结果: 如果数组为null, 则输出false
     *
     * @param sequence
     * @return
     */
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length < 1) return false;
        return helper(sequence, 0, sequence.length - 1);
    }

    private boolean helper(int[] nums, int l, int r) {
        if (l < r) {
            int i = r - 1;
            while (i >= l && nums[i] > nums[r])
                i--;
            for (int k = l; k < i; k++) {
                if (nums[k] > nums[r]) return false;
            }
            return helper(nums, l, i) && helper(nums, i + 1, r - 1);
        }
        return true;
    }

    public static void main(String[] args) {
        JudgeBST test = new JudgeBST();
    }
}
