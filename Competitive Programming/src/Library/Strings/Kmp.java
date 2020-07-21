package Library.Strings;

public class Kmp {
	static char[]T;//the whole text
	static char[]P;//the pattern that need to be found in the text
	static int n, m;
	static int[]b;
	
	static void kmpPreprocess() {
		n=T.length;
		m=P.length;
		b=new int[m+1];
		int i = 0, j = -1; b[0] = -1;
		while (i < m) {
			while (j >= 0 && P[i] != P[j]) j = b[j];
			i++; j++;
			b[i] = j;
		} 
	}
	void kmpSearch() {
		int i = 0, j = 0;
		while (i < n) {
			while (j >= 0 && T[i] != P[j]) j = b[j];
			i++; j++;
			if (j == m) { // a match found when j == m at index i-j
				j = b[j]; 
			} 
		}
	}
}
