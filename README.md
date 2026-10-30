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

Aus diesen einfachen Regeln entstehen überraschend komplexe Muster: stabile Formen, Oszillatoren und Raumschiffe, die über das Spielfeld wandern.

## Funktionen

- Darstellung des Spielfelds als Raster in einem Swing-Fenster
- Lebende Zellen farbig, tote Zellen schwarz
- Berechnung der nächsten Generation nach Conways Regeln
- Automatischer Ablauf der Generationen über einen Swing-Timer
- Vordefinierte Muster, auswählbar über ein Dropdown-Menü
- Muster per Mausklick an einer beliebigen Stelle im Spielfeld platzieren
- Einzelne Zellen per Mausklick ein- und ausschalten
- Textvorschau jedes Musters

## Enthaltene Muster

| Muster             | Typ                         |
|--------------------|-----------------------------|
| Block              | Stabile Form (Still Life)   |
| Tub                | Stabile Form (Still Life)   |
| Boat               | Stabile Form (Still Life)   |
| Blinker            | Oszillator                  |
| Cloverleaf         | Oszillator                  |
| Glider             | Raumschiff                  |
| Hammerhead         | Raumschiff                  |
| Gosper Glider Gun  | Kanone (erzeugt Glider)     |

Die Muster werden im Klartextformat gespeichert (`.` = tot, `O` = lebendig), wie es auch im [LifeWiki](https://conwaylife.com/wiki/) verwendet wird. Neue Muster lassen sich mit einer einzigen Zeile im Enum `Patterns` hinzufügen.

## Projektstruktur

| Klasse      | Aufgabe                                                                                   |
|-------------|-------------------------------------------------------------------------------------------|
| `Frame`     | Hauptfenster (JFrame), fügt Spielfeld und Bedienelemente zusammen                         |
| `Board`     | Verwaltet das Spielfeld als `boolean[][]` (lebendig / tot)                                |
| `Rules`     | Zählt die lebenden Nachbarn und berechnet die nächste Generation                          |
| `Cells`     | Zeichenfläche (JPanel), die das Spielfeld darstellt                                       |
| `Patterns`  | Enum mit den vordefinierten Mustern, jeweils mit Anzeigename, Zeilen und Vorschau         |
| `Movements` | Bedienung: Mausklicks, Musterauswahl und Timer für den automatischen Ablauf               |

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
3. Die Klasse mit der `main`-Methode ausführen.

## Status

Das Projekt befindet sich noch in Entwicklung. Die Spiellogik (Nachbarn zählen, nächste Generation berechnen) und die Muster sind fertig; die grafische Darstellung und die Bedienung folgen.

## Autorin

**Anja Boin**
Praktikum bei der Bruckmann Steuerungstechnik GmbH
