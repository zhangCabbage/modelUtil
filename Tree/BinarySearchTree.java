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
        for (int i = 0; i < nums.length; i++) {
            this.insert(nums[i]);
        }
    }

    public static BinarySearchTree instance(int[] nums) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.constructTree(nums);
        return binarySearchTree;
    }

    /**
     * insert of binary Search tree<br/>
     * <p>
     * this method reference other mind
     * if num is the same as the value of node in tree, we do not insert to tree.
     *
     * @param num
     */
    private void insert(int num) {
        this.setRoot(this.insert(this.getRoot(), num));
    }

    /**
     * this way is more brief than me.
     * Insert in child tree
     *
     * @param num
     */
    private TreeNode insert(TreeNode curNode, int num) {
        if (curNode == null) {
            return new TreeNode(num);
        }
        if (curNode.val > num) {
            curNode.left = insert(curNode.left, num);
        } else if (curNode.val < num) {
            curNode.right = insert(curNode.right, num);
        }
        //if equal do nothing

        return curNode;
    }

    /**
     * Search of binary Search tree<br/>
     *
     * @param num
     * @return
     */
    public boolean search(int num) {
        return search(this.getRoot(), num);
    }

    private boolean search(TreeNode root, int num) {
        if (root == null) {
            return false;
        } else if (root.val == num) {
            return true;
        } else if (root.val > num) {
            return search(root.left, num);
        }
        return search(root.right, num);
    }

    public void remove(int num) {
        this.setRoot(remove(this.getRoot(), num));
    }

    private TreeNode remove(TreeNode node, int num) {
        if (node == null) return node;
        if (node.val > num) {
            node.left = remove(node.left, num);
        } else if (node.val < num) {
            node.right = remove(node.right, num);
        } else if (node.left != null && node.right != null) {
            node.val = findMin(node.right).val;
            node.right = remove(node.right, node.val);
        } else {
            //left and right child tree at least one is null
            node = (node.left == null) ? node.right : node.left;
        }
        return node;
    }

    public TreeNode findMax() {
        return findMax(this.getRoot());
    }

    private TreeNode findMax(TreeNode node) {
        if (node == null) return node;
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public TreeNode findMin() {
        return findMin(this.getRoot());
    }

    private TreeNode findMin(TreeNode node) {
        if (node == null) return node;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 8};
        BinarySearchTree binarySearchTree = BinarySearchTree.instance(nums);
        binarySearchTree.print();

        System.out.println(binarySearchTree.search(9));
        binarySearchTree.insert(6);
        binarySearchTree.insert(7);
        binarySearchTree.insert(9);
        binarySearchTree.print();

        System.out.println(binarySearchTree.findMax());
        System.out.println(binarySearchTree.findMin());

        binarySearchTree.remove(8);
        binarySearchTree.print();
    }
}
