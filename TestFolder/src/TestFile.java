



public class TestFile {
	
	
	
	
	
	
	
	public static void main(String[] args) {
		String word = "Samuel";
		
		String word1 = "";
		
		for (int i = word.length()-1; i >= 1; i--) {
			word1 += word.charAt(i);
			//System.out.println(word1);
		}
		
		for(int i = word1.length(); i >= 0; i-- ) {
			if (i%2 == 1) {
				System.out.println(word1.substring(0, i).toUpperCase());
			}
			else{
				System.out.println(word1.substring(0, i).toLowerCase());
			}
		}
	}

}
