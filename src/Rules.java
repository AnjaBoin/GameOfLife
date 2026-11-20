// the rules of the game
// calculates next generation, gets the actual board and counts neighbors

public class Rules {

    //CONSTRUCTOR
    public Rules(){
        // still empty
    }

    // checks if a cell fits the board
    public static boolean insideBoard(boolean[][] board, int row, int col){
        return ((row >= 0 && row < board.length) && (col >= 0 && col < board[0].length));
    }

    // returns a random coordinate between limits
    public static int randomBetween(int min, int max){
        return min + (int) (Math.random() * (max - min + 1));
    }

    // checks neighbor cells to determine if cell lives or dies
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
                // counts living neighbors
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

    // calculates next generation of cells
    public static void nextGen(boolean[][] board, boolean[][] newBoard){
        for (int row = 0; row < board.length; row ++){
            for (int col = 0; col < board[row].length; col++){
                boolean cell = board[row][col];
                newBoard[row][col] = checkNeighbours(board, row, col, cell);
            }
        }
    }

}
