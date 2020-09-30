package Library.DataStructures;

import java.util.Random;


//for reversing sub-array and query range sum
public class treap
{
	treap(int[]in,int n){
		for(int i=0;i<n;i++) {
			insert(in[i]);
		}
	}
	class Node
	{
		Node left, right;
		int prior, size;
		
		long val,sum;
		int reversed;
		
		Node(int p, int s, Node l, Node r,long v)
		{
			prior = p;
			size = s;
			left = l;
			right = r;
			val=v;
		}
		Node(Node l,Node r) {
			left=l;
			right=r;
		}
	}
	Random rand = new Random();
	Node nill = new Node(Integer.MAX_VALUE, 0, null, null,0);
	Node curRoot = nill;
	
	
	Node meld(Node small,Node big) {
		if(small==nill) {
			propagate(big);
			update(big);
			return big;
		}
		if(big==nill) {
			propagate(small);
			update(small);
			return small;
		}
		propagate(small);
		propagate(big);
		if(small.prior<big.prior) {
			small.right=meld(small.right, big);
			update(small);
			return small;
		}
		big.left=meld(small, big.left);
		update(big);
		return big;
	}
	
	Node split(Node root,int key) {
		if(root==nill)return new Node(nill,nill);
		propagate(root);
		Node ans;
		int cntLeft=root.left.size;
		if(key<=cntLeft) {
			ans = split(root.left, key);
            root.left = ans.right;
            ans.right = root;
		}
		else {
			ans = split(root.right, key-1-cntLeft);
            root.right = ans.left;
            ans.left = root;
		}
		update(root);
		return ans;
	}
	
	int size() { return curRoot.size; }
	
	void update(Node root)
	{
		if(root != nill) {
			root.size = root.left.size + 1 + root.right.size;
			root.sum = root.left.sum + root.val + root.right.sum;
		}
	}
	
	
	void propagate(Node root)
	{
		if(root != nill && root.reversed==1) {
			root.reversed^=1;
			Node l=root.left;
			Node r=root.right;
			
			root.left=r;
			root.right=l;
			root.left.reversed^=1;
			root.right.reversed^=1;
			
		}
	}
	//insert value to the end of the array
	void insert(long vals) {
		curRoot=balance(meld(curRoot, new Node(rand.nextInt(1<<30), 1, nill, nill,vals)));
	}
	Node balance(Node root)
	{
		if(root.left.prior < root.prior)
			return rotateRight(root);
		if(root.right.prior < root.prior)
			return rotateLeft(root);
		return root;
	}
	Node rotateLeft(Node root)
	{
		Node T = root.right;
		root.right = T.left;
		T.left = root;
		update(T.left);
		update(T);
		return T;
	}
	
	Node rotateRight(Node root)
	{
		Node T = root.left;
		root.left = T.right;
		T.right = root;
		update(T.right);
		update(T);
		return T;
	}
	
	long query(int l,int r) {//0-based indexed
		Node rev1=split(curRoot, l);
		Node A=rev1.left;
		
		Node rev2=split(rev1.right, r-l+1);
		
		Node B=rev2.left;
		Node C=rev2.right;
		
		long ans=B.sum;
		
		curRoot=balance(meld(A, meld(B, C)));
		
		return ans;
	}
	void reverse(int l,int r) {//0-based indexed
		Node rev1=split(curRoot, l);
		Node A=rev1.left;
		
		Node rev2=split(rev1.right, r-l+1);
		
		Node B=rev2.left;
		Node C=rev2.right;
		
		B.reversed^=1;
		
		curRoot=balance(meld(A, meld(B, C)));
	}
}
