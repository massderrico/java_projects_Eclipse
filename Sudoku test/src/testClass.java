public class testClass {
    int SIZE = 3;
	int N = 9;
    boolean check;
	private static int[][] grid = {
            {9,5,0,2,7,3,8,0,9},
            {0,0,0,0,5,0,0,0,8},
            {9,8,6,1,0,4,0,5,7},
            {1,5,5,0,0,0,0,0,0},
            {4,0,0,0,0,0,0,0,2},
            {0,1,0,0,0,0,5,0,3},
            {5,7,8,3,0,1,0,2,6},
            {0,0,5,0,4,8,9,0,0},
            {0,9,0,6,2,5,0,8,9}
        };
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testClass t1 = new testClass();
		//System.out.println(grid[0][0]);
		//System.out.println(t1.checkColumn(0,6));
		System.out.println(t1.checkStandard(4, 5, 2));
		
		
	}
	
	
	private boolean checkStandard(int value,int row, int column){ 
//		for (int x = 0; x < N; x++) { //loops through each column of a specified row
//			System.out.println(grid[row][x]);
//			if (grid[row][x] == value && x != column) {
//				return false;
//			}
//		}	
//		for (int y = 0; y < this.N; y++) {
//			//System.out.println(grid[y][column]);
//			if (grid[y][column] == value && y != row) {
//				return false;
//			}
//		}
		
	  int row_start = (row/SIZE)*SIZE;
      int col_start = (column/SIZE)*SIZE;
        for(int k=row_start;k<row_start+SIZE;k++){
            for(int j=col_start;j<col_start+SIZE;j++){
            	
            	System.out.println(grid[k][j]);

            	if(grid[k][j]== value && j != column && k!= row) {
                    return false;
                }
            }
        }	
			
		return true;	
	}
//	
	
	
	
	
	private boolean checkRow(int row, int column){
		int value = grid[row][column];
		for (int i = 0; i < this.N; i++) {
			if (grid[row][i] == value && i != column) {
				return false;
			}
		}
		return true;
		
	}

	
	
	private boolean checkSquare(int value, int row, int column) {
		//int value = grid[row][column];

		int row_start = (row/3)*3;
        int col_start = (column/3)*3;
        for(int i=row_start;i<row_start+3;i++)
        {
            for(int j=col_start;j<col_start+3;j++)
            {
                if(grid[i][j]== value && i != row && j!= column)
                    return false;
            }
        }
		return true;
	}
	
	
	
	private boolean checkColumn(int row, int column){
		int value = grid[row][column];
		for (int i = 0; i < this.N; i++) {
			if (grid[i][column] == value && i != row) {
				return false;
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

//	
//	private boolean solveHelper(boolean allSolutions) {
//		int[] coords = this.find_empty();
//		
//		
//		if  (coords == null){
//			ChessSudoku copiedChessSudoku = new ChessSudoku(SIZE); // making a new chessSoduko object to copy the grid to
//			copiedChessSudoku.kingRule = this.kingRule;  // setting the same parameters as the original
//			copiedChessSudoku.knightRule = this.knightRule;
//			copiedChessSudoku.queenRule = this.queenRule;
//			int[][] copygrid = grid.clone();
//			copiedChessSudoku.grid = copygrid;
//			solutions.add(copiedChessSudoku);
//			return true;
//		}
//		else {
//			int row = coords[0];
//			int column = coords[1];
//			for (int n = 1 ; n <= N; n++) {
//				if (checkStandard(n, row, column)&& checkKnightRule(n, row, column, knightRule)&& checkKingRule(n, row, column, kingRule)&&checkQueenRule(n, row, column, queenRule)) {
//					grid[row][column] = n;
//					
//					if(this.solveHelper(allSolutions)){
//						return true;
//					}
//					else{
//						grid[row][column]= 0;
//					}
//				}
//			}
//		}
//		return false;
//		
//	}
	
	


}
