package Library.others;

import java.io.*;
import java.util.*;

public class sqrtDecompositionTrees{
	static LinkedList<int[]>[]adj;
	static int[]tin,tout;
	static int curTime;
	static int log = 18;
	static int[] level, up[];
	static int[]vals,nodes;
	static void preLCA(int root,int n) {
		up = new int[n][log];
		level = new int[n];
		tin=new int[n];
		tout=new int[n];
		vals=new int[n<<1];
		nodes=new int[n<<1];
		dfs(root, root,-1);
	}
 
	static int lca(int u, int v) {
 
		if (level[u] < level[v])
			return lca(v, u);
		for (int i = log - 1; i >= 0; i--) {
			int u2 = up[u][i];
			if (level[u2] >= level[v])
				u = u2;
		}
		if (u == v)
			return u;
		for (int i = log - 1; i >= 0; i--) {
			int u2 = up[u][i], v2 = up[v][i];
			if (u2 != v2) {
				u = u2;
				v = v2;
			}
		}
		return up[u][0];
	}
 
	static void dfs(int u, int p,int parEdge) {
		vals[curTime]=parEdge;
		nodes[curTime]=u;
		tin[u]=curTime++;
		up[u][0] = p;
		for (int i = 1; i < log; i++)
			up[u][i] = up[up[u][i - 1]][i - 1];
		for (int[]e : adj[u]) {
			int v=e[0];
			int x=e[1];
			if (v != p) {
				level[v] = level[u] + 1;
				dfs(v, u,x);
			}
		}
		nodes[curTime]=u;
		vals[curTime]=parEdge;
		tout[u]=curTime++;
	}	
	static void main() throws Exception{
		int n=sc.nextInt(),q=sc.nextInt();
		final int sq=300;
		adj=new LinkedList[n];
		for(int i=0;i<n;i++)adj[i]=new LinkedList<int[]>();
		for(int i=0;i<n-1;i++) {
			int x=sc.nextInt()-1,y=sc.nextInt()-1,c=Math.min(sc.nextInt(), 100006);
			adj[x].add(new int[] {y,c});
			adj[y].add(new int[] {x,c});
		}
		preLCA(0, n);
		int[][]qs=new int[q][];
		for(int i=0;i<q;i++) {
			int x=sc.nextInt()-1,y=sc.nextInt()-1;
			if(tin[x]>tin[y]) {
				int tmp=x;
				x=y;
				y=tmp;
			}
			int lca=lca(x, y);
			if(lca==x) {
				qs[i]=new int[] {tin[x]+1,tin[y],(tin[x]+1)/sq,i};
			}
			else {
				qs[i]=new int[] {tout[x],tin[y],tout[x]/sq,i};
			}
		}
		Arrays.sort(qs,(x,y)->x[2]!=y[2]?x[0]-y[0]:x[1]-y[1]);
		int[]ans=new int[q];
			
		occ=new int[n];
		map=new int[100007];
		mex=0;
		int curL=qs[0][0],curR=curL-1;
		for(int curQ=0;curQ<q;curQ++) {
			int l=qs[curQ][0],r=qs[curQ][1];
			
			while(curL>l) {
				curL--;
				add(nodes[curL],vals[curL]);
			}
			while(curR<r) {
				curR++;
				add(nodes[curR],vals[curR]);
			}
			while(curL<l) {
				remove(nodes[curL],vals[curL]);
				curL++;
			}
			while(curR>r) {
				remove(nodes[curR],vals[curR]);
				curR--;
			}
			
			ans[qs[curQ][3]]=mex;
		}
			

		
		for(int i=0;i<q;i++) {
			pw.println(ans[i]);
		}
	}
	static int[]map,occ;
	static int mex;
	static void add(int x) {
		map[x]++;
		while(map[mex]>0)mex++;
	}
	static void remove(int x) {
		if(map[x]==1) {
			if(x<mex) {
				mex=x;
			}
		}
		map[x]--;
	}
	static void add(int x,int val) {
		int newOcc=++occ[x];
		if(newOcc==1) {
			add(val);
		}
		if(newOcc==2) {
			remove(val);
		}
	}
	static void remove(int x,int val) {
		int newOcc=--occ[x];
		if(newOcc==0) {
			remove(val);
		}
		else {	
			add(val);
		}
		
	}
	public static void main(String[] args) throws Exception{
		sc=new MScanner(System.in);
		pw = new PrintWriter(System.out);
//		int tc=1;
//		tc=sc.nextInt();
//		while(tc-->0)
			main();
		pw.flush();
	}
	static PrintWriter pw;
	static MScanner sc;
	static class MScanner {
		StringTokenizer st;
		BufferedReader br;
		public MScanner(InputStream system) {
			br = new BufferedReader(new InputStreamReader(system));
		}
	 
		public MScanner(String file) throws Exception {
			br = new BufferedReader(new FileReader(file));
		}
	 
		public String next() throws IOException {
			while (st == null || !st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}
		public int[] intArr(int n) throws IOException {
	        int[]in=new int[n];for(int i=0;i<n;i++)in[i]=nextInt();
	        return in;
		}
		public long[] longArr(int n) throws IOException {
	        long[]in=new long[n];for(int i=0;i<n;i++)in[i]=nextLong();
	        return in;
		}
		public int[] intSortedArr(int n) throws IOException {
	        int[]in=new int[n];for(int i=0;i<n;i++)in[i]=nextInt();
	        shuffle(in);
	        Arrays.sort(in);
	        return in;
		}
		public long[] longSortedArr(int n) throws IOException {
	        long[]in=new long[n];for(int i=0;i<n;i++)in[i]=nextLong();
	        shuffle(in);
	        Arrays.sort(in);
	        return in;
		}
		public Integer[] IntegerArr(int n) throws IOException {
	        Integer[]in=new Integer[n];for(int i=0;i<n;i++)in[i]=nextInt();
	        return in;
		}
		public Long[] LongArr(int n) throws IOException {
	        Long[]in=new Long[n];for(int i=0;i<n;i++)in[i]=nextLong();
	        return in;
		}
		public String nextLine() throws IOException {
			return br.readLine();
		}
	 
		public int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	 
		public double nextDouble() throws IOException {
			return Double.parseDouble(next());
		}
	 
		public char nextChar() throws IOException {
			return next().charAt(0);
		}
	 
		public long nextLong() throws IOException {
			return Long.parseLong(next());
		}
	 
		public boolean ready() throws IOException {
			return br.ready();
		}
	 
		public void waitForInput() throws InterruptedException {
			Thread.sleep(3000);
		}
		
	}
	static void shuffle(int[]in) {
		for(int i=0;i<in.length;i++) {
			int idx=(int)(Math.random()*in.length);
			int tmp=in[i];
			in[i]=in[idx];
			in[idx]=tmp;
		}
	}
	static void shuffle(long[]in) {
		for(int i=0;i<in.length;i++) {
			int idx=(int)(Math.random()*in.length);
			long tmp=in[i];
			in[i]=in[idx];
			in[idx]=tmp;
		}
	}
}
