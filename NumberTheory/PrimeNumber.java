package zhang.algorithm.modelUtil.NumberTheory;

import java.util.Arrays;

/**
 * 求素数(Prime Number) <br/>
 * 素数的概念：只能被1和本身整除，最小的素数为2.
 * 
 * @author zhang_zack
 *
 */
public class PrimeNumber {
	/**
	 * 给定一个数，求2-number中所有的素数 <br/>
	 * 使用筛选法求number以内的所有素数，从小到大的筛选 <br/>
	 * @param number
	 */
	public static void filterPrime(int number){
		int[] nums = new int[number+1];
		Arrays.fill(nums, 1);
		
		for(int i=2; i<number+1; i++){
			if(nums[i] != 0){
				for(int k=2*i; k<number+1; k+=i){
					nums[k] = 0;
				}
			}
		}
		
		for(int i=2; i<number+1; i++){
			if(nums[i] != 0){
				System.out.print(i+"、");
			}
		}
	}
	
	public static void main(String[] args){
		PrimeNumber.filterPrime(100);
	}
}
