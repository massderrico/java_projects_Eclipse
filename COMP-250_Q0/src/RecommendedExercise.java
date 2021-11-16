
public class RecommendedExercise {
	
	static int[] c = {1,2,3,4,5,6,7,8,9,10,30};
	static int[] d = {3,4,6,8,10,5,15,20,22,24};
	
	public static void main(String[] args) {
		System.out.println(getIntersectionSize(d, c));
	}

	public static int getIntersectionSize(int[] a, int[] b) {
		int count = 0;
		for (int i=0; i < a.length; i++) {
			for (int j=0; j < b.length; j++) {
				if(a[i] == b[j]) {
					count ++;
				}
			}
		}
		return count;
	}
}
