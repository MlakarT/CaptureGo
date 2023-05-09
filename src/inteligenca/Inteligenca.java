package inteligenca;

import logika.Igra;
import splosno.KdoIgra;
import splosno.Poteza;

import java.util.Random;

public abstract class Inteligenca extends KdoIgra {
    public Inteligenca() {
        super("TempName");
    }

    public abstract Poteza izberiPotezo(Igra igra);
    public abstract void igrajPotezo(Igra game, Poteza p);

}
