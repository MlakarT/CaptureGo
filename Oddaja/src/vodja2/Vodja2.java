package vodja2;

import inteligenca2.Inteligenca2;
import inteligenca2.Minimax;
import inteligenca2.RandomMove2;

import logika2.Igra2;
import splosno.KdoIgra;
import splosno.Poteza;

import javax.swing.*;
import java.util.Map;

public class Vodja2 {
    public static Map<KdoIgra, vodja2.PlayerType> vrstaIgralca;
    public static gui2.Frame frame;

    public static Igra2 igra = null;
    public static boolean clovekNaVrsti = false;

    //public static Inteligenca2 inteligenca = new RandomMove2();
    public static Inteligenca2 inteligenca = new Minimax(3); //actual depth 4

    public static void playNewGame(int size, KdoIgra player1, KdoIgra player2) {
        igra = new Igra2(size, player1, player2);
        play();
    }

    public static void play() {
        if (igra.gameOver) {return;}
        vodja2.PlayerType player = (igra.naVrsti == igra.black ? vrstaIgralca.get(igra.black) : vrstaIgralca.get(igra.white));
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
                    igra.playMove(poteza);
                    igra.setGroups();
                    igra.nasprotnik();
                    frame.repaint();
                    play();
                }
            }
        };
        worker.execute();
    }

    public static boolean playHumanTurn(Poteza poteza) {
        if (igra.grid[poteza.y()][poteza.x()] == 'e') {
            //postavi potezo
            igra.playMove(poteza);
            //poracunaj grupe
            igra.setGroups();
            //zamenjaj barvo
            igra.nasprotnik();
            clovekNaVrsti = false;
            play();
            return true;
        }
        return false;
    }
}
