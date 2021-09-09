package Library.Graphs;

public class DSU {
	static int[] p, rank, setSize;
	static int numSets,N;
	static void preDSU(int n) {
		N=n;
		p = new int[numSets = N];
		rank = new int[N];
		setSize = new int[N];
		for (int i = 0; i < N; i++) {  p[i] = i; setSize[i] = 1; }
	}
	static int findSet(int i) { return p[i] == i ? i : (p[i] = findSet(p[i])); }
	
	static boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }
	
	static boolean unionSet(int i, int j) 
	{ 
		if (isSameSet(i, j)) 
			return false;
		numSets--; 
		int x = findSet(i), y = findSet(j);
		if(rank[x] > rank[y]) { p[y] = x; setSize[x] += setSize[y]; }
		else
		{	p[x] = y; setSize[y] += setSize[x];
			if(rank[x] == rank[y]) rank[y]++; 
		} 
		return true;
	}
}
