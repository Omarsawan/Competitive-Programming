package Library.RangeMinimumQuery;

public class SegmentTree2D {
	static int INF=(int)2e9;
	int n, m, min[][], a[][];
	 
	SegmentTree2D(int[][] a) {
		this.a = a;
		n = a.length;
		m = a[0].length;
		min = new int[4 * n][4 * m];
		build_x(1, 0, n - 1);
	}

	void build_y(int vx, int lx, int rx, int vy, int ly, int ry) {
		if (ly == ry) {
			if (lx == rx)
				min[vx][vy] = a[lx][ly];
			else
				min[vx][vy] = Math.min(min[vx * 2][vy], min[vx * 2 + 1][vy]);
		} else {
			int my = (ly + ry) / 2;
			build_y(vx, lx, rx, vy * 2, ly, my);
			build_y(vx, lx, rx, vy * 2 + 1, my + 1, ry);
			min[vx][vy] = Math.min(min[vx][vy * 2], min[vx][vy * 2 + 1]);
		}
	}

	void build_x(int vx, int lx, int rx) {
		if (lx != rx) {
			int mx = (lx + rx) / 2;
			build_x(vx * 2, lx, mx);
			build_x(vx * 2 + 1, mx + 1, rx);
		}
		build_y(vx, lx, rx, 1, 0, m - 1);
	}

	int query(int lN, int rN, int lM, int rM) {
		return query(1, 0, n - 1, lN, rN, lM, rM);
	}

	int query(int node, int tl, int tr, int lN, int rN, int lM, int rM) {
		if (rN < tl || tr < lN)
			return INF;
		if (tl >= lN && tr <= rN) {
			return query(node, 1, 0, m - 1, lM, rM);
		}
		int mid = tl + tr >> 1, left = node << 1, right = left | 1;
		return Math.min(query(left, tl, mid, lN, rN, lM, rM), query(right, mid + 1, tr, lN, rN, lM, rM));

	}

	int query(int nodeN, int node, int tl, int tr, int l, int r) {
		if (r < tl || tr < l)
			return INF;
		if (tl >= l && tr <= r)
			return min[nodeN][node];
		int mid = tl + tr >> 1, left = node << 1, right = left | 1;
		return Math.min(query(nodeN, left, tl, mid, l, r), query(nodeN, right, mid + 1, tr, l, r));
	}
}
