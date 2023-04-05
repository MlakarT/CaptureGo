import splosno.KdoIgra;
import splosno.Poteza;

public class Igra {
    protected int size;
    protected int[][] grid;
    protected int state;
    static final int NULL_STATE = 0;
    static final int BLACK_STATE = 1;
    static final int WHITE_STATE = -1;
    private Poteza turn;
    private KdoIgra currentPlayer;




    public Igra() {
        size = 9;
        grid = makeGrid(size);
        state = BLACK_STATE;
    }
    private int[][] makeGrid (int n) {
        grid = new int[n][n];
        return grid;
    }

    public boolean odigraj(Poteza poteza) {
        if (this.grid[poteza.y()][poteza.x()] == 0) return true;
        else return false;
    }

}
