package vodja;


import java.util.Map;

import javax.swing.SwingWorker;
import java.util.concurrent.TimeUnit;

// import gui.GlavnoOkno;
import inteligenca.Inteligenca;
import logika.Igra;
import logika.Igra.*;
import logika.Igralec;

//import inteligenca.Minimax;
// import inteligenca.Alphabeta;

import splosno.Poteza;
import splosno.KdoIgra;

public class Vodja {

    public static Map<Igralec, PlayerType> vrstaIgralca;
    public static Map<Igralec, KdoIgra> kdoIgra;

    //public static GlavnoOkno okno;

    public static Igra igra = null;

    public static boolean clovekNaVrsti = false;

    public static void igramoNovoIgro() {
        igra = new Igra();
    }
}