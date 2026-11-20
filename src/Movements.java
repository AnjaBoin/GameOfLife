import javax.swing.*;
import java.awt.event.*;


// this Class sets mouse activities

public class Movements extends MouseAdapter {

    // VARIABLES
    private Board board;
    private Cells cells;
    private JComboBox<Patterns> patternBox;
    private JToggleButton drawToggle;
    private long lastTimeStamp;

    // CONSTANTS
    private static final int STAMP_DELAY = 50;

    // CONSTRUCTOR
    public Movements(Board board, Cells cells, JComboBox<Patterns> patternBox, JToggleButton drawToggle){
        this.board = board;
        this.cells = cells;
        this.patternBox = patternBox;
        this.drawToggle = drawToggle;
    }

    // centers pattern on mouse position when placed
    private void placePatternCentered(Patterns pattern, int mousePosRow, int mousePosCol){
        int col = mousePosCol - pattern.getWidth() / 2;
        int row = mousePosRow - pattern.getHeight() / 2;
        board.placePattern(pattern, row, col);
    }

    // buffers the time between mouseclick and dragging mouse
    private boolean placePatternAllowed(){
        long timePassed = System.currentTimeMillis() - lastTimeStamp;
        return (timePassed > STAMP_DELAY);
    }

    // sets timer for time between mouse clicks on actual time
    private void resetPatternTimer(){
        lastTimeStamp = System.currentTimeMillis();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!drawToggle.isSelected()) {
            return;
        }
        int mousePosCol = cells.pixelToCol(e.getX());
        int mousePosRow = cells.pixelToRow(e.getY());

        boolean[][] grid = board.getGrid();
        if (!Rules.insideBoard(grid, mousePosRow, mousePosCol)) {
            return;
        }
        Patterns pattern = (Patterns) patternBox.getSelectedItem();
        if (pattern == null) {
            return;
        }
        if (pattern == Patterns.PIXEL) {
            board.setCell(mousePosRow, mousePosCol, true);
        } else {
            placePatternCentered(pattern, mousePosRow, mousePosCol);
            resetPatternTimer();
        }
        cells.repaint();
    }

    // when mouse button is held, patterns will be drawn when dragged
    @Override
    public void mouseDragged(MouseEvent e) {
        if (!drawToggle.isSelected()) {
            return;
        }
        int mousePosCol = cells.pixelToCol(e.getX());
        int mousePosRow = cells.pixelToRow(e.getY());

        boolean[][] grid = board.getGrid();
        if (!Rules.insideBoard(grid, mousePosRow, mousePosCol)) {
            return;
        }
        Patterns pattern = (Patterns) patternBox.getSelectedItem();
        if (pattern == null) {
            return;
        }

        if (pattern == Patterns.PIXEL) {
            // with pixel selected, it is sufficient when one cell is set to living
            board.setCell(mousePosRow, mousePosCol, true);

        } else {
            // when pattern other than pixel, pattern has to be placed on the board
            if (placePatternAllowed()) {
                placePatternCentered(pattern, mousePosRow, mousePosCol);
                resetPatternTimer();
            }
        }
        cells.repaint();
    }
}