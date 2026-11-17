[🇩🇪 Deutsch](README.md) | 🇬🇧 English | [🇳🇱 Nederlands](README.nl.md)

# Game of Life

A Java application that simulates Conway's "Game of Life" with a graphical user interface (Java Swing).

## About this project

This project was created during my internship at **Bruckmann Steuerungstechnik GmbH**. The task was to program Conway's Game of Life in Java. The result can be found in this repository.

## What is the Game of Life?

The Game of Life is a cellular automaton devised by the mathematician John Horton Conway in 1970. On a grid, every cell is either alive or dead. From one generation to the next, the eight neighbouring cells decide the fate of each cell:

- A living cell with fewer than two living neighbours dies (underpopulation).
- A living cell with two or three living neighbours stays alive.
- A living cell with more than three living neighbours dies (overpopulation).
- A dead cell with exactly three living neighbours comes to life (birth).

These simple rules give rise to surprisingly complex patterns: stable shapes, oscillators, spaceships that travel across the board, and guns that keep producing new spaceships.

## Features

**Simulation**
- Display of the board as a grid in a Swing window: living cells white, dead cells black
- Calculation of the next generation according to Conway's rules; cells outside the board count as dead
- Automatic progression using a Swing timer (10 generations per second), with a Start/Stop button
- Efficient calculation: two arrays are swapped every generation instead of creating a new array each time

**Filling the board**
- **Random:** fills the board randomly; the share of living cells can be set with a spinner (0–100 %)
- **Empty Board:** clears the board
- **Show patterns:** shows all available patterns next to and below each other, without touching
- **Place random pattern:** places a random pattern at a random position where it fits completely on the board

**Pattern selection**
- Two linked drop-down menus: first choose a category, then the second menu only shows the patterns of that category. With "All patterns", every pattern is shown.
- For each category, a short explanation appears at the top of the board.

**Drawing with the mouse**
- The "Drawing on/off" button switches drawing on or off.
- A mouse click places the selected pattern, centred on the mouse position.
- With the "Pixel" pattern you set single cells; dragging with the mouse button held down draws a line.
- With other patterns, dragging leaves a trail of stamps, with a short delay between two stamps.
- Drawing works both on an empty board and while the simulation is running.

## Included patterns

The patterns are divided into categories:

| Category             | Patterns                                                                                   |
|----------------------|--------------------------------------------------------------------------------------------|
| Still lifes          | Barge, Block, Canoe, Long Barge, Long boat, Mango, Pond, Tub                               |
| Strict still lifes   | Beehive, Big S, Boat, Loaf, Ship                                                           |
| Pseudo still lifes   | Half Fleet, Quad pseudo still life, Triple pseudo still life                               |
| Eater                | Century eater, Eater 1, Eater 3, Eater 5, Glider eater, Demonstration of eaters            |
| Oscillators          | Beacon, Blinker, Cloverleaf, Pentadecathlon, Pulsar, Quadpole, Queen bee shuttle, Toad     |
| Spaceships           | Glider, Hammerhead, Heavyweight spaceship, Hivenudger, Lightweight spaceship               |
| Blinker Ship         | Blinker Ship                                                                               |
| Guns                 | B52 Bomber, Bi gun, Gosper glider Gun, Medusa, Simkin glider gun, Vacuum                   |
| Growth patterns      | Block-laying switch engine, Noahs Arc, Pufferfish                                          |
| Methuselahs          | Acorn, Diehard, Edna, Hershel, R-pentomino                                                 |
| Pixel                | Pixel (a single cell, for drawing)                                                         |

The patterns are stored in plaintext format (`.` = dead, `O` = alive), the same format used on the [LifeWiki](https://conwaylife.com/wiki/). A new pattern can be added with a single entry in the `Patterns` enum: display name, category and the rows of the pattern. If a pattern contains a character other than `.` or `O`, the program reports a clear error message at start-up, with the name, row and position.

## Project structure

| Class       | Responsibility                                                                                           |
|-------------|----------------------------------------------------------------------------------------------------------|
| `Main`      | Starts the program                                                                                       |
| `Frame`     | Main window (JFrame): board, controls, drop-down menus, timer and category description                   |
| `Board`     | Manages the board as a `boolean[][]`; random filling, placing patterns, showing all patterns             |
| `Rules`     | Counts the living neighbours, calculates the next generation and provides helper methods (edge check, random positions) |
| `Cells`     | Drawing surface (JPanel) that displays the board and converts mouse positions into rows and columns      |
| `Movements` | Mouse control: clicking and dragging to set cells and patterns                                           |
| `Patterns`  | Enum with all patterns, each with display name, category, rows, width, height and text preview           |
| `Category`  | Enum with the pattern categories, each with display name and explanation                                 |

## Technologies used

- Java
- Java Swing for the user interface
- IntelliJ IDEA
- Git and GitHub

## Requirements

The project only requires a current JDK. No external libraries are needed.

## Getting started

1. Clone the repository:
   ```
   git clone https://github.com/AnjaBoin/GameOfLife_Swing_public.git
   ```
2. Open the project in IntelliJ IDEA.
3. Run the `Main` class.

## Status

The program is fully functional. Possible extensions:
- Automatically adapting the board to the screen size
- A colour of its own for each pattern
- Showing the calculation time per generation, and parallel calculation

## Author

**Anja Boin**
Internship at Bruckmann Steuerungstechnik GmbH
