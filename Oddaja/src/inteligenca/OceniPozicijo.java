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
        for (Group g : yourGroups) {costYou += g.liberties.size() + g.group.size() * g.group.size() * g.group.size();}
        int costOpponent = 0;
        for (Group g : opponentGroups) {costOpponent += g.liberties.size();}
        switch (igra.state) {
            case Igra.WHITE_WIN -> {cost = (state == Igra.WHITE_STATE ? WIN : LOSS);}
            case Igra.BLACK_WIN -> {cost = (state == Igra.BLACK_STATE ? WIN : LOSS);}// <- the are bug (vedno loss ker je stanje igre captured medtem ko je state nekoncno)
            default -> {
                cost = costYou - costOpponent;
            }
        }
        //System.out.println("Current game cost is " + cost + " for " + (state == Igra.BLACK_STATE ? "black" : "white"));
        return cost;
    }

    private static int oceniPozicijoProcenti (Igra igra, int state) {
        //int me = igra.nasprotnik();
        //float cost = 100f;
        //for (Group g : igra.groups) {
        //    if (igra.groupState(g) == me) {
        //        float costF = 1f + ((float) g.liberties.size() / (2f * (float) g.group.size() + 2f));
        //        cost =  Math.max(cost, 100f * costF);
        //    } else {
        //        float costF = 1f - ((float) g.liberties.size() / (2f * (float) g.group.size() + 2f));
        //        cost =  Math.min(cost, 100f * costF);
        //
        //    }
        //}
        //return (int) cost;

        //int state = igra.nasprotnik();
        //boolean me1 = false;
        //List<Group> yourGroups = new LinkedList<>();
        //List<Group> opponentGroups = new LinkedList<>();
        //for (Group g : igra.groups) {
        //    if (igra.groupState(g) == state) yourGroups.add(g);
        //    else opponentGroups.add(g);
        //}
        //if (me1) {
        //    for (Group g : opponentGroups) {
        //        float costF = 100f + 100f *  ((float) g.liberties.size() / (2f * (float) g.group.size() + 2f));
        //        cost =  Math.max(cost, costF);
        //    }
        //} else {
        //    for (Group g : yourGroups) {
        //        float costF = 1f - ((float) g.liberties.size() / (2f * (float) g.group.size() + 2f));
        //        cost =  Math.min(cost, 100f * costF);
        //    }
        //}
                //System.out.println("Calculated procenti cost of "+ cost);
        //return (int) cost;
            switch (igra.state) {
                case Igra.WHITE_WIN -> {
                    return (state == Igra.WHITE_STATE ? WIN : LOSS);
                }
                case Igra.BLACK_WIN -> {
                    return (state == Igra.BLACK_STATE ? WIN : LOSS);
                }// <- the are bug (vedno loss ker je stanje igre captured medtem ko je state nekoncno)
                default -> {
                    boolean me = (igra.state == state);
                    float cost = 100f;
                    List<Group> yourGroups = new LinkedList<>();
                    List<Group> opponentGroups = new LinkedList<>();
                    for (Group g : igra.groups) {
                        if (igra.groupState(g) == state) yourGroups.add(g);
                        else opponentGroups.add(g);
                    }
                    if (me) {
                        for (Group g : opponentGroups) {
                            float costF = 1f + ((float) g.liberties.size() / (2f * (float) g.group.size() + 2f));
                            cost =  Math.max(cost, 100f *costF);
                        }
                    } else {
                        for (Group g : yourGroups) {
                            float costF = 1f - ((float) g.liberties.size() / (2f * (float) g.group.size() + 2f));
                            cost =  Math.min(cost, 100f * costF);
                        }
                    }
                    System.out.println("Calculated procenti cost of "+ cost);
                    return (int) cost;
                }
            }
    }

    public static int oceniPlosco (Igra igra) {
        int blackCost = 0;
        int whiteCost = 0;
        switch (igra.state) {
            //check boundary condition
            case Igra.BLACK_WIN -> {blackCost = OceniPozicijo.WIN; whiteCost = OceniPozicijo.LOSS;}
            case Igra.WHITE_WIN -> {whiteCost = OceniPozicijo.WIN; blackCost = OceniPozicijo.LOSS;}
            default -> {
                //sum up the tiles
                for (int j = 0; j < igra.size; ++j){
                    for (int i = 0; i < igra.size; ++i) {
                        if (igra.grid[j][i] == 1) {++blackCost;} else {++whiteCost;}
                    }
                }
                //sum up group sizes
                for (Group g : igra.groups) {
                    if (igra.groupState(g) == 1) {
                        blackCost += g.group.size();
                    } else if (igra.groupState(g) == 2) {
                        whiteCost += g.group.size();
                    }
                }
            }
        }
        return blackCost - whiteCost;
    }
}
