import javax.swing.*;
import java.awt.event.MouseAdapter;



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






}
