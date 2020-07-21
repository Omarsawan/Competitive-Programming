package Library.RangeMinimumQuery;

public class SparseTable {
	int[]log,min[],arr;
	int inf=(int)1e9;
	SparseTable(int[]in,int n) {
		arr=in;
		log = new int[n+1];
		for (int i = 2; i <= n; i++)
			log[i] = log[i / 2] + 1;
		
		min=new int[n][22];
		for(int i=0;i<n;i++) {
			min[i][0]=i;
		}
		for(int i=1,len=2;len<=n;i++,len<<=1) {
			for(int j=0;j<n;j++) {
				int u=min[j][i-1],v=j+(len>>1)>=n?inf:min[j+(len>>1)][i-1];
				if(v==inf) {
					min[j][i]=u;
				}
				else
					min[j][i]=(arr[u]<arr[v]?u:v);
			}
		}
	}
	int query(int l,int r) {
		int len = r - l + 1;
		int lg = log[len];
		int u = min[l][lg];
		int v = min[r - (1 << lg) + 1][lg];
		int bestIdx = arr[u] < arr[v] ? u : v;
		return arr[bestIdx];//or return bestIdx if we want the best index
	}
	
}
