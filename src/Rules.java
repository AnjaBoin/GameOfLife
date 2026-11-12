public class Rules {
    // the rules of the game
    // calculates next generation, gets the actual board and counts neighbours

    //CONSTRUCTOR
    public Rules(){

    }

    // checks if a cell fits the board
    public static boolean insideBoard(boolean[][] board, int row, int col){
        return ((row >= 0 && row < board.length) && (col >= 0 && col < board[0].length));
    }

    // returns a random coordinate between limits
    public static int randomBetween(int min, int max){
        return min + (int) (Math.random() * (max - min + 1));
    }

    // returns random coordinates for a pattern which fits the board
    // coordinates[0] is row, coordinates[1] is col
    public static int[] randomCoordinate(Patterns pattern){
        int [] coordinates = new int[2];
        // calculate coordinate limits
        int minRow = Board.getMargin();
        int minCol = Board.getMargin();
        int maxRow = Board.BOARD_HEIGHT - pattern.getHeight() - Board.getMargin();
        int maxCol = Board.BOARD_WIDTH - pattern.getWidth() - Board.getMargin();

        coordinates[0] = randomBetween(minRow, maxRow);
        coordinates[1] = randomBetween(minCol, maxCol);
        return coordinates;
    }

    public static boolean checkNeighbours(boolean[][] board, int row, int col, boolean cell){
        int amountNeighbours = 0;

        // determine how many neighbours are living
        for (int height = -1; height <= 1; height++){
            for (int width = -1; width <= 1; width++){
                if (height == 0 && width == 0) {
                    continue;
                }

                int r = row + height;
                int c = col + width;

                // disregard positions on outside board
                if (!insideBoard(board, r, c)) {
                    continue;
                }

                if (board[r][c]) {
                    amountNeighbours ++;
                }
            }
        }
        // switch case determines cell lives or dies
        switch(amountNeighbours) {
            case 0, 1 -> cell = false;
            case 3 -> cell = true;
            case 4, 5, 6, 7, 8 -> cell = false;
        }

    return cell;
    }

    public static void nextGen(boolean[][] board, boolean[][] newBoard){
        for (int row = 0; row < board.length; row ++){
            for (int col = 0; col < board[row].length; col++){
                boolean cell = board[row][col];
                newBoard[row][col] = checkNeighbours(board, row, col, cell);
            }
        }
    }

}
