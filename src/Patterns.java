

// puts startpatterns on the board
// list of patterns as cells, this class turns those cells on

public enum Patterns {
    //CONSTANTS
    PIXEL("Pixel", "O"),
    GLIDER("Glider", ".O.", "..O", "OOO"),
    BLINKER("Blinker", "OOO"),
    BLOCK("Block", "OO", "OO"),
    TUB("Tub", ".O.", "O.O", ".O."),
    BOAT("Boat", "OO.", "O.O", ".O."),
    SHIP("Ship", "OO.", "O.O", ".OO"),
    BEEHIVE("Beehive", ".OO.", "O..O", ".OO."),
    TOAD("Toad", "OOO.", ".OOO"),
    BARGE("Barge", ".O..", "O.O.", ".O.O", "..O."),
    BEACON("Beacon", "OO..", "O...", "...O", "..OO"),
    LONG_BOAT("Long boat", "OO..", "O.O.", ".O.O", "..O."),
    LOAF("Loaf", ".OO.", "O..O", "O.O.", ".O.."),
    POND("Pond", ".OO.", "O..O", "O..O", ".OO."),
    MANGO("Mango", ".OO..", "O..O.", ".O..O", "..OO."),
    LONG_BARGE("Long Barge", ".O...", "O.O..", ".O.O.","..O.O", "...O."),
    HALF_FLEET("Half Fleet", "OO....", "O.O...", ".OO...", "...OO.", "...O.O", "....OO"),
    BIG_S("Big S", ".....OO.", "....O..O", "....O.OO", ".OO.O...", ".O..O...", "..OO...."),
    BLINKER_SHIP("Blinker Ship", "............O..O...............",
                             "...........O...................",
                             "...........O...O...............",
                             "...OO......OOOO................",
                             "..OOOO.........................",
                             ".OO.OO.........................",
                             "..OO.....OO.OOO................",
                             "........O.....OO.......O....OOO",
                             ".......OO.......O......O....O.O",
                             "........O.....OO.......O....OOO",
                             "..OO.....OO.OOO................",
                             ".OO.OO.........................",
                             "..OOOO.........................",
                             "...OO......OOOO................",
                             "...........O...O...............",
                             "...........O...................",
                             "............O..O..............."),
    CANOE("Canoe", "...OO", "....O", "...O.", "O.O..", "OO..."),
    CLOVERLEAF("Cloverleaf", "...O.O...",
            ".OOO.OOO.",
            "O...O...O",
            "O.O...O.O",
            ".OO.O.OO.",
            ".........",
            ".OO.O.OO.",
            "O.O...O.O",
            "O...O...O",
            ".OOO.OOO.",
            "...O.O..."),
    HAMMERHEAD("Hammerhead", "OOOOO.............",
            "O....O.......OO...",
            "O...........OO.OOO",
            ".O.........OO.OOOO",
            "...OO...OO.OO..OO.",
            ".....O....O..O....",
            "......O.O.O.O.....",
            ".......O..........",
            ".......O..........",
            "......O.O.O.O.....",
            ".....O....O..O....",
            "...OO...OO.OO..OO.",
            ".O.........OO.OOOO",
            "O...........OO.OOO",
            "O....O.......OO...",
            "OOOOO............."),
    GLIDER_GUN("Glider Gun", "........................O...........",
            "......................O.O...........",
            "............OO......OO............OO",
            "...........O...O....OO............OO",
            "OO........O.....O...OO..............",
            "OO........O...O.OO....O.O...........",
            "..........O.....O.......O...........",
            "...........O...O....................",
            "............OO......................");



    // FIELDS
    private final String displayName;
    private final String[] rows;
    private final boolean[][] rowsBool;

    // CONSTRUCTOR
    Patterns(String displayName, String... rows){
        this.displayName = displayName;
        this.rows = rows;
        this.rowsBool = constructPattern();
    }

    // GETTERS
    public String getDisplayName() {
        return displayName;
    }
    public String[] getRows() {
        return rows;
    }
    public boolean[][] getRowsBool(){return rowsBool;}
    public int getWidth(){return rows[0].length();}
    public int getHeight(){return rows.length;}


    // Preview to show pattern
    public String getPreview(){
        StringBuilder output = new StringBuilder();
        for (String row : getRows()){
            output.append (row);
            output.append ("\n");
        }
        if (! output.isEmpty()) {
            output.deleteCharAt(output.length()-1);
        }
        return output.toString();
    }

    // construct pattern into boolean array
    public boolean [][] constructPattern(){
        boolean[][] pattern = new boolean[rows.length][rows[0].length()];
        for (int row = 0; row < rows.length; row++){
            for (int character = 0; character < rows[row].length(); character ++){
                if (rows[row].charAt(character) == 'O'){
                    pattern[row][character] = true;
                } else if (rows[row].charAt(character) == '.'){
                    pattern[row][character] = false;
                } else {
                    throw new IllegalArgumentException(
                    String.format("Pattern %s has another char in the String than . or O in row %d and in character %d", displayName, row, character));
                }

            }
        }

        return pattern;
    }

    // Amendment, so JCombo Dropdown will show names
    @Override
    public String toString() {
        return displayName;
    }
}
