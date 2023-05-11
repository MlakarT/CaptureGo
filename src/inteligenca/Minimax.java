package inteligenca;

import java.util.List;

import logika.Igra;

import splosno.Poteza;

import inteligenca.OceniPozicijo;

public class Minimax extends Inteligenca {
    private static final int WIN = 10000; // vrednost zmage
    private static final int LOSS = -WIN;  // vrednost izgube
    private final int globina;
    public Minimax (int globina) {
        this.globina = globina;
    }
    @Override
    public Poteza izberiPotezo (Igra igra) {
        OcenjenaPoteza najboljsaPoteza = minimax(igra, this.globina);
        return najboljsaPoteza.poteza;
    }

    @Override
    public void igrajPotezo(Igra igra, Poteza poteza) {
        igra.odigraj(poteza);
    }

    public OcenjenaPoteza minimax(Igra igra, int globina) {
        //System.out.println("Shut up im thinking..");
        OcenjenaPoteza najboljsaPoteza = null;
        List<Poteza> moznePoteze = igra.poteze();
        for (Poteza p: moznePoteze) {
            //System.out.println(".. thinking of " + p);
            Igra kopijaIgre = Igra.copy(igra);
            kopijaIgre.odigraj (p);
            int ocena;
            switch (kopijaIgre.state) {
                //check if game over
                case Igra.CAPTURED_BLACK -> {
                    ocena = (igra.state == Igra.WHITE_STATE ? WIN : LOSS);
                    System.out.println("Will capture black");
                }
                case Igra.CAPTURED_WHITE -> {
                    ocena = (igra.state == Igra.BLACK_STATE ? WIN : LOSS);
                    System.out.println("Will capture white");
                }
                default -> {
                    // nekdo je na potezi
                    if (globina == 1) ocena = OceniPozicijo.oceniPozicijo(kopijaIgre, p, igra.state).cost;
                        // globina > 1
                    else {
                        ocena = minimax(kopijaIgre, globina - 1).cost;
                        System.out.println("Doing minimax again on depth" + (globina - 1));
                    }
                }
            }
            if (najboljsaPoteza == null
                    // max, če je p moja poteza
                    //todo vedno se ta izvede
                    || ocena > najboljsaPoteza.cost
                    // sicer min
                    || ocena < najboljsaPoteza.cost)
                najboljsaPoteza = new OcenjenaPoteza (p, ocena);
        }
        System.out.println("Picked " + najboljsaPoteza.poteza + " of value " + najboljsaPoteza.cost);
        return najboljsaPoteza;
    }
}