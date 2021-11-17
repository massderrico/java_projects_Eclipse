
public class Quiz0 {
	public static void main(String[] args) {
		mystery1(16);
	}
	
	
	//question 9 
	public static int mystery(int a) {
		int b =0;
		while (a>0) {
			b = b*10 + a%10;
			a = a/10;
		}
		return b;
	}
	 //question 10
	public static void mystery1(int n) {
		int i = 0;
		int exp =1; 
		while (exp < n) {
			i ++;
			exp = exp*2;
		}
		System.out.println(i);
	}
	

}
