package inteligenca;

import logika.Igra;
import splosno.Poteza;

import java.util.List;
import java.util.Random;

public class RandomMove extends Inteligenca {

    public RandomMove() {};
    @Override
    public Poteza izberiPotezo (Igra igra) {
        Random r = new Random();
        List<Poteza> poteze = igra.poteze();
        int n = poteze.size();
        return poteze.get(r.nextInt(n-1));
    }

    @Override
    public void igrajPotezo (Igra igra, Poteza poteza) {
        igra.odigraj(poteza);
    }
}
