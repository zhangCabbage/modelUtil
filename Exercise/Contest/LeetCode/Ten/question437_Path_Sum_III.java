package zhang.algorithm.modelUtil.Exercise.Contest.LeetCode.Ten;

import zhang.algorithm.modelUtil.Tree.BinaryTree;
import zhang.algorithm.modelUtil.Tree.TreeNode;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/10/23
 * Time: 下午2:04
 * To change this template use File | Settings | File Templates.
 */
public class question437_Path_Sum_III {

    private int[] count = new int[1000]; //most 1000 nodes

    /**
     * 本来感觉都要超时了的, 竟然过了!
     * 126 / 126 test cases passed
     * Status: Accepted
     * Runtime: 24 ms
     *
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        return helper(root, sum, 0);
    }

    private int helper(TreeNode root, int sum, int level) {
        int total = 0;
        if (root != null) {
            for (int i = 0; i <= level; i++) {
                count[i] += root.val;
                if (count[i] == sum) total++;
            }

            total += helper(root.left, sum, level + 1);
            for (int i = 0; i <= level + 1; i++) {
                if (root.left == null) break;
                count[i] -= root.left.val;
            }

            total += helper(root.right, sum, level + 1);
            for (int i = 0; i <= level + 1; i++) {
                if (root.right == null) break;
                count[i] -= root.right.val;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        question437_Path_Sum_III test = new question437_Path_Sum_III();
        int[] nums = {10, 5, -3, 3, 2, 0, 11, 3, -2, 0, 1};
        System.out.println(test.pathSum(BinaryTree.instance(nums).getRoot(), 10));
    }
}
