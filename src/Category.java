// this Class determines the categories of the patterns

public enum Category {
    // CONSTANTS
    ALL("All patterns", ""),
    BLINKER_SHIP("Blinker Ship", "A growing spaceship in which the wick consists of a line of blinkers."),
    EATER("Eater", "A still life is called an eater when it can be used to absorb some other pattern (often a glider, spaceship, or the debris from a more complicated reaction) and returns to its original state after the collision."),
    GROWTH_PATTERNS("Growth patterns", "Patterns that keep leaving debris, signals, or repeated output behind. They are a natural bridge from small examples to the advanced infinite board."),
    GUNS("Guns", "Periodic factories that emit spaceships. They connect local reactions to larger signal circuits and computing constructions."),
    METHUSELAHS("Methuselahs", "Small seeds with unexpectedly long lifetimes. They are useful for studying growth, stabilization, and emergent debris fields."),
    OSCILLATORS("Oscillators", "Patterns that return to their starting phase after a fixed number of generations. They make periods, phases, and symmetry visible."),
    PIXEL("Pixel", "Just one living cell"),
    PSEUDO_STILL_LIFES("Pseudo still lifes", "A pseudo still life consists of two or more adjacent islands which can be partitioned (either individually or as sets) into non-interacting subpatterns which are by themselves each still lifes"),
    SPACESHIPS("Spaceships", "Self-contained patterns that reappear translated across the grid. They are the basic signal carriers of Life engineering."),
    STILL_LIFES("Still lifes", "Stable objects that never change after the first generation. They are the anchors and catalysts used to contain more active reactions."),
    STRICT_STILL_LIFES("Strict still lifes", "A strict still life is a still life that is either connected (i.e., has only one island), or is such that removing one or more its islands destroys the stability of the pattern.");

    private final String displayName;
    private final String description;

    // CONSTRUCTOR
    Category(String displayName, String description){
        this.displayName = displayName;
        this.description = description;
    }

    // getters
    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    // Amendment, so JCombo Dropdown will show names
    @Override
    public String toString() {
        return displayName;
    }
}
