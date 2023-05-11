package inteligenca;
import logika.*;
import splosno.*;

import java.util.LinkedList;
import java.util.List;

public class OceniPozicijo {
    public static final int modifier = 1;
    public static OcenjenaPoteza oceniPozicijo(Igra igra, Poteza poteza, int state) {
        //basic idea : preverjamo kok libertijou je
        //postaulas kamen tako da ma tvoj nasprotnik cim manj libertijou generalno
        // in da jih mas ti cim vec pa da so grupe cim vecje
        List<Group> yourGroups = new LinkedList<>();
        List<Group> opponentGroups = new LinkedList<>();
        for (Group g : igra.groups) {
            if (igra.groupState(g) == state) yourGroups.add(g);
            else opponentGroups.add(g);
        }
        int costYou = 0;
        for (Group g : yourGroups) {costYou += modifier * g.liberties.size() + g.group.size();}
        int costOpponent = 0;
        for (Group g : opponentGroups) {costOpponent += g.liberties.size();}
        return new OcenjenaPoteza(poteza, - costYou + costOpponent * modifier);
    }
}
