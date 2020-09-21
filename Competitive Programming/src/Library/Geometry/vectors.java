package Library.Geometry;

public class vectors {
	static long[] vector(long[]point1,long[]point2) {
		return new long[] {point2[0]-point1[0],point2[1]-point1[1]};
	}
	static long dotProduct(long[]v1,long[]v2) {
		return v1[0]*v2[0]+v1[1]*v2[1];
	}
	static long crossProduct(long[]v1,long[]v2) {
		return v1[0]*v2[1]-v1[1]*v2[0];
	}
	static boolean leftOrParrallel(long[]v,long[]vec) {
		return crossProduct(vec, v)>0 || (crossProduct(vec, v)==0 && dotProduct(vec, v)>0);
	}
	//comparator for sorting vectors anti-clockwise from a constant vector (vec)
	//Arrays.sort(arrayOfVectors,(x,y)->compare(x,y,vec));
	static int compare(long[]v1,long[]v2,long[]vec) {
		boolean v1Left=leftOrParrallel(v1, vec),v2Left=leftOrParrallel(v2, vec);
		if(v1Left!=v2Left) {
			return v1Left?-1:1;
		}
		long cp=crossProduct(v1, v2);
		if(cp>0) {
			return -1;
		}
		if(cp<0) {
			return 1;
		}
		return 0;
	}
}
