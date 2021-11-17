package E1_2;
import java.util.Arrays;
import java.util.Iterator;
/**
 * 
 * @author Massimo D'Errico 260949564
 *
 */
import java.util.Random;



public class Exercise1_2 {

	

		
	
	
	
	
	public static void main(String[] args) {
		int[][] a = {{2,3},{5,1}};
		int[][] b = {{-1,5},{2,-4}};
		int[] c = {1,2};
		int[] d = {10,6};
		//System.out.print(charRightShift('z', 1));
		//System.out.println(checkPass("Hello"));
		//System.out.println(checkVowel("hello", 0));
		//System.out.println(isUpperCase('C'));
		//System.out.println(countUpper("HeLLo"));
		//System.out.println(largestValue(a));
		//System.out.print(Arrays.toString(firstNPrime(2)));
		//System.out.println(Arrays.toString(rightShift(a)));
		//System.out.println(Arrays.toString(show_intersect(a, b)));
		//System.out.print(isMatrix(c));
		//System.out.println(Arrays.toString(getColumn(c, 0)))
		//System.out.println(Arrays.deepToString(sumMatrix(a, b))); 
		//System.out.println(dotProduct(c, d));
		//System.out.println(Arrays.deepToString(multiplyMatrix(a,b)));
		//RandInt();
		//System.out.println("");
		//RandIntSeed(123);
		char[] ch = {'A','C','w'};
		System.out.println(toLowerCaseStrings("heLLO"));
		System.out.println(Arrays.toString(toLowerCaseArrays(ch)));
		
	}
	
	// Primitive Data Types and Strings
	 // 1.1
    public static char charRightShift(char c, int shift) {
        if (c < 'a' || c > 'z') {
            return c;
        }

        int pos = c - 'a';
        if (shift < 0) {
            shift = 26 - ((shift * -1) % 26);
        }

        int newPos = (pos + shift) % 26;

        return (char)(c + newPos);
    }
	
    //1.2
    public static boolean checkPass(String pass) {
		String password = "Hello";
    	return pass.equals(password);
    }
    
    //1.3
    public static boolean checkVowel(String s, int i) {
    	char c = s.charAt(i);
    	if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
    		return true;
    	}
    	return false;	
    }
    
    //1.4
    public static boolean isUpperCase(char c ) {
    	return (c >= 'A' && c<= 'Z');
    }
    
    //1.5
    public static int countUpper(String word) {
    	int count = 0;
    	for (int i = 0; i < word.length();i++) {
    		char c = word.charAt(i);
    		if (c >= 'A' && c<= 'Z') {
    			count ++;
    		}
    	}
    	return count;
    }
    
    //Arrays 
    //2.1
    
    public static int largestValue(int[] a) {
    	int largestnum = 0;
    	for (int i = 0; i < a.length; i++) {
    		if (a[i] >= largestnum){
    			largestnum = a[i];
    		}
    	}
    	return largestnum;
    }
    
    //2.2
    public static int[] firstNPrime(int n) {
        //create an array with n elements
        int[] primes = new int[n];
        // declare and initialize a variable to store the numbers
        // and one to count how many primes we have found
        int number = 2;
        int count = 0;
        // loop until we found n primes
        while(count<n) {
            // if number is prime we store it inside the array
            if(isPrime(number)) {
                primes[count] = number;
                count++;
            }
            number++;
        }
        return primes;

    }

    //helper function to test prime number
    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        int d = 2;
        while (d<=Math.sqrt(n)) {
            if (n%d == 0) {
                return false;
            }
            d++;
        }
        return true;
    }
    
   //2.3
    public static int[] rightShift(int[] a) {
    	int[] arrayint = new int[a.length];
    	arrayint[0] = a[a.length-1];
    	for (int i=0; i< (a.length-1); i++) {
    		arrayint[i+1] = a[i];
    	}
    	return arrayint;
    }
    
    //2.4
    public static void rightShift2(int[] a) {
    	int temp = a[a.length-1];
    	for (int i=(a.length-1);i >0; i--) {
    		a[i]= a[i-1];
    	}
    	a[0]= temp;
    }
    
    //2.5
	 public static int[] show_intersect(int[] a, int[] b) {
	  //first figure out the size of the intersection
	  // that is, how many elements the two arrays have in common
	  int size = 0;
	  for(int i=0; i<a.length; i++) {
	   for (int j=0; j<b.length; j++) {
	    if (a[i] == b[j])
	     size++;
	   }
	  }
	        // now populate the array representing the intersection
	  int[] intersection = new int[size];
	  int index = 0;
	  for(int i =0; i<a.length; i++) {
	   for (int j=0; j<b.length; j++) {
	    if (a[i] == b[j]) {
	     intersection[index] = a[i];
	     index++;
	    }
	   }
	  }
	  return intersection;
	 }

	 //2.6
	 public static boolean isMatrix(int[][] a){
		for(int i=1; i< a.length;i++) {
			if (a[i].length != a[i-1].length) {
				return false;
			}
		}
		return true;
	 }
	 
	 //2.7
	 public static int[] getColumn (int [][] a, int n){
		int[] columm = new int[a.length];
		for (int i=0; i< a.length;i++) {
			columm[i] = a[i][n];
		}
		return columm;
	}
	//2.8
	public static int[][] sumMatrix(int [][] a, int[][] b) {
		int[][] summedMatrix = new int[a.length][a[0].length];
		for (int i=0; i< a.length;i++) {
			for (int j=0; j< a[0].length;j++) {
				summedMatrix[i][j] = a[i][j] + b[i][j];
			}
		}
		return summedMatrix;
	}
	 
	public static int dotProduct(int[] a, int[] b) {
		int product = 0;
		if (a.length != b.length){
			throw new IllegalArgumentException("different length arrays");
		}
		else {
			 for(int i= 0; i< a.length; i++) {
				 product += a[i]*b[i];
			 }
		return product;
		}
	}
	
	public static int[][] multiplyMatrix(int[][] a, int[][] b){
		int aRow = a.length;
		int aCol = a[0].length;
		int bRow = b.length;
		int bCol = b[0].length;
		if (!isMatrix(a) || !isMatrix(b) || (aCol != bRow) ) {
			throw new IllegalArgumentException("cannot multiply");
			}
		int[][] mulMatrix = new int[aRow][bCol];
		for(int i =0;i<aRow;i++) {
			for (int j=0; j< bCol;j++) {
				int[] columnj= getColumn(b, j);
				mulMatrix[i][j]= dotProduct(a[i], columnj);
			}
			}	
		
		return mulMatrix;
		
	}
	
	//Reference types and Random
	//3.1
	public static void RandInt(){
		Random random = new Random();
		for (int i= 0; i<10;i++) {
			System.out.println(random.nextInt(51));
		}
	}
	
	public static void RandIntSeed(int seed){
		Random random = new Random(seed);
		for (int i= 0; i<10;i++) {
			System.out.println(random.nextInt(51));
		}
	}
	
	//3.2
	public static String toLowerCaseStrings(String s) {
		String lower = "";
		for (int i =0; i<s.length();i++){
			char letter = s.charAt(i);
			if (letter >= 'A' && letter <= 'Z') {
				int diff = 'a'-'A';
				letter = (char)(letter+diff);
				lower = lower+ letter;
			}
			else {
				lower = lower +letter;
			}
		}
		return lower;
	}
	
	//3.3
	public static char[] toLowerCaseArrays(char[] c) {
		int diff = 'a'-'A';
		for (int i =0; i<c.length;i++){
			if (c[i] >= 'A' && c[i] <= 'Z') {
				c[i]= (char)(c[i]+diff);
			}
		}
		return c;
	}
	
	
}
