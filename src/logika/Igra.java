package logika;

import splosno.KdoIgra;
import splosno.Poteza;
import logika.Group;

public class Igra {
    protected int size;
    protected int[][] grid;
    protected int state;
    public static final int NULL_STATE = 0;
    public static final int BLACK_STATE = 1;
    public static final int WHITE_STATE = -1;
    private KdoIgra[] players;
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
    private void switchState() {
        if (this.state == BLACK_STATE) {
            this.state = WHITE_STATE;
        }
        else {this.state = BLACK_STATE;}
    }

    private boolean isSuicide(Poteza poteza) {
        return false; // ni treba, mogoče samo za običn go
    }
    public boolean odigraj(Poteza poteza) {
        if (this.grid[poteza.y()][poteza.x()] == 0) {
            this.grid[poteza.y()][poteza.x()] = this.state;
            this.switchState();
            return true;
        }
        else return false;
    }
    public boolean gameOver() {
        //todo here goes the game logic
        //check state

    }
}
