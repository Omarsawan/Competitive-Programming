package Library;
import java.util.ArrayList;

public class mathematics {
	//distribute n elements into k partitions
	static long ways(int n,int k) {
		long ans=0;
		for(int i=0;i<=k;i++) {
			long cur=(ncr(k, i)*fastpow(k-i, n))%mod;
			if((i&1)==1) {
				ans=(ans-cur+mod)%mod;
			}
			else {
				ans=(ans+cur)%mod;
			}
		}
		ans=(ans*facInv[k])%mod;
		return ans;
	}
	static int mod=(int)1e9+7;
	static long fastpow(long n,long ti) {
		 if (ti == 0) 
		        return 1l;
		if(ti%2==0) {
			long y=fastpow(n, ti/2);
			long k=y*y;
			k%=mod;
			return k;
		}
		else {
			long y=fastpow(n, ti/2);
			long k=((n*y)%mod)*y;
			k%=mod;
			return k;
		}
	}
	static long modInverse(long x) {
    	return fastpow(x, mod-2);
    }
	static long[]fac,facInv;
	static void preProcessNCR(int maxN) {
		//compute factorial for all numbers smaller than or equal maxN
		fac=new long[maxN+1];fac[0]=1;
		for(int i=1;i<fac.length;i++)fac[i]=(fac[i-1]*i)%mod;
		
		facInv=new long[fac.length];
		facInv[fac.length-1]=modInverse(fac[fac.length-1]);//modInverse(n!)
		for (int i=fac.length-2; i>=0; i--) {
			//modInverse(x!)=(x+1)/(x+1)!=(x+1)*modInverse((x+1)!)
			facInv[i]=(facInv[i+1]*(i+1))%mod;
		}
	}

	public static long ncr(int n,int r) {
		if(n<r)return 0;
		long num=fac[n];
		long den=(facInv[n-r]*facInv[r])%mod;
		long ans=(num*den)%mod;
		return ans;
	}
	
	
	static long comb[][];
	static void preProcessNCR2(int maxN)		// O(N * N)
	{
		comb = new long[maxN][maxN];
		comb[0][0] = 1;
		for (int i = 1; i < maxN; i++)
		{
			comb[i][0] = 1;
			for (int j = 1; j <= i; j++)
				comb[i][j] = ((comb[i-1][j] + comb[i-1][j-1]))%mod;
		}
	}
	public static long ncr2(int n,int r) {
		return comb[n][r];
	}
	
    static long lcm(long a,long b) {
    	return (a/gcd(a,b))*b;
    }
    static long gcd(long a, long b) {

		if (b == 0)
			return a;
		return gcd(b, a % b);
	}
    
    static int x, y, d;
	
	void extendedEuclid(int a, int b)
	{
		if(b == 0) { x = 1; y = 0; d = a; return; }
		extendedEuclid(b, a % b);
		int x1 = y;
		int y1 = x - a / b * y;
		x = x1; y = y1;
	}
    
    static int[]primes;
    static int sizeofp=0;
	static int[] isComposite;
	
	static void sieve(int N)	// O(N log log N) 
	{
		isComposite = new int[N+1];					
		isComposite[0] = isComposite[1] = 1;			
		primes = new int[N];

		for (int i = 2; i <= N; ++i) 					
			if (isComposite[i] == 0) 					
			{
				primes[sizeofp++]=i;;
				if(1l * i * i <= N)
					for (int j = i * i; j <= N; j += i)	
						isComposite[j] = 1;
			}   
	}
	
	static int[]lp;
	static void sieveLinear(int N)
	{
		ArrayList<Integer> primes = new ArrayList<Integer>();
		lp = new int[N + 1];								//lp[i] = least prime divisor of i
		for(int i = 2; i <= N; ++i)
		{
			if(lp[i] == 0)
			{
				primes.add(i);
				lp[i] = i;
			}
			int curLP = lp[i];
			for(int p: primes)//all primes smaller than or equal my lowest prime divisor
				if(p > curLP || p * 1l * i > N)
					break;
				else
					lp[p * i] = p;
		}
	}
	
	
	static long[][] matMul(long[][] A, long[][] B)	//C(p x r) = A(p x q) x (q x r) -- O(p x q x r)
	{
		int p=A.length,q=B.length,r=B[0].length;
		long[][] C = new long[p][r];
		for(int i = 0; i < p; ++i)
			for(int j = 0; j < r; ++j)
				for(int k = 0; k < q; ++k) {
					C[i][j] += A[i][k] * B[k][j];
					C[i][j]%=mod;
				}
		return C;
	}
	
	static long[][] matPow(long[][] base, int p)
	{
		int n = base.length;
		long[][] ans = new long[n][n];
		for(int i = 0; i < n; i++)
			ans[i][i] = 1;
		while(p != 0)
		{
			if((p & 1) == 1)
				ans = matMul(ans, base);
			base = matMul(base, base);
			p >>= 1;
		}
		
		return ans;
	}
	
	static ArrayList<Integer> primeFactors(int N)		// O(sqrt(N) / ln sqrt(N))
	{
		//prime factors of positive integer N
		ArrayList<Integer> factors = new ArrayList<Integer>();
		int idx = 0, p = primes[idx];

		while(p * p <= N)
		{
			while(N % p == 0) { factors.add(p); N /= p; }
			p = primes[++idx];
		}

		if(N != 1)						// last prime factor may be > sqrt(N)
			factors.add(N);				// for integers whose largest prime factor has a power of 1
		return factors;
	}
	
	
	//sum all different triplets:
	//sum (in[i]*in[j]*in[k]) for all 1<= i < j < k <=n is:
	//((s1^3)-(3*s1*s2)+(2*s3))/6
	//where s1 -> sum in[i] for all i
	//where s2 -> sum in[i]^2 for all i
	//where s3 -> sum in[i]^3 for all i
	
}
