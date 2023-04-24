import gui.Frame;
import logika.Group;
import logika.Igra;
import splosno.Poteza;

public class Main {
    public static void main(String[] args) {
        //todo stuff comes here
        Igra game = new Igra();
        Poteza poteza1 = new Poteza(2,3);
        Poteza poteza2 = new Poteza(2, 4);
        Poteza poteza3 = new Poteza(5, 7);
        Poteza poteza4 = new Poteza(3,3);
        Poteza poteza5 = new Poteza(6,7);
        Poteza poteza6 = new Poteza(1,3);
        Poteza poteza7 = new Poteza(7,7);
        Poteza poteza8 = new Poteza(2,2);
        Poteza poteza9 = new Poteza(4,7);
        Poteza poteza10 = new Poteza(1,2);
        Poteza poteza11 = new Poteza(6,6);
        game.odigraj(poteza1);
        game.odigraj(poteza2);
        game.odigraj(poteza3);
        game.odigraj(poteza4);
        game.odigraj(poteza5);
        game.odigraj(poteza6);
        game.odigraj(poteza7);
        game.odigraj(poteza8);
        game.odigraj(poteza9);
        game.odigraj(poteza10);
        game.odigraj(poteza11);
        game.printGame();

        //Frame frame = new Frame();
        //frame.pack();
        //frame.setVisible(true);
    }

}