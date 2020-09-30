package Library.DataStructures;

import java.util.Arrays;

public class fenwickTree {
	int INF=(int)2e9;
	int n;
	long[]ft;
	int log;
	fenwickTree(int z,boolean sum){
		if(sum) {
			//tree for sum
			ft=new long[z+1];
			n=z;
		}
		else {
			//tree for maximum
			ft=new long[z+1];
			Arrays.fill(ft,-1);//smallest possible number
			n=z;
		}
		while((1<<log)<=n) {
			log++;
		}
		log--;
	}
	
	void updateSum(int idx,int k) {
		while(idx<=n) {
			ft[idx]+=k;
			idx+=(idx&(-1*idx));
		}
		
	}
	long querySum(int idx) {
		long sum=0;
		while(idx>0) {
			sum+=ft[idx];
			idx-=(idx&(-1*idx));
		}
		return sum;
	}
	void updateMax(int idx,long k) {
		while(idx<=n) {
			ft[idx]=Math.max(ft[idx],k);
			idx+=(idx&(-1*idx));
		}
		
	}
	long queryMax(int idx) {
		long max=0;
		while(idx>0) {
			max=Math.max(ft[idx],max);
			idx-=(idx&(-1*idx));
		}
		return max;
	}
	int findKth(int k) {
		int cntLess=0;
		int mask=0;
		for(int l=log;l>=0 && cntLess<k;l--) {
			if((mask|(1<<l))<=n && ft[(mask|(1<<l))]+cntLess<k) {
				mask|=(1<<l);
				cntLess+=ft[mask];
			}
		}
		mask++;
		return mask;
	}
	
}
