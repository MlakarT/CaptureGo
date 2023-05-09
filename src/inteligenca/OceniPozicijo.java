package inteligenca;
import logika.*;
import splosno.*;

public class OceniPozicijo {
    public static OcenjenaPoteza oceniPozicijo(Igra igra, Poteza poteza) {
        //basic idea : preverjamo kok libertijou je
        int cost = igra.isInGroup(poteza).liberties.size();
        return new OcenjenaPoteza(poteza, cost);
    }
}
