package zhang.algorithm.modelUtil;

/**
 * 求两个数的最大公约数
 * @author zhang_zack
 *
 */
public class NewtonGCD {
	/**
	 * 使用牛顿法求x和y的最大公约数
	 * @param x 
	 * @param y 
	 * @return
	 */
	public static int newtonGCD(int x, int y){
		int bigger = Math.max(x, y);
		int smaller = Math.min(x, y);
		if(smaller <= 0){
			return 0;
		}
		while(smaller>0){
			int temp = smaller;
			smaller = bigger%smaller;
			bigger = temp;
		}
		return bigger;
	}
	
	public static void main(String[] args){
		int x = 15;
		int y = 11;
		System.out.println(newtonGCD(x, y));
	}
}
