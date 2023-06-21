package logika2;

import logika2.Group2;
import splosno.KdoIgra;
import splosno.Poteza;

import java.util.LinkedList;
import java.util.List;
public class Igra2 {
    public int size;
    public char[][] grid;
    public static KdoIgra black;
    public static KdoIgra white;
    public KdoIgra naVrsti;
    public boolean gameOver = false;
    protected static final Poteza[] directions = {new Poteza(1,0),new Poteza(0,1),new Poteza(-1,0),new Poteza(0,-1)};
    public List<Group2> groups;
    public List<Poteza> moznePoteze;
    public List<Poteza> izvedenePoteze;
    public Igra2 (int size, KdoIgra player1, KdoIgra player2) {
        size = size;
        grid = makeGrid(size);
        naVrsti = black;
        groups = new LinkedList<>();
        moznePoteze = new LinkedList<>();
        for (int j = 0; j < size; ++j) {
            for (int i = 0; i < size; ++i) {
                grid[j][i] = '\0';
                moznePoteze.add(new Poteza(i, j));
                //merkamo vrstni red
            }
        }
        izvedenePoteze = new LinkedList<>();
    }
    private static char[][] makeGrid(int size) {
        return new char[size][size];
    }
    public KdoIgra nasprotnik() {
        return (naVrsti == black ? white : black);
    }

    public char barva(KdoIgra barva) {
        return (naVrsti == black ? 'B' : 'W');
    }
    public KdoIgra barvaFromGrid(Poteza p) {
        char c = grid[p.y()][p.x()];
        return (c == 'B' ? black : white);
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
                    if (grid[p1.y()][p.x()] == '\0') {
                        if (!g.liberties.contains(p1) && !g.contains(p1)) {g.liberties.add(p);}
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

    public void resetAllGroups() {
        for (Group2 g : groups) {
            if (g.group.size() == 0) { groups.remove(g); }
            else { setLiberties(g);}
        }
    }

    public boolean isSuicide(Poteza p) {
        for (Poteza dir : directions) {
            Poteza p1 = new Poteza(p.x() + dir.x(), p.y() + dir.y());
            if (grid[p1.y()][p1.x()] == '\0') {return false;}
        }
        return true;
    }

    public void playMove(Poteza poteza) {
        if (gameOver) {}
        if (grid[poteza.y()][poteza.x()] == '\0' && !isSuicide(poteza)) {
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
        if (grid[poteza.y()][poteza.x()] != '\0') {
            grid[poteza.y()][poteza.x()] = '\0';
            moznePoteze.add(poteza);
            izvedenePoteze.remove(0);
            //need to unmerge groups if they were merged
        }
    }

    public void setGroups() {
        groups.clear();
        //first we just set all the groups, then we reset all of their liberties
        for (int j = 0; j < size; ++j) {
            for (int i = 0; i < size; ++i) {
                //grem po potezah
                Poteza p = new Poteza(i, j);
                //sproti delam grupe
                Group2 g = findGroup(p);
                KdoIgra barva = barvaFromGrid(p);
                //poiscem grupo, ce je ni, nardim novo
                if (g == null) {
                    g = new Group2(barva);
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
        resetAllGroups();
    }
    //winner checking inherently relies on groups so leave that for last
    public KdoIgra checkWinner() {
        for (Group2 g : groups) {
            if (g.isCaptured()) {
                return (g.barva == black ? white : black);
            }
        }
        return null;
    }
}
