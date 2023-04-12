package logika;

import splosno.Poteza;

import java.util.HashMap;
import java.util.LinkedList;

public class Group {
    protected int size; //pod nujno da ves na kaksni plosci si
    protected HashMap<Poteza, Integer> group;
    protected static final Poteza[] directions = {new Poteza(1,0), new Poteza(-1, 0), new Poteza(0,1), new Poteza(0, -1)};
    protected LinkedList<Poteza> liberties;

    public Group(int n) {
        size = n;
        group = new HashMap<>();
    }

    public void setLiberties() {
        for (Poteza p : this.group.keySet()) {
            for (Poteza dir : directions) {
                Poteza p1 = new Poteza(p.x() + dir.x(),p.y() + dir.y());
                if (!this.group.containsKey(p1) && 0 <= p1.x()  && p1.x() < size && 0 <= p1.y() && p1.y() < size) {
                    this.liberties.add(0, p1);
                }
            }
        }
    }

    public void addTo(Poteza poteza, int state) {
        this.group.putIfAbsent(poteza, state);
        this.setLiberties();
    }

    public Group addGroups(Group g, Group h) {
        Group o = new Group(g.size);
        o.group = g.group;
        for (Poteza p : h.group.keySet()) {
            o.group.putIfAbsent(p, h.group.get(p));
        }
        o.setLiberties();
        return o;
    }
    public boolean isCaptured() {
        return (this.group.size() != 0 && this.liberties.size() == 0);
    }
}

