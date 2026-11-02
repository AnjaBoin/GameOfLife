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

These simple rules give rise to surprisingly complex patterns: stable shapes, oscillators and spaceships that travel across the board.

## Features

- Display of the board as a grid in a Swing window
- Living cells in colour, dead cells in black
- Calculation of the next generation according to Conway's rules
- Automatic progression of generations using a Swing timer
- Predefined patterns, selectable from a drop-down menu
- Place patterns anywhere on the board with a mouse click
- Toggle individual cells on and off with a mouse click
- Text preview of each pattern

## Included patterns

| Pattern            | Type                        |
|--------------------|-----------------------------|
| Block              | Still life                  |
| Tub                | Still life                  |
| Boat               | Still life                  |
| Blinker            | Oscillator                  |
| Cloverleaf         | Oscillator                  |
| Glider             | Spaceship                   |
| Hammerhead         | Spaceship                   |
| Gosper Glider Gun  | Gun (produces gliders)      |

The patterns are stored in plaintext format (`.` = dead, `O` = alive), the same format used on the [LifeWiki](https://conwaylife.com/wiki/). New patterns can be added with a single line in the `Patterns` enum.

## Project structure

| Class       | Responsibility                                                                            |
|-------------|-------------------------------------------------------------------------------------------|
| `Frame`     | Main window (JFrame), combines the board and the controls                                 |
| `Board`     | Manages the board as a `boolean[][]` (alive / dead)                                       |
| `Rules`     | Counts the living neighbours and calculates the next generation                           |
| `Cells`     | Drawing surface (JPanel) that displays the board                                          |
| `Patterns`  | Enum with the predefined patterns, each with display name, rows and preview               |
| `Movements` | Controls: mouse clicks, pattern selection and timer for automatic progression             |

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
3. Run the class containing the `main` method.

## Status

The project is still under development. The game logic (counting neighbours, calculating the next generation) and the patterns are finished; the graphical display and the controls will follow.

## Author

**Anja Boin**
Internship at Bruckmann Steuerungstechnik GmbH
