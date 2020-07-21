package Library.Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class ShortestPath {
	static LinkedList<Integer>adjList[];
	static int INF=(int)1e9;
	static int n;
	//unweighted graph
	static int minDist(int s,int e) {
		int[]dist=new int[n];Arrays.fill(dist,INF); 
		dist[s] = 0; // distance from source s to s is 0
		Queue<Integer> q=new LinkedList<Integer>(); q.add(s);
		
		while (!q.isEmpty()) {
			int u = q.poll();
			for (int v:adjList[u]) {
				if (dist[v] == INF) {
					dist[v] = dist[u] + 1;
					q.add(v);
				} 
				
			}
			
		}
		return dist[e];
	}
	
	//weighted graph
	static ArrayList<Edge>[] adjListW;
	static int dijkstra(int S, int T)	//O(E log E)
	{
		int[] dist = new int[n];
		Arrays.fill(dist, INF);
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		dist[S] = 0;
		pq.add(new Edge(S, 0));						//may add more in case of MSSP (Mult-Source)
		while(!pq.isEmpty())
		{
			Edge cur = pq.remove();
			if(cur.node == T)						//remove if all computations are needed
				return dist[T];
			if(cur.cost > dist[cur.node])			//lazy deletion
				continue;
			for(Edge nxt: adjListW[cur.node])
				if(cur.cost + nxt.cost < dist[nxt.node])
					pq.add(new Edge(nxt.node, dist[nxt.node] = cur.cost + nxt.cost ));
		}
		return -1;
	}
	static class Edge implements Comparable<Edge>
	{
		int node, cost;
		
		Edge(int a, int b) { node = a;	cost = b; }
		
		public int compareTo(Edge e){ return cost - e.cost;	}
	}
	
	
	//all pairs shortest path
	static int[][] adjMatrix;
	static int V;
	
	static void floyd()
	{
		//adjMatrix contains: directed edges, zero for i=j, INF (1B) otherwise
		
		for(int k = 0; k < V; k++) {
			for(int i = 0; i < V; i++) {
				for(int j = 0; j < V; j++) {
					if(adjMatrix[i][j] > adjMatrix[i][k] + adjMatrix[k][j]){
						adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];
					}
				}
			}
		}
	}
}
