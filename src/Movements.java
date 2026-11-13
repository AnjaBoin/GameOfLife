import javax.swing.*;
import java.awt.event.*;



public class Movements extends MouseAdapter {

    private Board board;
    private Cells cells;
    private JComboBox<Patterns> patternBox;
    private JToggleButton drawToggle;

    public Movements(Board board, Cells cells, JComboBox<Patterns> patternBox, JToggleButton drawToggle){
        this.board = board;
        this.cells = cells;
        this.patternBox = patternBox;
        this.drawToggle = drawToggle;


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
        } else {
            Patterns pattern = (Patterns) patternBox.getSelectedItem();
            if (pattern == Patterns.PIXEL) {
                board.setCell(mousePosRow, mousePosCol, true);
            } else {
                int col = mousePosCol - pattern.getWidth() / 2;
                int row = mousePosRow - pattern.getHeight() / 2;
                board.placePattern(pattern, row, col);
            }
        }
        cells.repaint();


    }

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
        } else {
            Patterns pattern = (Patterns) patternBox.getSelectedItem();
            if (pattern == Patterns.PIXEL) {
                board.setCell(mousePosRow, mousePosCol, true);
            }
        }
        cells.repaint();
    }




}
