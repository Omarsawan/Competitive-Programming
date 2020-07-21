package Library.Strings;

public class Hashing{
	static int mod=(int)1e9+9,prime=31;
	static long[]hashIn;
	static long powerofP[],invpofp[];
	static char[]in;
	
	static void preHash(int n,String input) {
		in=input.toCharArray();
		
		powerofP=new long[n+1];
		invpofp=new long[n+1];
		powerofP[0]=1;
		invpofp[0]=1;
		for(int i=1;i<powerofP.length;i++) {
			powerofP[i]=(powerofP[i-1]*prime)%mod;
			invpofp[i]=fastpow(powerofP[i], mod-2,mod);
		}
		
		long p=1,p2=1;
		for(int i=1;i<=n;i++) {
			hashIn[i]=(p*(in[i-1]-'a'+1))%mod;
			hashIn[i]=(hashIn[i]+hashIn[i-1])%mod;
			p=(p*prime)%mod;
		}
	}
	
	static long fastpow(long n,long ti,int mod3) {
		 if (ti == 0) 
		        return 1l;
		if(ti%2==0) {
			long y=fastpow(n, ti/2,mod3);
			long k=y*y;
			k%=mod3;
			return k;
		}
		else {
			long y=fastpow(n, ti/2,mod3);
			long k=((n*y)%mod3)*y;
			k%=mod3;
			return k;
		}
	}
	//the hash value of substring [l,r] depending on mod , prime
	static long compHash(int l,int r) {
		long val=(hashIn[r]-hashIn[l-1]+mod)%mod;
		val=(val*invpofp[l-1])%mod;
		return val;
	}
	
}