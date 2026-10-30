import javax.swing.*;
import java.awt.*;

// the JPanel which reads and draws the board (which cells live)

public class Cells extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        // fillRect hierin verwerken (x, y, breedte, hoogte)
        //cel (rij, kolom), celgrootte isd bv 10, begin bij x = kolom x celgroote en y = rij x celgrootte
    }

    //super.paintComponent() {
        // deze aanroepen omdat anders de oude cellen blijven staan
    //}

}
