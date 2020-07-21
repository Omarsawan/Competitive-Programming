package Library;

public class fraction {
	long num,den;
	fraction(long x,long y){
		long gcd=gcd(x,y);
		num=x/gcd;den=y/gcd;
	}
	
	
	long compareTo(fraction o) {
		fraction res=sub(this, o);
		long ret=res.num*res.den;
		return ret;
	}
	public String toString() {return num+" "+den;}
		
	
	
	static long gcd(long a,long b) {
		if(b==0)return a;
		return gcd(b,a%b);
	}
	static fraction add(fraction x,fraction y) {
		return new fraction((x.num*(y.den))+(y.num*(x.den)), (x.den)*y.den);
	}
	static fraction sub(fraction x,fraction y) {
		return new fraction((x.num*(y.den))-(y.num*(x.den)), (x.den)*y.den);
	}
	static fraction mul(fraction x,fraction y) {
		long num1=x.num,num2=y.num,den1=x.den,den2=y.den;
		return new fraction(num1*num2, den1*den2);
	}
	
	static fraction min(fraction f1,fraction f2) {
		if(f1.compareTo(f2)<=0)return f1;
		return f2;
	}
	
	
}
