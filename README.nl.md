[🇩🇪 Deutsch](README.md) | [🇬🇧 English](README.en.md) | 🇳🇱 Nederlands

# Game of Life

Een Java-applicatie die Conways „Game of Life" simuleert met een grafische gebruikersinterface (Java Swing).

## Over dit project

Dit project is ontstaan tijdens mijn stage bij **Bruckmann Steuerungstechnik GmbH**. De opdracht was om Conways Game of Life in Java te programmeren. Het resultaat staat in deze repository.

## Wat is Game of Life?

Game of Life is een cellulaire automaat die de wiskundige John Horton Conway in 1970 bedacht. Op een raster is elke cel levend of dood. Van generatie tot generatie bepalen de acht buurcellen het lot van elke cel:

- Een levende cel met minder dan twee levende buren sterft (eenzaamheid).
- Een levende cel met twee of drie levende buren blijft leven.
- Een levende cel met meer dan drie levende buren sterft (overbevolking).
- Een dode cel met precies drie levende buren komt tot leven (geboorte).

Uit deze eenvoudige regels ontstaan verrassend complexe patronen: stabiele vormen, oscillatoren en ruimteschepen die over het speelveld bewegen.

## Functies

- Weergave van het speelveld als raster in een Swing-venster
- Levende cellen in kleur, dode cellen zwart
- Berekening van de volgende generatie volgens de regels van Conway
- Automatisch verloop van de generaties via een Swing-timer
- Voorgedefinieerde patronen, te kiezen via een dropdownmenu
- Patronen met een muisklik op een willekeurige plek in het speelveld plaatsen
- Losse cellen met een muisklik aan- en uitzetten
- Tekstvoorbeeld van elk patroon

## Meegeleverde patronen

| Patroon            | Type                          |
|--------------------|-------------------------------|
| Block              | Stabiele vorm (still life)    |
| Tub                | Stabiele vorm (still life)    |
| Boat               | Stabiele vorm (still life)    |
| Blinker            | Oscillator                    |
| Cloverleaf         | Oscillator                    |
| Glider             | Ruimteschip                   |
| Hammerhead         | Ruimteschip                   |
| Gosper Glider Gun  | Kanon (produceert gliders)    |

De patronen zijn opgeslagen in plaintext-formaat (`.` = dood, `O` = levend), hetzelfde formaat als op de [LifeWiki](https://conwaylife.com/wiki/). Nieuwe patronen voeg je toe met één regel in de enum `Patterns`.

## Projectstructuur

| Klasse      | Taak                                                                                      |
|-------------|-------------------------------------------------------------------------------------------|
| `Frame`     | Hoofdvenster (JFrame), brengt speelveld en bedieningselementen samen                      |
| `Board`     | Beheert het speelveld als `boolean[][]` (levend / dood)                                   |
| `Rules`     | Telt de levende buren en berekent de volgende generatie                                   |
| `Cells`     | Tekenvlak (JPanel) dat het speelveld weergeeft                                            |
| `Patterns`  | Enum met de voorgedefinieerde patronen, elk met weergavenaam, rijen en voorbeeld          |
| `Movements` | Bediening: muisklikken, patroonkeuze en timer voor het automatische verloop               |

## Gebruikte technologieën

- Java
- Java Swing voor de gebruikersinterface
- IntelliJ IDEA
- Git en GitHub

## Vereisten

Het project heeft alleen een actuele JDK nodig. Externe bibliotheken zijn niet nodig.

## Starten

1. Repository klonen:
   ```
   git clone https://github.com/AnjaBoin/GameOfLife_Swing_public.git
   ```
2. Project openen in IntelliJ IDEA.
3. De klasse met de `main`-methode uitvoeren.

## Status

Het project is nog in ontwikkeling. De spellogica (buren tellen, volgende generatie berekenen) en de patronen zijn klaar; de grafische weergave en de bediening volgen nog.

## Auteur

**Anja Boin**
Stage bij Bruckmann Steuerungstechnik GmbH
