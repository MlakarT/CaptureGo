package inteligenca2;

import logika.Igra;
import logika2.Igra2;
import splosno.Poteza;

import java.util.Random;

public class RandomMove2 extends Inteligenca2 {

    public RandomMove2() {};
    @Override
    public Poteza izberiPotezo (Igra2 igra) {
        Random r = new Random();
        int n = igra.moznePoteze.size();
        return igra.moznePoteze.get(r.nextInt(n-1));
    }
}
