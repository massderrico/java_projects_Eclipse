import java.util.Iterator;
import java.util.Scanner;



public class testclass {

	
	public static void main(String[] args) {
		Scanner key = new Scanner(System.in);
		System.out.println();
		// TODO Auto-generated method stub
		String str = key.next();
		
		
		
		for(int i = str.length()-1; i >= 0; i--) {
			char[] arr  = new char[str.length()];
			
			for(int j = 0; j < arr.length; j++) {
				arr[j] = ' ';
			}
			
			char e = str.charAt(i);
			//System.out.println(e);
			
			arr[i] = e;
			arr[str.length() - 1 - i] = e;
			System.out.println(arr);
			
		}
		
		
	}

}
