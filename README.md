### PL: <br/> 

Program będący automatem komórkowym 'Gra w życie' wg najpopularniejszego modelu Conwaya. W menu głównym mamy możliwość wyboru rozmiaru planszy (10x10, 20x20, 50x50 lub 100x100 komórek), następnie położenia 'żywych' komórek na planszy (możliwość ciągłego zaznaczania) oraz częstotliwości odświeżania planszy. W każdym cyklu każda żywa komórka posiadająca 2 lub 3 żywych sąsiadów pozostaje żywa, a każda martwa komórka posiadająca dokładnie 3 żywych sąsiadów staje się żywa. W każdym innym przypadku komórki stają się lub pozostają martwe. <br/>
Program zrealizowany przy użyciu biblioteki JavaFX. Wykorzystano wzorzec projektowy Model-Widok-Kontroler. Stany komórek w każdym cyklu są przechowywane w dwuwymiarowej tablicy typu boolean. <br/>

[Więcej informacji o grze](https://pl.wikipedia.org/wiki/Gra_w_%C5%BCycie) <br/>

---
### ENG: <br/> 

A program being a 'Game of life' cellular automaton created under Conway's rules. In the main menu there is a possibility to choose a board size (10x10, 20x20, 50x50 lub 100x100 cells), location of 'live' cells on the board (continous marking option included) and a frequency of refreshing board. <br/>
+ Any live cell with fewer than two live neighbours dies, as if caused by underpopulation. <br/>
+ Any live cell with two or three live neighbours lives on to the next generation. <br/>
+ Any live cell with more than three live neighbours dies, as if by overpopulation. <br/>
+ Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction. <br/>

Program created using JavaFX library. Model-View-Controller design pattern was used. Cells' states in every cycle are saved in two-dimmensional array of booleans. <br/> 

[More informations about the game](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life) <br/>
