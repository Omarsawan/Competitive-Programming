package Library.Strings;

import java.util.Arrays;

public class suffixArrayRecursive {
	int[][]arrs;
	char[]in;
	int n;
	public suffixArrayRecursive(char[]in) {//has a terminating character (e.g. '$')
		this.n=in.length;//O(n*log(n))
		this.in=in;
		int k=0;
		while((1<<k)<n) {
			k++;
		}
		arrs=solve(k);
	}
	int[] saArr() {
		return arrs[0];
	}
	int[] cArr() {
		return arrs[1];
	}
	int[][] solve(int k){
		if(k==0) {
			int[][]a=new int[n][2];
			for(int i=0;i<in.length;i++) {
				a[i]=new int[] {in[i]-'a',i};
			}
			Arrays.sort(a,(x,y)->x[0]-y[0]);
			int[]p=new int[n],c=new int[n];
			for(int i=0;i<n;i++) {
				p[i]=a[i][1];
			}
			c[a[0][1]]=0;
			for(int i=1;i<n;i++) {
				c[a[i][1]]=(a[i][0]==a[i-1][0])?c[a[i-1][1]]:(c[a[i-1][1]]+1);
			}
			return new int[][] {p,c};
		}
		k--;
		int[][]prev=solve(k);
		int[]p=prev[0],c=prev[1];
		
		for(int i=0;i<n;i++) {
			p[i]=(p[i]+n-(1<<k))%n;
		}
		p=countingSort(p, c);
		
		int[]newc=new int[n];
		newc[p[0]]=0;
		for(int i=1;i<n;i++) {
			int[]curPair=new int[] {c[p[i]],c[(p[i]+(1<<k))%n]};
			int[]prevPair=new int[] {c[p[i-1]],c[(p[i-1]+(1<<k))%n]};
			newc[p[i]]=(compare(prevPair, curPair)==0)?newc[p[i-1]]:(newc[p[i-1]]+1);
		}
		return new int[][] {p,newc};
	}
	
	int compare(int[]x,int[]y) {
		return x[0]!=y[0]?x[0]-y[0]:x[1]-y[1];
	}
	int[] countingSort(int[]sa,int[]c) {
		int n=sa.length;
		int[]cnt=new int[n];
		for(int i:sa) {
			cnt[c[i]]++;
		}
		int[]pointers=new int[n];
		pointers[0]=0;
		for(int i=1;i<n;i++) {
			pointers[i]=pointers[i-1]+cnt[i-1];
		}
		int[]newSa=new int[n];
		for(int i:sa) {
			newSa[pointers[c[i]]++]=i;
		}
		return newSa;
	}
	int[] lcpArr() {
		int[]sa=saArr();
		int[]c=cArr();
		int[]lcp=new int[n];
		int k=0;
		for(int i=0;i<n-1;i++) {
			int cur=i,prev=sa[c[i]-1];
			while(in[cur+k]==in[prev+k])k++;
			lcp[c[i]]=k;
			k=Math.max(k-1, 0);
		}
		return lcp;
	}
}
