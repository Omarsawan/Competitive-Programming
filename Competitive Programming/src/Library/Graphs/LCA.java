package Library.Graphs;
import java.util.ArrayList;

public class LCA {
		
	static int log = 22;
	int[] level, up[],sub;
	static ArrayList<Integer>[]adj;
	LCA(int root,int n) {
		up = new int[n][log];
		level = new int[n];
		sub=new int [n];
		dfs(root, root);
	}
 
	int lca(int u, int v) {
 
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
 
	int dfs(int u, int p) {
 
		int ans=1;
		up[u][0] = p;
		for (int i = 1; i < log; i++)
			up[u][i] = up[up[u][i - 1]][i - 1];
		for (int v : adj[u])
			if (v != p) {
				level[v] = level[u] + 1;
				ans+=dfs(v, u);
			}
		return sub[u]=ans;
 
	}
 
}