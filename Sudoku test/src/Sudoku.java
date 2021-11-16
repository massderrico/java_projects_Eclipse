class Sudoku
{

    private static final int SIZE = 9;
    private static int[][] matrix = {
        {6,5,0,8,7,3,0,9,0},
        {0,0,3,2,5,0,0,0,8},
        {9,8,0,1,0,4,3,5,7},
        {1,0,5,0,0,0,0,0,0},
        {4,0,0,0,0,0,0,0,2},
        {0,0,0,0,0,0,5,0,3},
        {5,7,8,3,0,1,0,2,6},
        {2,0,0,0,4,8,9,0,0},
        {0,9,0,6,2,5,0,8,1}
    };

    private static void printSudoku()
    {
        for(int i=0;i<SIZE;i++)
        {
            for(int j=0;j<SIZE;j++)
            {
                System.out.print(matrix[i][j]+"\t");
            }
            System.out.println("");
        }
    }

    //function to check if all cells are assigned or not
    //if there is any unassigned cell
    //then this function will change the values of
    //row and col accordingly
    private static int[] numberUnassigned(int row, int col)
    {
        int numunassign = 0;
        for(int i=0;i<SIZE;i++)
        {
            for(int j=0;j<SIZE;j++)
            {
                //cell is unassigned
                if(matrix[i][j] == 0)
                {
                    //changing the values of row and col
                    row = i;
                    col = j;
                    //there is one or more unassigned cells
                    numunassign = 1;
                    int[] a = {numunassign, row, col};
                    return a;
                }
            }
        }
        int[] a = {numunassign, -1, -1};
        return a;
    }

    //function to check if we can put a
    //value in a paticular cell or not
    private static boolean isSafe(int n, int r, int c)
    {
        //checking in row
        for(int i=0;i<SIZE;i++)
        {
            //there is a cell with same value
            if(matrix[r][i] == n)
                return false;
        }
        //checking column
        for(int i=0;i<SIZE;i++)
        {
            //there is a cell with the value equal to i
            if(matrix[i][c] == n)
                return false;
        }
        //checking sub matrix
        int row_start = (r/3)*3;
        int col_start = (c/3)*3;
        for(int i=row_start;i<row_start+3;i++)
        {
            for(int j=col_start;j<col_start+3;j++)
            {
                if(matrix[i][j]==n)
                    return false;
            }
        }
        return true;
    }

    //function to solve sudoku
    //using backtracking
    private static boolean solveSudoku()
    {
        int row=0;
        int col=0;
        int[] a = numberUnassigned(row, col);
        //if all cells are assigned then the sudoku is already solved
        //pass by reference because number_unassigned will change the values of row and col
        if(a[0] == 0)
            return true;
        //number between 1 to 9
        row = a[1];
        col = a[2];
        for(int i=1;i<=SIZE;i++)
        {
            //if we can assign i to the cell or not
            //the cell is matrix[row][col]
            if(isSafe(i, row, col))
            {
                matrix[row][col] = i;
                //backtracking
                if(solveSudoku())
                    return true;
                //if we can't proceed with this solution
                //reassign the cell
                matrix[row][col]=0;
            }
        }
        return false;
    }

    public static void main(String[] args)
    {
        if (solveSudoku()) {
            printSudoku();
        
        System.err.println(matrix[0][3]);
        }
        else
            System.out.println("No solution");
        
        
    }
}