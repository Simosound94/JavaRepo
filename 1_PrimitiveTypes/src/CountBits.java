
public class CountBits {
	
	public static void main(String[] args) {
		System.out.println("Count 15: " +countBits(15));
		System.out.println("swap 100 -> 010: "+ swapBits(4,2,1));
		System.out.println("Mul 6x3: "+ multiplyBinary(6, 3));



		
		
	}
	
	public static int countBits(int x){
		int numBits = 0;
		while(x!=0){
			numBits = numBits + (x&1);
			x  >>>= 1;
		}
		return numBits;
	}
	
	public static long swapBits(long x, int i, int j){
//		i = 1 << i;/*(int)Math.pow(2, i);*/
//		j = 1<<j; /*(int)Math.pow(2, j);*/
//		long bitI = x & i;
//		long bitJ = x & j;
//		if(bitI != bitJ){
//			if(bitI==0){
//				x=x-bitJ+i;
//			}
//			else{
//				x=x-bitI+j;
//			}
//		}
//	
//		return x;
		
		if(((x>>> i)& 1) != ((x >>> j)& 1)){
			long bitMask = (1L << i) | (1L << j);
			x ^= bitMask;
			
		}
		return x;
		
	}
	
	public static long closestSameBitCount(long x){
		final int NUM_UNSIGNED_BITS = 63;
		
		for(int i=0; i<NUM_UNSIGNED_BITS -1; ++i){
			if(((x>>> i)& 1) != ((x >>> (i+1))& 1)){ //Trovo prima coppia successiva di bit diversi
				x ^= (1L << i) | (1L << (i+1));	//Li scambio come fatto prima
				return x;
			}
		}
		throw new IllegalArgumentException("All bits 0 or 1");
		
	}
	
	public static long multiplyBinary(long x, long y){
		long temp;
		// O(#bit x) swap per fare inmodo che x sia il minore dei due
		if(x>y){
			temp = x;
			x=y;
			y=temp;
		}
		
		long mul=0;
		int i = 0;
		while((x>>i)!= 0){
			if(((x>>i)&1)==1)
				temp = y;
			else
				temp = 0;
			mul += (temp<<i); // NON POTEVO USARLA !!!
			i++;
		}
		return mul;
		
	}

	
	

}
