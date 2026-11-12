import javax.swing.*;
import java.awt.*;

// the JPanel which reads and draws the board (which cells live)

public class Cells extends JPanel {
    // CONSTANTS
    private static final int CELL_SIZE = 3;
    private static final Color COLOR_LIVING = Color.WHITE;
    private static final Color COLOR_DEAD = Color.BLACK;
    private final Board board;


    public Cells(Board board){
        this.board = board;
    }

    // getter
    public static int getCellSize(){return CELL_SIZE;}

    // sets the preferred window size
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(Board.BOARD_WIDTH * CELL_SIZE, Board.BOARD_HEIGHT * CELL_SIZE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int row = 0; row < board.getRows(); row ++){
            for (int col = 0; col < board.getColumns(); col ++){
                if (board.getCell(row, col)){
                    g.setColor(COLOR_LIVING);
                    g.fillRect(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                } else {
                    g.setColor(COLOR_DEAD);
                    g.fillRect(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }



}
