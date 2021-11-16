
public class TicTacToeBoard {
	
	String[] board = new String[9];
	
	
	public static void main(String[] args) {
		
	}
	
	
	public void toPrint() {
		String b = "";
		for(int i =0; i<9; i++) {
			if((i+1)%3 != 0) {
				b += board[i]+ "|";
			}
			else {
				b+="\n";
			}
			if((i+1)%3 == 0) {
				
			}
		
		}
		
	}
}
