package zhang.algorithm.modelUtil.NumberTheory;

/**
 * (Combination)组合问题<br/>
 * 
 * 参考资料：
 * [【全排列和全组合实现】](http://wuchong.me/blog/2014/07/28/permutation-and-combination-realize/)<br/>
 * 
 * @author zhang_zack
 * 
 */
public class Combination {
	/**
	 * 普通递归组合算法？？
	 * @param str
	 */
	public static void combine1(String str){
		
	}
	
	/**
	 * 我们知道对于一个n位字符串的组合数为：Cn1+Cn2+Cn3+...+Cnn = (1+1)^n-1 = 2^n-1
	 * 我们可以把问题看做字符串每一位选或者不选，选用1表示，不选用0表示。
	 * 比如abc的字符串，其可能的组合为:a\b\c\ab\ac\bc\abc，即100\010\001\110\101\011\111
	 * 那么我们只需要循环输出就可以了。
	 * @param str
	 */
	public static void combine2(String str){
		char[] c = str.toCharArray();
		int len = c.length;
		int n = 1<<len;
		
		System.out.print("总共有"+(n-1)+"种组合数，分别为：");
		for(int i=1; i<n; i++){
			for(int j=0; j<len; j++){
				if( (i&(1<<j)) > 0 ){
					System.out.print(c[j]);
				}
			}
			if(i != n-1){
				System.out.print("、");
			}else{
				System.out.println();
			}
		}
	}
	
	public static void main(String[] args){
		long start1 = System.currentTimeMillis();
		Combination.combine2("abc");
		long end1 = System.currentTimeMillis();
		System.out.println("位图算法耗时----> "+(end1-start1)+"ms");
	}
}
