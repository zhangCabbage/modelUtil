package zhang.algorithm.modelUtil;


/**
 * 用来计算1-n中所有数出现1的次数总和 <br/>
 * <br/>
 * 对于计算十进制n位数中出现1的次数总和，我们可以很容易推导出递推公式 <br/>
 * 比如：我们假设1位数(1-9)中出现1的次数为a(1)，则很容易知道a(1)=1;<br/>
 * 相应的2位数(1-99)中出现1的次数为a(2)，则a(2)也不难求出a(2)=19 <br/>
 * 那么可以推导出：当n>=2时，a(n) = 10*a(n-1)+9*10^(n-2) <br/>
 * <br/>
 * 编程之美系列之三——计算1的个数
 * http://www.cnblogs.com/jy02414216/archive/2011/03/09/1977724.html
 * @author zhang_zack
 *
 */
public class CountOne {
	
	/**
	 * 改造递推的方法<br/>
	 * @return
	 */
	public long countOne1(long num){
		if(num==0){
			return 0;
		}else if(num>=1 && num<10){
			return 1;
		}
		
		long count = 0;
		long hightest = num;//表示最高位数字
		int bit = 0;//表示num总共有多少位
		while(hightest >= 10){
			hightest = hightest/10;
			bit++;
		}
		long weight = (long)Math.pow(10, bit);
		if(hightest == 1){
			count = countOne1(weight-1) + countOne1(num-weight) + (num-weight+1);
		}else{
			count = (hightest*countOne1(weight-1)+weight) + countOne1(num-hightest*weight);
		}
		
		return count;
	}
	
	/**
	 * 单独计算数字n每一位出现1的个数<br/>
	 * 每一位出现1的个数会受到其前后位数的影响
	 * @param num
	 * @return
	 */
	public long countOne2(long num){
		long count = 0;
		long i = 1;
		
		while(num/i != 0){
			int current = (int) ((num/i)%10);
			long before = num/(i*10);
			long after = num-(num/i)*i;
			if(current == 0){
				count = count + before*i;
			}else if(current == 1){
				count = count + before*i + after+1;
			}else{
				count = count + (before+1)*i;
			}
			i = i*10;
		}
		return count;
	}
	
	public static void main(String[] args){
		CountOne test = new CountOne();
		long num = 999911212999999999L;
		
		long start1 = System.currentTimeMillis();
		System.out.println(test.countOne1(num));
		long end1 = System.currentTimeMillis();
		System.out.println("方法1耗时--->"+(end1-start1)+"ms");
		
		long start2 = System.currentTimeMillis();
		System.out.println(test.countOne2(num));
		long end2 = System.currentTimeMillis();
		System.out.println("方法2耗时--->"+(end2-start2)+"ms");
		
//		结果为：
//		1799875962700000000
//		方法1耗时--->14ms
//		1799875962700000000
//		方法2耗时--->0ms
	}
}
