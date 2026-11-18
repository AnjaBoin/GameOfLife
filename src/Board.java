public class Board {
    // this Class keeps the board as a raster

    // CONSTANTS
    static final int BOARD_HEIGHT = 700;
    static final int BOARD_WIDTH = 1400;
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
            if (cursorCol + p.getWidth() + MARGIN > getColumns()) {
                cursorRow += lineHeight + GAP;
                cursorCol = MARGIN;
                lineHeight = 0;
            }

            // if pattern does not fit on board: skip
            if (cursorRow + p.getHeight() + MARGIN > getRows()) {
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
        int randomPattern = Rules.randomBetween(0, Patterns.values().length-1);
        Patterns pattern = Patterns.values()[randomPattern];
        // pattern does not fit on the board: do nothing
        if (pattern.getHeight() + 2 * MARGIN > getRows()
                || pattern.getWidth() + 2 * MARGIN > getColumns()) {
            return;
        }
        int []coordinates = randomCoordinate(pattern);
        placePattern(pattern, coordinates[0], coordinates[1]);
    }

    // makes the board resizable
    public void resizeBoard(int rows, int cols){
        if (rows <= 1 || cols <= 1) {
            return;
        }
        boolean[][]oldGrid = grid;
        grid = new boolean[rows][cols];
        int heightMin = Math.min(oldGrid.length, grid.length);
        int widthMin = Math.min(oldGrid[0].length,grid[0].length);
        for(int r = 0; r < heightMin; r ++){
            for (int c = 0; c < widthMin; c++){
                grid[r][c] = oldGrid[r][c];
            }
        }
        nextGrid = new boolean[grid.length][grid[0].length];

    }

    // returns random coordinates for a pattern which fits the board
    // coordinates[0] is row, coordinates[1] is col
    public int[] randomCoordinate(Patterns pattern){
        int [] coordinates = new int[2];
        // calculate coordinate limits
        int minRow = MARGIN;
        int minCol = MARGIN;
        int maxRow = getRows() - pattern.getHeight() - Board.getMargin();
        int maxCol = getColumns() - pattern.getWidth() - Board.getMargin();

        coordinates[0] = Rules.randomBetween(minRow, maxRow);
        coordinates[1] = Rules.randomBetween(minCol, maxCol);
        return coordinates;
    }

}
