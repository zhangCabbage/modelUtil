package zhang.algorithm.modelUtil.Exercise;

import zhang.algorithm.modelUtil.Tree.BinaryTree;
import zhang.algorithm.modelUtil.Tree.TreeNode;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/7/21
 * Time: 下午10:02
 * To change this template use File | Settings | File Templates.
 */
public class BinarySearchTree extends BinaryTree {
    //再一次实现二叉查找树

    public static BinarySearchTree instance(int[] nums) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.constructTree(nums);
        return binarySearchTree;
    }

    @Override
    public void constructTree(int[] nums) {
        if (nums == null || nums.length < 1) return;
        for (int i = 0; i < nums.length; i++) {
            this.setRoot(insert(this.getRoot(), nums[i]));
        }
    }

    public TreeNode insert(TreeNode root, int num) {
        if (root == null) {
            TreeNode curNode = new TreeNode(num);
            return curNode;
        }

        if (root.val < num) {
            root.right = insert(root.right, num);
        } else if (root.val > num) {
            root.left = insert(root.left, num);
        }
        return root;
    }

    public TreeNode find(int num) {
        return find(this.getRoot(), num);
    }

    public TreeNode find(TreeNode root, int num) {
        if (root == null) return null;
        if (root.val == num) return root;
        else if (root.val < num) return find(root.right, num);
        else return find(root.left, num);
    }

    /**
     * 1、if left and right is null, then delete
     * 2、if left and right only one is null, then delete and let left or right equal root
     * 3、if left and right none is null, so
     *
     * @param num
     * @return
     */
    public TreeNode delete(int num) {
        return delete(this.getRoot(), num);
    }

    public TreeNode delete(TreeNode root, int num) {
        if (root == null) return null;

        if (root.val < num) {
            root.right = delete(root.right, num);
        } else if (root.val > num) {
            root.left = delete(root.left, num);
        } else {
            if (root.left == null || root.right == null) {
                return root.left == null ? root.right : root.left;
            } else {
                //this sense that left and right is none null
                //this is a easy method to deal with it.
                //that exchange the root val with the min or max node
                root.val = findMin(root.right).val;
                root.right = delete(root.right, root.val);
            }
        }

        return root;
    }

    public TreeNode findMin(TreeNode node) {
        if (node == null) return null;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
