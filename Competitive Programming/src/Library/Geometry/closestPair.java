package Library.Geometry;

import java.util.ArrayList;
import java.util.Arrays;

public class closestPair {
	static int[][]points;
	static int ans,best[];
	static int dist(int i,int j) {
		return (points[i][0]-points[j][0])*(points[i][0]-points[j][0])+(points[i][1]-points[j][1])*(points[i][1]-points[j][1]);
	}
	static void update(int i,int j) {
		int cur=dist(i, j);
		if(cur<ans) {
			ans=cur;
			best=new int[] {points[i][2],points[j][2]};
		}
	}
	static int square(int x) {
		return x*x;
	}
	static int[][] solve(int l,int r){
		int[][]res=new int[r-l+1][];
		if(r-l+1<4) {
			for(int c=0,o=l;o<=r;c++,o++) {
				res[c]=new int[] {points[o][0],points[o][1],o};
				for(int j=o-1;j>=l;j--) {
					update(o,j);	
				}
			}
			Arrays.sort(res,(x,y)->x[1]-y[1]);
			return res;
		}
		int mid=(l+r)>>1;
		int midx=points[mid][0];
		int[][]left=solve(l, mid),right=solve(mid+1, r);
		int i=0,j=0;
		for(int c=0;c<res.length;c++) {
			if(j==right.length || (i<left.length && left[i][1]<=right[j][1])) {
				res[c]=new int[] {left[i][0],left[i][1],left[i][2]};
				i++;
			}
			else {
				res[c]=new int[] {right[j][0],right[j][1],right[j][2]};
				j++;
			}
		}
		ArrayList<Integer>B=new ArrayList<Integer>();
		for(int idx=0;idx<res.length;idx++) {
			if(square(Math.abs(midx-res[idx][0]))>=ans)continue;
			for(int p1=B.size()-1;p1>=0;p1--) {
				if(square(Math.abs(points[B.get(p1)][1]-points[res[idx][2]][1]))>=ans)break;
				update(res[idx][2],B.get(p1));
			}
			B.add(res[idx][2]);
		}
		
		return res;
	}
	closestPair(int[][]in) {//in[i][0] -> x-coordinate , in[i][1] -> y-coordinate if the ith point
		int n=in.length;
		ans=(int)1e9;
		points=new int[n][3];
		for(int i=0;i<n;i++) {
			for(int j=0;j<2;j++) {
				points[i][j]=in[i][j];
			}
			points[i][2]=i;
		}
		Arrays.sort(points,(x,y)->x[0]==y[0]?x[1]-y[1]:x[0]-y[0]);
		solve(0, n-1);
		//distance squared between the closest square
		System.out.println(ans);
		//two closest points
		System.out.println(best[0]+" "+best[1]);
	}
}
