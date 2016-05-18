package zhang.algorithm.modelUtil;

/**
 * 约瑟环问题的解决
 * @author zhang_zack
 * 
 */
public class JosephRing {
	/**
	 * <strong>问题描述：</strong><br/>
	 * n个人（编号0~(n-1))，从0开始报数，报到(m-1)的退出，剩下的人继续从0开始报数。求最后胜利者的编号。<br/>
	 * <br/>
	 * [【[约瑟环]圆圈中最后剩下的数字】](http://blog.chinaunix.net/uid-20618535-id-274053.html)<br/>
	 * 其中有一段解说不太清楚，对第一个删除的数字是m%n-1，我有疑问，如果n==m，那么算的结果就是-1了啊，可以替换成(m-1)%n
	 * 
	 * f(n,m)=f’(n-1,m)=[f(n-1,m)+m]%n <br/>
	 * @param n n个人参与游戏
	 * @param m 从1开始报数的话，每次报到数m时，此人out。
	 * @return
	 */
	public static int findFinalWinnerNumber(int n, int m){
		if(n<1 || m<0){
			return -1;
		}
		int finalWinner = 0;
		for(int i=2; i<=n; i++){
			finalWinner = (finalWinner+m)%i;
			System.out.println("----> "+(finalWinner+1));
		}
		return finalWinner+1;
	}
	
	/**
	 * 问题二：有n个好人，n个坏人，找出n个坏人的排法，使得优先把所有坏人给pass掉<br/>
	 * 暴力法解决问题,O(n*m)
	 * @param n
	 * @param m
	 */
	public static void findAllIndex1(int n, int m){
		int[] flag = new int[2*n];
		int num = n;
		int index = 0;//当前的下标
		int count = 0;//表示当前这一轮数了多少个数
		while(num > 0){
			if(flag[index] == 0){
				count++;
			}
			if(count == m){
				flag[index] = 1;
				count = 0;
				num--;
			}
			//递增，为下一步做准备
			index++;
			if(index == flag.length){
				index = 0;
			}
		}
		for(int i=0; i<2*n; i++){
			if(flag[i] == 1){
				System.out.println(i+1);
			}
		}
	}
	/**
	 * 问题三：2n个人围成一圈，前n个人为好人，后n个人为坏人
	 * 从第一个人开始报数，报数报到m时，该人退出此圈
	 * 现要求确定一个最小m的值使得所有坏人在任意一个好人之前全部出圈。题目来源:【HDU1443_Joseph_约瑟环】<br/>
	 * <br/>
	 * 
	 * @param n 0<n<14
	 * @param m 
	 * @return
	 */
	public static int findMinM2KillAllBad(int n, int m){
		return 0;
	}
	
	public static void main(String[] args){
//		System.out.println(findFinalWinnerNumber(2, 2));
//		findAllIndex1(4, 3);
	}
}
