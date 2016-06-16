package zhang.algorithm.modelUtil.Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by zhang_zack on 16/6/16.
 * build common binary tree
 */
public class BinaryTree {
    private TreeNode root = null;//树的根节点
    private int depth;//树深度

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(int[] nums) {
        this.root = BinaryTree.constructTree(nums);
    }

    /**
     * use to find the depth of current Tree <br/>
     *
     * @return
     */
    public int getDepth() {
        int depth = 0;
        if (this.getRoot() == null) {
            return depth;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(this.getRoot());
        while (queue.peek() != null) {
            depth++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
            }
        }
        return depth;
    }

    /**
     * use a int array to construct a binary tree in order to verify the use of binary tree<br/>
     *
     * @param nums here we define if the value of int array is -1, so its respective treeNode is null
     * @return
     */
    public static TreeNode constructTree(int[] nums) {
        if (nums.length < 1) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode root = new TreeNode(nums[0]);
        queue.offer(root);

        for (int i = 1; i < nums.length; ) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                TreeNode curNode = queue.poll();
                if (nums[i] != -1) {
                    TreeNode leftNode = new TreeNode(nums[i]);
                    curNode.left = leftNode;
                    queue.offer(leftNode);
                }
                i++;
                if (i < nums.length && nums[i] != -1) {
                    TreeNode rightNode = new TreeNode(nums[i]);
                    curNode.right = rightNode;
                    queue.offer(rightNode);
                }
                i++;
            }
        }
        return root;
    }

    @Override
    public String toString() {
        depth = this.getDepth();
        StringBuffer[] sb = new StringBuffer[depth];
        for (int i = 0; i < depth; i++) {
            sb[i] = new StringBuffer();
        }
        printTree(this.getRoot(), 1, sb);
        for (int i = depth - 1; i > 0; i--) {
            sb[i - 1].append("\n");
            sb[i - 1].append(sb[i]);
        }
        sb[0].append("\n");
        return sb[0].toString();
    }

    /**
     * how to print binary tree in grace way?<br/>
     * first we solve a more simple scene that we assume all node value is single digit.<br/>
     * what style we want to print is like below:<br/>
     * 1
     * 2       3
     * 4   5
     * 6 7   8
     * every node space its child leaf width:2^depth-1<br/>
     */
    public void printTree(TreeNode root, int curLevel, StringBuffer[] sb) {
        int spaceNum = (int) Math.pow(2, depth - curLevel) - 1;//对应深度下满二叉子树的节点个数

        //print current child tree of this current level
        for (int i = 0; i < spaceNum; i++) {
            sb[curLevel - 1].append(" ");
        }
        sb[curLevel - 1].append(root == null ? " " : root.val);
        for (int i = 0; i < spaceNum; i++) {
            sb[curLevel - 1].append(" ");
        }

        if (curLevel == depth) {
            return;
        }
        //if some child tree is null, but we still want to print the total tree in format style
        //so this print way control by curlevel mainly no matter what child node is null or not
        printTree(root == null || root.left == null ? null : root.left, curLevel + 1, sb);
        sb[curLevel].append(" ");
        printTree(root == null || root.right == null ? null : root.right, curLevel + 1, sb);
        sb[curLevel].append(" ");
    }

    //next is all kinds of common tree traversal methods
    //1、preOrder、inOrder、postOrder in recursive way

    /**
     * traversal tree in preorder way
     *
     * @return
     */
    public String preOrder1() {
        StringBuffer sb = new StringBuffer();
        preOrderRecursive(this.getRoot(), sb);
        return sb.toString();
    }

    private void preOrderRecursive(TreeNode root, StringBuffer sb) {
        if (root != null) {
            sb.append(root.val);
            preOrderRecursive(root.left, sb);
            preOrderRecursive(root.right, sb);
        }
    }

    /**
     * traversal tree in inorder way
     *
     * @return
     */
    public String inOrder1() {
        StringBuffer sb = new StringBuffer();
        inOrderRecursive(this.getRoot(), sb);
        return sb.toString();
    }

    private void inOrderRecursive(TreeNode root, StringBuffer sb) {
        if (root != null) {
            inOrderRecursive(root.left, sb);
            sb.append(root.val);
            inOrderRecursive(root.right, sb);
        }
    }

    /**
     * traversal tree in postorder way
     *
     * @return
     */
    public String postOrder1() {
        StringBuffer sb = new StringBuffer();
        postOrderRecursive(this.getRoot(), sb);
        return sb.toString();
    }

    private void postOrderRecursive(TreeNode root, StringBuffer sb) {
        if (root != null) {
            postOrderRecursive(root.left, sb);
            postOrderRecursive(root.right, sb);
            sb.append(root.val);
        }
    }

    //2、because of inefficient of recursive way, so we achieve not in recursive way

    /**
     * PreOrder not in recursive way, this way we need the help of stack
     * this is not my thought reference others
     *
     * @return
     */
    public String preOrder2() {
        TreeNode curNode = this.getRoot();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        StringBuffer sb = new StringBuffer();

        while (curNode != null || !stack.isEmpty()) {
            while (curNode != null) {
                sb.append(curNode.val);
                stack.push(curNode);
                curNode = curNode.left;
            }
            if (!stack.isEmpty()) {
                curNode = stack.pop();
                curNode = curNode.right;
            }
        }
        return sb.toString();
    }

    /**
     *
     * @return
     */
    public String inOrder2(){
        TreeNode curNode = this.getRoot();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        StringBuffer sb = new StringBuffer();

        while(curNode!=null || !stack.isEmpty()){
            while(curNode!=null){
                stack.push(curNode);
                curNode = curNode.left;
            }
            if(!stack.isEmpty()){
                curNode = stack.pop();
                sb.append(curNode.val);
                curNode = curNode.right;
            }
        }

        return sb.toString();
    }

    /**
     *
     * @return
     */
    public String postOrder2(){
        StringBuffer sb = new StringBuffer();

        return sb.toString();
    }

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
//        int[] nums = {1, 2, 3, -1, -1, 4, -1, 5, 6, -1, -1, 7, 8, -1, -1, 9};
        int[] nums = {1, 2, 3, -1, 4, 5, -1, 6, 7, -1, 8};
        binaryTree.setRoot(nums);
        System.out.println(binaryTree);
        System.out.println("PreOrder1 traversal --> " + binaryTree.preOrder1());
        System.out.println("InOrder1 traversal --> " + binaryTree.inOrder1());
        System.out.println("PostOrder1 traversal --> " + binaryTree.postOrder1());
        System.out.println();
        System.out.println("PreOrder2 traversal --> " + binaryTree.preOrder2());
        System.out.println("InOrder2 traversal --> " + binaryTree.inOrder2());
//        System.out.println("PostOrder2 traversal --> " + binaryTree.postOrder2());
    }
}
