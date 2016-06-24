package zhang.algorithm.modelUtil.AlgorithmDesign.DynamicProgramming;

import zhang.algorithm.modelUtil.ZhangUtil;

/**
 * Created by zhang_zack on 16/6/15.
 * Application of dynamic programming --> Optimal Binary Search Tree<br/>
 * <br/>
 * this teach us how to use dynamic to analysic problem<br/>
 * 1、Top-down analysis problem similar recursive<br/>
 * 2、Find the recursive formula(递推公式)<br/>
 * 3、decide how to storage middle data<br/>
 * 4、solve from down to up<br/>
 * <br/>
 * [【【算法导论】动态规划之“最优二叉搜索树”】](http://blog.csdn.net/cyp331203/article/details/43083647)
 * 优化思想!<br/>
 * [【【算法学习】最优二叉查找树（动态规划）】](http://blog.csdn.net/xiajun07061225/article/details/8088784)
 * 图解说明!<br/>
 */
public class OptimalBinaryTree {
    /**
     * only arrays we can find in this optimal binary tree, more easy to thought
     * the sum of p is 1
     * @param p
     * @param num
     * @return how we should to use this root array
     */
    public static int[][] optimalBST(double[] p, int num){
        double[][] e = new double[num+2][num+2];
        double[][] w = new double[num+1][num+1];
        //this will to easy count the sum of w from i to j
        //so use w[i][j] = w[i][j-1] + p[j]
        //therefor the size of w is also need num+1

        int[][] root = new int[num+1][num+1];//记录根节点
        //root[i][j] -- 用来存放i-j组成的最优二叉查找树的根节点

        for(int d = 0; d<num; d++){
            for(int i=1; i<=num-d; i++){
                int j = i + d;
                e[i][j] = Double.MAX_VALUE;
                w[i][j] = w[i][j-1] + p[j-1];//
                for(int k=i; k<=j; k++){
                    double temp = w[i][j] + e[i][k-1] + e[k+1][j];;
                    if(temp < e[i][j]){
                        e[i][j] = temp;
                        root[i][j] = k;
                    }
                }
            }
        }
        ZhangUtil.printIntMatrix(w);
        System.out.println("the total search probability of this optimal BST is "+ e[1][num]);
        return root;
    }

    //----------------------------------------------------------------------------------
    //Consider the failure of success
    //----------------------------------------------------------------------------------

    /**
     * Time complexity is O(n^3)<br/>
     * @param p key probability, size is num
     * @param q virtual key(can find key) probability, size is num+1
     * @param num size of key
     */
    public static int[][] optimalBST(double[] p,double[] q, int num){
        double[][] e = new double[num+2][num+2];//子树期望代价
        double[][] w = new double[num+2][num+2];//子树总概率
        //why e and w is two bigger than num?
        //because when i==j, e[i][i-1] + e[i+1][j] + w[i][j];
        //when i = 1 or j = num, so e[1][0]、e[num+1][num]
        //here w can use the size of num+1, but in order not to so much complexity in init to judge i
        //so add a bigger w is look good!

        int[][] root = new int[num+1][num+1];//记录根节点
        //root[i][j] -- 用来存放i-j组成的最优二叉查找树的根节点

        //init--初始化
        for(int i=1; i<num+2; i++){
            e[i][i-1] = q[i-1];
            w[i][i-1] = q[i-1];
        }

        for(int d = 0; d<num; d++){
            //插入个数 : 0 - num-1, 从0开始
            for(int i=1; i<=num-d; i++){
                //起始下标 : 1 - num, 从1开始
                int j = i + d;
                e[i][j] = 9999;
                w[i][j] = w[i][j-1] + p[j] + q[j];//
                for(int k=i; k<=j; k++){
                    //中间下标
                    double temp = e[i][k-1] + e[k+1][j] + w[i][j];
                    if(temp < e[i][j]){
                        e[i][j] = temp;
                        //找到小的,记录下来
                        System.out.println("i--> "+i+", j--> "+j);
                        root[i][j] = k;
                    }
                }
            }
        }
        return root;
    }

    /**
     * optimization of the first method.<br/>
     * Time complexity from O(n^3) to O(n^2)<br/>
     * @param p
     * @param q
     * @param num
     * @return
     */
    public static int[][] optimalBST2(double[] p,double[] q, int num){
        double[][] e = new double[num+2][num+2];//子树期望代价
        double[][] w = new double[num+2][num+2];//子树总概率
        //why e and w is two bigger than num?
        //because when i==j, e[i][i-1] + e[i+1][j] + w[i][j];

        int[][] root = new int[num+1][num+1];//记录根节点
        //root[i][j] -- 用来存放i-j组成的最优二叉查找树的根节点

        //init--初始化
        for(int i=1; i<num+2; i++){
            e[i][i-1] = q[i-1];
            w[i][i-1] = q[i-1];
        }

        for(int d = 0; d<num; d++){
            //插入个数 : 0 - num-1, 从0开始
            for(int i=1; i<=num-d; i++){
                //起始下标 : 1 - num, 从1开始
                int j = i + d;
                e[i][j] = 9999;
                w[i][j] = w[i][j-1] + p[j] + q[j];//

                //because of root[i][j-1] <= root[i][j] <= root[i+1][j]
                //so why?
                //
                //进行优化!!!
                if(i == j){
                    root[i][j] = i;
                    e[i][j] = e[i][i-1] + e[i+1][i] + w[i][i];
                }else{
                    for(int k=root[i][j-1]; k<=root[i+1][j]; k++){
                        //中间下标
                        double temp = e[i][k-1] + e[k+1][j] + w[i][j];
                        if(temp < e[i][j]){
                            e[i][j] = temp;
                            //找到小的,记录下来
                            root[i][j] = k;
                        }

                    }
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {
        double[] p = {-1, 0.15, 0.1, 0.05, 0.1, 0.2};
        double[] q = {0.05, 0.1, 0.05, 0.05, 0.05, 0.1};
        int num = 5;
        ZhangUtil.printIntMatrix(OptimalBinaryTree.optimalBST(p, q, num));
        ZhangUtil.printIntMatrix(OptimalBinaryTree.optimalBST2(p, q, num));


        double[] p1 = {0.3, 0.05, 0.08, 0.45, 0.12};
        int num1 = 5;
        System.out.println(OptimalBinaryTree.optimalBST(p1, num1));
    }
}
