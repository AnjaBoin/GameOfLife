import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {
    // this class sets the frame for the board with control panels
    // use IntStream to thread and make programm run faster

    // CONSTANTS
    private static final int VALUE_SPINNER = 30;
    private static final int MIN_SPINNER = 0;
    private static final int MAX_SPINNER = 100;
    private static final int STEP_SPINNER = 5;
    private static final int TIMER_DELAY = 100;
    private static final Font STANDARD_FONT = new Font("SansSerif", Font.BOLD, 16);
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Color TEXT_COLOR = Color.GREEN.darker();

    private final Board board = new Board();
    private final Cells cells = new Cells(board);
    private JToggleButton drawToggle;
    private JLabel categoryInfo;

    private JComboBox<Patterns> patternBox;
    private JComboBox<Category> categoryBox;

    public Frame (){
        setTitle("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // weghalen ivm veranderbare size
        //setResizable(false);

        // Control panel
        JPanel panel = new JPanel();
        JPanel panelSettings = new JPanel();
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(1,4, 10, 0));
        SpinnerNumberModel model = new SpinnerNumberModel(VALUE_SPINNER, MIN_SPINNER, MAX_SPINNER, STEP_SPINNER);
        JSpinner spinner = new JSpinner(model);
        patternBox = createComboBox();
        categoryBox = createCategoryBox();
        filterPatterns();
        patternBox.setPreferredSize(patternBox.getPreferredSize());
        categoryBox.setPreferredSize(categoryBox.getPreferredSize());

        // label on top that shows name and description of the selected category
        categoryInfo = createDescriptionLabel();
        // this 1 line for adding label to the Frame
        //add(categoryInfo, BorderLayout.NORTH);
        // these 2 lines for adding label to the Board
        cells.setLayout(new BorderLayout());
        cells.add(categoryInfo, BorderLayout.NORTH);
        // updates the label with the actual description
        updateCategoryInfo();

        panelSettings.add(createLabel("Density %"));
        panelSettings.add(spinner);
        panelSettings.add(createLabel("Category: "));
        panelSettings.add(categoryBox);
        panelSettings.add(createLabel("Pattern: "));
        panelSettings.add(patternBox);
        panel.setLayout(new BorderLayout());
        panel.add(panelSettings, BorderLayout.NORTH);
        panel.add(panelButtons, BorderLayout.SOUTH);
        //panel.add(panelSettings);
        //panel.add(panelButtons);


        // Frame
        add(panel, BorderLayout.SOUTH);
        add(cells, BorderLayout.CENTER);

        // Button Timer for simulation
        Timer timer = new Timer(TIMER_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.nextGeneration();
                cells.repaint();
            }
        });
        panelButtons.add(createRandomButton(spinner));
        panelButtons.add(createStartStopButton(timer));
        panelButtons.add(createEmptyButton());
        panelButtons.add(createShowPatterns());
        panelButtons.add(createRandomPattern());


        // implement Mouse
        drawToggle = createToggleMouse();
        panelButtons.add(drawToggle);
        setupMouse();


        // last piece
        // adjusts frame to fit for items within it
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


    // setup for the mouse
    private void setupMouse(){
        Movements movements = new Movements(board, cells, patternBox, drawToggle);
        cells.addMouseListener(movements);
        cells.addMouseMotionListener(movements);
    }

    // creates a standard label
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(STANDARD_FONT);
        return label;
    }

    // creates label for description of the patterns on top of the screen
    private JLabel createDescriptionLabel(){
        categoryInfo = new JLabel();
        categoryInfo.setFont(STANDARD_FONT.deriveFont(Font.PLAIN));
        categoryInfo.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        categoryInfo.setForeground(TEXT_COLOR);
        categoryInfo.setBackground(BACKGROUND_COLOR);
        // this line for adding label to the frame
        //categoryInfo.setOpaque(true);
        // this line for adding label to the board
        categoryInfo.setOpaque(false);
        return categoryInfo;
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

    // create JCombobox with the patterns
    private JComboBox<Patterns> createComboBox() {
        patternBox = new JComboBox<>();
        patternBox.setFont(STANDARD_FONT);
        return patternBox;
    }

    // create JCombobox with the category of the patterns
    private JComboBox<Category> createCategoryBox() {
        categoryBox = new JComboBox<Category>(Category.values());
        categoryBox.setFont(STANDARD_FONT);
        categoryBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterPatterns();
                updateCategoryInfo();
            }
        });

        return categoryBox;
    }

    // shows name and description of the selected category
    private void updateCategoryInfo() {
        Category selected = (Category) categoryBox.getSelectedItem();
        if (selected == null) {
            return;
        }
        categoryInfo.setText("<html><b>" + selected.getDisplayName() + ":</b> "
                + selected.getDescription() + "</html>");
        categoryInfo.setVisible(selected != Category.ALL);
    }

    // filters the patterns on category via ComboBox
    private void filterPatterns(){
        Category selectedCategory = (Category) categoryBox.getSelectedItem();
        patternBox.removeAllItems();
        for (Patterns p : Patterns.values()){
            if (p.getCategory() == selectedCategory || selectedCategory == Category.ALL){
                patternBox.addItem(p);
            }
        }
    }

    // Button that shows all the patterns
    private JButton createShowPatterns(){
        JButton button = createButton("Show patterns");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.showAllPatterns();
                cells.repaint();
            }
        });
        return button;
    }

    // Button that places a random pattern on the board
    private JButton createRandomPattern(){
        JButton button = createButton("Place random pattern");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.placeRandomPattern();
                cells.repaint();
            }
        });
        return button;
    }

    // creates a button that toggles mouse function
    private JToggleButton createToggleMouse() {
        JToggleButton button = new JToggleButton("Drawing off");
        button.setFont(STANDARD_FONT);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (button.isSelected()){
                    button.setText("Drawing on");
                } else {
                    button.setText("Drawing off");
                }
            }
        });

        return button;
    }




}

