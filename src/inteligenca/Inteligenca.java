package inteligenca;

import logika.Igra;
import splosno.KdoIgra;
import splosno.Poteza;

import java.util.Random;

public abstract class Inteligenca extends KdoIgra {
    public Inteligenca() {
        super("TempName");
    }

    public Poteza izberiPotezo(Igra game) {
        Random rng = new Random();
        int n = game.size -1 ;
        Poteza p = new Poteza(rng.nextInt(n), rng.nextInt(n));
        if (game.grid[p.y()][p.x()] != 0) {
            return izberiPotezo(game);
        }
        return p;
    }
    public Poteza igrajPotezo(Igra game, Poteza p) {
        Igra copy = new Igra();
        copy.grid = game.grid.clone();
        if (copy.odigraj(p)) {
            return p;
        }
        return p;
    }

}
