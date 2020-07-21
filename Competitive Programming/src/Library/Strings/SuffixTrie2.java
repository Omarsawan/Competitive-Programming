package Library.Strings;


public class SuffixTrie2 {
	//implementation with arrays with static size
	
	static int alpha=26;//26 letters of alphabet
	static int lastTrieNode=0;
	static int[][]next;
	static int root;
	
	static int[]vals;//any array that specify the attributes of some node
	
	static void preProcess(int maxSize){//maxSize is the max number of trie nodes that can be made
		next=new int[maxSize][];
		vals=new int[maxSize];
		root=makeNode();
	}
	
	static int makeNode() {
		int idx=lastTrieNode++;
		next[idx]=new int[alpha];
		return idx;
	}
	
	
	static int insert(String s)// O(n) where n = |s|. Can be implemented recursively
	{
		int cur=root;
		for(int i=0;i<s.length();i++) {
			char c=s.charAt(i);
			int nxt = next[cur][c-'0'];
			if(nxt == 0) {
				int idx=makeNode();
				nxt = next[cur][c - '0'] = idx;
			}
			cur = nxt;
		}
		
		
		return cur;
	}
	
	int search(String s)
	{
		int cur = root;
		for(int i=0;i<s.length();i++) {
			char c=s.charAt(i);
			int nxt = next[cur][c-'0'];
			if(nxt == 0) {
				return -1;
			}
			cur = nxt;
		}
		return vals[cur];
	}
}
