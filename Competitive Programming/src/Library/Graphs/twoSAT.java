package Library.Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class twoSAT{
	static ArrayList<Integer> adjList [];
	static int [] dfs_num, dfs_low, visited, component, rootOfComponent;
	static int dfsNumberCounter, numSCC;
	static Stack <Integer> S;
	static int UNVISITED = 0;
	static int V;//V = 2 * number of vertices 
				 //even numbers -> vertices , odd numbers -> negation of vertices 
	
	public static void tarjanSCC ()
	{
	        dfs_low = new int[V];
	        dfs_num = new int[V];
	        visited = new int[V];
	        component = new int[V];
	        rootOfComponent = new int[V];
	        S = new Stack<>();
	        dfsNumberCounter = 0;
	        numSCC = 0;
	        for (int i = 0; i < V; ++i)
	                if (dfs_num[i] == UNVISITED)
	                        tarjanSCC(i);
	}
	
	public static void tarjanSCC(int u)
	{
	        dfs_low[u] = dfs_num[u] = ++dfsNumberCounter;
	        S.push(u);
	        visited[u] = 1;
	        for (int v : adjList[u])
	        {
	                if (dfs_num[v] == UNVISITED)
	                        tarjanSCC(v);
	                if (visited[v] == 1)
	                        dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
	        }
 
	        if (dfs_low[u] == dfs_num[u])
	        {
	                while (true)
	                {
	                        int v = S.pop();
	                        visited[v] = UNVISITED;
	                        component[v] = numSCC;
	                        if (u == v) break;
	                }
	                rootOfComponent[numSCC] = u;
	                numSCC++;
	        }
	}

	static int NOT (int x) {
		return x ^ 1;
	}
	 
	// acceptable values: (0, 1), (1, 0), (1, 1)
	static void OR(int a, int b) {
        adjList[NOT(a)].add(b);
        adjList[NOT(b)].add(a);
	}
	 
	// acceptable values: (0, 1), (1, 0)
	static void or_but_not_both(int a, int b) {
	    // CNF: (a v b) & (!a v !b)  ==> E.g. can't be true for (1, 1) / (0, 0)
        OR(a, b);
        OR(NOT(a), NOT(b));
	}
	static void bothOrNone(int a, int b) {
        // CNF: (!a v b) & (a v !b)  ==> E.g. can't be true for (0, 1) / (1, 0)
        OR(NOT(a), b);
        OR(a, NOT(b));
	}
	 
	// x -> y = (not x) or y
	 
 
	static boolean is_solvable() {
        for (int i = 0; i < V; i += 2)
                if (component[i] == component[NOT(i)])
                        return false;
        return true;
	}
	static void force_value(int i, boolean b) {
        // because after reversed toposort, I give true to what comes first
        if (b)
                adjList[NOT(i)].add(i);
        else
                adjList[i].add(NOT(i));
	}
 
	static int[] assign_values() {//greedy
        int comp_result [] = new int[numSCC];
        Arrays.fill(comp_result, -1);
        for (int i = 0; i < numSCC; i++) {
                if (comp_result[i] == -1) {
                	int not_ithcomp = component[NOT(rootOfComponent[i])];
                    comp_result[i] = 1;
                    comp_result[not_ithcomp] = 0;
                    
                }
        }
 
        int[]res=new int[V];
        for (int i = 0; i < V; i++) res[i] = comp_result[component[i]];
        return res;
	}
}
