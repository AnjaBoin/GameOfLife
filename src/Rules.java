public class Rules {
    // the rules of the game
    // calculates next generation, gets the actual board and counts neighbours

    //CONSTRUCTOR
    public Rules(){

    }

    public boolean checkNeighbours(boolean[][] board, int row, int col, boolean cell){
        int amountNeighbours = 0;

        // determine how many neighbours are living
        for (int h = -1; h <= 1; h++){
            for (int w = -1; w <= 1; w++){
                if (h == 0 && w == 0) {
                    continue;
                }

                int r = row + h;
                int c = col + w;

                // disregard positions on outside board
                if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) {
                    continue;
                }

                if (board[r][c]) {
                    amountNeighbours ++;
                }
            }
        }
        // switch case of cell lives or dies

        switch(amountNeighbours) {
            case 0, 1 -> cell = false;
            case 3 -> cell = true;
            case 4, 5, 6, 7, 8 -> cell = false;
        }

    return cell;
    }

    public boolean[][] nextGen(boolean[][] board){
        boolean[][]newBoard = new boolean [board.length][board[0].length];
        for (int row = 0; row < board.length; row ++){
            for (int col = 0; col < board[row].length; col++){
                boolean cell = board[row][col];
                newBoard[row][col] = checkNeighbours(board, row, col, cell);
            }
        }
    return newBoard;
    }

}
