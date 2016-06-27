package zhang.algorithm.modelUtil.search;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_Lenovo
 * Date: 2016/6/27
 * Time: 16:25
 * To change this template use File | Settings | File Templates.
 *
 * This special way is to find the kth top of array that are not in order.
 * launch by Blum、Floyd、Pratt、Rivest、Tarjan, so we call this way bfprt algorithm.
 */
public class BFPRT {
    /**
     * 用以找到数组中第k大的元素，时间复杂度为O(n)
     *
     * @param array
     * @param start
     * @param end
     * @param k means the kth number of the array
     * @return
     */
    public static int findKth(int[] array, int start, int end, int k){
        if(k < 1 || k > array.length){
            return Integer.MIN_VALUE;
        }
        int l = start;
        int r = end;
        int povitKey = array[start];

        //借鉴快速排序的逻辑方式
        while(l<r){
            while(l<r && array[r] >= povitKey){
                r--;
            }
            array[l] = array[r];

            while(l<r && array[l] <= povitKey){
                l++;
            }
            array[r] = array[l];
        }
        int distance = l - start + 1;
        if(distance == k){
            return povitKey;
        }else if(distance > k){
            return findKth(array, start, l-1, k);
        }else{
            return findKth(array, l+1, end, k-distance);
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(findKth(array, 0, array.length-1, 7));
    }
}
