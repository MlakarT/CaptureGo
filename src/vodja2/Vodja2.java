package vodja2;

import gui.Frame;
import inteligenca2.Inteligenca2;
import inteligenca2.RandomMove2;
import logika.Igra;
import logika2.Igra2;
import splosno.KdoIgra;
import splosno.Poteza;
import vodja.PlayerType;

import javax.swing.*;
import java.util.Map;

public class Vodja2 {
    public static Map<KdoIgra, vodja2.PlayerType> vrstaIgralca;
    public static Frame frame;

    public static Igra2 igra = null;
    public static boolean clovekNaVrsti = false;

    public static Inteligenca2 inteligenca = new RandomMove2();

    public static void playNewGame(int size, KdoIgra player1, KdoIgra player2) {
        igra = new Igra2(size, player1, player2);
        play();
    }

    public static void play() {
        if (igra.gameOver) {return;}
        vodja2.PlayerType player = (igra.naVrsti == Igra2.black ? vrstaIgralca.get(Igra2.black) : vrstaIgralca.get(Igra2.white));
        switch (player) {
            case C -> clovekNaVrsti = true;
            case R -> playCpTurn();
        }
    }

    public static void playCpTurn() {
        Igra2 startGame = igra;
        SwingWorker<Poteza, Void> worker = new SwingWorker<>() {
            @Override
            protected Poteza doInBackground() {
                return inteligenca.izberiPotezo(igra);
            }
            @Override
            protected void done () {
                Poteza poteza = null;
                try {poteza = get();} catch (Exception e) {};
                if (igra == startGame && poteza != null) {
                    inteligenca.igrajPotezo(igra,poteza);
                    frame.repaint();
                    play();
                }
            }
        };
        worker.execute();
    }
}
