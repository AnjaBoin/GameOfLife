public class Board {
    // this Class keeps the board as a raster
    // Methodes to turn cells on and off
    // how to

    // CONSTANTS
    static final int BOARD_HEIGHT = 240;
    static final int BOARD_WIDTH = 480;
    private static final int STANDARD_DENSITY = 30;

    // FIELD
    private boolean [][] grid;

    // CONSTRUCTOR
    public Board(){
        grid = new boolean[BOARD_HEIGHT][BOARD_WIDTH];
    }

    // getter
    public boolean[][] getGrid() {
        return grid;
    }

    // setter
    public void setGrid(boolean[][] grid) {
        this.grid = grid;
    }

    // get rows
    public int getRows(){
        return grid.length;
    }

    // get columns
    public int getColumns(){
        return grid[0].length;
    }

    // gets the current value of the cell (living or dead)
    public boolean getCell(int row, int col){
        return grid[row][col];
    }

    // sets percentage of living cells on random grid
    private double setDensity(int density){
        return density / 100.0;
    }

    // sets the cell to status living or dead
    public void setCell(int row, int col, boolean value){
        grid[row][col] = value;
    }

    // start of randomized board, user sets density
    public void randomiseBoard(int density){
        double fieldDensity = setDensity(density);
        for (int row = 0; row < grid.length; row ++){
            for (int col = 0; col < grid[0].length; col ++){
                grid[row][col] = Math.random() < fieldDensity;
            }
        }
    }

    // start of randomized board with standard density
    public void randomiseBoard(){
        randomiseBoard(STANDARD_DENSITY);
    }

}
