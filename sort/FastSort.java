package zhang.algorithm.modelUtil.sort;

import zhang.algorithm.modelUtil.ZhangUtil;

public class FastSort {
	/**
	 * 快速排序算法
	 * @param nums
	 * @return
	 */
	public static void fastSort(int[] nums, int low, int high){
		int l = low;
		int h = high;
		int povitKey = nums[l];
		int index = 0;
		while(l < h){
			if(index%2 == 0){
				if(nums[h] < povitKey){
					nums[l++] = nums[h];
					index++;
				}else{
					h--;
				}
			}else{
				if(nums[l] > povitKey){
					nums[h--] = nums[l];
					index++;
				}else{
					l++;
				}
			}
		}
		nums[l] = povitKey;
		if(l>low){
			fastSort(nums, low, l-1);
		}
		if(h<high){
			fastSort(nums, h+1, high);
		}
	}

	public static void main(String[] args) {
		int[] nums = {1, 3, 2};
		fastSort(nums, 0, nums.length-1);
		ZhangUtil.printArray(nums);
	}
}
