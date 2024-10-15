public class CellAviJosh {
    private boolean hasMine;
    private boolean isEmpty;
    private int surroundingMines;
    private boolean isRevealed;
    private String printedSymbol;
    private boolean isFlagged;

    // Constructors
    public CellAviJosh(boolean hasMine, boolean isEmpty, int surroundingMines, boolean isRevealed, String printedSymbol) {
        this.hasMine = hasMine;
        this.isEmpty = isEmpty;
        this.surroundingMines = surroundingMines;
        this.isRevealed = isRevealed;
        this.printedSymbol = printedSymbol;
        this.isFlagged = false;
    }

    // Getters and setters
    public boolean hasMine() {
        return hasMine;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public int getSurroundingMines() {
        return surroundingMines;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public String getPrintedSymbol() {
        return printedSymbol;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public void setPrintedSymbol(String symbol) {
        printedSymbol = symbol;
    }

    public void setSurroundingMines(int surroundingMines) {
        this.surroundingMines = surroundingMines;
    }

    public void setEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public void toggleFlag() {
        isFlagged = !isFlagged;
    }
}
