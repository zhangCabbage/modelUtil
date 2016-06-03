package zhang.algorithm.modelUtil.sort;

import zhang.algorithm.modelUtil.ZhangUtil;

/**
 * 冒泡排序的实现
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
