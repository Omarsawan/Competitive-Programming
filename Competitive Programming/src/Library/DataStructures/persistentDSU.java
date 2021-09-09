package Library.DataStructures;

import java.util.Stack;

public class persistentDSU {
	int N;
	int[] p,rank;
	int numSets;
	Stack<int[]>events;
	int curTime;
	persistentDSU(int n) {
		N=n;
		p = new int[numSets = N];
		events=new Stack<int[]>();
		rank=new int[N];
		for (int i = 0; i < N; i++) {  p[i] = i;}
	}
	int findSet(int i) { return p[i] == i ? i : (findSet(p[i])); }
	
	boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }
	
	
	void unionSet(int i, int j) 
	{ 
		if (isSameSet(i, j)) 
			return;
		numSets--;
		int x=findSet(i),y=findSet(j);
		if(rank[x] > rank[y]) { 
			p[y] = x;
			events.push(new int[] {curTime,y,x,0});//time , lower rank , higher rank , change of rank of higher rank
		}
		else
		{	p[x] = y;
			if(rank[x] == rank[y]) {
				rank[y]++;
				events.push(new int[] {curTime,x,y,1});//time , lower rank , higher rank ,change of rank of higher rank 
			}
			else {
				events.push(new int[] {curTime,x,y,0});//time , lower rank , higher rank ,change of rank of higher rank
			}
		} 
		
	}
	void persist() {
		curTime++;
	}
	void rollback() {
		while(!events.isEmpty() && events.peek()[0]==curTime) {
			int[]e=events.pop();
			numSets++;
			int low=e[1],hi=e[2],oldrankhi=e[3];
			if(oldrankhi==1)
				rank[hi]--;
			p[low]=low;
		}
		curTime--;
	}
}
