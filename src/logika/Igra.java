package logika;

import splosno.KdoIgra;
import splosno.Poteza;

import java.util.LinkedList;
import java.util.List;

public class Igra {
    public int size;
    public int[][] grid;
    public int state;
    public static final int NULL_STATE = 0;
    public static final int BLACK_STATE = 1;
    public static final int WHITE_STATE = 2;

    public static final int CAPTURED_BLACK = 3;
    public static final int CAPTURED_WHITE = 4;

    protected static final Poteza[] directions = {new Poteza(1,0), new Poteza(-1, 0), new Poteza(0,1), new Poteza(0, -1)};
    private KdoIgra[] players;
    private KdoIgra currentPlayer;
    public List<Group> groups;

    public Igra() {
        size = 9;
        grid = makeGrid(size);
        state = BLACK_STATE;
        groups = new LinkedList<>();
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
    private boolean isAlone(Poteza poteza) {
        boolean alone = true;
        //System.out.println("Checking neighbours for poteza " + poteza.x() + ", " + poteza.y() + ":");
        //todo check boundary conditions
        for (Poteza dir : directions) {
            Poteza p = new Poteza(poteza.x() + dir.x(), poteza.y() + dir.y());
            //System.out.println("Checking poteza " + p);
            if (0 <= p.x() && p.x() < this.size) {
                if (0 <= p.y() && p.y() < this.size) {
                    //System.out.println("    " + p + " is within bounds");
                    alone = this.grid[p.y()][p.x()] != this.state;
                }
                //else {
                //    System.out.println("    " + p + " is within bounds in x dir");
                //    alone = this.grid[poteza.y()][p.x()] != this.state;
                //}
            }
            //else {
            //    if (0 <= poteza.y() + dir.y() && poteza.y() + dir.y() < this.size) {
            //        System.out.println("    " + p + " is out of bounds in x dir");
            //        alone = this.grid[p.y()][poteza.x()] != this.state;
            //    }
            //}
            if (!alone) break;
        }
        return alone;
    }

    private Group isInGroup(Poteza poteza) {
        for (Group g : this.groups) {
            if (g.contains(poteza)) {
                return g;
            }
        }
        return null;
    }

    private int groupState(Group g) {
        return g.group.get(g.group.keySet().iterator().next());
    }

    private boolean isSuicide(Poteza poteza) {
        return false; // ni treba, mogoče samo za običn go
    }
    public void resetLiberties (Group g) {
        g.liberties.clear();
        for (Poteza p : g.group.keySet()) {
            for (Poteza dir : directions) {
                Poteza p1 = new Poteza(p.x() + dir.x(), p.y() + dir.y());
                //Check boundary constraints
                if (0 <= p1.x() && p1.x() < size && 0 <= p1.y() && p1.y() < size ) {
                    //Check for open position
                    if (this.grid[p1.y()][p1.x()] == 0) {
                        if (!g.liberties.contains(p1) && !g.group.containsKey(p1)) {
                            g.liberties.add(0, p1);
                        }
                    }
                }
            }
        }
    }
    private void resetAllGroups() {
        for (Group g : this.groups) {
            this.resetLiberties(g);
        }
    }
    public boolean odigraj(Poteza poteza) {
        //System.out.println("current state is " + this.state);
        if (this.state == CAPTURED_BLACK || this.state == CAPTURED_WHITE) {return false;}
        // this only happens once, does not let any other Poteza play
        // used mostly for testing purposes, as the game closes immediately
        if (this.grid[poteza.y()][poteza.x()] == 0 && !isSuicide(poteza)) {
            this.grid[poteza.y()][poteza.x()] = this.state;
            //System.out.println(poteza +" is alone " + this.isAlone(poteza));
            //todo check if poteza is alone
            //todo okej tuki skos ne pride
            if (!this.isAlone(poteza)) {
                for (Poteza dir : directions) {
                    //todo find the first nonempty group
                    Group g = this.isInGroup(new Poteza(poteza.x() + dir.x(), poteza.y() + dir.y()));
                    if (g != null && this.groupState(g) == this.state) {
                        //System.out.println("found group " + g + " with state " + this.groupState(g));
                        g.addTo(poteza, this.state);
                        //System.out.println("Added poteza " + poteza.x() + ", " + poteza.y() + " to group " + g);
                        //todo add the poteza to the group
                        for (Poteza dir2 : directions) {
                            //todo merge other groups with group
                            Group h = this.isInGroup(new Poteza(poteza.x() + dir2.x(), poteza.y() + dir2.y()));
                            if (h != g && h != null && this.groupState(g) == this.groupState(h)) {
                                g.mergeGroup(h);
                                //System.out.println("Merged group " + h + " to " + g);
                                this.groups.remove(h);
                            }
                        }
                        break;
                    }
                }
            } else {
                Group g = new Group(this.size);
                //System.out.println("placed new group " + g + " at position " + poteza.x() + ", " + poteza.y());
                g.addTo(poteza, this.state);
                this.resetLiberties(g);
                this.groups.add(g);
                //System.out.println("added group " + g + " to game groups");
            }
            //merge with other groups
            //System.out.println("Reached the end of 'odigraj' block");
            this.resetAllGroups();
            this.switchState();
            //System.out.println("Switched state to " + this.state );
            //System.out.println("----------------------");
            //System.out.println();
            if (this.gameOver() != null) {
                this.close();
            }
            return true;
        }
        return false;
    }

    private void close() {
        if (this.state == BLACK_STATE) {this.state = CAPTURED_WHITE;}
        else { this.state = CAPTURED_BLACK;}
    }
    public Group gameOver() {
        //todo here goes the game logic
        //check state
        for (Group g : groups) {
            if (g.isCaptured()) {
                // check if this actually happened
                System.out.println("Group " + g + " is captured");
                for (Poteza p : g.group.keySet()) {
                    int locState = (this.grid[p.y()][p.x()] == BLACK_STATE ? CAPTURED_BLACK : CAPTURED_WHITE);
                    g.group.replace(p, locState);
                    this.grid[p.y()][p.x()] = locState;
                }
                return g;
            }
        }
        return null;
    }

    public List<Poteza> poteze() {
        List<Poteza> poteze = new LinkedList<>();
        for (int i = 0; i < this.size; ++i) {
            for (int j = 0; j < this.size; ++j) {
                if (this.grid[i][j] == 0) poteze.add(new Poteza(j,i));
            }
        }
        return poteze;
    }
    public void printGame() {
        for (int i = 0; i < this.size; ++i) {
            System.out.print("|");
            for (int j = 0; j < this.size; ++j) {
                System.out.print(this.grid[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static Igra copy(Igra igra) {
    Igra kopija = new Igra();
    kopija.state = igra.state;
    for (int i = 0; i < igra.size; ++i) {
        for (int j = 0; j < igra.size; ++j) {
            kopija.grid[j][i] = igra.grid[j][i];
        }
    }
    kopija.resetAllGroups();
    return kopija;
    }
}
