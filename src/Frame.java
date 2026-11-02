import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {
    // use JComboBox for dropdownmenu
    // pack() lets the frame determine its own size
    // getPreferredSize() lets the frame make a perfect fit
    // use javax.swing.Timer om automatisch te genereren.
    // eventueel IntStream gebruiken om te threaden en het sneller te maken

    // CONSTANTS
    private static final int VALUE_SPINNER = 50;
    private static final int MIN_SPINNER = 0;
    private static final int MAX_SPINNER = 100;
    private static final int STEP_SPINNER = 1;

    private Board board = new Board();
    private Cells cells = new Cells(board);
    private Rules rules = new Rules();

    public Frame (){
        setTitle("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Control panel
        JPanel panel = new JPanel();
        SpinnerNumberModel model = new SpinnerNumberModel(VALUE_SPINNER, MIN_SPINNER, MAX_SPINNER, STEP_SPINNER);
        JSpinner spinner = new JSpinner(model);
        JLabel labelSpinner = new JLabel("Density %");
        panel.add(labelSpinner);
        panel.add(spinner);


        // Frame
        add(panel, BorderLayout.SOUTH);
        add(cells, BorderLayout.CENTER);

        // Button
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.setGrid(rules.nextGen(board.getGrid()));
                cells.repaint();
            }
        });
        showButton(panel, spinner, timer);




        // last piece
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public void showButton(JPanel panel, JSpinner spinner, Timer timer) {
        JButton buttonSpinner = new JButton("Random");
        buttonSpinner.setFont(new Font("SansSerif", Font.BOLD, 16));
        JButton buttonTimer = new JButton("Start");
        buttonTimer.setFont(new Font("SansSerif", Font.BOLD, 16));

        buttonSpinner.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int value = (int) spinner.getValue();
                board.randomiseBoard(value);
                cells.repaint();

            }
        });

        buttonTimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (timer.isRunning()) {
                    timer.stop();
                    buttonTimer.setText("Start");
                } else {
                    timer.start();
                    buttonTimer.setText("Stop");
                }


            }
        });
        panel.add(buttonSpinner);
        panel.add(buttonTimer);
    }





}

