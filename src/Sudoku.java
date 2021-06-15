import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Sudoku {

    int[][] board = new int[9][9];
    Integer[] seed = new Integer[]{1,2,3,4,5,6,7,8,9};

    public Sudoku(){
        //shuffle integer list seed to ensure randomness of game
        Collections.shuffle(Arrays.asList(seed));
        //initialize array board
        for (int[] row:board)
            Arrays.fill(row,0);
    }
    public boolean fillboard(){
        for (int i = 0;i<9;i++){
            for (int j = 0;j<9;j++){
                //if value is 0, we need to fill it
                if (board[i][j]==0){
                    //loop through array seed
                    for (int k:seed){
                        //check if value can be put into the board,
                        //checkCol checkRow and checkSub
                        if (validPut(i,j,k)){
                            //if valid
                            //repalce it
                            board[i][j] = k;
                            if (fillboard())
                                return true;
                            else
                                //if not continue
                                board[i][j] = 0;}
                    }
                    return false;//not yet, continue running
                }
            }
        }
        return true;//after every position has been placed, return ture
    }
    /**
     * @param row
     * @param n
     * @return Boolean
     * Check row
     * */
    public boolean checkRow(int row,int n){
        for (int i = 0;i<9;i++){
            if (board[row][i]== n)
                return true;
        }
        return false;
    }
    /**
     * @param col
     * @param n
     * @return Boolean
     * Check column
     * */
    public boolean checkCol(int col,int n){
        for (int i = 0;i<9;i++){
            if (board[i][col]==n)
                return true;
        }
        return false;
    }
    /**
     * @param row
     * @param col
     * @param n
     * @return Boolean
     * Check within the 3*3 box
     * */
    public boolean checkSub(int row,int col,int n){
        //calculate start position of the box or square
        int x = row - row%3;
        int y = col - col%3;
        for (int i = x;i<x+3;i++){
            for (int j = y;j<y+3;j++){
                if (board[i][j] == n)
                    return true;
            }
        }
        return false;
    }
    /**
     * @param row row number
     * @param col col number
     * @param n number to be checked
     * Check in row col and box whether it is valid to put the number
     * */
    public boolean validPut(int row, int col,int n){
        return !(checkCol(col,n)||checkRow(row,n)||checkSub(row,col,n));
    }

    /**
     * @param difficulty
     * Convert difficulty into number of zeros needs to be placed in array board
     * After generate correspond game board, print it using nested loop
     * */
    public void printBoard(String difficulty){
        if (difficulty=="Easy")
            puzzleGenerator(81*0.56);
        else if (difficulty=="Medium")
            puzzleGenerator(81*0.62);
        else if (difficulty== "Hard")
            puzzleGenerator(81*0.7);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++)
                System.out.print(" " + board[i][j]);
            System.out.print("\n");
        }
    }
    /**
     * Input numbers of zeros to put into the board
     *  @param prob
     * */
    public void puzzleGenerator(double prob){
        //prob indicate number of zeros to replace
        for (int p = 0;p<prob;p++) {
            //generate random row and col number
            int row = new Random().nextInt(9);
            int col = new Random().nextInt(9);
            //if that position is already placed, p-- or run this loop again
            if (board[row][col]==0)
                p--;
            //if that position is not yet placed, put it as 0
            else
                board[row][col] = 0;
        }
    }
    

    public static void main(String[] args) {
        Sudoku s = new Sudoku();
        if (s.fillboard()){

            s.printBoard("Hard");
        }
    }

}
