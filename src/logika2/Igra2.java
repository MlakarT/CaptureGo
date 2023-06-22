package logika2;

import logika.Igra;
import logika2.Group2;
import splosno.KdoIgra;
import splosno.Poteza;

import java.util.LinkedList;
import java.util.List;
public class Igra2 {
    public int size;
    public char[][] grid;
    public KdoIgra black;
    public KdoIgra white;
    public KdoIgra naVrsti;
    public KdoIgra winner = null;
    public boolean gameOver = false;
    protected static final Poteza[] directions = {new Poteza(1,0),new Poteza(0,1),new Poteza(-1,0),new Poteza(0,-1)};
    public List<Group2> groups;
    public List<Poteza> moznePoteze;
    public List<Poteza> izvedenePoteze;
    public Igra2 (int size, KdoIgra player1, KdoIgra player2) {
        this.size = size;
        this.grid = makeGrid(size);
        this.black = player1;
        this.white = player2;
        this.naVrsti = black;
        this.groups = new LinkedList<>();
        this.moznePoteze = new LinkedList<>();
        for (int j = 0; j < size; ++j) {
            for (int i = 0; i < size; ++i) {
                grid[j][i] = 'e';
                this.moznePoteze.add(new Poteza(i, j));
                //merkamo vrstni red
            }
        }
        this.izvedenePoteze = new LinkedList<>();
    }
    private static char[][] makeGrid(int size) {
        return new char[size][size];
    }
    public void nasprotnik() {
        naVrsti = (naVrsti == black ? white : black);
    }

    public char barva(KdoIgra barva) {
        return (barva == black ? 'B' : 'W');
    }
    public KdoIgra barvaFromGrid(Poteza p) {
        char c = grid[p.y()][p.x()];
        if (c == 'e') {
            return null;
        } else {
            return (c == 'B' ? black : white);
        }
    }
    public Group2 findGroup(Poteza p) {
        for (Group2 g : groups) {
            if (g.contains(p)) { return g; }
        }
        return null;
    }

    public void setLiberties(Group2 g) {
        for (Poteza p : g.group) {
            for (Poteza dir : directions) {
                Poteza p1 = new Poteza(p.x() + dir.x(),p.y() + dir.y() );
                if (0 <= p1.x() && p1.x() < size && 0 <= p1.y() && p1.y() < size ) {
                    //check boundary constraints
                    if (grid[p1.y()][p1.x()] == 'e') {
                        if (!g.liberties.contains(p1) && !g.contains(p1)) {g.liberties.add(p1);}
                    }
                }
            }
        }
    }
    public void mergeGroups( Group2 g, Group2 h) {
        if (g.barva != h.barva) { return; }
        for (Poteza p : h.group) {
            //adds only if not already in
            g.addPoteza(p);
            //also means merge(g,g) does nothing
            //is error prone however
        }
        //ne tikaj liberties
        groups.remove(h);
    }

    public void resetAllGroupLiberties() {
        for (Group2 g : groups) {
            //this is okay because there are no empty groups
            //because setGroups always removes ALL groups
            setLiberties(g);
        }
    }

    public boolean isSuicide(Poteza p) {
        for (Poteza dir : directions) {
            Poteza p1 = new Poteza(p.x() + dir.x(), p.y() + dir.y());
            if (0 <= p1.x() && p1.x() < size && 0 <= p1.y() && p1.y() < size ) {
                if (grid[p1.y()][p1.x()] == 'e') {
                    return false;
                }
            }
        }
        return true;
    }

    public void playMove(Poteza poteza) {
        if (gameOver) {}
        if (grid[poteza.y()][poteza.x()] == 'e' && !isSuicide(poteza)) {
            //place char in grid
            grid[poteza.y()][poteza.x()] = barva(naVrsti);
            //remove poteza from possible
            moznePoteze.remove(poteza);
            //add last move as first in the played moves list
            izvedenePoteze.add(0, poteza);
        }
    }

    //i should separate group calculation from playing moves just like i did for suicide
    public void undoMove(Poteza poteza) {
        if (grid[poteza.y()][poteza.x()] != 'e') {
            KdoIgra prejsnjiNaVrsti;
            if (gameOver){
                gameOver = false;
                prejsnjiNaVrsti = (grid[poteza.y()][poteza.x()] == 'b' ? black : white);
            } else {
                prejsnjiNaVrsti = (naVrsti == black ? white : black);
            }
            grid[poteza.y()][poteza.x()] = 'e';
            moznePoteze.add(poteza);
            izvedenePoteze.remove(0);

            naVrsti = prejsnjiNaVrsti;
            //need to unmerge groups if they were merged
        }
    }

    public void setGroups() {
        groups.clear();
        //first we just set all the groups, then we reset all of their liberties
        for (int j = 0; j < size; ++j) {
            for (int i = 0; i < size; ++i) {
                //grem po potezah
                if (grid[j][i] == 'e') {continue;}
                Poteza p = new Poteza(i, j);
                //sproti delam grupe
                Group2 g = findGroup(p);
                KdoIgra barva = barvaFromGrid(p);
                //poiscem grupo, ce je ni, nardim novo
                if (g == null) {
                    g = new Group2(barva);
                    g.addPoteza(p);
                    groups.add(g);
                }
                //pogledamo ce je kje zdravn kere druge grupe iste barve
                for (Poteza dir : directions) {
                    Poteza p1 = new Poteza(p.x() + dir.x(), p.y() + dir.y());
                    //ce najdemo grupo, jo mergamo s PRVO GRUPO g
                    Group2 h = findGroup(p1);
                    if (h != null && h.barva == g.barva) {
                        mergeGroups(g, h);
                    }
                    //tako se vse grupe okoli mergajo s prvo
                }
            }
        }
        resetAllGroupLiberties();
        //System.out.println(groups.size());
        checkWinner();
        //System.out.println("Game over is " + gameOver);
    }
    //winner checking inherently relies on groups so leave that for last
    public void checkWinner() {
        for (Group2 g : groups) {
            if (g.isCaptured()) {
                gameOver = true;
                KdoIgra barva = (g.barva == black ? white : black);
                for (Poteza p : g.group) {
                    grid[p.y()][p.x()] = (g.barva == black ? 'b' : 'w');
                    //swtting the captured one to small
                    /*
                    basically
                    'W' -> 'w'
                    'B' -> 'b'
                     */
                }
                winner = barva;
                break;
            }
        }
    }
    public static Igra2 copy(Igra2 igra) {
        Igra2 kopija = new Igra2(igra.size, igra.black, igra.white);
        kopija.naVrsti = igra.naVrsti;
        for (int i = 0; i < igra.size; ++i) {
            for (int j = 0; j < igra.size; ++j) {
                kopija.grid[j][i] = igra.grid[j][i];
                if (igra.grid[j][i] == 'e') {
                    kopija.moznePoteze.add(new Poteza(i,j));
                }
            }
        }
        kopija.setGroups();
        return kopija;
    }
}
