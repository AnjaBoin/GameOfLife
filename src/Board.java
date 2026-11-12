public class Board {
    // this Class keeps the board as a raster
    // Methodes to turn cells on and off
    // how to

    // CONSTANTS
    static final int BOARD_HEIGHT = 240;
    static final int BOARD_WIDTH = 480;
    private static final int STANDARD_DENSITY = 30;
    private static final int GAP = 40;
    private static final int MARGIN = 5;

    // FIELD
    private boolean [][] grid;
    private boolean [][] nextGrid;

    // CONSTRUCTOR
    public Board(){
        grid = new boolean[BOARD_HEIGHT][BOARD_WIDTH];
        nextGrid = new boolean[grid.length][grid[0].length];
    }

    // getter
    public boolean[][] getGrid() {
        return grid;
    }
    public static int getMargin(){return MARGIN;}
    public static int getGap(){return GAP;}

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

    // places pattern on the board
    public void placePattern(Patterns pattern, int row, int col){
        boolean [][]patternGrid = pattern.getRowsBool();

        for (int r = 0; r < patternGrid.length; r++){
            for (int c = 0; c < patternGrid[0].length; c++){
                int boardRow = row + r;
                int boardCol = col + c;

                // skip positions outside the board
                if (!Rules.insideBoard(grid, boardRow, boardCol)) {
                    continue;
                }
                // copy value from pattern to board
                setCell(boardRow, boardCol, patternGrid[r][c]);
            }
        }
    }

    // changes the generation on the board
    public void nextGeneration(){
        Rules.nextGen(grid, nextGrid);
        boolean[][]temp = grid;
        grid = nextGrid;
        nextGrid = temp;
    }

    // places pattern in the board
    public void showAllPatterns(){
        randomiseBoard(0);

        int cursorRow = MARGIN;
        int cursorCol = MARGIN;

        int lineHeight = 0;

        for (Patterns p : Patterns.values()){
            // if pattern does not fit on line: next line
            if (cursorCol + p.getWidth() + MARGIN > BOARD_WIDTH) {
                cursorRow += lineHeight + GAP;
                cursorCol = MARGIN;
                lineHeight = 0;
            }

            // if pattern does not fit on board: skip
            if (cursorRow + p.getHeight() + MARGIN > BOARD_HEIGHT) {
                continue;
            }

            placePattern(p, cursorRow, cursorCol);

            // adjust lineHeight with pattern height & jump next position
            lineHeight = Math.max(lineHeight, p.getHeight());
            cursorCol += p.getWidth() + GAP;
        }
    }

    // places a random pattern on the board
    public void placeRandomPattern(){
        int randomPattern = Rules.randomBetween(0, Patterns.values().length);
        Patterns pattern = Patterns.values()[randomPattern];
        int []coordinates = Rules.randomCoordinate(pattern);
        placePattern(pattern, coordinates[0], coordinates[1]);
    }

}
