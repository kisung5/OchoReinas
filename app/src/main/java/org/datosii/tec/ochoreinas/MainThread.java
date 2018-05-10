package org.datosii.tec.ochoreinas;

/*
    Posiciones de las reinas en todo el tablero,
    {{[10,10],[140,10],[270,10],[410,10],[540,10],[680,10],[740,10],[950,10]},
     {[10,180],[140,180],[270,180],[410,180],[540,180],[680,180],[740,180],[950,180]},
     {[10,350],[140,350],[270,350],[410,350],[540,350],[680,350],[740,350],[950,350]},
     {[10,520],[140,520],[270,520],[410,520],[540,520],[680,520],[740,520],[950,520]},
     {[10,680],[140,680],[270,680],[410,680],[540,680],[680,680],[740,680],[950,680]},
     {[10,850],[140,850],[270,850],[410,850],[540,850],[680,850],[740,850],[950,850]},
     {[10,1010],[140,1010],[270,1010],[410,1010],[540,1010],[680,1010],[740,1010],[950,1010]},
     {[10,1180],[140,1180],[270,1180],[410,1180],[540,1180],[680,1180],[740,1180],[950,1180]}}

*/

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private MainView mainView;
    private boolean running;
    public static Canvas canvas;
    final private int N = 8;
    private int [][] solution;

    final private int table[][][] =
            {{{10,10},{140,10},{270,10},{410,10},{540,10},{680,10},{740,10},{950,10}},
        {{10,180},{140,180},{270,180},{410,180},{540,180},{680,180},{740,180},{950,180}},
        {{10,350},{140,350},{270,350},{410,350},{540,350},{680,350},{740,350},{950,350}},
        {{10,520},{140,520},{270,520},{410,520},{540,520},{680,520},{740,520},{950,520}},
        {{10,680},{140,680},{270,680},{410,680},{540,680},{680,680},{740,680},{950,680}},
        {{10,850},{140,850},{270,850},{410,850},{540,850},{680,850},{740,850},{950,850}},
        {{10,1010},{140,1010},{270,1010},{410,1010},{540,1010},{680,1010},{740,1010},{950,1010}},
        {{10,1180},{140,1180},{270,1180},{410,1180},{540,1180},{680,1180},{740,1180},{950,1180}}};

    public MainThread (SurfaceHolder surfaceHolder, MainView mainView) {

        super();
        this.surfaceHolder = surfaceHolder;
        this.mainView = mainView;
        solution = solveNQ();
    }

    @Override
    public void run(){

        while(running) {
            canvas = null;
            for (int y = 0; y< N; y++) {
                for (int x = 0; x < N; x++) {
                    try {
                        if (solution[y][x] == 1) {
                            canvas = this.surfaceHolder.lockCanvas();
                            synchronized (surfaceHolder) {
                                this.mainView.update(table[y][x][0], table[y][x][1]);
                                this.mainView.draw(canvas);
                            }
                        }


                    } catch (Exception e) {
                    }
                    finally {
                        if (canvas!=null) {
                            try {
                                surfaceHolder.unlockCanvasAndPost(canvas);

                            } catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                }
                mainView.getOnNewQueen(table[y][0][0],10);
            }


        }
    }

    public void setRunning(boolean isRunnig) {

        running = isRunnig;
    }


//------------------Algoritmo--------------------------------------------

    boolean isSafe(int board[][], int row, int col) {
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

                if (solve(board, col + 1) == true) {
                    return true;
                }

                board[i][col] = 0;
            }
        }


        return false;
    }

    int[][] solveNQ() {
        int board[][] = {{0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
        };

        if (solve(board, 0) == true) {

            return board;
        }

        else return board;
    }

}
