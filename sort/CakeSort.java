package zhang.algorithm.modelUtil.sort;

import zhang.algorithm.modelUtil.ZhangUtil;

/**
 * Created by DELL on 2016/6/2.
 * 编程之美1.3章节的烙饼排序问题，这里暂时不知道出了什么问题
 */
public class CakeSort {
    /**
     * 表示整个排序过程中的总查找次数
     */
    public static int searchNum = 0;
    public static int maxSearchSwap = 0;
    public static int[] swapArray;
    public static int[] finalSwapArray;
    /**
     * 根据编程之美编写对应的Java代码，使用使用【遍历】+【剪枝】=【分支限界法】求最优解问题
     * @param cakeArray
     * @return 返回遍历次数
     */
    public static int sort(int[] cakeArray){
        myInit(cakeArray.length);
        recusiveSort(cakeArray, cakeArray.length, 0);
        return searchNum;
    }

    private static void myInit(int cakeCnt) {
        maxSearchSwap = UpperBound(cakeCnt);
        swapArray = new int[maxSearchSwap+1];
        finalSwapArray = new int[maxSearchSwap+1];
    }

    private static void recusiveSort(int[] cakeArray, int cakeCnt, int step){
        searchNum++;
        int estimateTime = LowerBound(cakeArray, cakeCnt);
        if(step + estimateTime > maxSearchSwap){
            return;
        }
        if(isSorted(cakeArray)){
            //输出结果
            if(step < maxSearchSwap){
                maxSearchSwap = step;
                for(int i=0; i<maxSearchSwap; i++){
                    finalSwapArray[i] = swapArray[i];
                }
            }
            return;
        }
        for(int i=1; i<cakeCnt; i++){
            ZhangUtil.reverse(cakeArray, 0, i);
            swapArray[step] = i;
            recusiveSort(cakeArray, cakeCnt, step+1);
            ZhangUtil.reverse(cakeArray, 0, i);
        }
    }

    public static void printResult(int[] cakeArray){
        System.out.print("通过以下变换：");
        for(int i=0; i<maxSearchSwap; i++){
            System.out.print(finalSwapArray[i]+" ");
            ZhangUtil.swap(cakeArray, 0, finalSwapArray[i]);
        }
        System.out.println();
        System.out.println("共查找--->"+searchNum);
        System.out.println("最小交换次数为--->"+maxSearchSwap);
    }

    private static boolean isSorted(int[] cakeArray) {
        for(int i=1; i<cakeArray.length; i++){
            if(cakeArray[i] < cakeArray[i-1]){
                return false;
            }
        }
        return true;
    }

    /**
     * 上界函数
     * @param cakeCnt
     * @return
     */
    private static int UpperBound(int cakeCnt){
        return 2*(cakeCnt);
    }

    /**
     * 下界函数
     * @param cakeArray
     * @param cakeCnt
     * @return
     */
    private static int LowerBound(int[] cakeArray, int cakeCnt){
        int estimate = 0;
        for(int i=1; i<cakeCnt; i++){
            int distance = cakeArray[i]-cakeArray[i-1];
            if(Math.abs(distance) != 1){
                estimate++;
            }
        }
        return estimate;
    }

    public static void main(String[] args) {
        int[] cakeArray = {3,1,2,6,5,4,9,8,7,0};
        ZhangUtil.setStartTime();
        CakeSort.sort(cakeArray);
        System.out.println("总耗时-----> "+ZhangUtil.getIntervalTime());
        ZhangUtil.printArray(cakeArray);
        printResult(cakeArray);
        ZhangUtil.printArray(cakeArray);
        //{3,1,2,6,5,4,9,8,7,0}
        //答案是4 8 6 8 4 9
    }
}
