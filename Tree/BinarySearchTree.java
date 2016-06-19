package zhang.algorithm.modelUtil.Tree;

/**
 * Created by zhang_zack on 16/6/15.
 * this Class is to use practice handle Binary Search Tree.
 */
public class BinarySearchTree extends BinaryTree {
    /**
     * @param nums here we define if the value of int array is -1, so its respective treeNode is null
     * @return
     */
    @Override
    public void constructTree(int[] nums) {
        for(int i=0; i<nums.length; i++){
            this.insertTree(nums[i]);
        }
    }

    public static BinarySearchTree instance(int[] nums){
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.constructTree(nums);
        return binarySearchTree;
    }

    /**
     * this method reference other mind
     * if num is the same as the value of node in tree, we do not insert to tree.
     *
     * @param num
     */
    private void insertTree(int num) {
        this.setRoot(this.insertTree(this.getRoot(), num));
    }

    /**
     * this way is more brief than me
     *
     * @param num
     */
    private TreeNode insertTree(TreeNode curNode, int num) {
        if (curNode == null) {
            return new TreeNode(num);
        }
        if (curNode.val > num) {
            curNode.left = insertTree(curNode.left, num);
        } else if (curNode.val < num) {
            curNode.right = insertTree(curNode.right, num);
        }
        //if equal do nothing

        return curNode;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        BinarySearchTree binarySearchTree = BinarySearchTree.instance(nums);
        binarySearchTree.print();

    }
}
