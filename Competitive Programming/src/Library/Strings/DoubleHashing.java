package Library.Strings;

public class DoubleHashing{
	//Double Hashing is done to decrease the probability of collisions
	static int mod=(int)1e9+9,prime=31;//may use different mod and prime
	static int mod2=(int)1e9+7,prime2=29;//may use different mod and prime
	static long[]hashIn;
	static long[]hashIn2;
	static long powerofP[],invpofp[];
	static long powerofP2[],invpofp2[];
	static char[]in;
	
	static void preHash(int n,String input) {
		in=input.toCharArray();
		
		powerofP=new long[n+1];
		invpofp=new long[n+1];
		powerofP[0]=1;
		invpofp[0]=1;
		hashIn=new long[n+1];
		hashIn2=new long[n+1];
		powerofP2=new long[n+1];
		invpofp2=new long[n+1];
		powerofP2[0]=1;
		invpofp2[0]=1;
		for(int i=1;i<powerofP.length;i++) {
			powerofP[i]=(powerofP[i-1]*prime)%mod;
			invpofp[i]=fastpow(powerofP[i], mod-2,mod);
		}
		for(int i=1;i<powerofP.length;i++) {
			powerofP2[i]=(powerofP2[i-1]*prime2)%mod2;
			invpofp2[i]=fastpow(powerofP2[i], mod2-2,mod2);
		}
		
		long p=1,p2=1;
		for(int i=1;i<=n;i++) {
			hashIn[i]=(p*(in[i-1]-'a'+1))%mod;
			hashIn[i]=(hashIn[i]+hashIn[i-1])%mod;
			p=(p*prime)%mod;
			
			hashIn2[i]=(p2*(in[i-1]-'a'+1))%mod2;
			hashIn2[i]=(hashIn2[i]+hashIn2[i-1])%mod2;
			p2=(p2*prime2)%mod2;
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
	//the hash value of substring [l,r] depending on mod2 , prime2
	static long compHash2(int l,int r) {
		long val=(hashIn2[r]-hashIn2[l-1]+mod2)%mod2;
		val=(val*invpofp2[l-1])%mod2;
		return val;
	}
}