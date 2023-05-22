package inteligenca;
import logika.*;
import splosno.*;

import java.util.LinkedList;
import java.util.List;

public class OceniPozicijo {
    public static final int modifier = 10;
    public static final int WIN = 10000; // vrednost zmage
    public static final int LOSS = -WIN;  // vrednost izgube

    public static int oceniPozicijo(Igra igra, int state) {
        return oceniPozicijoProcenti(igra, state);
    }
    private static int oceniPozicijoNaivno(Igra igra, int state) {
        //basic idea : preverjamo kok libertijou je
        //postaulas kamen tako da ma tvoj nasprotnik cim manj libertijou generalno
        // in da jih mas ti cim vec pa da so grupe cim vecje
        List<Group> yourGroups = new LinkedList<>();
        List<Group> opponentGroups = new LinkedList<>();
        for (Group g : igra.groups) {
            if (igra.groupState(g) == state) yourGroups.add(g);
            else opponentGroups.add(g);
        }
        int cost;
        int costYou = 0;
        for (Group g : yourGroups) {costYou += g.liberties.size() + g.group.size() * modifier;}
        int costOpponent = 0;
        for (Group g : opponentGroups) {costOpponent += g.liberties.size();}
        switch (igra.state) {
            case Igra.CAPTURED_BLACK-> {cost = (state == Igra.WHITE_STATE ? WIN : LOSS);}
            case Igra.CAPTURED_WHITE -> {cost = (state == Igra.BLACK_STATE ? WIN : LOSS);}// <- the are bug (vedno loss ker je stanje igre captured medtem ko je state nekoncno)
            default -> {
                cost = costYou - costOpponent;
            }
        }
        System.out.println("Current game cost is " + cost + " for " + (state == Igra.BLACK_STATE ? "black" : "white"));
        return cost;
    }

    private static int oceniPozicijoProcenti (Igra igra, int state) {
        switch (igra.state) {
            case Igra.CAPTURED_BLACK -> {
                return (state == Igra.WHITE_STATE ? WIN : LOSS);
            }
            case Igra.CAPTURED_WHITE -> {
                return (state == Igra.BLACK_STATE ? WIN : LOSS);
            }// <- the are bug (vedno loss ker je stanje igre captured medtem ko je state nekoncno)
            default -> {
                boolean me = (igra.state == state);
                float cost = 1;
                List<Group> yourGroups = new LinkedList<>();
                List<Group> opponentGroups = new LinkedList<>();
                for (Group g : igra.groups) {
                    if (igra.groupState(g) == state) yourGroups.add(g);
                    else opponentGroups.add(g);
                }
                if (me) {
                    for (Group g : opponentGroups) {
                        float costF = 1 + ((float) g.liberties.size() / (2 * (float) g.group.size() + 2));
                        cost = 100 * Math.max(cost, costF);
                    }
                } else {
                    for (Group g : yourGroups) {
                        float costF = 1 - ((float) g.liberties.size() / (2 * (float) g.group.size() + 2));
                        cost = 100 * Math.min(cost, costF);
                    }
                }
                System.out.println("Calculated procenti cost of "+ cost);
                return (int) cost;
            }
        }
    }

}
