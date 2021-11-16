package finalproject;

import java.util.*;
import java.io.*;


public class ChessSudoku
{
	/* SIZE is the size parameter of the Sudoku puzzle, and N is the square of the size.  For 
	 * a standard Sudoku puzzle, SIZE is 3 and N is 9. 
	 */
	public int SIZE, N;

	/* The grid contains all the numbers in the Sudoku puzzle.  Numbers which have
	 * not yet been revealed are stored as 0. 
	 */
	public int grid[][];

	/* Booleans indicating whether of not one or more of the chess rules should be 
	 * applied to this Sudoku. 
	 */
	public boolean knightRule;
	public boolean kingRule;
	public boolean queenRule;

	
	// Field that stores the same Sudoku puzzle solved in all possible ways
	public HashSet<ChessSudoku> solutions = new HashSet<ChessSudoku>();
	

	/* The solve() method should remove all the unknown characters ('x') in the grid
	 * and replace them with the numbers in the correct range that satisfy the constraints
	 * of the Sudoku puzzle. If true is provided as input, the method should find finds ALL 
	 * possible solutions and store them in the field named solutions. */
	public void solve(boolean allSolutions) {		
		this.solveHelper1(allSolutions);
	}

private boolean solveHelper1(boolean allSolutions) {
	int[] coords = this.find_easy();
	
	if (coords == null ){
		ChessSudoku copiedChessSudoku = new ChessSudoku(SIZE); // making a new chessSoduko object to copy the grid to
		copiedChessSudoku.kingRule = this.kingRule;  // setting the same parameters as the original
		copiedChessSudoku.knightRule = this.knightRule;
		copiedChessSudoku.queenRule = this.queenRule;
		int[][] copygrid = new int[N][N];
		for (int r = 0; r<N; r++) {
			for (int c = 0; c < N; c++) {
				copiedChessSudoku.grid[r][c] = this.grid[r][c];
			}
		}
		solutions.add(copiedChessSudoku);
		if (!allSolutions) {
			return true;
		}
		return false;
		
	}
	else{
		int row = coords[0];
		int column = coords[1];
		for (int n = 1 ; n <= N; n++) {
			if (checkStandard(n, row, column)&& checkKnightRule(n, row, column, knightRule)&& checkKingRule(n, row, column, kingRule)&&checkQueenRule(n, row, column, queenRule)){
				grid[row][column] = n;
				if(this.solveHelper1(allSolutions)){
					return true;
				}
				else{
					grid[row][column]= 0;
				}
			}
		}
	}
	
	return false;
}
	
	private int[] find_easy(){
		//add the checks for the boxes
		int[] returncoords = new int[2];
		int[] zeros = new int[3*N];
		int rowcol = -1;
		int smallestint = 2*N;
		for (int i = 0; i < this.N; i++) {
			for (int j = 0; j < this.N; j++) {
				if(grid[i][j] == 0) {
					zeros[i] += 1;
					zeros[j+N] += 1;
					int row = i/SIZE;
					int col = j/SIZE;
					int box = (row*SIZE)+ col;
					zeros[2*N+box] +=1;
					
				}
				}
		}
		
		for (int k=0; k<zeros.length;k++) {
			if(zeros[k]< smallestint && zeros[k]>0) {
				smallestint = zeros[k];
				rowcol = k;
			}
		}

		
		if (rowcol<N && rowcol>=0) { //row has smallest num of zeros
			for(int r = 0; r<N;r++) {
				if (grid[rowcol][r]==0) {
					returncoords[0]= rowcol;
					returncoords[1] = r;
					return returncoords;
				}
			}
		}
		if(rowcol>=N && rowcol<(2*N)) {
			int rowcol1 = rowcol-N;
			for(int c = 0; c<N;c++) {
				if (grid[c][rowcol1]==0) {
					returncoords[0]= c;
					returncoords[1] = rowcol1;
					return returncoords;
				}
			}
		if(rowcol>=(2*N)) {
				int rowcol2 = rowcol-(2*N);
				int row = rowcol2/SIZE;
				int col = rowcol2-(row*SIZE);
				for (int i=row; i<row+SIZE; i++) {
					for(int j = col;col<col+SIZE; j++) {
						if (grid[i][j]==0) {
							returncoords[0]= i;
							returncoords[1] = j;
							return returncoords;
						}
					}
				}
			}
			
		}
		return null;
		
	}
	
	private int[] find_empty(){ 
		int[] coord = new int[2];
		for (int i = 0; i < this.N; i++) {
			for (int j = 0; j < this.N; j++) {
				if(grid[i][j] == 0) {
					coord[0] = i;
					coord[1] = j;
			
					return coord;
				}
			}
		}
		return null;
	}
	
	private boolean checkStandard(int value,int row, int column){ 
		for (int x = 0; x < N; x++) { //loops through each column of a specified row
			
			if (grid[row][x] == value && x != column) {
				return false;
			}
		}	
		for (int y = 0; y < this.N; y++) {
			
			if (grid[y][column] == value && y != row) {
				return false;
			}
		}
	  int row_start = (row/SIZE)*SIZE;
      int col_start = (column/SIZE)*SIZE;
        for(int k=row_start;k<row_start+SIZE;k++){
            for(int j=col_start;j<col_start+SIZE;j++){
            	
            	

            	if(grid[k][j]== value && j != column && k!= row) {
                    return false;
                }
            }
        }	
			
		return true;	
	}
	

	private boolean checkKnightRule(int value, int row, int column, boolean use){
		if(!use) {
			return true;
		}
		else {
			int[] rowCheck = {2,2,-2,-2,1,1,-1,-1};
			int[] columnCheck = {1,-1,1,-1,2,-2,2,-2};
			for(int i = 0; i<rowCheck.length;i++) {
			int newRow = row+rowCheck[i];
			int newColumn = column+columnCheck[i];
			if(newRow>=0 && newRow< N && newColumn>=0 && newColumn<N) {
					if(grid[newRow][newColumn] == value) {
					return false;
				}
				}
			}
		}
		return true;
	}
	
	
	private boolean checkKingRule(int value, int row, int column, boolean use){
		if(!use) {
			return true;
		}
		else {
			int[] rowCheck = {1,-1,1,-1};
			int[] columnCheck = {1,1,-1,-1};
			for(int i = 0; i<rowCheck.length;i++) {
			int newRow = row+rowCheck[i];
			int newColumn = column+columnCheck[i];
			if(newRow>=0 && newRow< N && newColumn>=0 && newColumn<N) {
					if(grid[newRow][newColumn] == value) {
					return false;
				}
				}
			}
		}
		return true;
	}
	
	private boolean checkQueenRule(int value, int row, int column, boolean use){
		if(!use) {
			return true;
		}
		else {
			if (value!=N) {
				return true;
			}
			int[] rowCheck = {1,-1,1,-1};
			int[] columnCheck = {1,1,-1,-1};
			for(int i = 1; i<N;i++) {
				for (int j = 0; j<rowCheck.length;j++) {
				int newRow = row+(i*rowCheck[j]);
				int newColumn = column+(i * columnCheck[j]);
					if(newRow>=0 && newRow< N && newColumn>=0 && newColumn<N) {
						if(grid[newRow][newColumn] == value) {
						return false;
					}
				}
			}
		}
		}
		return true;
	}
	/*****************************************************************************/
	/* NOTE: YOU SHOULD NOT HAVE TO MODIFY ANY OF THE METHODS BELOW THIS LINE. */
	/*****************************************************************************/

	/* Default constructor.  This will initialize all positions to the default 0
	 * value.  Use the read() function to load the Sudoku puzzle from a file or
	 * the standard input. */
	public ChessSudoku( int size ) {
		SIZE = size;
		N = size*size;

		grid = new int[N][N];
		for( int i = 0; i < N; i++ ) 
			for( int j = 0; j < N; j++ ) 
				grid[i][j] = 0;
	}


	/* readInteger is a helper function for the reading of the input file.  It reads
	 * words until it finds one that represents an integer. For convenience, it will also
	 * recognize the string "x" as equivalent to "0". */
	static int readInteger( InputStream in ) throws Exception {
		int result = 0;
		boolean success = false;

		while( !success ) {
			String word = readWord( in );

			try {
				result = Integer.parseInt( word );
				success = true;
			} catch( Exception e ) {
				// Convert 'x' words into 0's
				if( word.compareTo("x") == 0 ) {
					result = 0;
					success = true;
				}
				// Ignore all other words that are not integers
			}
		}

		return result;
	}


	/* readWord is a helper function that reads a word separated by white space. */
	static String readWord( InputStream in ) throws Exception {
		StringBuffer result = new StringBuffer();
		int currentChar = in.read();
		String whiteSpace = " \t\r\n";
		// Ignore any leading white space
		while( whiteSpace.indexOf(currentChar) > -1 ) {
			currentChar = in.read();
		}

		// Read all characters until you reach white space
		while( whiteSpace.indexOf(currentChar) == -1 ) {
			result.append( (char) currentChar );
			currentChar = in.read();
		}
		return result.toString();
	}


	/* This function reads a Sudoku puzzle from the input stream in.  The Sudoku
	 * grid is filled in one row at at time, from left to right.  All non-valid
	 * characters are ignored by this function and may be used in the Sudoku file
	 * to increase its legibility. */
	public void read( InputStream in ) throws Exception {
		for( int i = 0; i < N; i++ ) {
			for( int j = 0; j < N; j++ ) {
				grid[i][j] = readInteger( in );
			}
		}
	}


	/* Helper function for the printing of Sudoku puzzle.  This function will print
	 * out text, preceded by enough ' ' characters to make sure that the printint out
	 * takes at least width characters.  */
	void printFixedWidth( String text, int width ) {
		for( int i = 0; i < width - text.length(); i++ )
			System.out.print( " " );
		System.out.print( text );
	}


	/* The print() function outputs the Sudoku grid to the standard output, using
	 * a bit of extra formatting to make the result clearly readable. */
	public void print() {
		// Compute the number of digits necessary to print out each number in the Sudoku puzzle
		int digits = (int) Math.floor(Math.log(N) / Math.log(10)) + 1;

		// Create a dashed line to separate the boxes 
		int lineLength = (digits + 1) * N + 2 * SIZE - 3;
		StringBuffer line = new StringBuffer();
		for( int lineInit = 0; lineInit < lineLength; lineInit++ )
			line.append('-');

		// Go through the grid, printing out its values separated by spaces
		for( int i = 0; i < N; i++ ) {
			for( int j = 0; j < N; j++ ) {
				printFixedWidth( String.valueOf( grid[i][j] ), digits );
				// Print the vertical lines between boxes 
				if( (j < N-1) && ((j+1) % SIZE == 0) )
					System.out.print( " |" );
				System.out.print( " " );
			}
			System.out.println();

			// Print the horizontal line between boxes
			if( (i < N-1) && ((i+1) % SIZE == 0) )
				System.out.println( line.toString() );
		}
	}


	/* The main function reads in a Sudoku puzzle from the standard input, 
	 * unless a file name is provided as a run-time argument, in which case the
	 * Sudoku puzzle is loaded from that file.  It then solves the puzzle, and
	 * outputs the completed puzzle to the standard output. */
	public static void main( String args[] ) throws Exception {
		InputStream in = new FileInputStream("/Users/MassimoLaptop/Desktop/eclipse projects/COMP-250_Final/src/finalproject/veryHard3x3.txt");

		// The first number in all Sudoku files must represent the size of the puzzle.  See
		// the example files for the file format.
		int puzzleSize = readInteger( in );
		if( puzzleSize > 100 || puzzleSize < 1 ) {
			System.out.println("Error: The Sudoku puzzle size must be between 1 and 100.");
			System.exit(-1);
		}

		ChessSudoku s = new ChessSudoku( puzzleSize );
		
		// You can modify these to add rules to your sudoku
		s.knightRule = false;
		s.kingRule = false;
		s.queenRule = false;
		
		// read the rest of the Sudoku puzzle
		s.read( in );

		System.out.println("Before the solve:");
		s.print();
		System.out.println();

		// Solve the puzzle by finding one solution.
		s.solve(false);

		// Print out the (hopefully completed!) puzzle
		System.out.println("After the solve:");
		//s.print();
		
		for (ChessSudoku chess : s.solutions) {
			chess.print();
			System.out.println();
		}
		//System.out.println(s.solutions);
		
	}
}

