package Library.RangeMinimumQuery;

public class fenwickTree2D {
	int[][]tree;
	int max_x;int max_y;
	fenwickTree2D(int x,int y){
		tree=new int[x+1][y+1];
		max_x=x;max_y=y;
	}
	void update(int x , int y , int val){
		int y1;
	    while (x <= max_x){
	        y1 = y;
	        while (y1 <= max_y){
	            tree[x][y1] += val;
	            y1 += (y1 & -y1); 
	        }
	        x += (x & -x); 
	    }
	}
	int read(int x,int y){
		int sum = 0; 
		  
		int y1;
	    while (x >0){
	        y1 = y;
	        while (y1 >0){
	            sum+=tree[x][y1];
	            y1 -= (y1 & -y1); 
	        }
	        x -= (x & -x); 
	    }
	    return sum;
	}
	
	int sum(int lx,int rx,int ly,int ry) {
		return read(rx,ry)-read(rx,ly-1)-read(lx-1,ry)+read(lx-1,ly-1);
	}
}
