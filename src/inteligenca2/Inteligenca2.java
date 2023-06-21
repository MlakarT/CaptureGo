package inteligenca2;

import logika2.Igra2;
import splosno.KdoIgra;
import splosno.Poteza;

public abstract class Inteligenca2 extends KdoIgra {
    public Inteligenca2() {super("computer");}
    public abstract Poteza izberiPotezo (Igra2 igra);

    public void igrajPotezo(Igra2 igra, Poteza poteza){
        igra.playMove(poteza);
        igra.setGroups();
        igra.naVrsti = igra.nasprotnik();
    }
}
