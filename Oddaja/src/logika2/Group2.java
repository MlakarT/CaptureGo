package logika2;

import splosno.KdoIgra;
import splosno.Poteza;

import javax.naming.LinkLoopException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Group2 {
    public KdoIgra barva;
    public List<Poteza> group;
    public List<Poteza> liberties;

    public Group2(KdoIgra barvaIgralca) {
        barva = barvaIgralca;
        group = new LinkedList<>();
        liberties = new LinkedList<>();
    }

    public boolean contains(Poteza p) {
        return group.contains(p);
    }

    public void addPoteza(Poteza p) {
        if (contains(p)) {return ;}
        group.add(p);
    }

    public boolean isCaptured() {
        return (liberties.size() == 0 && group.size() != 0);
    }
}
