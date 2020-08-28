package Library.others;

//for convex hull where queries are of type double
public class liChaoTreeMin {
	treeNode root;
	double minN,maxN;
	liChaoTreeMin(double min,double max){
		root=new treeNode(new double[] {0,inf});
		minN=min;
		maxN=max;
	}
	
	static class treeNode{
		double[]val;
		treeNode left,right;
		treeNode(double[]vals){
			val=new double[] {vals[0],vals[1]};
		}
	}
	static double inf=1e19,EPS=1e-9;
	static double valueAt(double[]line,double x) {
		return line[0]*x+line[1];
	}
	void add_line(double[]newLine) {
		add_line(newLine, root,minN,maxN);
	}
	treeNode add_line(double[]newLine,treeNode node,double left,double right) {
		if(node==null) {
			node=new treeNode(new double[] {0,inf});
		}
	    double mid=(left+right)/2;
		int cur=0,nw=1;
	    int dominatingAtLeft = valueAt(newLine, left) < valueAt(node.val, left) ? cur : nw;
	    int dominatingAtMid = valueAt(newLine, mid) < valueAt(node.val, mid) ? cur : nw;
	    
	    if(dominatingAtMid!=nw) {
	    	double[]tmp=newLine.clone();
	    	newLine=node.val.clone();
	    	node.val=tmp.clone();
	    }
	    
	    if(newLine[1]>=inf || right-left<=EPS) {
	    	return node;
	    }
	    
	    if(dominatingAtLeft!=dominatingAtMid) {
	    	//intersection at left subtree
	    	node.left=add_line(newLine, node.left, left, mid);
	    	
	    }
	    else {
	    	//intersection at right subtree
	    	node.right=add_line(newLine, node.right, mid, right);
	    	
	    }
	    return node;
	}
	double get(double x) {
		return get(x, root, minN, maxN);
	}
	double get(double x, treeNode node, double left, double right) {
		if(node==null) {
			return inf;
		}
	    double mid=(left + right)/2;
	    if(right-left<=EPS) {
	        return valueAt(node.val, x);
	    }
	    if(x <= mid) {
	        return Math.min(valueAt(node.val, x), get(x, node.left, left, mid));
	    }
	    return Math.min(valueAt(node.val, x), get(x, node.right, mid, right));
	    
	}
	
}
