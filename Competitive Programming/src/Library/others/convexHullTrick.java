package Library.others;

import java.util.ArrayList;
import java.util.Arrays;

public class convexHullTrick {
	static class convexHullMin{
		int[][]lines;
		ArrayList<int[]> hull, vecs;
		convexHullMin(int[][]in){//in is array of the lines ax+b where lines[i][0]=a,lines[i][1]=b
			int n=in.length;
			lines=new int[n][];
			for(int i=0;i<n;i++) {
				lines[i]=new int[] {in[i][0],in[i][1],i};
			}
			Arrays.sort(lines,(x,y)->x[0]==y[0]?y[1]-x[1]:x[0]-y[0]);
			hull=new ArrayList<int[]>();
			vecs=new ArrayList<int[]>();
			for(int[]line:lines) {
				add_line(line);
			}

		}

		void add_line(int[]cur) {
			while(!hull.isEmpty() && cur[0]==hull.get(hull.size()-1)[0]) {
				hull.remove(hull.size()-1);
				if(!vecs.isEmpty())
					vecs.remove(vecs.size()-1);
			}
		    while(!vecs.isEmpty() && dotProduct(vecs.get(vecs.size()-1), vector(hull.get(hull.size()-1), cur)) < 0) {
		        hull.remove(hull.size()-1);
		        vecs.remove(vecs.size()-1);
		    }
		    if(!hull.isEmpty()) {
		    	int[]curV=vector(hull.get(hull.size()-1), cur);
		        vecs.add(new int[] {-curV[1],curV[0]});
		    }
		    hull.add(cur);
		}
		static long dotProduct(int[]v1,int[]v2) {
			return v1[0]*1l*v2[0]+v1[1]*1l*v2[1];
		}
		static int[] vector(int[]p,int[]q) {
			return new int[] {q[0]-p[0],q[1]-p[1]};
		}
		
		int getMin(int x) {
		    int idx=bestIdx(x);
		    int[]bestLine=hull.get(idx);
		    int a=bestLine[0],b=bestLine[1];//the line that has the minimum value at x
		    int i=bestLine[2];//the index of the line in the input array
		    return i;
		}
		
		int bestIdx(int query) {
			int lo=0,hi=vecs.size()-1;
			int ans=hi+1;
			while(lo<=hi) {
				int mid=(lo+hi)>>1;
				int[]cur=vecs.get(mid);
				long crossProduct=cur[0]-cur[1]*1l*query;
				if(crossProduct>0) {
					lo=mid+1;
				}
				else {
					ans=mid;
					hi=mid-1;
				}
			}
			return ans;
		}
	}
	
	static class convexHullMax{
		long[][]lines;
		ArrayList<long[]> hull, vecs;
		convexHullMax(long[][]in){//in is array of the lines ax+b where lines[i][0]=a,lines[i][1]=b
			int n=in.length;
			lines=new long[n][];
			for(int i=0;i<n;i++) {
				lines[i]=new long[] {-in[i][0],-in[i][1],i};
			}
			Arrays.sort(lines,(x,y)->x[0]==y[0]?Long.compare(y[1],x[1]):Long.compare(x[0],y[0]));
			hull=new ArrayList<long[]>();
			vecs=new ArrayList<long[]>();
			for(long[]line:lines) {
				add_line(line);
			}

		}

		void add_line(long[]cur) {
			while(!hull.isEmpty() && cur[0]==hull.get(hull.size()-1)[0]) {
				hull.remove(hull.size()-1);
				if(!vecs.isEmpty())
					vecs.remove(vecs.size()-1);
			}
		    while(!vecs.isEmpty() && dotProduct(vecs.get(vecs.size()-1), vector(hull.get(hull.size()-1), cur)) < 0) {
		        hull.remove(hull.size()-1);
		        vecs.remove(vecs.size()-1);
		    }
		    if(!hull.isEmpty()) {
		    	long[]curV=vector(hull.get(hull.size()-1), cur);
		        vecs.add(new long[] {-curV[1],curV[0]});
		    }
		    hull.add(cur);
		}
		static long dotProduct(long[]v1,long[]v2) {
			return v1[0]*v2[0]+v1[1]*v2[1];
		}
		static long[] vector(long[]p,long[]q) {
			return new long[] {q[0]-p[0],q[1]-p[1]};
		}
		
		long getMax(long x) {
		    int idx=bestIdx(x);
		    long[]bestLine=hull.get(idx);
		    long a=bestLine[0],b=bestLine[1];//the line that has the maximum value at x
		    long i=bestLine[2];//the index of the line in the input array
		    return a*x+b;
		}
		
		int bestIdx(long query) {
			int lo=0,hi=vecs.size()-1;
			int ans=hi+1;
			while(lo<=hi) {
				int mid=(lo+hi)>>1;
				long[]cur=vecs.get(mid);
				long crossProduct=cur[0]-cur[1]*query;
				if(crossProduct>0) {
					lo=mid+1;
				}
				else {
					ans=mid;
					hi=mid-1;
				}
			}
			return ans;
		}
	}
}
