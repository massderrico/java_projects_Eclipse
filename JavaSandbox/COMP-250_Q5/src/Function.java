
public class Function {
	
	public static void main(String[] args) {
		System.out.println(f(3,4));
	}
	
	// 2^x + y-1
	public static int f(int x, int y) {
		if (x == 0)
			return y;
		return 2 *f(x-1, 1) + (f(0,y))/y;
	}
}
