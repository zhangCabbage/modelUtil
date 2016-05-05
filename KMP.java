package zhang.algorithm.modelUtil;

/**
 * 实现KMP算法
 * @since 16/5/5
 * @author DELL
 *
 */
public class KMP {
	private int[] next;//next数组
	private char[] patternChar;//模式串
	private char[] textChar;//文本串
	
	public void setPatternAndTextChar(String patternStr, String textStr){
		this.patternChar = patternStr.toCharArray();
		this.textChar = textStr.toCharArray();
		next = new int[patternChar.length];
	}
	
	/**
	 * 通过模式串递推得到next数组
	 */
	private void getNext(){
		int len = patternChar.length;
		next[0] = -1;
		int preIndex = -1;//前缀下标
		int currIndex = 0;//后缀当前下标
		while(currIndex<len-1){
			if(preIndex==-1 || patternChar[currIndex]==patternChar[preIndex]){
				preIndex++;
				currIndex++;
				next[currIndex] = preIndex;
			}else{
				preIndex = next[preIndex];
			}
		}
	}
	
	/**
	 * 依赖生成的前后缀公共元素长度数组，进行字符串模式匹配，是否查找到第一个匹配的就立马返回？
	 */
	public int find(){
		int p = 0;//模式串的匹配下标
		int t = 0;//文本串的匹配下标
		while(p<patternChar.length && t<textChar.length){
			if(p==-1 || textChar[t]==patternChar[p]){
				p++;
				t++;
			}else{
				p = next[p];
			}
		}
		if(p == patternChar.length){
			return t-p;
		}else{
			return -1;
		}
	}
	
	public static void main(String[] args){
		KMP kmp = new KMP();
		kmp.setPatternAndTextChar("ABCDABD", "BBC ABCDAB ABCDABCDABDE");
		kmp.getNext();
		System.out.println(kmp.find());
	}
}
