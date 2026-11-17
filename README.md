🇩🇪 Deutsch | [🇬🇧 English](README.en.md) | [🇳🇱 Nederlands](README.nl.md)

# Game of Life

Eine Java-Anwendung, die Conways „Game of Life" mit grafischer Oberfläche (Java Swing) simuliert.

## Über dieses Projekt

Dieses Projekt ist im Rahmen meines Praktikums bei der **Bruckmann Steuerungstechnik GmbH** entstanden. Die Aufgabe bestand darin, Conways Game of Life in Java zu programmieren. Das Ergebnis ist in diesem Repository zu finden.

## Was ist Game of Life?

Das Game of Life ist ein zellulärer Automat, den der Mathematiker John Horton Conway 1970 entwickelt hat. Auf einem Raster ist jede Zelle entweder lebendig oder tot. Von Generation zu Generation entscheiden die acht Nachbarzellen über das Schicksal jeder Zelle:

- Eine lebende Zelle mit weniger als zwei lebenden Nachbarn stirbt (Vereinsamung).
- Eine lebende Zelle mit zwei oder drei lebenden Nachbarn bleibt am Leben.
- Eine lebende Zelle mit mehr als drei lebenden Nachbarn stirbt (Überbevölkerung).
- Eine tote Zelle mit genau drei lebenden Nachbarn wird lebendig (Geburt).

Aus diesen einfachen Regeln entstehen überraschend komplexe Muster: stabile Formen, Oszillatoren, Raumschiffe, die über das Spielfeld wandern, und Kanonen, die immer neue Raumschiffe erzeugen.

## Funktionen

**Simulation**
- Darstellung des Spielfelds als Raster in einem Swing-Fenster: lebende Zellen weiß, tote Zellen schwarz
- Berechnung der nächsten Generation nach Conways Regeln; Zellen außerhalb des Spielfelds gelten als tot
- Automatischer Ablauf über einen Swing-Timer (10 Generationen pro Sekunde), mit einer Start/Stop-Taste
- Effiziente Berechnung: zwei Arrays werden pro Generation getauscht, statt jedes Mal ein neues Array anzulegen

**Spielfeld füllen**
- **Random:** füllt das Spielfeld zufällig; der Anteil lebender Zellen ist über einen Regler einstellbar (0–100 %)
- **Empty Board:** leert das Spielfeld
- **Show patterns:** zeigt alle verfügbaren Muster nebeneinander und untereinander, ohne dass sie sich berühren
- **Place random pattern:** setzt ein zufälliges Muster an eine zufällige Stelle, an der es vollständig auf das Spielfeld passt

**Musterauswahl**
- Zwei verknüpfte Dropdown-Menüs: Zuerst wird eine Kategorie gewählt, danach zeigt das zweite Menü nur die Muster dieser Kategorie. Mit „All patterns" sind alle Muster sichtbar.
- Zu jeder Kategorie erscheint oben auf dem Spielfeld eine kurze Erklärung.

**Zeichnen mit der Maus**
- Über die Taste „Drawing on/off" wird das Zeichnen ein- oder ausgeschaltet.
- Ein Mausklick setzt das gewählte Muster, zentriert um die Mausposition.
- Mit dem Muster „Pixel" lassen sich einzelne Zellen setzen; beim Ziehen mit gedrückter Maustaste entsteht eine Linie.
- Bei anderen Mustern wird beim Ziehen eine Spur von Stempeln gesetzt, mit einer kurzen Verzögerung zwischen zwei Stempeln.
- Das Zeichnen funktioniert sowohl auf einem leeren Spielfeld als auch während die Simulation läuft.

## Enthaltene Muster

Die Muster sind in Kategorien eingeteilt:

| Kategorie            | Muster                                                                                     |
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
| Pixel                | Pixel (eine einzelne Zelle, zum Zeichnen)                                                  |

Die Muster werden im Klartextformat gespeichert (`.` = tot, `O` = lebendig), wie es auch im [LifeWiki](https://conwaylife.com/wiki/) verwendet wird. Ein neues Muster lässt sich mit einem einzigen Eintrag im Enum `Patterns` hinzufügen: Anzeigename, Kategorie und die Zeilen des Musters. Enthält ein Muster ein anderes Zeichen als `.` oder `O`, meldet das Programm beim Start eine verständliche Fehlermeldung mit Name, Zeile und Position.

## Projektstruktur

| Klasse      | Aufgabe                                                                                                  |
|-------------|----------------------------------------------------------------------------------------------------------|
| `Main`      | Startet das Programm                                                                                     |
| `Frame`     | Hauptfenster (JFrame): Spielfeld, Bedienelemente, Dropdown-Menüs, Timer und Kategoriebeschreibung        |
| `Board`     | Verwaltet das Spielfeld als `boolean[][]`; zufälliges Füllen, Muster platzieren, alle Muster anzeigen    |
| `Rules`     | Zählt die lebenden Nachbarn, berechnet die nächste Generation und liefert Hilfsmethoden (Randprüfung, Zufallspositionen) |
| `Cells`     | Zeichenfläche (JPanel), die das Spielfeld darstellt und Mauspositionen in Zeilen und Spalten umrechnet   |
| `Movements` | Maussteuerung: Klicken und Ziehen zum Setzen von Zellen und Mustern                                       |
| `Patterns`  | Enum mit allen Mustern, jeweils mit Anzeigename, Kategorie, Zeilen, Breite, Höhe und Textvorschau        |
| `Category`  | Enum mit den Kategorien der Muster, jeweils mit Anzeigename und Erklärung                                |

## Verwendete Technologien

- Java
- Java Swing für die Benutzeroberfläche
- IntelliJ IDEA
- Git und GitHub

## Voraussetzungen

Für das Projekt wird nur ein aktuelles JDK benötigt. Externe Bibliotheken sind nicht erforderlich.

## Starten

1. Repository klonen:
   ```
   git clone https://github.com/AnjaBoin/GameOfLife_Swing_public.git
   ```
2. Projekt in IntelliJ IDEA öffnen.
3. Die Klasse `Main` ausführen.

## Status

Das Programm ist funktionsfähig. Mögliche Erweiterungen:
- Das Spielfeld automatisch an die Bildschirmgröße anpassen
- Eine eigene Farbe pro Muster
- Anzeige der Rechenzeit pro Generation und paralleles Rechnen

## Autorin

**Anja Boin**
Praktikum bei der Bruckmann Steuerungstechnik GmbH
