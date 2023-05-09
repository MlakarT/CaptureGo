package inteligenca;

import java.util.List;

import logika.Igra;

import splosno.Poteza;

import inteligenca.OceniPozicijo;

public class Minimax extends Inteligenca {
    private static final int WIN = 1000; // vrednost zmage
    private static final int LOSS = -WIN;  // vrednost izgube
    private int globina;
    public Minimax (int globina) {
        this.globina = globina;
    }
    @Override
    public Poteza izberiPotezo (Igra igra) {
        OcenjenaPoteza najboljsaPoteza = minimax(igra, this.globina, igra.state);
        return najboljsaPoteza.poteza;
    }

    public OcenjenaPoteza minimax(Igra igra, int globina, int jaz) {
        OcenjenaPoteza najboljsaPoteza = null;
        List<Poteza> moznePoteze = igra.poteze();
        for (Poteza p: moznePoteze) {
            Igra kopijaIgre = Igra.copy(igra);
            kopijaIgre.odigraj (p);
            int ocena;
            switch (kopijaIgre.state) {
                //check if game over
                case Igra.CAPTURED_BLACK: ocena = (jaz == Igra.WHITE_STATE ? WIN : LOSS);
                case Igra.CAPTURED_WHITE: ocena = (jaz == Igra.BLACK_STATE ? WIN : LOSS);
                default:
                    // nekdo je na potezi
                    if (globina == 1) ocena = OceniPozicijo.oceniPozicijo(kopijaIgre, p).cost;
                        // globina > 1
                    else ocena = minimax(kopijaIgre, globina-1, jaz).cost;
            }
            if (najboljsaPoteza == null
                    // max, če je p moja poteza
                    || jaz == igra.state && ocena > najboljsaPoteza.cost
                    // sicer min
                    || jaz != igra.state && ocena < najboljsaPoteza.cost)
                najboljsaPoteza = new OcenjenaPoteza (p, ocena);
        }
        return najboljsaPoteza;
    }
}