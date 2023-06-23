package vodja;


import java.util.Map;

import javax.swing.SwingWorker;

import gui.Frame;
import inteligenca.Inteligenca;
import logika.Igra;
import logika.Igralec;

import inteligenca.Minimax;
// import inteligenca.Alphabeta;

import splosno.Poteza;
import splosno.KdoIgra;

public class Vodja {

    public static Map<Integer, PlayerType> vrstaIgralca;
    public static Map<Igralec, KdoIgra> kdoIgra;
    public static Frame frame;
    public static Igra game = null;
    public static boolean clovekNaVrsti = false;

    //public static Inteligenca inteligenca = new RandomMove();
    public static Inteligenca inteligenca = new Minimax(4);
    //public static Inteligenca inteligenca = new AlphaBeta();

    public static void playNewGame() {
        game = new Igra();
        play();
    }

    public static void play() {
        switch (game.state) {
            case Igra.WHITE_WIN, Igra.BLACK_WIN -> {}
            case Igra.BLACK_STATE, Igra.WHITE_STATE -> {
                //poglej kdoj na vrst
                PlayerType player = (game.state == Igra.BLACK_STATE ? vrstaIgralca.get(Igra.BLACK_STATE) : vrstaIgralca.get(Igra.WHITE_STATE) );
                switch (player) {
                    case C -> clovekNaVrsti = true;
                    case R -> playCpTurn();
                }
            }
        }
    }

    public static void playCpTurn () {
        Igra startGame = game;
        SwingWorker<Poteza, Void> worker = new SwingWorker<>() {
            @Override
            protected Poteza doInBackground() {
                return inteligenca.izberiPotezo(game);
            }
            @Override
            protected void done () {
                Poteza poteza = null;
                try {poteza = get();} catch (Exception e) {};
                if (game == startGame && poteza != null) {
                    inteligenca.igrajPotezo(game,poteza);
                    frame.repaint();
                    play();
                }
            }
        };
        worker.execute();
    }

    public static boolean playHumanTurn (Poteza poteza) {
        if (game.odigraj(poteza)) {
            clovekNaVrsti = false;
            play();
            return true;
        }
        return false;
    }
}