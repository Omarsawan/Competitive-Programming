package Library;

public class fft {
	static int maxn=1048577,MOD=1009;
	static double[] wR = new double[maxn],
            wI = new double[maxn],
            rR = new double[maxn],
            rI = new double[maxn],
            aR = new double[maxn],
            aI = new double[maxn];
	static int n, k, lastk = -1, dp[] = new int[maxn];
 
    static void fft(boolean inv) {
        if (lastk != k) {
            lastk = k;
            dp[0] = 0;
            for (int i = 1, g = -1; i < n; i++) {
                if ((i & (i - 1)) == 0) {
                    g++;
                }
                dp[i] = dp[i ^ (1 << g)] ^ (1 << (k - 1 - g));
            }
            wR[1] = 1;
            wI[1] = 0;
            for (int t = 0; t < k - 1; t++) {
                double a = Math.PI / n * (1 << (k - 1 - t));
                double curR = Math.cos(a), curI = Math.sin(a);
                int p2 = (1 << t), p3 = p2 * 2;
                for (int j = p2, k = j * 2; j < p3; j++, k += 2) {
                    wR[k] = wR[j];
                    wI[k] = wI[j];
                    wR[k + 1] = wR[j] * curR - wI[j] * curI;
                    wI[k + 1] = wR[j] * curI + wI[j] * curR;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            int d = dp[i];
            if (i < d) {
                double tmp = aR[i];
                aR[i] = aR[d];
                aR[d] = tmp;
                tmp = aI[i];
                aI[i] = aI[d];
                aI[d] = tmp;
            }
        }
        if (inv) {
            for (int i = 0; i < n; i++) {
                aI[i] = -aI[i];
            }
        }
        for (int len = 1; len < n; len <<= 1) {
            for (int i = 0; i < n; i += len) {
                int wit = len;
                for (int it = 0, j = i + len; it < len; it++, i++, j++) {
                    double tmpR = aR[j] * wR[wit] - aI[j] * wI[wit];
                    double tmpI = aR[j] * wI[wit] + aI[j] * wR[wit];
                    wit++;
                    aR[j] = aR[i] - tmpR;
                    aI[j] = aI[i] - tmpI;
                    aR[i] += tmpR;
                    aI[i] += tmpI;
                }
            }
        }
    }
    //take 2 polynomials a , b and return the result of multiplication of the 2 polynomials in O(n*log(n))
    static long[] multiply(long[] a, long[] b) {
        int na = a.length, nb = b.length;
        for (k = 0, n = 1; n < na + nb - 1; n <<= 1, k++) {
        }
        for (int i = 0; i < n; ++i) {
            aR[i] = i < na ? a[i] : 0;
            aI[i] = i < nb ? b[i] : 0;
        }
        fft(false);
        aR[n] = aR[0];
        aI[n] = aI[0];
        double q = -1.0 / n / 4.0;
        for (int i = 0; i <= n - i; ++i) {
            double tmpR = aR[i] * aR[i] - aI[i] * aI[i];
            double tmpI = aR[i] * aI[i] * 2;
            tmpR -= aR[n - i] * aR[n - i] - aI[n - i] * aI[n - i];
            tmpI -= aR[n - i] * aI[n - i] * -2;
            aR[i] = -tmpI * q;
            aI[i] = tmpR * q;
            aR[n - i] = aR[i];
            aI[n - i] = -aI[i];
        }
	    fft(true);
	    long[] ans = new long[n = na + nb - 1]; // ONLY MOD IF NEEDED
		for (int i = 0; i < n; i++) {
		    ans[i] = Math.round(aR[i])%MOD;
		}
		return ans;
	}
}
