package Library.others;

public class liChaoTree {
	//for convex hull
	static long inf=(long)1e18;
	int maxn=(int)1e6+1;
	long[][]lines;
	liChaoTree(){
		lines=new long[maxn*4][2];
		for(int i=1;i<lines.length;i++) {
			lines[i]=new long[] {0,inf};
		}
	}
	static long valueAt(long[]line,int x) {
		return line[0]*x+line[1];
	}
	void add_line(long[]newLine) {
		add_line(newLine, 1, 1, maxn-1);
	}
	void add_line(long[]newLine,int node, int left, int right) {
		if(left==right) {
			if(valueAt(newLine, left) < valueAt(lines[node], left)) {
				//new line is better
				lines[node]=newLine.clone();
			}
			return;
		}
		
		
	    int mid=(left+right)>>1;
		int cur=0,nw=1;
	    int dominatingAtLeft = valueAt(newLine, left) < valueAt(lines[node], left) ? cur : nw;
	    int dominatingAtMid = valueAt(newLine, mid) < valueAt(lines[node], mid) ? cur : nw;
	    
	    if(dominatingAtMid!=nw) {
	    	long[]tmp=newLine;
	    	newLine=lines[node];
	    	lines[node]=tmp;
	    }
	    if(dominatingAtLeft!=dominatingAtMid) {
	    	//intersection at left subtree
	    	add_line(newLine, node<<1, left, mid);
	    	
	    }
	    else {
	    	//intersection at right subtree
	    	add_line(newLine, node<<1|1, mid+1, right);
	    	
	    }
	}
	long get(int x) {
		return get(x, 1, 1, maxn-1);
	}
	long get(int x, int node, int left, int right) {
	    int mid=(left + right)>>1;
	    if(left==right) {
	        return valueAt(lines[node], x);
	    }
	    if(x <= mid) {
	        return Math.min(valueAt(lines[node], x), get(x, node<<1, left, mid));
	    }
	    return Math.min(valueAt(lines[node], x), get(x, node<<1|1, mid+1, right));
	    
	}
	
}
