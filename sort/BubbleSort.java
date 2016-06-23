package zhang.algorithm.modelUtil.sort;

import zhang.algorithm.modelUtil.ZhangUtil;

/**
 * 交换排序，通过交换相邻项，直至有序。其中典型的实现是冒泡排序
 *
 * 时间复杂度分析：
 * 最好：比较 n-1 次，O(n)
 * 最坏：比较 n(n-1)/2 次，O(n^2)
 * 平均：O(n^2)
 * @author zhang_zack
 *
 */
public class BubbleSort {
	/**
	 * 实现冒泡排序,最大的放在下面
	 * @param nums
     */
	public static void bubbleSort(int[] nums){
		for(int i=1; i<nums.length; i++){
			boolean flag = false;
			for(int j=0; j<nums.length-i; j++){
				if(nums[j] > nums[j+1]){
					flag = true;
					ZhangUtil.swap(nums, j, j+1);
				}
			}
			if(flag == false){
				break;
			}
		}
	}

	public static void main(String[] args) {
		int[] nums = {3, 2};
		bubbleSort(nums);
		ZhangUtil.printArray(nums);
	}
}
