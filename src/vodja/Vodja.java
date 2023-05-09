package vodja;


import java.util.Map;

import javax.swing.SwingWorker;
import java.util.concurrent.TimeUnit;

import gui.Frame;
import inteligenca.Inteligenca;
import logika.Igra;
import logika.Igra.*;
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

    public static Inteligenca inteligenca = new Minimax(9);

    public static void playNewGame() {
        game = new Igra();
        play();
    }

    public static void play() {
        switch (game.state) {
            case Igra.CAPTURED_BLACK, Igra.CAPTURED_WHITE -> {}
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

            /**
             * Computes a result, or throws an exception if unable to do so.
             *
             * <p>
             * Note that this method is executed only once.
             *
             * <p>
             * Note: this method is executed in a background thread.
             *
             * @return the computed result
             * @throws Exception if unable to compute a result
             */
            @Override
            protected Poteza doInBackground() throws Exception {
                return inteligenca.izberiPotezo(game);
            }
        };
        worker.execute();
    }
}