package Library.Graphs;

import java.util.ArrayList;
import java.util.Stack;

public class StonglyConnectedComponents {
	static ArrayList<Integer>[] adjList1;
	static int  counter, SCC, dfs_num[], dfs_low[];
	static boolean[] inSCC;
	static Stack<Integer> stack;
	static int V;
	
	static void tarjanSCC()	{//O(V + E)
		for(int i = 0; i < V; ++i)
			if(dfs_num[i] == 0)
				tarjanSCC(i);
	}
	
	static void tarjanSCC(int u){
		dfs_num[u] = dfs_low[u] = ++counter;
		stack.push(u);
		
		for(int v: adjList1[u])
		{
			if(dfs_num[v] == 0)
				tarjanSCC(v);
			if(!inSCC[v])
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);	
		}
		if(dfs_num[u] == dfs_low[u])
		{
			//SCC found
			SCC++;
			while(true)
			{
				int v = stack.pop();
				inSCC[v] = true;
				if(v == u)
					break;
			}
		}	
	}
}
