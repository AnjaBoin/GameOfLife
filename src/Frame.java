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
    private static final int STEP_SPINNER = 5;

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
        panel.add(createRandomButton(spinner));
        panel.add(createStartStopButton(timer));




        // last piece
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


    // creates a button with the standard layout
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        return button;
    }

    // button that fills the board randomly
    private JButton createRandomButton(JSpinner spinner) {
        JButton buttonSpinner = createButton("Random");

        buttonSpinner.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int value = (int) spinner.getValue();
                board.randomiseBoard(value);
                cells.repaint();
            }
        });

        return buttonSpinner;
    }

    // button that starts and stops the timer
    private JButton createStartStopButton(Timer timer) {
        JButton buttonTimer = createButton("Start");

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

        return buttonTimer;
    }

    // Button that creates patterns






}

