import gui.Frame;
// import inteligenca.Inteligenca;
import logika.Group;
import logika.Igra;
import splosno.Poteza;

public class Main {
    public static void main(String[] args) {
        //todo stuff comes here
        Igra game = new Igra();

        // Inteligenca i = new Inteligenca();
        // Poteza p = i.izberiPotezo(game);

        Poteza poteza1 = new Poteza(4,4);
        Poteza poteza2 = new Poteza(3,4);
        Poteza poteza3 = new Poteza(4,5);
        Poteza poteza4 = new Poteza(3,5);
        Poteza poteza5 = new Poteza(5,8);
        Poteza poteza6 = new Poteza(4,3);
        Poteza poteza7 = new Poteza(6,8);
        Poteza poteza8 = new Poteza(4,6);
        Poteza poteza9 = new Poteza(4,8);
        Poteza poteza10 = new Poteza(5,4);
        Poteza poteza11 = new Poteza(3,8);
        Poteza poteza12 = new Poteza(5,5);



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
        game.odigraj(poteza12);
        game.printGame();
        System.out.println(game.gameOver());
        for (Group g : game.groups) {
            System.out.println(g);
            System.out.println(g.group);
            System.out.println(g.liberties);
            System.out.println(g.isCaptured());
        }
    }

}