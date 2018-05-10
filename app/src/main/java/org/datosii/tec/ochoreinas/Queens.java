package org.datosii.tec.ochoreinas;

/*
    Posiciones de las reinas en todo el tablero,
    {{[10,10],[140,10],[270,10],[410,10],[540,10],[680,10],[740,10],[950,10]},
     {[10,180],[140,180],[270,180],
     {[10,350],
     {[10,520],
     {[10,680],
     {[10,850],
     {[10,1010],
     {[10,1180],

*/

public class Queens {
	final int N = 8;
	 
    void printSolution(int board[][])
    {
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
                System.out.print(" " + board[i][j]
                                 + " ");
            System.out.println();
        }
    }
 
    boolean isSafe(int board[][], int row, int col)
    {
        int i, j;
 
        for (i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;
 
        for (i=row, j=col; i>=0 && j>=0; i--, j--)
            if (board[i][j] == 1)
                return false;
 
        for (i=row, j=col; j>=0 && i<N; i++, j--)
            if (board[i][j] == 1)
                return false;
 
        return true;
    }

    boolean solve(int board[][], int col)
    {

        if (col >= N)
            return true;
        for (int i = 0; i < N; i++)
        {
            if (isSafe(board, i, col))
            {
                board[i][col] = 1;
 
                if (solve(board, col + 1) == true)
                    return true;
 
                board[i][col] = 0;
            }
        }
 
     
        return false;
    }
 
    
    boolean solveNQ()
    {
        int board[][] = {{0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
        };
 
        if (solve(board, 0) == false)
        {
            System.out.print("No hay solucion");
            return false;
        }
 
        printSolution(board);
        return true;
    }
 
//    public static void main(String args[])
//    {
//        Queens Queen = new Queens();
//        Queen.solveNQ();
//    }
}
