package zhang.algorithm.modelUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 全排列的问题，递归思想的完全体现！！！
 * @author zhang_zack
 * 
 */
public class Permutation {
	private Map<String, Integer> map = new HashMap<String, Integer>();
	/**
	 * 通常解法
	 * 1、采用递归的方式，没有限制的列出所有可能情况 <br/>
	 * 针对只需要列出全排列的情况：可通过在最后一步，根据字符串的长度来判断是否输出，从而达到全排列输出的效果。<br/>
	 * <br/>
	 * 但是这种方法，在输入字符串有重复的条件下，不太容易实现去重复的全排列(如：abca)<br/>
	 * <br/>
	 * <br/>
	 * 如何解决可重复字符串的全排列问题？？？？这种方法由于是一个字符一个字符堆叠，所以没法以很好的办法来进行去重。
	 * 只能使用map来存放已有的字符串，来去重！！
	 * @param str
	 */
	public void recursion1(String str){
		List<String> list = new ArrayList<String>();
		for(int i=0; i<str.length(); i++){
			list.add(str.substring(i, i+1));
		}
		listAll(list, "", str.length());
	}
	
	public void listAll(List<String> list, String prefix, int len){
		//输出所有可能的情况，而非仅仅全排列
//		if(!prefix.isEmpty()){
//			System.out.println(prefix);
//		}
		//长度足够，并且不重复
		if(prefix.length() == len && !map.containsKey(prefix)){
//			System.out.println(prefix);
			map.put(prefix, 1);
		}
		
		for(int i=0; i<list.size(); i++){
			List<String> temp = new LinkedList<String>(list);
			listAll(temp, prefix+temp.remove(i), len);
		}
	}
	
//--------------------------------------------------------------------------------------------------------------------
	
	/**
	 * 2、同样是采用递归的方法进行全排列，只不过这里没有多使用额外的空间，并且没有使用集合 <br/>
	 * 
	 * @param str
	 */
	public void recursion2(String str){
		char[] strChar = str.toCharArray();
		arrange(strChar, 0, strChar.length-1);
	}
	
	public void arrange(char[] c, int start, int end){
		if(start == end){
//			System.out.println(new String(c));
		}else{
			for(int i=start; i<=end; i++){
				if(isSwap(c, start, i)){
					swap(c, start, i);
					arrange(c, start+1, end);
					swap(c, i, start);
				}
			}
		}
	}
	
	public void swap(char[] c, int head, int tail){
		char temp = c[head];
		c[head] = c[tail];
		c[tail] = temp;
	}
	/**
	 * 对于可重复的输入字符串，用来判断上述逻辑中两个下标对应的字符是否交换 <br/>
	 * 如果当前要交换的下标前只要有与它相同的字符，那么就不再进行交换 <br/>
	 * @param c
	 * @param start 起始下标
	 * @param cur 当前要交换的下标
	 * @return
	 */
	public boolean isSwap(char[] c, int start, int cur){
		boolean flag = true;
		for(int i=start; i<cur; i++){
			if(c[i] == c[cur]){
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	public static void main(String[] args){
		Permutation test = new Permutation();
		long start1 = System.currentTimeMillis();
		test.recursion1("122323544");
		long end1 = System.currentTimeMillis();
		System.out.println("使用map用时----> "+(end1-start1)+"ms");
		
		System.out.println("-------------------------------------");
		long start2 = System.currentTimeMillis();
		test.recursion2("122323544");
		long end2 = System.currentTimeMillis();
		System.out.println("自身逻辑解决重复问题用时----> "+(end2-start2)+"ms");
		
//		从以下运行结果可以看出两者巨大的耗时差异！！
//		当str = "122323544"时
//		使用map用时----> 313ms
//		-------------------------------------
//		自身逻辑解决重复问题用时----> 2ms
		
	}
}
