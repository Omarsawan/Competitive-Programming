package Library.Strings;

public class prefixFunction {
	static int[] prefixFunction(String in) {
		char[]s=in.toCharArray();
		int len =s.length;
	    int[]pi=new int[len];
	    for (int i = 1; i < len; i++) {
	        int j = pi[i-1];
	        while (j > 0 && s[i] != s[j])
	            j = pi[j-1];
	        if (s[i] == s[j])
	            j++;
	        pi[i] = j;
	    }
	    return pi;
	}
}
