package inteligenca;

import logika.Igra;
import splosno.KdoIgra;
import splosno.Poteza;

import java.util.Random;

public class Inteligenca extends KdoIgra {
    public Inteligenca() {
        super("TimoTeja's Computer");
    }

    public Poteza izberiPotezo(Igra igra){
        int globina = 3;
        OcenjenaPoteza najboljsaPoteza = minimax(igra, globina, true);
        return najboljsaPoteza.poteza;
    }
    public void igrajPotezo(Igra igra, Poteza poteza)  {
        igra.odigraj(poteza);
    }

    public OcenjenaPoteza minimax(Igra igra, int globina, boolean me) {
        OcenjenaPoteza ocenjenaPoteza = null;
        for (Poteza p : igra.poteze) {
            Igra kopija = Igra.copy(igra);
            kopija.odigraj(p);
            //check if we've reached either the end of minimax or end of game
            if (globina == 1 || kopija.state == Igra.CAPTURED_BLACK || kopija.state == Igra.CAPTURED_WHITE) {
                //int ocena = ((igra.state == Igra.BLACK_STATE && kopija.state == Igra.CAPTURED_WHITE) ||
                //        (igra.state == Igra.WHITE_STATE && kopija.state == Igra.CAPTURED_BLACK) ? OceniPozicijo.WIN : OceniPozicijo.LOSS);
                if (me) { //if my turn then take highest
                    ocenjenaPoteza = OcenjenaPoteza.max(ocenjenaPoteza,
                            new OcenjenaPoteza(p, OceniPozicijo.oceniPozicijo(kopija, igra.state)));
                } else { //else take lowest
                    ocenjenaPoteza = OcenjenaPoteza.min(ocenjenaPoteza,
                            new OcenjenaPoteza(p, OceniPozicijo.oceniPozicijo(kopija, igra.state)));
                }
            } else if (me) { // if my turn
                ocenjenaPoteza = OcenjenaPoteza.max(ocenjenaPoteza, minimax(kopija, globina - 1, false));
            } else { // if !my turn
                ocenjenaPoteza = OcenjenaPoteza.min(ocenjenaPoteza, minimax(kopija, globina - 1, true));
            }
        }
        return ocenjenaPoteza;
    }
}
