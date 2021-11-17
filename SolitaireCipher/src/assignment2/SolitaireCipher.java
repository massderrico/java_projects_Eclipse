package assignment2;


public class SolitaireCipher {
	public Deck key;
	
	public SolitaireCipher (Deck key) {
		this.key = new Deck(key); // deep copy of the deck
	}
	
	/* 
	 * TODO: Generates a keystream of the given size
	 */
	public int[] getKeystream(int size) {
		int[] keyStream = new int[size];
		for (int i = 0; i <size; i++) {
			keyStream[i] = this.key.generateNextKeystreamValue();
		}
		return keyStream; 
	}
		
	/* 
	 * TODO: Encodes the input message using the algorithm described in the pdf.
	 */
	public String encode(String msg) {
		String encoded = "";
		String encodedCipher = "";
		
		for (int i = 0; i < msg.length(); i++){
		    char c = msg.charAt(i); 
		    if ((c >= 'A' && c <= 'Z')||(c >= 'a' && c<= 'z')) {
		    	encoded += c;
		    }
		}
	    encoded = encoded.toUpperCase();
	    
	    int[] keystream = this.getKeystream(msg.length());  /// A   --->65  -64 == 1 
	    for (int i = 0; i < encoded.length(); i++){
		    char ch = encoded.charAt(i);
		    int shifted = ch+ (keystream[i]%26);
		    if (shifted > 'Z') {
		    	shifted = shifted - 26 ;
		    }
		    encodedCipher+= (char)shifted;
		}
		
		return encodedCipher; 
	}
	
	/* 
	 * TODO: Decodes the input message using the algorithm described in the pdf.
	 */
	public String decode(String msg) {
		String decoded = "";
		int[] keystream = this.getKeystream(msg.length());
		for (int i = 0; i < msg.length(); i++){
			char c = msg.charAt(i); 
			int shiftedDown = c - (keystream[i]%26);
			if(shiftedDown < 'A') {
				shiftedDown = shiftedDown+26;
			}
			decoded += (char)shiftedDown;
		}
		return decoded;
	}
	
}
