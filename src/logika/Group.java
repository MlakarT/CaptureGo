package logika;

import splosno.Poteza;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Group {
    protected int size; //pod nujno da ves na kaksni plosci si
    public HashMap<Poteza, Integer> group;
    protected static final Poteza[] directions = {new Poteza(1,0), new Poteza(-1, 0), new Poteza(0,1), new Poteza(0, -1)};
    public List<Poteza> liberties;

    public Group(int n) {
        size = n;
        group = new HashMap<>();
        liberties = new LinkedList<>();
    }
    public boolean contains(Poteza poteza) {
        return this.group.containsKey(poteza);
    }
    public void addTo(Poteza poteza, int state) {
        this.group.putIfAbsent(poteza, state);
    }

    public void mergeGroup (Group h) {
        for (Poteza p : h.group.keySet()) {
            this.group.putIfAbsent(p, h.group.get(p));
        }
    }

    public boolean isCaptured() {
        return (this.group.size() != 0 && this.liberties.size() == 0);
    }
}

