import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {
    // use JComboBox for dropdownmenu
    // pack() lets the frame determine its own size
    // getPreferredSize() lets the frame make a perfect fit
    // javax.swing.Timer starts the simulation.
    // eventueel IntStream gebruiken om te threaden en het sneller te maken

    // CONSTANTS
    private static final int VALUE_SPINNER = 50;
    private static final int MIN_SPINNER = 0;
    private static final int MAX_SPINNER = 100;
    private static final int STEP_SPINNER = 5;
    private static final Font STANDARD_FONT = new Font("SansSerif", Font.BOLD, 16);

    private final Board board = new Board();
    private final Cells cells = new Cells(board);
    private final Rules rules = new Rules();

    private JComboBox<Patterns> comboBox;

    public Frame (){
        setTitle("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Control panel
        JPanel panel = new JPanel();
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(1,4, 10, 0));
        SpinnerNumberModel model = new SpinnerNumberModel(VALUE_SPINNER, MIN_SPINNER, MAX_SPINNER, STEP_SPINNER);
        JSpinner spinner = new JSpinner(model);
        comboBox = createComboBox();
        JLabel labelSpinner = createLabel("Density %");
        JLabel labelCombo = createLabel("Pattern: ");
        panel.add(labelSpinner);
        panel.add(spinner);
        panel.add(labelCombo);
        panel.add(comboBox);
        panel.add(panelButtons);


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
        panelButtons.add(createRandomButton(spinner));
        panelButtons.add(createStartStopButton(timer));
        panelButtons.add(createEmptyButton());


        // last piece
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


    // creates a standard label
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(STANDARD_FONT);
        return label;
    }

    // creates a button with the standard layout
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(STANDARD_FONT);
        return button;
    }

    // creates a button that fills the board randomly
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

    // creates a button that empties the board
    private JButton createEmptyButton() {
        JButton buttonEmpty = createButton("Empty Board");

        buttonEmpty.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.randomiseBoard(0);
                cells.repaint();
            }
        });

        return buttonEmpty;
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

    // create JCombobox which selects patterns
    private JComboBox<Patterns> createComboBox() {
        comboBox = new JComboBox<Patterns>(Patterns.values());
        comboBox.setFont(STANDARD_FONT);


        return comboBox;
    }

    // Button that creates patterns






}

