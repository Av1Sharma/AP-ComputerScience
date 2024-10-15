/************************************************************
* Programming Assignment: Minesweeper
*
* TestAviJosh.java test the Minesweeper Game using corresponding
* Cell and Minesweeper classes
*
* YOU ARE NOT SUPPOSED TO MAKE ANY CHANGES TO THIS JAVA FILE
* EXCEPT CLASS NAMES.
*
* *********************************************************/
public class TestAviJosh{ // what is your first name?
public static void main(String[] args){
MinesweeperAviJosh m = new MinesweeperAviJosh(10, 10); // Minesweeper
CellAviJosh[][] ms = m.gameBoard(8, 15); // initialize the board
m.setAllContents(ms); // set contents of each cell
m.printBoard(ms, false); // print the initial board
m.play(ms); // play the game
}
}
