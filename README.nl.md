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

Uit deze eenvoudige regels ontstaan verrassend complexe patronen: stabiele vormen, oscillatoren, ruimteschepen die over het speelveld bewegen, en kanonnen die steeds nieuwe ruimteschepen afschieten.

## Functies

**Simulatie**
- Weergave van het speelveld als raster in een Swing-venster: levende cellen wit, dode cellen zwart
- Berekening van de volgende generatie volgens de regels van Conway; cellen buiten het speelveld gelden als dood
- Automatisch verloop via een Swing-timer met een Start/Stop-knop; de vertraging tussen twee generaties (in milliseconden) is in te stellen met een spinner, en het speelveld wordt daarna automatisch bijgewerkt
- Efficiënte weergave: het speelveld wordt eerst in een `BufferedImage` geschreven (één pixel per cel) en daarna in één keer vergroot getekend, in plaats van elke cel apart te tekenen met `fillRect`. Dat bespaart rekentijd (processorbelasting)
- Efficiënte berekening: per generatie worden twee arrays omgewisseld, in plaats van steeds een nieuwe array te maken
- Het venster opent gemaximaliseerd; het speelveld past zich automatisch aan de venstergrootte en daarmee aan de schermresolutie aan, ook als het venster later groter of kleiner wordt gemaakt. Bestaande cellen blijven daarbij behouden
- De celgrootte (1–10 pixels per cel) is in te stellen met een spinner; het aantal rijen en kolommen wordt daarna automatisch opnieuw berekend

**Het speelveld vullen**
- **Random:** vult het speelveld willekeurig; het aandeel levende cellen is in te stellen met een spinner (0–100 %)
- **Empty Board:** maakt het speelveld leeg
- **Show patterns:** toont alle beschikbare patronen naast en onder elkaar, zonder dat ze elkaar raken
- **Place random pattern:** zet een willekeurig patroon op een willekeurige plek waar het helemaal op het speelveld past

**Patroonkeuze**
- Twee gekoppelde dropdownmenu's: eerst kies je een categorie, daarna toont het tweede menu alleen de patronen uit die categorie. Met „All patterns" zie je alle patronen.
- Bij elke categorie verschijnt bovenaan het speelveld een korte uitleg.

**Tekenen met de muis**
- Met de knop „Drawing on/off" zet je het tekenen aan of uit.
- Een muisklik plaatst het gekozen patroon, gecentreerd rond de muispositie.
- Met het patroon „Pixel" zet je losse cellen; slepen met ingedrukte muisknop tekent een lijn.
- Bij andere patronen laat slepen een spoor van stempels achter, met een korte vertraging tussen twee stempels.
- Tekenen werkt zowel op een leeg speelveld als terwijl de simulatie loopt.

## Meegeleverde patronen

De patronen zijn ingedeeld in categorieën:

| Categorie            | Patronen                                                                                   |
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
| Pixel                | Pixel (één losse cel, om mee te tekenen)                                                   |

De patronen zijn opgeslagen in plaintext-formaat (`.` = dood, `O` = levend), hetzelfde formaat als op de [LifeWiki](https://conwaylife.com/wiki/). Een nieuw patroon voeg je toe met één regel in de enum `Patterns`: weergavenaam, categorie en de rijen van het patroon. Bevat een patroon een ander teken dan `.` of `O`, dan geeft het programma bij het opstarten een duidelijke foutmelding, met de naam, de rij en de positie.

## Projectstructuur

| Klasse      | Taak                                                                                                     |
|-------------|----------------------------------------------------------------------------------------------------------|
| `Main`      | Start het programma                                                                                      |
| `Frame`     | Hoofdvenster (JFrame): speelveld, bedieningselementen, dropdownmenu's, timer en categorie-uitleg         |
| `Board`     | Beheert het speelveld als `boolean[][]`; willekeurig vullen, patronen plaatsen, alle patronen tonen      |
| `Rules`     | Telt de levende buren, berekent de volgende generatie en levert hulpmethodes (randcontrole, willekeurige posities) |
| `Cells`     | Tekenvlak (JPanel) dat het speelveld weergeeft en muisposities omrekent naar rijen en kolommen           |
| `Movements` | Muisbediening: klikken en slepen om cellen en patronen te plaatsen                                       |
| `Patterns`  | Enum met alle patronen, elk met weergavenaam, categorie, rijen, breedte, hoogte en tekstvoorbeeld        |
| `Category`  | Enum met de categorieën van de patronen, elk met weergavenaam en uitleg                                  |

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
3. De klasse `Main` uitvoeren.

## Status

Het programma is volledig werkend. Mogelijke uitbreidingen:
- Een eigen kleur per patroon
- De rekentijd per generatie tonen, en parallel rekenen

## Auteur

**Anja Boin**
Stage bij Bruckmann Steuerungstechnik GmbH
