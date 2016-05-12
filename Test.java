package zhang.algorithm.modelUtil;

import java.util.Arrays;

public class Test {
	/**
	 * 重新练习LeetCode question3
	 * @param str
	 */
	public void test(String str){
		int[] map = new int[128];
		Arrays.fill(map, -1);
		int maxLen = 0;
		int count = 0;
		int i = 0;
		int start = -1;
		while(i<str.length()-count){
			int index = map[str.charAt(i)];
			if(index > start){
				maxLen = (maxLen<count)?count:maxLen;
				//后两句为重写错误所在：
				//count算的是真正的长度，而index是重复的前一个，所以去掉前面有重复的应该是index-start
				//count -= (index-start)
				//这样又没有包含重复的新的一个，所以if后面的语句还是要执行的！！
				count = count-index;
				start = index;
			}
			//没有的话
			map[str.charAt(i)] = i++;
			count++;
		}
	}
	
	/**
	 * 重新练习KMP算法中的next数组的求法
	 */
	public void test2(String pattern){
		char[] c = pattern.toCharArray();
		int[] next = new int[c.length];
		next[0] = -1;
		int pre = -1;
		int cur = 0;
		//next数组是存放的是当前下标字符前面的子字符串的相同前后缀个数，next数组最后一个的值只需要比较前一个的值
		while(cur < c.length-1){
			if(pre==-1 || c[pre]==c[cur]){
				cur++;
				pre++;
				next[cur] = pre+1;
			}else{
				pre = next[pre];
			}
		}
	}
}
