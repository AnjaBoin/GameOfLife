import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;


// this Class reads and draws the board (which cells live)

public class Cells extends JPanel {
    // CONSTANTS
    private static final Color COLOR_LIVING = Color.WHITE;
    private static final Color COLOR_DEAD = Color.BLACK;
    private static final int COLOR_IMAGE_DEAD = COLOR_DEAD.getRGB();
    private static final int COLOR_IMAGE_LIVING = COLOR_LIVING.getRGB();
    private final Board board;
    private int cellSize = 3;

    // fields for buffered image
    private BufferedImage image;
    private int[] pixels;

    // CONSTRUCTOR
    public Cells(Board board){
        this.board = board;
        setBackground(COLOR_DEAD);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateBoardSize();
            }
        });
    }

    // getter
    public int getCellSize(){return cellSize;}

    // setter
    public void setCellSize(int value){
        if (value < 1){return;}
        cellSize = value;
        updateBoardSize();}

    // calculate position on the board with mouse
    public int pixelToRow(int y){
        return y / cellSize;
    }
    public int pixelToCol(int x){
        return x / cellSize;
    }

    // sets the preferred window size
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(board.getColumns() * cellSize, board.getRows() * cellSize);
    }

    // paints the image to the board
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        prepareImage();
        int rows = board.getRows();
        int cols = board.getColumns();
        boolean[][] grid = board.getGrid();

        // make all pixels dead (black) in one go
        Arrays.fill(pixels, COLOR_IMAGE_DEAD);

        // set only the living cells
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col]) {
                    pixels[row * cols + col] = COLOR_IMAGE_LIVING;
                }
            }
        }
        g.drawImage(image, 0, 0, cols * cellSize, rows * cellSize, null);
    }

    // prepares the buffered image to reduce processor capacity
    private void prepareImage(){
        if (image == null || image.getWidth() != board.getColumns() || image.getHeight() != board.getRows()){
            image = new BufferedImage(board.getColumns(), board.getRows(), BufferedImage.TYPE_INT_RGB);
            pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        }
    }

    // updates board after cell size is changed
    private void updateBoardSize(){
        int cols = getWidth() / cellSize;
        int rows = getHeight() / cellSize;
        if (cols != board.getColumns() || rows != board.getRows()){
            board.resizeBoard(rows, cols);
        }
        repaint();
    }
}
