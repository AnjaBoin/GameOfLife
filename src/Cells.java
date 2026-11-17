import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

// this class reads and draws the board (which cells live)

public class Cells extends JPanel {
    // CONSTANTS
    private static final int CELL_SIZE = 3;
    private static final Color COLOR_LIVING = Color.WHITE;
    private static final Color COLOR_DEAD = Color.BLACK;
    private final Board board;


    public Cells(Board board){
        this.board = board;
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int cols = getWidth() / CELL_SIZE;
                int rows = getHeight() / CELL_SIZE;
                if (cols != board.getColumns() || rows != board.getRows()){
                    board.resizeBoard(rows, cols);
                }
                repaint();
            }

        });
    }

    // getter
    public static int getCellSize(){return CELL_SIZE;}

    // calculate position on the board with mouse
    public int pixelToRow(int y){
        return y / CELL_SIZE;
    }

    public int pixelToCol(int x){
        return x / CELL_SIZE;
    }

    // sets the preferred window size
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(board.getColumns() * CELL_SIZE, board.getRows() * CELL_SIZE);
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
